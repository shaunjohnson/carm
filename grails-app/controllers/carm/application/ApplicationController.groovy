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
    def sourceControlRepositoryService
    def projectService
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
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.project?.id])}"
            redirect(action: "list")
        }
        else {
            def applicationInstance = new Application()
            applicationInstance.properties = params

            [
                    applicationInstance: applicationInstance,
                    applicationTypeList: applicationTypeService.list(),
                    sourceControlRepositoryList: sourceControlRepositoryService.list(),
                    systemEnvironmentList: systemEnvironmentService.list()
            ]
        }
    }

    def save() {
        def projectInstance = projectService.get(params.project?.id)
        if (!projectInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.project.id])}"
            redirect(action: "list")
        }
        else {
            def applicationInstance = applicationService.create(projectInstance, params)
            if (!applicationInstance.hasErrors()) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'application.label', default: 'Application'), applicationInstance.name])}"
                redirect(action: "show", id: applicationInstance.id)
            }
            else {
                render(view: "create", model: [
                        applicationInstance: applicationInstance,
                        applicationTypeList: applicationTypeService.list(),
                        sourceControlRepositoryList: sourceControlRepositoryService.list(),
                        systemEnvironmentList: systemEnvironmentService.list()
                ])
            }
        }
    }

    def show() {
        def applicationInstance = applicationService.get(params.id)
        if (!applicationInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.id])}"
            redirect(action: "list")
        }
        else {
            def deployments = [:]

            applicationInstance?.sysEnvironment?.environments?.each { SystemDeploymentEnvironment environment ->
                deployments[environment] = [
                        lastDeployment: applicationDeploymentService.findLatestDeployment(applicationInstance, environment)
                ]
            }

            [
                    applicationInstance: applicationInstance,
                    deployments: deployments,
                    activityList: activityTraceService.listActivityByApplication(applicationInstance, [:]),
                    pendingTasks: applicationService.findAllPendingTasks(applicationInstance)
            ]
        }
    }

    def edit() {
        def applicationInstance = applicationService.get(params.id)
        if (!applicationInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    applicationInstance: applicationInstance,
                    applicationTypeList: applicationTypeService.list(),
                    sourceControlRepositoryList: sourceControlRepositoryService.list(),
                    systemList: systemEnvironmentService.list()
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
                            sourceControlRepositoryList: sourceControlRepositoryService.list(),
                            systemList: systemEnvironmentService.list()
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
                        sourceControlRepositoryList: sourceControlRepositoryService.list(),
                        systemList: systemEnvironmentService.list()
                ])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete() {
        def applicationInstance = applicationService.get(params.id)
        if (applicationInstance) {
            def projectId = applicationInstance.project.id
            try {
                def name = applicationInstance.name
                applicationService.delete(applicationInstance.project, applicationInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'application.label', default: 'Application'), name])}"
                redirect(controller: "project", action: "show", id: projectId)
            }
            catch (DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'application.label', default: 'Application'), params.id])}"
                redirect(action: "show", id: params.id)
            }
            catch (DomainInUseException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'application.label', default: 'Application'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.id])}"
            redirect(action: "list")
        }
    }

    def listReleases() {
        def applicationInstance = applicationService.get(params.id)
        if (!applicationInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.id])}"
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
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.id])}"
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

    def showFullHistory() {
        def applicationInstance = applicationService.get(params.id)
        if (!applicationInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.id])}"
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
