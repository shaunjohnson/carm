package carm

class ApplicationDeploymentService {

    static transactional = false

    def findLatestDeployment(Application application, SystemEnvironment environment) {
        def criteria = ApplicationDeployment.createCriteria()
        def results = criteria.list {
            createAlias("applicationRelease", "applicationRelease")
            eq("environment", environment)
            eq("applicationRelease.application", application)
            maxResults(1)
            order("dateCreated", "desc")
        }

        return results.size() ? results[0] : null
    }
}
