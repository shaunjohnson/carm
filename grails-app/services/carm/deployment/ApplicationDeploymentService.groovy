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
import org.apache.commons.lang.time.DateUtils
import carm.security.User
import org.springframework.context.ApplicationContextAware
import org.springframework.context.ApplicationContext
import org.springframework.context.MessageSource

class ApplicationDeploymentService implements ApplicationContextAware {

    static transactional = false

    ApplicationContext applicationContext
    def carmSecurityService
    def grailsApplication
    def linkGeneratorService
    def notificationService

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
     * Counts all completed deployments each day starting at the provided start Date
     *
     * @param startDate Date to start counting; matched against completed deployment date
     * @return List of tuplets (day of month, count of deployments)
     */
    def countCompletedDeploymentsGroupByDay(Date startDate) {
        ApplicationDeployment.executeQuery("""
                select
                    day(completedDeploymentDate),
                    count(completedDeploymentDate)
                from
                    ApplicationDeployment
                where
                    deploymentState = :deploymentState
                    and completedDeploymentDate > :startDate
                group by
                    day(completedDeploymentDate)""",
                [deploymentState: ApplicationDeploymentState.COMPLETED, startDate: startDate])
    }

    /**
     * Determines if an ApplicationDeployment with the ID exists.
     *
     * @param id ID of ApplicationDeployment to find
     * @return true if the ApplicationDeployment exists
     */
    boolean exists(Serializable id) {
        ApplicationDeployment.exists(id)
    }

    /**
     * Finds all ApplicationDeployment objects filtered by Application.
     *
     * @return All ApplicationDeployment objects filtered by Application.
     */
    List<ApplicationDeployment> findAllByApplication(Application application, Map params) {
        ApplicationDeployment.createCriteria().list([
                max: grailsApplication.config.ui.application.showFullHistoryMax,
                offset: params?.offset ?: 0,
                sort: params?.sort,
                order: params?.order
        ]) {
            createAlias("applicationRelease", "applicationRelease")
            eq("applicationRelease.application", application)
        }
    }

    /**
     * Finds all ApplicationDeployment filtered by ApplicationRelease and ordered by environment name ascending.
     *
     * @param applicationRelease ApplicationRelease used for querying
     * @return List of ApplicationDeployment objects
     */
    List<ApplicationDeployment> findAllByApplicationReleaseOrderByEnvironment(ApplicationRelease applicationRelease) {
        ApplicationDeployment.createCriteria().list {
            createAlias("deploymentEnvironment", "deploymentEnvironment")
            eq("applicationRelease", applicationRelease)
            order("deploymentEnvironment.name", "asc")
        }
    }

    /**
     * Finds all ApplicationDeployment filtered by ApplicationRelease and ordered by requestedDeploymentDate descending.
     *
     * @param applicationRelease ApplicationRelease used for querying
     * @return List of ApplicationDeployment objects
     */
    List<ApplicationDeployment> findAllByApplicationReleaseOrderByRequestedDate(ApplicationRelease applicationRelease) {
        ApplicationDeployment.createCriteria().list {
            eq("applicationRelease", applicationRelease)
            order("requestedDeploymentDate", "desc")
        }
    }

    def findAllDeploymentsGroupedByDay(SystemEnvironment systemEnvironment, Date completedDate) {
        def applicationDeployments =findAllBySystemEnvironmentAndCompletedDate(systemEnvironment, completedDate)
        groupDeploymentsByDateEnvironmentType(applicationDeployments, "completedDeploymentDate")
    }

    def findAllUpcomingDeploymentsGroupedByDay(SystemEnvironment systemEnvironment) {
        def applicationDeployments = findAllUpcomingBySystemEnvironment(systemEnvironment)
        groupDeploymentsByDateEnvironmentType(applicationDeployments, "requestedDeploymentDate")
    }

    private groupDeploymentsByDateEnvironmentType(List<ApplicationDeployment> applicationDeployments, String dateField) {
        def applicationDeploymentsGrouped = [:]

        applicationDeployments.each { ApplicationDeployment deployment ->
            def date = deployment["${dateField}"]
            def dateGroup = applicationDeploymentsGrouped[date]
            if (!dateGroup) {
                dateGroup = [:]
                applicationDeploymentsGrouped[date] = dateGroup
            }

            def envGroup = dateGroup[deployment.deploymentEnvironment]
            if (!envGroup) {
                envGroup = [:]
                dateGroup[deployment.deploymentEnvironment] = envGroup
            }

            def typeList = envGroup[deployment.applicationRelease.application.type]
            if (!typeList) {
                typeList = []
                envGroup[deployment.applicationRelease.application.type] = typeList
            }

            typeList.add(deployment)
        }

        applicationDeploymentsGrouped
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

        ApplicationDeployment applicationDeployment = new ApplicationDeployment(
                applicationRelease: applicationRelease,
                requestedDeploymentDate: requestedDeploymentDate,
                deploymentEnvironment: deploymentEnvironment,
                deploymentInstructions: deploymentInstructions)

        ApplicationDeployment lastApplicationDeployment = findLastApplicationDeploymentByApplicationRelease(applicationRelease)

        if (lastApplicationDeployment) {
            lastApplicationDeployment.moduleDeployments.each { moduleDeployment ->
                applicationDeployment.addToModuleDeployments(new ModuleDeployment(
                        applicationDeployment: applicationDeployment,
                        moduleRelease: moduleDeployment.moduleRelease,
                        deploymentState: moduleDeployment.deploymentState))
            }
        }
        else {
            applicationRelease.moduleReleases.each { moduleRelease ->
                applicationDeployment.addToModuleDeployments(new ModuleDeployment(
                        applicationDeployment: applicationDeployment,
                        moduleRelease: moduleRelease,
                        deploymentState: ModuleDeploymentState.DEPLOYED))
            }
        }

        return applicationDeployment
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
        applicationRelease.moduleReleases.each { moduleRelease ->
            def deploymentState = ModuleDeploymentState.NOT_DEPLOYED

            if (params["moduleRelease.${moduleRelease.id}"] == 'on') {
                deploymentState = ModuleDeploymentState.DEPLOYED
            }

            ModuleDeployment moduleDeployment = new ModuleDeployment(applicationDeployment: applicationDeployment,
                    moduleRelease: moduleRelease, deploymentState: deploymentState)
            applicationDeployment.addToModuleDeployments(moduleDeployment)
        }

        // TODO Set completed deployment date to the requested date by default. Needed since there is no workflow yet.
        applicationDeployment.completedDeploymentDate = applicationDeployment.requestedDeploymentDate

        applicationDeployment.save()

        if (!applicationRelease.hasErrors()) {
            sendNotification(applicationDeployment)
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
     * Redeploys an existing deployment to the same environment.
     *
     * @param project Project in which the associated application exists
     * @param applicationDeployment Deployment to redeploy
     * @return Newly created deployment
     */
    @Transactional
    @PreAuthorize("hasPermission(#project, admin)")
    ApplicationDeployment redeploy(Project project, ApplicationDeployment applicationDeployment) {
        ApplicationDeployment newApplicationDeployment = new ApplicationDeployment()

        Date requestedDeploymentDate = inferNextDeploymentDate()

        newApplicationDeployment.applicationRelease = applicationDeployment.applicationRelease
        newApplicationDeployment.deploymentEnvironment = applicationDeployment.deploymentEnvironment
        newApplicationDeployment.deploymentInstructions = applicationDeployment.deploymentInstructions
        newApplicationDeployment.requestedDeploymentDate = requestedDeploymentDate

        applicationDeployment.moduleDeployments.each { moduleDeployment ->
            newApplicationDeployment.addToModuleDeployments(new ModuleDeployment(
                    applicationDeployment: newApplicationDeployment,
                    moduleRelease: moduleDeployment.moduleRelease,
                    deploymentState: moduleDeployment.deploymentState))
        }

        newApplicationDeployment
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

        //
        applicationDeployment.moduleDeployments.each { moduleDeployment ->
            if (params["moduleDeployment.${moduleDeployment.id}"] == 'on') {
                moduleDeployment.deploymentState = ModuleDeploymentState.DEPLOYED
            }
            else {
                moduleDeployment.deploymentState = ModuleDeploymentState.NOT_DEPLOYED
            }
        }

        // TODO Set completed deployment date to the requested date by default. Needed since there is no workflow yet.
        applicationDeployment.completedDeploymentDate = applicationDeployment.requestedDeploymentDate

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
        def environments = applicationRelease.application.sysEnvironment.environments
        def deployments = ApplicationDeployment.findAllByApplicationRelease(applicationRelease)

        // Return null if there are no deployments
        if (!deployments.size()) {
            return null
        }

        SystemDeploymentEnvironment nextEnvironment = null

        // Start at last environment and work back towards the first
        for (SystemDeploymentEnvironment environment: environments.reverse()) {
            if (deployments.find { it.deploymentEnvironment == environment }) {
                break
            }

            nextEnvironment = environment
        }

        // If the matching environment is the first and we already deployed to it, set to null
        if (deployments.find { it.deploymentEnvironment == nextEnvironment }) {
            nextEnvironment = null
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
     * Finds all application deployments for a system completed on a specific day.
     *
     * @param systemEnvironment SystemEnvironment used to filter deployments
     * @param completedDeploymentDate Completed deployment date used for filtering deployments
     * @return List of ApplicationDeployment objects
     */
    def findAllBySystemEnvironmentAndCompletedDate(SystemEnvironment systemEnvironment, Date completedDeploymentDate) {
        ApplicationDeployment.executeQuery("""
            from
                ApplicationDeployment ad
            where
                ad.deploymentEnvironment.sysEnvironment = :systemEnvironment
                and ad.completedDeploymentDate = :completedDeploymentDate
            order by
                ad.requestedDeploymentDate asc,
                ad.deploymentEnvironment.name asc,
                ad.applicationRelease.application.type asc,
                ad.applicationRelease.application.name asc
        """, [systemEnvironment: systemEnvironment, completedDeploymentDate: completedDeploymentDate])
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

        // TODO Temporary workaround to show upcoming deployments. The following query should be removed and the
        // previous should be used once the workflow has been completed
        ApplicationDeployment.executeQuery("""
            from
                ApplicationDeployment ad
            where
                ad.deploymentEnvironment.sysEnvironment = :system
                and (
                    ad.completedDeploymentDate is null
                    or ad.completedDeploymentDate >= :today
                )
            order by
                ad.requestedDeploymentDate asc,
                ad.deploymentEnvironment.name asc,
                ad.applicationRelease.application.type asc,
                ad.applicationRelease.application.name asc
        """, [today: DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH), system: systemEnvironment])
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

    private sendNotification(ApplicationDeployment applicationDeployment) {
        def currentUser = carmSecurityService.getCurrentUser()

        def projectService = applicationContext.getBean("projectService")
        def projectOwners = projectService.findAllProjectOwners(applicationDeployment.applicationRelease.application.project)
        
        // Do not send notification to current user
        projectOwners.remove(currentUser.username)
        
        // Send notification only if there is at least one other project owner
        if (projectOwners.size()) {
            def args = [
                    applicationDeployment.applicationRelease.application.name,
                    applicationDeployment.applicationRelease.releaseNumber,
                    applicationDeployment.deploymentEnvironment.name,
                    currentUser.fullName,
                    linkGeneratorService.createLink(controller: 'application', action: 'show',
                            id: applicationDeployment.applicationRelease.application.id)
            ]

            notificationService.sendEmail(
                    currentUser.email,
                    carmSecurityService.findAllEmailByUsernameInList(projectOwners),
                    'applicationDeployed.notification.subject', 'applicationDeployed.notification.message', args)
        }
    }
}