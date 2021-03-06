package carm.release

import carm.application.Application
import carm.exceptions.DomainInUseException
import org.springframework.dao.DataIntegrityViolationException

class ApplicationReleaseController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def activityTraceService
    def applicationDeploymentService
    def applicationService
    def applicationReleaseService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        [
                applicationReleaseInstanceList: applicationReleaseService.list(params),
                applicationReleaseInstanceTotal: applicationReleaseService.count()
        ]
    }

    def create() {
        def applicationInstance = Application.get(params.application.id)
        if (!applicationInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.application.id])}"
            redirect(action: "list")
        }
        else {
            [
                    applicationReleaseInstance: applicationReleaseService.newApplicationRelease(applicationInstance)
            ]
        }
    }

    def save() {
        Application applicationInstance = applicationService.get(params.application?.id)
        if (!applicationInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.application?.id])}"
            redirect(action: "list")
        }
        else {
            def applicationReleaseInstance = applicationReleaseService.create(applicationInstance.project, params)
            if (!applicationReleaseInstance.hasErrors()) {
                def releaseNumber = applicationReleaseInstance.releaseNumber
                flash.message = "${message(code: 'applicationRelease.created.message', default: 'Release {0} of {1} created', args: [releaseNumber, applicationInstance])}"
                redirect(controller: "application", action: "show", id: applicationReleaseInstance.application.id)
            }
            else {
                render(view: "create", model: [applicationReleaseInstance: applicationReleaseInstance])
            }
        }
    }

    def show() {
        def applicationReleaseInstance = applicationReleaseService.get(params.id)
        if (!applicationReleaseInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'applicationRelease.label', default: 'Application Release'), params.id])}"
            redirect(action: "list")
        }
        else {
            def nextEnvironment = applicationDeploymentService.inferNextEnvironment(applicationReleaseInstance)
            def lastApplicationDeploymentInstance = nextEnvironment ? applicationDeploymentService.findLatestDeployment(applicationReleaseInstance.application, nextEnvironment) : null

            [
                    applicationReleaseInstance: applicationReleaseInstance,
                    applicationDeploymentList: applicationDeploymentService.findAllByApplicationReleaseOrderByRequestedDate(applicationReleaseInstance),
                    nextEnvironment: nextEnvironment,
                    lastApplicationDeploymentInstance: lastApplicationDeploymentInstance
            ]
        }
    }

    def edit() {
        def applicationReleaseInstance = applicationReleaseService.get(params.id)
        if (!applicationReleaseInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'applicationRelease.label', default: 'Application Release'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [applicationReleaseInstance: applicationReleaseInstance]
        }
    }

    def update() {
        def applicationReleaseInstance = applicationReleaseService.get(params.id)
        if (applicationReleaseInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (applicationReleaseInstance.version > version) {
                    applicationReleaseInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'applicationRelease.label', default: 'Application Release')] as Object[], "Another user has updated this ApplicationRelease while you were editing")
                    render(view: "edit", model: [applicationReleaseInstance: applicationReleaseInstance])
                    return
                }
            }
            applicationReleaseService.update(applicationReleaseInstance.application.project, applicationReleaseInstance, params)
            if (!applicationReleaseInstance.hasErrors() && applicationReleaseInstance.save(flush: true)) {
                def releaseNumber = applicationReleaseInstance.releaseNumber
                def application = applicationReleaseInstance.application
                flash.message = "${message(code: 'applicationRelease.updated.message', default: 'Release {0} of {1} updated', args: [releaseNumber, application])}"
                redirect(controller: "applicationRelease", action: "show", id: applicationReleaseInstance.id)
            }
            else {
                render(view: "edit", model: [applicationReleaseInstance: applicationReleaseInstance])
            }
        }
        else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'applicationRelease.label', default: 'Application Release'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete() {
        def applicationReleaseInstance = applicationReleaseService.get(params.id)
        if (applicationReleaseInstance) {
            def applicationId = applicationReleaseInstance.application.id
            def releaseNumber = applicationReleaseInstance.releaseNumber
            def application = applicationReleaseInstance.application

            try {
                applicationReleaseService.delete(applicationReleaseInstance.application.project, applicationReleaseInstance)
                flash.message = "${message(code: 'applicationRelease.deleted.message', default: 'Release {0} of {1} deleted', args: [releaseNumber, application])}"
                redirect(controller: "application", action: "show", id: applicationId)
            }
            catch (DataIntegrityViolationException e) {
                flash.error = "${message(code: 'applicationRelease.not.deleted.message', args: [releaseNumber, application])}"
                redirect(action: "show", id: params.id)
            }
            catch (DomainInUseException e) {
                flash.error = "${message(code: 'applicationRelease.not.deleted.message', args: [releaseNumber, application])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'applicationRelease.label', default: 'Application Release'), params.id])}"
            redirect(action: "list")
        }
    }

    def submit() {
        def applicationReleaseInstance = applicationReleaseService.get(params.id)
        if (applicationReleaseInstance) {
            applicationReleaseService.submit(applicationReleaseInstance.application.project, applicationReleaseInstance)

            flash.message = "${message(code: 'default.submitted.message', args: [message(code: 'applicationRelease.label', default: 'Application Release'), applicationReleaseInstance.releaseNumber])}"
            redirect(controller: "applicationRelease", action: "show", id: applicationReleaseInstance.id)
        }
        else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'applicationRelease.label', default: 'Application Release'), params.id])}"
            redirect(action: "list")
        }
    }

    def listActivity() {
        def applicationReleaseInstance = applicationReleaseService.get(params.id)
        if (!applicationReleaseInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'systemServer.label', default: 'SystemServer'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    domainInstance: applicationReleaseInstance,
                    activityList: activityTraceService.listActivityByApplicationRelease(applicationReleaseInstance, params),
                    activityTotal: activityTraceService.countActivityByApplicationRelease(applicationReleaseInstance)
            ]
        }
    }

    def ajaxShowMoreActivity() {
        def applicationReleaseInstance = applicationReleaseService.get(params.id)
        def activityList = []

        if (applicationReleaseInstance) {
            activityList = activityTraceService.listActivityByApplicationRelease(applicationReleaseInstance, params)
        }

        render(template: "/common/activityBlock", model: [activityList: activityList])
    }
}
