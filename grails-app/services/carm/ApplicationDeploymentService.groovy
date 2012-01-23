package carm

class ApplicationDeploymentService {

    static transactional = false

    def findLatestDeployment(Application application, SystemEnvironment environment) {
        def results = ApplicationDeployment.createCriteria().list {
            createAlias("applicationRelease", "applicationRelease")
            eq("environment", environment)
            eq("applicationRelease.application", application)
            maxResults(1)
            order("completedDeploymentDate", "desc")
        }

        return results.size() ? results[0] : null
    }

    def findAllLatestDeploymentsBySystem(System system) {
        def results = [:]

        Application.listOrderByType().each { application ->
            def deployments = ApplicationDeployment.executeQuery("""
                select ad.environment.name, ad.id, ad.applicationRelease.releaseNumber, max(ad.completedDeploymentDate)
                from ApplicationDeployment ad
                where ad.environment.system = :system and ad.applicationRelease.application = :application
                group by ad.environment
            """, [system: system, application: application])

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

    def findAllLatestDeploymentsBySystemEnvironment(SystemEnvironment systemEnvironment) {
        def results = [:]

        Application.listOrderByType().each { application ->
            def deployments = ApplicationDeployment.executeQuery("""
                select ad.applicationRelease.id, ad.id, ad.applicationRelease.releaseNumber, max(ad.completedDeploymentDate), ad.deploymentState
                from ApplicationDeployment ad
                where ad.environment = :systemEnvironment and ad.applicationRelease.application = :application
                group by ad.environment
            """, [systemEnvironment: systemEnvironment, application: application])

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
}
