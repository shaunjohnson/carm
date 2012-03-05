package carm.release

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.transaction.annotation.Transactional
import org.semver.Version
import org.semver.Delta
import carm.project.Project
import carm.application.Application

class ApplicationReleaseService {
    static transactional = false

    def applicationService
    def moduleService
    def springSecurityService

    /**
     * Determines if the application release is deployable. A release can be deployed if the release is in a completed
     * state and the application is deployable. Refer to the ApplicationService.isDeployable(app) method for info on
     * the logic used to determine if an application can be deployed.
     *
     * @param applicationRelease Application release object to test
     * @return True if the release can be deployed
     */
    boolean isDeployable(ApplicationRelease applicationRelease) {
        // Release has to be in a completed state
        if (applicationRelease?.releaseState != ApplicationReleaseState.COMPLETED) {
            return false
        }

        // Release must have at least one module release
        if (!applicationRelease?.moduleReleases?.size()) {
            return false
        }

        // At least one module must be deployable
        def moduleReleasesAreDeployable = false
        applicationRelease.moduleReleases.each { ModuleRelease moduleRelease ->
            if (moduleService.isDeployable(moduleRelease.module)) {
                moduleReleasesAreDeployable = true
                return
            }
        }

        if (!moduleReleasesAreDeployable) {
            return false;
        }

        // The application must be deployable
        return applicationService.isDeployable(applicationRelease?.application)
    }

    int count() {
        ApplicationRelease.count()
    }

    private void addToHistories(ApplicationRelease applicationRelease, String summary, String comments) {
        applicationRelease.addToHistories(new ApplicationReleaseHistory(
                summary: summary,
                username: springSecurityService.authentication.name,
                comments: comments ?: ""
        ))
    }

    /**
     * Find all ApplicationRelease objects by Application instance.
     *
     * @param application Application object used for filtering
     * @param params Query params
     * @return List of ApplicationRelease objects
     */
    List<ApplicationRelease> findAllByApplication(Application application, Map params) {
        return ApplicationRelease.findAllByApplication(application, params)
    }

    /**
     * Count all ApplicationRelease objects byt Application instance.
     *
     * @param application Application object used for filtering
     * @return Count of ApplicationRelease objects associated with the Application
     */
    int countByApplication(Application application) {
        return ApplicationRelease.countByApplication(application)
    }

    List<ApplicationRelease> findAllPendingReleasesByProject(Project project) {
        def releaseStates = [
                ApplicationReleaseState.DRAFT,
                ApplicationReleaseState.REJECTED,
                ApplicationReleaseState.COMPLETED,
                ApplicationReleaseState.ARCHIVED
        ]

        def applicationReleases = ApplicationRelease.findAll(
                "from ApplicationRelease where releaseState not in (:releaseStates) and application.project = :project order by dateCreated asc",
                [releaseStates: releaseStates, project: project])

        return applicationReleases
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ApplicationRelease create(Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        ApplicationRelease applicationRelease = new ApplicationRelease(params)
        applicationRelease.releaseState = ApplicationReleaseState.DRAFT

        applicationRelease.application.modules.each { module ->
            applicationRelease.addToModuleReleases(new ModuleRelease(applicationRelease: applicationRelease, module: module))
        }

        addToHistories(applicationRelease, "Created", null);

        applicationRelease.save()

        if (applicationRelease.hasErrors()) {
            applicationRelease.moduleReleases.clear()
        }

        log.debug "$prefix returning $applicationRelease"

        applicationRelease
    }

    @Transactional
    // @PreAuthorize("hasPermission(#applicationRelease, delete) or hasPermission(#applicationRelease, admin)")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(ApplicationRelease applicationRelease) {
        applicationRelease.delete()
    }

    ApplicationRelease get(long id) {
        ApplicationRelease.get id
    }

    List<ApplicationRelease> list(Map params) {
        ApplicationRelease.list params
    }

    @Transactional
    // @PreAuthorize("hasPermission(#applicationRelease, write) or hasPermission(#applicationRelease, admin)")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(ApplicationRelease applicationRelease, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        applicationRelease.properties = params

        addToHistories(applicationRelease, "Updated", null);

        log.debug "$prefix leaving"
    }

    /**
     * Infer what the next release number is going to be based on existing releases. If there are no existing releases
     * or the last release number used is does not match Semantic Versioning expectations null is returned.
     *
     * @param application Application used to search application releases
     * @return Next release number or null if one cannot be inferred
     */
    String inferNextRelease(Application application) {
        def releases = ApplicationRelease.where {
            eq("application", application)
            order("dateCreated", "desc")
            max(1)
        }.list()

        def nextReleaseNumber = null

        if (releases?.size() > 0) {
            try {
                Version version = Version.parse(releases.get(0).releaseNumber)

                // Loop until we find an unused release number (incrementing bug-fix number only)
                Version nextVersion = Delta.inferNextVersion(version, Delta.CompatibilityType.BACKWARD_COMPATIBLE_IMPLEMENTER)
                nextReleaseNumber = nextVersion.toString()

                while (ApplicationRelease.countByReleaseNumber(nextReleaseNumber) > 0) {
                    nextVersion = Delta.inferNextVersion(nextVersion, Delta.CompatibilityType.BACKWARD_COMPATIBLE_IMPLEMENTER)
                    nextReleaseNumber = nextVersion.toString()
                }
            }
            catch (IllegalArgumentException e) {
                // Not a semantic version number
                nextReleaseNumber = null
            }
        }

        return nextReleaseNumber
    }
}
