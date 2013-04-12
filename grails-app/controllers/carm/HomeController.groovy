package carm

import carm.project.Project
import grails.converters.JSON

import org.joda.time.DateTime

import carm.application.Application
import carm.module.Module
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class HomeController {
    def activityTraceService
    def applicationDeploymentService
    def applicationReleaseService
    def applicationService
    def favoriteService
    def grailsApplication
    def projectService
    def springSecurityService
    def systemEnvironmentService

    def index() {
        def mostActiveApplications = [:]
        def mostActiveSystemEnvironments = []
        def myFavorites = []
        def myPendingTasks = []
        def myProjects = [:]
        def mySystemEnvironments = []

        if (springSecurityService.isLoggedIn()) {
            myFavorites = favoriteService.findAllByCurrentUser()
            myProjects = projectService.findAllProjectsByCurrentUserIsAdmin()
            mySystemEnvironments = systemEnvironmentService.findAllByProjects(myProjects)
            myPendingTasks = projectService.findAllPendingTasksByProjects(myProjects)
        }
        else {
            mostActiveApplications = applicationService.findMostActiveApplications([sort: 'project.name']).groupBy { it.project }
            mostActiveApplications.entrySet().each { it.value.sort { it.name } }

            mostActiveSystemEnvironments = systemEnvironmentService.getMostActiveSystems()
        }

        [
                myFavorites: myFavorites,
                myPendingTasks: myPendingTasks,
                myProjects: myProjects,
                mySystemEnvironments: mySystemEnvironments,

                mostActiveApplications: mostActiveApplications,
                mostActiveSystemEnvironments: mostActiveSystemEnvironments,

                activityList: activityTraceService.listActivityByRoot([:]),
                activityCount: activityTraceService.countActivityByRoot(),

                loginPostUrl: generatePostUrl()
        ]
    }

    private String generatePostUrl() {
        def config = SpringSecurityUtils.securityConfig
        "${request.contextPath}${config.apf.filterProcessesUrl}"
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
        def maxResults = grailsApplication.config.ui.search.maxResults

        Application.findAllByNameIlike("%${params.term}%", [max: maxResults]).each {
            results << [type: "application", id: it.id, value: it.name, label: it.name]
        }

        if (results.size() < maxResults) {
            List<Module> modules = Module.findAllByNameIlike("%${params.term}%", [max: 2 * maxResults])

            for (Module module : modules) {
                if (!results.find { it.value == module.name }) {
                    results << [type: "module", id: module.id, value: module.name, label: module.name]
                }

                if (results.size() == maxResults) {
                    break
                }
            }
        }

        if (results.size() < maxResults) {
            List<Project> projects = Project.findAllByNameIlike("%${params.term}%", [max: 2 * maxResults])

            for (Project project : projects) {
                if (!results.find { it.value == project.name }) {
                    results << [type: "project", id: project.id, value: project.name, label: project.name]
                }

                if (results.size() == maxResults) {
                    break
                }
            }
        }

        results.sort { it.label }
        render(text: results as JSON)
    }
}
