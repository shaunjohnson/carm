package carm.application

import carm.exceptions.DomainInUseException
import org.springframework.dao.DataIntegrityViolationException

import carm.system.SystemDeploymentEnvironment

class ApplicationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def activityTraceService
    def applicationDeploymentService
    def applicationReleaseService
    def applicationService
    def applicationTypeService
    def notificationSchemeService
    def sourceControlRepositoryService
    def projectService
    def systemDeploymentEnvironmentService
    def systemEnvironmentService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        [
                applicationInstanceList: applicationService.list(params),
                applicationInstanceTotal: applicationService.count()
        ]
    }

    def create() {
        def projectInstance = projectService.get(params.project?.id)
        if (!projectInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.project?.id])}"
            redirect(action: "list")
        }
        else {
            def applicationInstance = new Application()
            applicationInstance.properties = params

            [
                    applicationInstance: applicationInstance,
                    applicationTypeList: applicationTypeService.list(),
                    notificationSchemeList: notificationSchemeService.list(),
                    sourceControlRepositoryList: sourceControlRepositoryService.list(),
                    systemEnvironmentList: systemEnvironmentService.list()
            ]
        }
    }

    def save() {
        def projectInstance = projectService.get(params.project?.id)
        if (!projectInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.project.id])}"
            redirect(action: "list")
        }
        else {
            def applicationInstance = applicationService.create(projectInstance, params)
            if (!applicationInstance.hasErrors()) {
                flash.message = "${message(code: 'application.created.message', args: [message(code: 'application.label', default: 'Application'), applicationInstance.name])}"
                redirect(controller: "module", action: "create", params: ['application.id': applicationInstance.id])
            }
            else {
                render(view: "create", model: [
                        applicationInstance: applicationInstance,
                        applicationTypeList: applicationTypeService.list(),
                        notificationSchemeList: notificationSchemeService.list(),
                        sourceControlRepositoryList: sourceControlRepositoryService.list(),
                        systemEnvironmentList: systemEnvironmentService.list()
                ])
            }
        }
    }

    def show() {
        def applicationInstance = applicationService.get(params.id)
        if (!applicationInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.id])}"
            redirect(action: "list")
        }
        else {
            def deployments = [:]

            applicationInstance?.sysEnvironment?.environments?.each { SystemDeploymentEnvironment deploymentEnvironment ->
                deployments[deploymentEnvironment] = [
                        deploymentList: applicationDeploymentService.findLatestDeployment(applicationInstance, deploymentEnvironment),
                        deploymentListCount: applicationDeploymentService.countByApplicationAndDeploymentEnvironment(applicationInstance, deploymentEnvironment)
                ]
            }

            [
                    applicationInstance: applicationInstance,
                    deployments: deployments,
                    activityList: activityTraceService.listActivityByApplication(applicationInstance, [:]),
                    activityCount: activityTraceService.countActivityByApplication(applicationInstance),
                    pendingTasks: applicationService.findAllPendingTasks(applicationInstance)
            ]
        }
    }

    def ajaxShowMoreDeployments() {
        def id = params.id?.split("_")
        def applicationInstance = applicationService.get(id[0])
        def environmentInstance = systemDeploymentEnvironmentService.get(id[1])
        def deploymentList = []

        if (applicationInstance && environmentInstance) {
            deploymentList = applicationDeploymentService.findAllCompletedByApplicationAndDeploymentEnvironment(applicationInstance, environmentInstance, params)
        }

        render(template: "applicationEnvironmentsBlock", model: [deploymentList: deploymentList])
    }

    def ajaxShowMoreReleases() {
        def applicationInstance = applicationService.get(params.id)
        def applicationReleaseList = []

        if (applicationInstance) {
            applicationReleaseList = applicationReleaseService.findAllByApplication(applicationInstance, params)
        }

        render(template: "applicationReleaseBlock", model: [
                applicationReleaseList: applicationReleaseList,
                addLeadingDivider: true
        ])
    }

    def edit() {
        def applicationInstance = applicationService.get(params.id)
        if (!applicationInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    applicationInstance: applicationInstance,
                    applicationTypeList: applicationTypeService.list(),
                    notificationSchemeList: notificationSchemeService.list(),
                    sourceControlRepositoryList: sourceControlRepositoryService.list(),
                    systemEnvironmentList: systemEnvironmentService.list()
            ]
        }
    }

    def update() {
        def applicationInstance = applicationService.get(params.id)
        if (applicationInstance) {
            if (params.version) {
                def version = params.version.toLong()

                if (applicationInstance.version > version) {
                    applicationInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'application.label', default: 'Application')] as Object[], "Another user has updated this Application while you were editing")
                    render(view: "edit", model: [
                            applicationInstance: applicationInstance,
                            applicationTypeList: applicationTypeService.list(),
                            notificationSchemeList: notificationSchemeService.list(),
                            sourceControlRepositoryList: sourceControlRepositoryService.list(),
                            systemEnvironmentList: systemEnvironmentService.list()
                    ])
                    return
                }
            }

            applicationService.update(applicationInstance.project, applicationInstance, params)
            if (!applicationInstance.hasErrors() && applicationInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'application.label', default: 'Application'), applicationInstance.name])}"
                redirect(action: "show", id: applicationInstance.id)
            }
            else {
                render(view: "edit", model: [
                        applicationInstance: applicationInstance,
                        applicationTypeList: applicationTypeService.list(),
                        notificationSchemeList: notificationSchemeService.list(),
                        sourceControlRepositoryList: sourceControlRepositoryService.list(),
                        systemEnvironmentList: systemEnvironmentService.list()
                ])
            }
        }
        else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete() {
        def applicationInstance = applicationService.get(params.id)
        if (applicationInstance) {
            def projectId = applicationInstance.project.id
            def name = applicationInstance.name

            try {
                applicationService.delete(applicationInstance.project, applicationInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'application.label', default: 'Application'), name])}"
                redirect(controller: "project", action: "show", id: projectId)
            }
            catch (DataIntegrityViolationException e) {
                flash.error = "${message(code: 'default.not.deleted.message', args: [message(code: 'application.label', default: 'Application'), name])}"
                redirect(action: "show", id: params.id)
            }
            catch (DomainInUseException e) {
                flash.error = "${message(code: 'default.not.deleted.message', args: [message(code: 'application.label', default: 'Application'), name])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.id])}"
            redirect(action: "list")
        }
    }

    def listReleases() {
        def applicationInstance = applicationService.get(params.id)
        if (!applicationInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    applicationInstance: applicationInstance,
                    applicationReleaseInstanceList: applicationReleaseService.findAllByApplication(applicationInstance, params),
                    applicationReleaseInstanceTotal: applicationReleaseService.countByApplication(applicationInstance)
            ]
        }
    }

    def listActivity() {
        def applicationInstance = applicationService.get(params.id)
        if (!applicationInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    domainInstance: applicationInstance,
                    activityList: activityTraceService.listActivityByApplication(applicationInstance, params),
                    activityTotal: activityTraceService.countActivityByApplication(applicationInstance)
            ]
        }
    }

    def ajaxShowMoreActivity() {
        def applicationInstance = applicationService.get(params.id)
        def activityList = []

        if (applicationInstance) {
            activityList = activityTraceService.listActivityByApplication(applicationInstance, params)
        }

        render(template: "/common/activityBlock", model: [activityList: activityList])
    }

    def showFullHistory() {
        def applicationInstance = applicationService.get(params.id)
        if (!applicationInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    applicationInstance: applicationInstance,
                    applicationDeploymentInstanceList: applicationDeploymentService.findAllByApplication(applicationInstance, params),
                    applicationDeploymentInstanceTotal: applicationDeploymentService.countByApplication(applicationInstance)
            ]
        }
    }
}
