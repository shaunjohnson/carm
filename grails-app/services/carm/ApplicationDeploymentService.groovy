package carm

import carm.enums.ApplicationDeploymentState
import org.joda.time.LocalDate
import org.joda.time.DateTimeConstants

class ApplicationDeploymentService {

    static transactional = false

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
     * Infers the next System Environment to deploy a release to. This determines the next environment by analyzing
     * existing deployments of the same release. The environment returned will be the next environment that can be
     * deployed to. If null is returned then the last environment has been deployed to.
     *
     * @param applicationRelease Application release used for querying
     * @return Next environment that can be used to deploy this release
     */
    SystemEnvironment inferNextEnvironment(ApplicationRelease applicationRelease) {
        def releases = ApplicationDeployment.findAllByApplicationRelease(applicationRelease)
        def nextEnvironment = null

        // Start at last environment and work back towards the first
        applicationRelease.application.system.environments.reverse().each { environment ->
            if (releases.find { it.sysEnvironment == environment }) {
                return
            }

            nextEnvironment = environment
        }

        return nextEnvironment
    }

    def findLatestDeployment(Application application, SystemEnvironment environment) {
        def results = ApplicationDeployment.createCriteria().list {
            createAlias("applicationRelease", "applicationRelease")
            eq("sysEnvironment", environment)
            eq("applicationRelease.application", application)
            maxResults(1)
            order("completedDeploymentDate", "desc")
        }

        return results.size() ? results[0] : null
    }

    def findAllLatestCompletedDeploymentsBySystem(System system) {
        def results = [:]

        Application.listOrderByType().each { application ->
            def deployments = ApplicationDeployment.executeQuery("""
                select
                    ad.sysEnvironment.name, ad.id, ad.applicationRelease.releaseNumber, max(ad.completedDeploymentDate)
                from
                    ApplicationDeployment ad
                where
                    ad.deploymentState = :deploymentState
                    and ad.sysEnvironment.system = :system
                    and ad.applicationRelease.application = :application
                group by
                    ad.sysEnvironment
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

    def findAllLatestCompletedDeploymentsBySystemEnvironment(SystemEnvironment systemEnvironment) {
        def results = [:]

        Application.listOrderByType().each { application ->
            def deployments = ApplicationDeployment.executeQuery("""
                select
                    ad.applicationRelease.id, ad.id, ad.applicationRelease.releaseNumber, max(ad.completedDeploymentDate), ad.deploymentState
                from
                    ApplicationDeployment ad
                where
                    ad.deploymentState = :deploymentState
                    and ad.sysEnvironment = :systemEnvironment
                    and ad.applicationRelease.application = :application
                group by
                    ad.sysEnvironment
            """, [deploymentState: ApplicationDeploymentState.COMPLETED, systemEnvironment: systemEnvironment, application: application])

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