package carm.application

import carm.system.System

import org.springframework.transaction.annotation.Transactional
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import carm.project.Project
import carm.release.ApplicationRelease
import carm.release.ApplicationReleaseState
import carm.deployment.ApplicationDeployment
import carm.deployment.ApplicationDeploymentState

class ApplicationService {

    static transactional = false

    def applicationDeploymentService
    def grailsApplication
    def systemService

    // Deferred service reference
    protected applicationReleaseService

    def initialize() {
        applicationReleaseService = grailsApplication.mainContext.applicationReleaseService
    }

    /**
     * Determines if the application is deployable. An application must be associated with a system that can be
     * deployed to.
     *
     * @param application Application to test
     * @return True if the application can be deployed
     */
    boolean isDeployable(Application application) {
        return systemService.canBeDeployedTo(application?.system)
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
        application.delete()
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
     * Gets a list of all Application objects.
     *
     * @param params Query parameters
     * @return List of Application objects
     */
    List<Application> list(Map params) {
        Application.list(params)
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

        log.debug "$prefix leaving"
    }

    /**
     * Finds all Application objects, grouped by ApplicationType using the provided System.
     *
     * @param system System used for querying
     * @return Map with key of ApplicationType and value of List of Application objects
     */
    SortedMap<ApplicationType, List<Application>> findAllBySystemGroupedByType(System system) {
        def applications = Application.createCriteria().list {
            eq('system', system)
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
        def pendingTasks = []
        
        pendingTasks.addAll applicationDeploymentService.findAllPendingDeploymentsByApplication(application)
        pendingTasks.addAll applicationReleaseService.findAllPendingReleasesByApplication(application)
        
        pendingTasks.sort { it.dateCreated }
    }
}
