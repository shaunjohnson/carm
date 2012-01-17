package carm

class ApplicationService {

    static transactional = false

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
