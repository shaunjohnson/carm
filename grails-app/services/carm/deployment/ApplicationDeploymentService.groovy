package carm.deployment

import org.joda.time.LocalDate
import org.joda.time.DateTimeConstants

import carm.system.SystemDeploymentEnvironment
import carm.release.ApplicationRelease
import carm.application.Application
import carm.project.Project
import org.springframework.transaction.annotation.Transactional
import org.springframework.security.access.prepost.PreAuthorize
import carm.release.ModuleRelease
import carm.system.SystemEnvironment

class ApplicationDeploymentService {

    static transactional = false

    /**
     * Returns a count of all ApplicationDeployment objects.
     *
     * @return Total number of ApplicationDeployment objects.
     */
    int count() {
        ApplicationDeployment.count()
    }

    /**
     * Gets the ApplicationDeployment object with the provided ID.
     *
     * @param id ID of ApplicationDeployment object
     * @return Matching ApplicationDeployment object
     */
    ApplicationDeployment get(Serializable id) {
        ApplicationDeployment.get(id)
    }

    /**
     * Gets a list of all ApplicationDeployment objects.
     *
     * @param params Query parameters
     * @return List of ApplicationDeployment objects
     */
    List<ApplicationDeployment> list(Map params) {
        ApplicationDeployment.list(params)
    }

    /**
     * Creates a new ApplicationDeployment instance with prefilled fields:
     * <ul>
     * <li>applicationRelease - Passed in ApplicationRelease object</li>
     * <li>requestedDeploymentDate - Next business (week) day</li>
     * <li>deploymentEnvironment - Next system environment to be deployed to</li>
     * </ul>
     *
     * @param applicationRelease ApplicationRelease this deployment is associated with
     * @return new ApplicationDeployment instance
     */
    ApplicationDeployment newApplicationDeployment(ApplicationRelease applicationRelease) {
        Date requestedDeploymentDate = inferNextDeploymentDate()
        SystemDeploymentEnvironment deploymentEnvironment = inferNextEnvironment(applicationRelease)

        new ApplicationDeployment(applicationRelease: applicationRelease,
                requestedDeploymentDate: requestedDeploymentDate, deploymentEnvironment: deploymentEnvironment)
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
    ApplicationDeployment create(Project project, Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        ApplicationDeployment applicationDeployment = new ApplicationDeployment(params)

        // Saving as COMPLETE instead of DRAFT for this release, which does not include the workflow.
        // applicationDeploymentInstance.deploymentState = ApplicationDeploymentState.DRAFT
        applicationDeployment.deploymentState = ApplicationDeploymentState.COMPLETED
        applicationDeployment.completedDeploymentDate = new Date()

        ApplicationRelease applicationRelease = applicationDeployment.applicationRelease
        applicationRelease.application.modules.each { module ->
            ModuleRelease moduleRelease = applicationRelease.moduleReleases.find { it.module == module }

            // Saving each ModuleDeployment as "DEPLOYED" for this release, which does not permit users to select which
            // modules to include in the deployment.
            ModuleDeployment moduleDeployment = new ModuleDeployment(applicationDeployment: applicationDeployment,
                    moduleRelease: moduleRelease, deploymentState: ModuleDeploymentState.DEPLOYED)
            applicationDeployment.addToModuleDeployments(moduleDeployment)
        }

        applicationDeployment.save()

        if (applicationDeployment.hasErrors()) {
            applicationDeployment.moduleDeployments.clear()
        }

        log.debug "$prefix returning $applicationDeployment"

        applicationDeployment
    }

    /**
     * Deletes the provided ApplicationDeployment object.
     *
     * @param project Parent project used for security
     * @param applicationDeployment ApplicationDeployment object to delete
     */
    @Transactional
    @PreAuthorize("hasPermission(#project, admin)")
    void delete(Project project, ApplicationDeployment applicationDeployment) {
        def prefix = "delete() :"

        log.debug "$prefix entered, applicationDeployment=$applicationDeployment"

//        if (isInUse(applicationDeployment)) {
//            throw new DomainInUseException()
//        }

        applicationDeployment.delete()
        
        log.debug "$prefix leaving"
    }

    /**
     * Updates the provided ApplicationDeployment object with the new properties.
     *
     * @param project Parent project used for security
     * @param applicationDeployment ApplicationDeployment to update
     * @param params New property values
     */
    @Transactional
    @PreAuthorize("hasPermission(#project, admin)")
    void update(Project project, ApplicationDeployment applicationDeployment, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        applicationDeployment.properties = params

        log.debug "$prefix leaving"
    }

    /**
     * Infers the next deployment date. Returns today unless today is Friday-Sunday. If Friday-Sunday return next
     * Monday.
     *
     * @return Next deployment date
     */
    Date inferNextDeploymentDate() {
        LocalDate localDate = new LocalDate().plusDays(1)

        if (localDate.dayOfWeek > DateTimeConstants.FRIDAY) {
            localDate = localDate.plusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY)
        }

        return localDate.toDate()
    }

    /**
     * Infers the next SystemEnvironment Deployment Environment to deploy a release to. This determines the next environment by analyzing
     * existing deployments of the same release. The environment returned will be the next environment that can be
     * deployed to. If null is returned then the last environment has been deployed to.
     *
     * @param applicationRelease Application release used for querying
     * @return Next environment that can be used to deploy this release
     */
    SystemDeploymentEnvironment inferNextEnvironment(ApplicationRelease applicationRelease) {
        def releases = ApplicationDeployment.findAllByApplicationRelease(applicationRelease)
        def nextEnvironment = null

        // Start at last environment and work back towards the first
        applicationRelease.application.sysEnvironment.environments.reverse().each { environment ->
            if (releases.find { it.deploymentEnvironment == environment }) {
                return
            }

            nextEnvironment = environment
        }

        return nextEnvironment
    }

    /**
     * Finds the latest ApplicationDeployment of the provided Application to the provided SystemDeploymentEnvironment.
     *
     * @param application Application used for querying
     * @param environment SystemDeploymentEnvironment for querying
     * @return Matching ApplicationDeployment object
     */
    def findLatestDeployment(Application application, SystemDeploymentEnvironment environment) {
        def results = ApplicationDeployment.createCriteria().list {
            createAlias("applicationRelease", "applicationRelease")
            eq("deploymentEnvironment", environment)
            eq("applicationRelease.application", application)
            inList("deploymentState", ApplicationDeploymentState.deployedStates)
            maxResults(1)
            order("completedDeploymentDate", "desc")
        }

        return results.size() ? results[0] : null
    }

    /**
     * Finds latest completed ApplicationDeployment objects for the provided SystemEnvironment.
     *
     * @param system SystemEnvironment used for querying
     * @return Array of maps containing basic ApplicationDeployment and ApplicationRelease field values
     */
    def findAllLatestCompletedDeploymentsBySystem(SystemEnvironment system) {
        def results = [:]

        Application.listOrderByType().each { application ->
            def deployments = ApplicationDeployment.executeQuery("""
                select
                    ad.deploymentEnvironment.name, ad.id, ad.applicationRelease.releaseNumber, max(ad.completedDeploymentDate)
                from
                    ApplicationDeployment ad
                where
                    ad.deploymentState = :deploymentState
                    and ad.deploymentEnvironment.sysEnvironment = :system
                    and ad.applicationRelease.application = :application
                group by
                    ad.deploymentEnvironment
            """, [deploymentState: ApplicationDeploymentState.COMPLETED, system: system, application: application])

            def applicationDeployments = [:]

            deployments.each {
                def environmentName = it[0]
                def applicationDeploymentId = it[1]
                def releaseNumber = it[2]
                def completedDeploymentDate = it[3]

                applicationDeployments[environmentName] = [
                        "applicationDeploymentId": applicationDeploymentId,
                        "releaseNumber": releaseNumber,
                        "completedDeploymentDate": completedDeploymentDate
                ]
            }

            results[application] = applicationDeployments
        }

        return results
    }

    /**
     * Finds latest completed ApplicationDeployment objects for the provided SystemDeploymentEnvironment.
     *
     * @param systemDeploymentEnvironment SystemDeploymentEnvironment used for querying
     * @return Array of maps containing basic ApplicationDeployment and ApplicationRelease field values
     */
    def findAllLatestCompletedDeploymentsBySystemDeploymentEnvironment(SystemDeploymentEnvironment systemDeploymentEnvironment) {
        def results = [:]

        Application.listOrderByType().each { application ->
            def deployments = ApplicationDeployment.executeQuery("""
                select
                    ad.applicationRelease.id, ad.id, ad.applicationRelease.releaseNumber, max(ad.completedDeploymentDate), ad.deploymentState
                from
                    ApplicationDeployment ad
                where
                    ad.deploymentState = :deploymentState
                    and ad.deploymentEnvironment = :systemDeploymentEnvironment
                    and ad.applicationRelease.application = :application
                group by
                    ad.deploymentEnvironment
            """, [deploymentState: ApplicationDeploymentState.COMPLETED, systemDeploymentEnvironment: systemDeploymentEnvironment, application: application])

            deployments.each {
                def applicationReleaseId = it[0]
                def applicationDeploymentId = it[1]
                def releaseNumber = it[2]
                def completedDeploymentDate = it[3]
                def deploymentState = it[4]

                results[application] = [
                        "applicationReleaseId": applicationReleaseId,
                        "applicationDeploymentId": applicationDeploymentId,
                        "releaseNumber": releaseNumber,
                        "completedDeploymentDate": completedDeploymentDate,
                        "deploymentState": deploymentState
                ]
            }
        }

        return results
    }

    /**
     * Finds all application deployments that are upcoming for a provided system.
     *
     * @param system SystemEnvironment used to filter deployments
     * @return List of ApplicationDeployment objects
     */
    def findAllUpcomingBySystem(SystemEnvironment system) {
        ApplicationDeployment.executeQuery("""
            from
                ApplicationDeployment ad
            where
                ad.deploymentState = :deploymentState
                and ad.completedDeploymentDate is null
                and ad.deploymentEnvironment.sysEnvironment = :system
            order by
                ad.requestedDeploymentDate asc,
                ad.deploymentEnvironment.name asc,
                ad.applicationRelease.application.type asc,
                ad.applicationRelease.application.name asc
        """, [deploymentState: ApplicationDeploymentState.SUBMITTED, system: system])
    }

    /**
     * Finds all pending (not completed) application deployments for the provided application.
     *
     * @param application Application used for filtering
     * @return List of ApplicationDeployment objects
     */
    List<ApplicationDeployment> findAllPendingDeploymentsByApplication(Application application) {
        ApplicationDeployment.executeQuery(
                """ from
                        ApplicationDeployment
                    where
                        deploymentState not in :deploymentStates
                        and applicationRelease.application = :application""",
                [deploymentStates: ApplicationDeploymentState.pendingStates, application: application])
    }

    /**
     * Finds all pending (not completed) application deployments for the provided project.
     *
     * @param project Project used for filtering
     * @return List of ApplicationDeployment objects
     */
    List<ApplicationDeployment> findAllPendingDeploymentsByProject(Project project) {
        ApplicationDeployment.executeQuery(
                """ from
                        ApplicationDeployment
                    where
                        deploymentState not in :deploymentStates
                        and applicationRelease.application.project = :project""",
                [deploymentStates: ApplicationDeploymentState.pendingStates, project: project])
    }
}