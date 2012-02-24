package carm

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

    SortedMap<String, List<Application>> findAllBySystemGroupedByType(System system) {
        def criteria = Application.createCriteria()
        def applications = criteria.list {
            eq('system', system)
            and {
                order('type', 'asc')
                order('name', 'asc')
            }
        }

        SortedMap<String, List<Application>> applicationsGrouped = new TreeMap<String, List<Application>>()
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

    SortedMap<String, List<Application>> findAllByProjectGroupedByType(Project project) {
        def criteria = Application.createCriteria()
        def applications = criteria.list {
            eq('project', project)
            and {
                order('type', 'asc')
                order('name', 'asc')
            }
        }

        SortedMap<String, List<Application>> applicationsGrouped = new TreeMap<String, List<Application>>()
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
