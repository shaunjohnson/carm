package carm.application

import carm.system.SystemEnvironment

import org.springframework.transaction.annotation.Transactional
import org.springframework.security.access.prepost.PreAuthorize
import carm.project.Project
import org.springframework.context.ApplicationContextAware
import org.springframework.context.ApplicationContext
import carm.notification.NotificationScheme

class ApplicationService implements ApplicationContextAware {

    static transactional = false

    ApplicationContext applicationContext
    def activityTraceService
    def applicationDeploymentService
    def favoriteService
    def grailsApplication
    def systemEnvironmentService
    def watchService

    /**
     * Determines if the application is deployable. An application must be associated with a system that can be
     * deployed to.
     *
     * @param application Application to test
     * @return True if the application can be deployed
     */
    boolean isDeployable(Application application) {
        return systemEnvironmentService.canBeDeployedTo(application?.sysEnvironment)
    }

    /**
     * Returns a count of all Application objects.
     *
     * @return Total number of Application objects.
     */
    int count() {
        Application.count()
    }

    /**
     * Creates and saves a new Application instance.
     *
     * @param project Parent project used for security
     * @param params Application properties
     * @return newly created Module object
     */
    @Transactional
    @PreAuthorize("hasPermission(#project, admin)")
    Application create(Project project, Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        Application application = new Application(params)
        application.description = application.description?.trim()
        application.save()

        log.debug "$prefix returning $application"

        application
    }

    /**
     * Deletes the provided Application object.
     *
     * @param project Parent project used for security
     * @param application Application object to delete
     */
    @Transactional
    @PreAuthorize("hasPermission(#project, admin)")
    void delete(Project project, Application application) {
        def prefix = "delete() :"

        log.debug "$prefix entered, application=$application"

        Application.withTransaction {
            def applicationId = application.id
            application.delete()
            favoriteService.deleteAllForApplicationId(applicationId)
            watchService.deleteAllForApplicationId(applicationId)
        }
        
        log.debug "$prefix leaving"
    }

    /**
     * Gets the Application object with the provided ID.
     *
     * @param id ID of Application object
     * @return Matching Application object
     */
    Application get(Serializable id) {
        Application.get(id)
    }

    /**
     * Lists out the most active applications.
     *
     * @param params Query parameters
     * @return List of Application objects
     */
    List<Application> getMostActiveApplications(Map params) {
        def queryParams = [
                max: grailsApplication.config.ui.application.listMax,
                offset: params?.offset,
                sort: params?.sort,
                order: params?.order
        ]

        def activeIds = activityTraceService.getMostActiveApplicationIds([ max: queryParams.max ])

        Application.findAllByIdInList(activeIds, queryParams)
    }

    /**
     * Gets a list of all Application objects.
     *
     * @param params Query parameters
     * @return List of Application objects
     */
    List<Application> list(Map params) {
        Application.list([
                max: grailsApplication.config.ui.application.listMax,
                offset: params?.offset,
                sort: params?.sort,
                order: params?.order
        ])
    }

    /**
     * Updates the provided Application object with the new properties.
     *
     * @param project Parent project used for security
     * @param application Application to update
     * @param params New property values
     */
    @Transactional
    @PreAuthorize("hasPermission(#project, admin)")
    void update(Project project, Application application, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        application.properties = params
        application.description = application.description?.trim()

        log.debug "$prefix leaving"
    }

    /**
     * Finds all Application objects, grouped by ApplicationType using the provided SystemEnvironment.
     *
     * @param systemEnvironment SystemEnvironment used for querying
     * @return Map with key of ApplicationType and value of List of Application objects
     */
    SortedMap<ApplicationType, List<Application>> findAllBySystemEnvironmentGroupedByType(SystemEnvironment systemEnvironment) {
        def applications = Application.createCriteria().list {
            eq('sysEnvironment', systemEnvironment)
            and {
                order('type', 'asc')
                order('name', 'asc')
            }
        }

        SortedMap<ApplicationType, List<Application>> applicationsGrouped = new TreeMap<ApplicationType, List<Application>>()
        applications.each {
            List<Application> group = applicationsGrouped[it.type]
            if (!group) {
                group = []
                applicationsGrouped[it.type] = group
            }

            group.add(it)
        }

        return applicationsGrouped
    }

    /**
     * Finds all Applications filtered by notification scheme
     *
     * @param notificationSchemeInstance NotificationScheme used for filtering
     * @return List of Application objects
     */
    List<Application> findAllByNotificationScheme(NotificationScheme notificationSchemeInstance) {
        Application.findAllByNotificationScheme(notificationSchemeInstance)
    }

    /**
     * Finds all Application Objects, grouped by Application type using the provided Project.
     *
     * @param project Project used for querying
     * @return Map with key of ApplicationType and value of List of Application objects
     */
    SortedMap<ApplicationType, List<Application>> findAllByProjectGroupedByType(Project project) {
        def applications = Application.createCriteria().list {
            eq('project', project)
            and {
                order('type', 'asc')
                order('name', 'asc')
            }
        }

        SortedMap<ApplicationType, List<Application>> applicationsGrouped = new TreeMap<ApplicationType, List<Application>>()
        applications.each {
            List<Application> group = applicationsGrouped[it.type]
            if (!group) {
                group = []
                applicationsGrouped[it.type] = group
            }

            group.add(it)
        }

        return applicationsGrouped
    }

    /**
     * Finds all pending deployments and releases for the provided Application.
     *
     * @param application Application used for filtering
     * @return List of ApplicationDeployment and ApplicationRelease objects ordered by dateCreated ascending
     */
    List findAllPendingTasks(Application application) {
        def applicationReleaseService = applicationContext.getBean("applicationReleaseService")

        def pendingTasks = []
        pendingTasks.addAll applicationDeploymentService.findAllPendingDeploymentsByApplication(application)
        pendingTasks.addAll applicationReleaseService.findAllPendingReleasesByApplication(application)
        
        pendingTasks.sort { it.dateCreated }
    }
}
