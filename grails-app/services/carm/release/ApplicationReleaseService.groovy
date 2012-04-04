package carm.release

import org.springframework.security.access.prepost.PreAuthorize

import org.springframework.transaction.annotation.Transactional
import org.semver.Version
import org.semver.Delta
import carm.project.Project
import carm.application.Application
import carm.deployment.ApplicationDeployment
import carm.exceptions.DomainInUseException
import org.springframework.context.ApplicationContextAware
import org.springframework.context.ApplicationContext

class ApplicationReleaseService implements ApplicationContextAware {

    static transactional = false

    ApplicationContext applicationContext
    def activityTraceService
    def applicationService
    def carmSecurityService
    def grailsApplication
    def linkGeneratorService
    def moduleService
    def notificationService
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
     * Determines if the provided release is in use.
     *
     * @param applicationRelease ApplicationRelease to test
     * @return True if the release is in use
     */
    boolean isInUse(ApplicationRelease applicationRelease) {
        ApplicationDeployment.countByApplicationRelease(applicationRelease) > 0
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

    /**
     * Returns a count of all ApplicationRelease objects.
     *
     * @return Total number of ApplicationRelease objects.
     */
    int count() {
        ApplicationRelease.count()
    }

    /**
     * Adds new ApplicationReleaseHistory record for the provided ApplicationRelease.
     *
     * @param applicationRelease ApplicationRelease to associate with the history record
     * @param summary Summary text
     * @param comments Comments text
     */
    private void addToHistories(ApplicationRelease applicationRelease, String summary, String comments) {
        applicationRelease.addToHistories(new ApplicationReleaseHistory(
                summary: summary,
                username: springSecurityService.authentication.name,
                comments: comments ?: ""
        ))
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
     * Counts all completed releases each day starting at the provided start Date
     *
     * @param startDate Date to start counting; matched against date created
     * @return List of tuplets (day of month, count of releases)
     */
    def countCompletedReleasesGroupByDay(Date startDate) {
        ApplicationRelease.executeQuery("""
                select
                    day(dateCreated),
                    count(dateCreated)
                from
                    ApplicationRelease
                where
                    releaseState = :releaseState
                    and dateCreated > :startDate
                group by
                    day(dateCreated)""",
                [releaseState: ApplicationReleaseState.COMPLETED, startDate: startDate])
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

    /**
     * Creates and saves a new ApplicationRelease instance.
     *
     * @param project Parent project used for security
     * @param params ApplicationRelease properties
     * @return newly created ApplicationRelease object
     */
    @Transactional
    @PreAuthorize("hasPermission(#project, admin)")
    ApplicationRelease create(Project project, Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        ApplicationRelease applicationRelease = new ApplicationRelease(params)

        // Saving as COMPLETE instead of DRAFT for this release, which does not include the workflow.
        //applicationRelease.releaseState = ApplicationReleaseState.DRAFT
        applicationRelease.releaseState = ApplicationReleaseState.COMPLETED

        applicationRelease.application.modules.each { module ->
            applicationRelease.addToModuleReleases(new ModuleRelease(applicationRelease: applicationRelease, module: module))
        }

        applicationRelease.save()

        if (applicationRelease.hasErrors()) {
            applicationRelease.moduleReleases.clear()
        }
        else {
            addToHistories(applicationRelease, "Created", null);
            // TODO application release is marked completed by default. Refer to ApplicationRelease domain.

            sendNotification(applicationRelease)
        }

        log.debug "$prefix returning $applicationRelease"

        applicationRelease
    }

    /**
     * Deletes the provided ApplicationRelease object.
     *
     * @param project Parent project used for security
     * @param applicationRelease ApplicationRelease object to delete
     */
    @Transactional
    @PreAuthorize("hasPermission(#project, admin)")
    void delete(Project project, ApplicationRelease applicationRelease) {
        def prefix = "delete() :"

        log.debug "$prefix entered, applicationRelease=$applicationRelease"

        if (isInUse(applicationRelease)) {
            log.error "$prefix Application release is in use and cannot be deleted"
            throw new DomainInUseException()
        }

        applicationRelease.delete()

        log.debug "$prefix leaving"
    }

    /**
     * Determines if an ApplicationRelease with the ID exists.
     *
     * @param id ID of ApplicationRelease to find
     * @return true if the ApplicationRelease exists
     */
    boolean exists(Serializable id) {
        ApplicationRelease.exists(id)
    }

    /**
     * Gets the ApplicationRelease object with the provided ID.
     *
     * @param id ID of ApplicationRelease object
     * @return Matching ApplicationRelease object
     */
    ApplicationRelease get(Serializable id) {
        ApplicationRelease.get(id)
    }

    /**
     * Gets a list of all ApplicationRelease objects.
     *
     * @param params Query parameters
     * @return List of ApplicationRelease objects
     */
    List<ApplicationRelease> list(Map params) {
        ApplicationRelease.list([
                max: grailsApplication.config.ui.applicationRelease.listMax,
                offset: params?.offset,
                sort: params?.sort,
                order: params?.order
        ])
    }

    /**
     * Gets a list of all ApplicationRelease objects filtered by Application.
     *
     * @param application Application used for filtering
     * @param params Query parameters
     * @return List of ApplicationRelease objects
     */
    List<ApplicationRelease> findAllByApplication(Application application, Map params) {
        def max = params?.max?.toLong() ?: grailsApplication.config.ui.applicationRelease.listMax

        ApplicationRelease.findAllByApplication(application, [
                max: Math.min(max, grailsApplication.config.ui.applicationRelease.listMax),
                offset: params?.offset,
                sort: params?.sort,
                order: params?.order
        ])
    }

    /**
     * Updates the provided ApplicationRelease object with the new properties.
     *
     * @param project Parent project used for security
     * @param applicationRelease ApplicationRelease to update
     * @param params New property values
     */
    @Transactional
    @PreAuthorize("hasPermission(#project, admin)")
    void update(Project project, ApplicationRelease applicationRelease, Map params) {
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
        }

        return null
    }

    /**
     * Changes and saves the provided ApplicationRelease to be in submitted state and set the dateSubmitted and
     * submittedBy fields.
     *
     * @param project Parent project used for security
     * @param applicationRelease ApplicationRelease to submit
     */
    @Transactional
    @PreAuthorize("hasPermission(#project, admin)")
    def submit(Project project, ApplicationRelease applicationRelease) {
        def prefix = "submit() :"

        log.debug "$prefix entered, applicationRelease=$applicationRelease"

        applicationRelease.releaseState = ApplicationReleaseState.SUBMITTED
        applicationRelease.submittedBy = carmSecurityService.currentUser
        applicationRelease.dateSubmitted = new Date()
        applicationRelease.save()

        addToHistories(applicationRelease, "Submitted", null);
        activityTraceService.applicationReleaseSubmitted(applicationRelease)

        log.debug "$prefix Application release submitted"
    }

    private sendNotification(ApplicationRelease applicationRelease) {
        def currentUser = carmSecurityService.getCurrentUser()

        def projectService = applicationContext.getBean("projectService")
        def projectOwners = projectService.findAllProjectOwners(applicationRelease.application.project)

        // Do not send notification to current user
        projectOwners.remove(currentUser.username)

        // Send notification only if there is at least one other project owner
        if (projectOwners.size()) {
            def args = [
                    applicationRelease.application.name,
                    applicationRelease.releaseNumber,
                    currentUser.username,
                    linkGeneratorService.createLink(controller: 'applicationRelease', action: 'show',
                            id: applicationRelease.application.id)
            ]

            notificationService.sendEmail(
                    currentUser.email,
                    carmSecurityService.findAllEmailByUsernameInList(projectOwners),
                    'applicationReleased.notification.subject', 'applicationReleased.notification.message', args)
        }
    }
}
