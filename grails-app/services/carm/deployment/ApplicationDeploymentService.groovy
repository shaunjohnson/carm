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

    def grailsApplication

    /**
     * Returns a count of all ApplicationDeployment objects.
     *
     * @return Total number of ApplicationDeployment objects.
     */
    int count() {
        ApplicationDeployment.count()
    }

    /**
     * Returns a count of all ApplicationDeployment objects filtered by Application.
     *
     * @return Total number of ApplicationDeployment objects filtered by Application.
     */
    int countByApplication(Application application) {
        ApplicationDeployment.executeQuery(
                'select count(ad) from ApplicationDeployment ad where ad.applicationRelease.application = ?',
                [application])[0] as int
    }

    /**
     * Finds all ApplicationDeployment objects filtered by Application.
     *
     * @return All ApplicationDeployment objects filtered by Application.
     */
    List<ApplicationDeployment> findAllByApplication(Application application, Map params) {
        ApplicationDeployment.executeQuery(
                'from ApplicationDeployment ad where ad.applicationRelease.application = ?',
                [application])
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
        ApplicationDeployment.list([
                max: grailsApplication.config.ui.applicationDeployment.listMax,
                offset: params?.offset,
                sort: params?.sort,
                order: params?.order
        ])
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
        String deploymentInstructions = inferDeploymentInstructions(applicationRelease)

        // TODO Set completed deployment date to the requested date by default. Needed since there is no workflow yet.
        new ApplicationDeployment(
                applicationRelease: applicationRelease,
                completedDeploymentDate: requestedDeploymentDate,
                requestedDeploymentDate: requestedDeploymentDate,
                deploymentEnvironment: deploymentEnvironment,
                deploymentInstructions: deploymentInstructions)
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
     * Infers what the deployment instructions should be. These will default to the instructions used for the last
     * deployment of the provided release. If this is the first deployment then the instructions from the Application
     * will be used.
     *
     * @param applicationRelease Application released used for querying
     * @return Deployment instructions
     */
    String inferDeploymentInstructions(ApplicationRelease applicationRelease) {
        ApplicationDeployment applicationDeployment = findLastApplicationDeploymentByApplicationRelease(applicationRelease)

        if (applicationDeployment) {
            return applicationDeployment.deploymentInstructions
        }
        else {
            return applicationRelease.application.deployInstructions
        }
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
     * Finds the last ApplicationDeployment created for the provided application release.
     *
     * @param applicationRelease ApplicationRelease used for filtering
     * @return Last ApplicationDeployment or null if there are no deployments of the application release
     */
    private ApplicationDeployment findLastApplicationDeploymentByApplicationRelease(ApplicationRelease applicationRelease) {
        def deployments = ApplicationDeployment.createCriteria().list {
            eq("applicationRelease", applicationRelease)
            maxResults(1)
            order("dateCreated", "desc")
        }

        return deployments.size() ? deployments[0] : null
    }

    /**
     * Finds the latest ApplicationDeployment of the provided Application to the provided SystemDeploymentEnvironment.
     *
     * @param application Application used for querying
     * @param environment SystemDeploymentEnvironment for querying
     * @return Matching ApplicationDeployment object
     */
    ApplicationDeployment findLatestDeployment(Application application, SystemDeploymentEnvironment environment) {
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
     * @param systemEnvironment SystemEnvironment used for querying
     * @return Array of maps containing basic ApplicationDeployment and ApplicationRelease field values
     */
    def findAllLatestCompletedDeploymentsBySystemEnvironment(SystemEnvironment systemEnvironment) {
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
            """, [deploymentState: ApplicationDeploymentState.COMPLETED, system: systemEnvironment, application: application])

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
     * @param systemEnvironment SystemEnvironment used to filter deployments
     * @return List of ApplicationDeployment objects
     */
    def findAllUpcomingBySystemEnvironment(SystemEnvironment systemEnvironment) {
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
        """, [deploymentState: ApplicationDeploymentState.SUBMITTED, system: systemEnvironment])
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