package carm

import carm.project.Project
import grails.converters.JSON
import carm.deployment.ApplicationDeployment
import org.joda.time.DateTime
import carm.deployment.ApplicationDeploymentState
import carm.release.ApplicationRelease
import carm.release.ApplicationReleaseState
import org.apache.commons.lang.time.DateUtils
import carm.application.Application

class HomeController {
    def activityTraceService
    def applicationDeploymentService
    def applicationReleaseService
    def projectService
    def springSecurityService
    def systemEnvironmentService

    def index() {
        def mostActiveProjects = []
        def mostActiveSystemEnvironments = []
        def myPendingTasks = []
        def myProjectCategories = [:]
        def mySystemEnvironments = []

        if (springSecurityService.isLoggedIn()) {
            List<Project> myProjects = projectService.getAllProjectsWhereOwner().sort { it.category.name <=> it.name }

            myProjects.each { Project project ->
                if (myProjectCategories[project.category]) {
                    myProjectCategories[project.category] << project
                }
                else {
                    myProjectCategories[project.category] = [ project ]
                }
            }

            mySystemEnvironments = systemEnvironmentService.findAllByProject(myProjects)
            myPendingTasks = projectService.findAllPendingTasks(myProjects)
        }
        else {
            mostActiveProjects = projectService.getMostActiveProjects()
            mostActiveSystemEnvironments = systemEnvironmentService.getMostActiveSystems()
        }

        [
                myPendingTasks: myPendingTasks,
                myProjectCategories: myProjectCategories,
                mySystemEnvironments: mySystemEnvironments,

                mostActiveProjects: mostActiveProjects,
                mostActiveSystemEnvironments: mostActiveSystemEnvironments,

                activityList: activityTraceService.listActivityByRoot([:])
        ]
    }

    def generateData() {
        DateTime startDate = DateTime.now().minusDays(9)

        def deploymentCounts = applicationDeploymentService.countCompletedDeploymentsGroupByDay(startDate.toDate())
        def releaseCounts = applicationReleaseService.countCompletedReleasesGroupByDay(startDate.toDate())
        def data = []

        for (i in 0..9) {
            int dayOfMonth = startDate.getDayOfMonth()
            def releaseCount = releaseCounts.find { it[0] == dayOfMonth }
            def deploymentCount = deploymentCounts.find { it[0] == dayOfMonth }

            data << [
                    releases: releaseCount ? releaseCount[1] : 0,
                    deployments: deploymentCount ? deploymentCount[1] : 0,
                    dayName: startDate.toString("EEE")]
            startDate = startDate.plusDays(1)
        }

        return data
    }

    def ajaxActivityData = {
        def map = [:]
        map.version = 0.5
        map.reqId = '0'
        map.status = 'ok'

        def columns = []
        columns << [label: message(code: 'day.label'), type: 'string']
        columns << [label: message(code: 'releases.label'), type: 'number']
        columns << [label: message(code: 'deployments.label'), type: 'number']

        def data = generateData()

        def rows = []
        def cells
        data.each {
            cells = []
            cells << [v: it.dayName] << [v: it.releases] << [v: it.deployments]
            rows << ['c': cells]
        }

        def table = [cols: columns, rows: rows]
        map.table = table

        render "google.visualization.Query.setResponse(" + (map as JSON) + ")"
    }

    def listActivity() {
        [
                activityList: activityTraceService.listActivityByRoot(params),
                activityTotal: activityTraceService.countActivityByRoot()
        ]
    }

    def ajaxShowMoreActivity() {
        def activityList = activityTraceService.listActivityByRoot(params)

        render(template: "/common/activityBlock", model: [activityList: activityList])
    }

    def ajaxQuickSearch() {
        def results = []

        Application.findAllByNameIlike("%${params.term}%", [max: 10]).each {
            results << [ id: it.id, value: it.name, label: it.name ]
        }

        render(text: results as JSON)
    }
}
