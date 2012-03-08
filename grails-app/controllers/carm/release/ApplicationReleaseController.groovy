package carm.release

import carm.application.Application
import carm.exceptions.DomainInUseException
import org.springframework.dao.DataIntegrityViolationException

class ApplicationReleaseController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def applicationReleaseService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [
                applicationReleaseInstanceList: applicationReleaseService.list(params),
                applicationReleaseInstanceTotal: applicationReleaseService.count()
        ]
    }

    def create() {
        def applicationInstance = Application.get(params.application.id)
        if (!applicationInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.application.id])}"
            redirect(action: "list")
        }
        else {
            [
                    applicationReleaseInstance: applicationReleaseService.newApplicationRelease(applicationInstance)
            ]
        }
    }

    def save() {
        def applicationReleaseInstance = applicationReleaseService.create(params)

        if (!applicationReleaseInstance.hasErrors()) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'applicationRelease.label', default: 'Application Release'), applicationReleaseInstance.releaseNumber])}"
            redirect(controller: "application", action: "show", id: applicationReleaseInstance.application.id)
        }
        else {
            render(view: "create", model: [applicationReleaseInstance: applicationReleaseInstance])
        }
    }

    def show() {
        def applicationReleaseInstance = applicationReleaseService.get(params.id)
        if (!applicationReleaseInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationRelease.label', default: 'Application Release'), params.id])}"
            redirect(action: "list")
        }
        else {
            [applicationReleaseInstance: applicationReleaseInstance]
        }
    }

    def edit() {
        def applicationReleaseInstance = applicationReleaseService.get(params.id)
        if (!applicationReleaseInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationRelease.label', default: 'Application Release'), params.id])}"
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
            applicationReleaseService.update(applicationReleaseInstance, params)
            if (!applicationReleaseInstance.hasErrors() && applicationReleaseInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'applicationRelease.label', default: 'Application Release'), applicationReleaseInstance.releaseNumber])}"
                redirect(controller: "applicationRelease", action: "show", id: applicationReleaseInstance.id)
            }
            else {
                render(view: "edit", model: [applicationReleaseInstance: applicationReleaseInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationRelease.label', default: 'Application Release'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete() {
        def applicationReleaseInstance = applicationReleaseService.get(params.id)
        if (applicationReleaseInstance) {
            try {
                def applicationId = applicationReleaseInstance.application.id
                def releaseNumber = applicationReleaseInstance.releaseNumber
                applicationReleaseService.delete(applicationReleaseInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'applicationRelease.label', default: 'Application Release'), releaseNumber])}"
                redirect(controller: "application", action: "show", id: applicationId)
            }
            catch (DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'applicationRelease.label', default: 'Application Release'), params.id])}"
                redirect(action: "show", id: params.id)
            }
            catch (DomainInUseException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'applicationRelease.label', default: 'Application Release'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationRelease.label', default: 'Application Release'), params.id])}"
            redirect(action: "list")
        }
    }

    def submit() {
        def applicationReleaseInstance = applicationReleaseService.get(params.id)
        if (applicationReleaseInstance) {
            applicationReleaseService.submit(applicationReleaseInstance)

            flash.message = "${message(code: 'default.submitted.message', args: [message(code: 'applicationRelease.label', default: 'Application Release'), applicationReleaseInstance.releaseNumber])}"
            redirect(controller: "applicationRelease", action: "show", id: applicationReleaseInstance.id)
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationRelease.label', default: 'Application Release'), params.id])}"
            redirect(action: "list")
        }
    }
}
