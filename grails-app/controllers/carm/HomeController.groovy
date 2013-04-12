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

            mostActiveSystemEnvironments = systemEnvironmentService.findMostActiveSystems()
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

    def ajaxGetRecentActivityChartData = {
        int timeSpanInDays = grailsApplication.config.ui.recentActivityChart.timeSpanInDays
        DateTime startDate = DateTime.now().minusDays(timeSpanInDays)

        def deploymentCounts = applicationDeploymentService.countCompletedDeploymentsGroupByDay(startDate.toDate())
        def releaseCounts = applicationReleaseService.countCompletedReleasesGroupByDay(startDate.toDate())
        def rows = []
        for (i in 0..timeSpanInDays) {
            int dayOfMonth = startDate.getDayOfMonth()

            rows << [c: [
                    [v: startDate.toString("EEE")],
                    [v: releaseCounts.find { it[0] == dayOfMonth }?.getAt(1) ?: 0],
                    [v: deploymentCounts.find { it[0] == dayOfMonth }?.getAt(1) ?: 0]]]

            startDate = startDate.plusDays(1)
        }

        def map = [
                version: 0.5,
                reqId: '0',
                status: 'ok',
                table: [
                        cols: [
                                [label: message(code: 'day.label'), type: 'string'],
                                [label: message(code: 'releases.label'), type: 'number'],
                                [label: message(code: 'deployments.label'), type: 'number']
                        ],
                        rows: rows
                ]
        ]

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
