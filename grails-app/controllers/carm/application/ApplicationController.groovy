package carm.application

import carm.exceptions.DomainInUseException

class ApplicationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def activityTraceService
    def applicationDeploymentService
    def applicationReleaseService
    def applicationService
    def projectService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [applicationInstanceList: applicationService.list(params), applicationInstanceTotal: applicationService.count()]
    }

    def create() {
        def projectInstance = projectService.get(params.project.id?.toLong())
        if (!projectInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.project.id])}"
            redirect(action: "list")
        }
        else {
            def applicationInstance = new Application()
            applicationInstance.properties = params
            return [applicationInstance: applicationInstance]
        }
    }

    def save() {
        def projectInstance = projectService.get(params.project.id?.toLong())
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
                render(view: "create", model: [applicationInstance: applicationInstance])
            }
        }
    }

    def show() {
        def applicationInstance = applicationService.get(params.id?.toLong())
        if (!applicationInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.id])}"
            redirect(action: "list")
        }
        else {
            def deployments = [:]

            applicationInstance?.system?.environments?.each { environment ->
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
        def applicationInstance = applicationService.get(params.id?.toLong())
        if (!applicationInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [applicationInstance: applicationInstance]
        }
    }

    def update() {
        def applicationInstance = applicationService.get(params.id?.toLong())
        if (applicationInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (applicationInstance.version > version) {

                    applicationInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'application.label', default: 'Application')] as Object[], "Another user has updated this Application while you were editing")
                    render(view: "edit", model: [applicationInstance: applicationInstance])
                    return
                }
            }
            applicationService.update(applicationInstance.project, applicationInstance, params)
            if (!applicationInstance.hasErrors() && applicationInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'application.label', default: 'Application'), applicationInstance.name])}"
                redirect(action: "show", id: applicationInstance.id)
            }
            else {
                render(view: "edit", model: [applicationInstance: applicationInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete() {
        def applicationInstance = applicationService.get(params.id?.toLong())
        if (applicationInstance) {
            def projectId = applicationInstance.project.id
            try {
                def name = applicationInstance.name
                applicationService.delete(applicationInstance.project, applicationInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'application.label', default: 'Application'), name])}"
                redirect(controller: "project", action: "show", id: projectId)
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
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
        def applicationInstance = applicationService.get(params.id?.toLong())
        if (!applicationInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.id])}"
            redirect(action: "list")
        }
        else {
            params.max = Math.min(params.max ? params.int('max') : 10, 100)

            [
                    applicationInstance: applicationInstance,
                    applicationReleaseInstanceList: applicationReleaseService.findAllByApplication(applicationInstance, params),
                    applicationReleaseInstanceTotal: applicationReleaseService.countByApplication(applicationInstance)
            ]
        }
    }

    def listActivity() {
        def applicationInstance = applicationService.get(params.id?.toLong())
        if (!applicationInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.id])}"
            redirect(action: "list")
        }
        else {
            params.max = Math.min(params.max ? params.int('max') : 10, 100)

            [
                    applicationInstance: applicationInstance,
                    activityList: activityTraceService.listActivityByApplication(applicationInstance, params),
                    activityTotal: activityTraceService.countActivityByApplication(applicationInstance)
            ]
        }
    }
}
