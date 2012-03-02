package carm.application

import carm.system.System

import org.springframework.transaction.annotation.Transactional
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import carm.project.Project

class ApplicationService {

    static transactional = false

    def systemService

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

    int count() {
        Application.count()
    }

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

    @Transactional
    @PreAuthorize("hasPermission(#project, admin)")
    void delete(Project project, Application application) {
        application.delete()
    }

    Application get(long id) {
        Application.get id
    }

    List<Application> list(Map params) {
        Application.list params
    }

    @Transactional
    @PreAuthorize("hasPermission(#project, admin)")
    void update(Project project, Application application, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        application.properties = params

        log.debug "$prefix leaving"
    }

    SortedMap<ApplicationType, List<Application>> findAllBySystemGroupedByType(System system) {
        def criteria = Application.createCriteria()
        def applications = criteria.list {
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

    SortedMap<ApplicationType, List<Application>> findAllByProjectGroupedByType(Project project) {
        def criteria = Application.createCriteria()
        def applications = criteria.list {
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
}
