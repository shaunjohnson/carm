package carm.release

import org.springframework.security.access.prepost.PreAuthorize

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

    /**
     * Determines if the application release is submittable. A release can be submitted if it is in DRAFT or REJECTED
     * state.
     *
     * @param applicationRelease ApplicationRelease object to test
     * @return True if the release can be submitted
     */
    boolean isSubmittable(ApplicationRelease applicationRelease) {
        ApplicationReleaseState.submittableStates.contains(applicationRelease?.releaseState)
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

    /**
     * Finds all pending (not completed) application releases for the provided application.
     *
     * @param application Application used for filtering
     * @return List of ApplicationRelease objects
     */
    List<ApplicationRelease> findAllPendingReleasesByApplication(Application application) {
        ApplicationRelease.executeQuery(
                """ from
                        ApplicationRelease
                    where
                        releaseState not in :releaseStates
                        and application = :application""",
                [releaseStates: ApplicationReleaseState.pendingStates, application: application])
    }

    /**
     * Finds all pending (not completed) application releases for applications in the provided project.
     *
     * @param project Project used for filtering
     * @return List of ApplicationRelease objects
     */
    List<ApplicationRelease> findAllPendingReleasesByProject(Project project) {
        ApplicationRelease.executeQuery(
                """ from
                        ApplicationRelease
                    where
                        releaseState not in (:releaseStates)
                        and application.project = :project""",
                [releaseStates: ApplicationReleaseState.pendingStates, project: project])
    }

    /**
     * Finds the last ApplicationRelease created for the provided application.
     *
     * @param application Application used for filtering
     * @return Last ApplicationRelease or null if there are no releases of the application
     */
    private ApplicationRelease findLastApplicationRelease(Application application) {
        def releases = ApplicationRelease.executeQuery(
                """ from
                        ApplicationRelease
                    where
                        application = :application
                    order by dateCreated desc""",
                [application: application], [max: 1])

        return releases?.size() ? releases.get(0) : null
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
     * Creates a new ApplicationRelease instance with prefilled fields:
     * <ul>
     * <li>application - Passed in Application</li>
     * <li>buildInstructions - Build instructions from Application</li>
     * <li>buildPath - Build path from last release</li>
     * <li>releaseNumber - Next maintenance release number or null</li>
     * </ul>
     *
     * @param application Application this release is associated with
     * @return new ApplicationRelease instance
     */
    ApplicationRelease newApplicationRelease(Application application) {
        ApplicationRelease lastApplicationRelease = findLastApplicationRelease(application)

        String nextReleaseNumber = inferNextRelease(application, lastApplicationRelease?.releaseNumber)
        String buildPath = lastApplicationRelease?.buildPath

        new ApplicationRelease(application: application, buildInstructions: application.buildInstructions,
                buildPath: buildPath, releaseNumber: nextReleaseNumber)
    }

    /**
     * Infer what the next release number is going to be based on existing releases. If there are no existing releases
     * then 1.0.0 will be returned. If the last release number used does not match Semantic Versioning expectations then
     * null is returned.
     *
     * @param application Application used to search application releases
     * @param releaseNumber Current release number
     * @return Next release number or null if one cannot be inferred
     */
    private String inferNextRelease(Application application, String releaseNumber) {
        if (!releaseNumber) {
            return "1.0.0"
        }

        try {
            Version nextVersion = Delta.inferNextVersion(Version.parse(releaseNumber),
                    Delta.CompatibilityType.BACKWARD_COMPATIBLE_IMPLEMENTER)

            // Loop until we find an unused release number (incrementing bug-fix number only)
            while (ApplicationRelease.countByApplicationAndReleaseNumber(application, nextVersion.toString()) > 0) {
                nextVersion = Delta.inferNextVersion(nextVersion, Delta.CompatibilityType.BACKWARD_COMPATIBLE_IMPLEMENTER)
            }

            return nextVersion.toString()
        }
        catch (IllegalArgumentException e) {
            // Not a semantic version number
            return null
        }
    }

    def submit(ApplicationRelease applicationRelease) {
        applicationRelease.releaseState = ApplicationReleaseState.SUBMITTED
        applicationRelease.submittedBy = springSecurityService.currentUser
        applicationRelease.dateSubmitted = new Date()
        applicationRelease.save()

        addToHistories(applicationRelease, "Submitted", null);
    }
}
