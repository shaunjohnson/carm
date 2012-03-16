package carm.deployment

import carm.release.ApplicationRelease
import carm.exceptions.DomainInUseException
import org.springframework.dao.DataIntegrityViolationException

class ApplicationDeploymentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def applicationDeploymentService
    def applicationReleaseService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [
                applicationDeploymentInstanceList: applicationDeploymentService.list(params),
                applicationDeploymentInstanceTotal: applicationDeploymentService.count()
        ]
    }

    def create() {
        def applicationReleaseInstance = applicationReleaseService.get(params.applicationRelease?.id)
        if (!applicationReleaseInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationRelease.label', default: 'Application Release'), params.applicationRelease?.id])}"
            redirect(action: "list")
        }
        else {
            [
                    applicationDeploymentInstance: applicationDeploymentService.newApplicationDeployment(applicationReleaseInstance)
            ]
        }
    }

    def save() {
        ApplicationRelease applicationReleaseInstance = applicationReleaseService.get(params.applicationRelease?.id)
        if (!applicationReleaseInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationRelease.label', default: 'Application Release'), params.applicationRelease?.id])}"
            redirect(action: "list")
        }
        else {
            def applicationDeploymentInstance = applicationDeploymentService.create(applicationReleaseInstance.application.project, params)
            if (!applicationDeploymentInstance.hasErrors()) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment'), applicationDeploymentInstance.id])}"
                redirect(action: "show", id: applicationDeploymentInstance.id)
            }
            else {
                render(view: "create", model: [applicationDeploymentInstance: applicationDeploymentInstance])
            }
        }
    }


    def show() {
        def applicationDeploymentInstance = applicationDeploymentService.get(params.id)
        if (!applicationDeploymentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment'), params.id])}"
            redirect(action: "list")
        }
        else {
            [applicationDeploymentInstance: applicationDeploymentInstance]
        }
    }

    def edit() {
        def applicationDeploymentInstance = applicationDeploymentService.get(params.id)
        if (!applicationDeploymentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [applicationDeploymentInstance: applicationDeploymentInstance]
        }
    }

    def update() {
        def applicationDeploymentInstance = applicationDeploymentService.get(params.id)
        if (applicationDeploymentInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (applicationDeploymentInstance.version > version) {

                    applicationDeploymentInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment')] as Object[], "Another user has updated this ApplicationDeployment while you were editing")
                    render(view: "edit", model: [applicationDeploymentInstance: applicationDeploymentInstance])
                    return
                }
            }
            applicationDeploymentService.update(applicationDeploymentInstance.applicationRelease.application.project, applicationDeploymentInstance, params)
            if (!applicationDeploymentInstance.hasErrors() && applicationDeploymentInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment'), applicationDeploymentInstance.id])}"
                redirect(action: "show", id: applicationDeploymentInstance.id)
            }
            else {
                render(view: "edit", model: [applicationDeploymentInstance: applicationDeploymentInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete() {
        def applicationDeploymentInstance = applicationDeploymentService.get(params.id)
        if (applicationDeploymentInstance) {
            ApplicationRelease applicationRelease = applicationDeploymentInstance.applicationRelease
            try {
                applicationDeploymentService.delete(applicationRelease.application.project, applicationDeploymentInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment'), params.id])}"
                redirect(controller: "applicationRelease", action: "show", id: applicationRelease.id)
            }
            catch (DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment'), params.id])}"
                redirect(action: "show", id: params.id)
            }
            catch (DomainInUseException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment'), params.id])}"
            redirect(action: "list")
        }
    }
}
