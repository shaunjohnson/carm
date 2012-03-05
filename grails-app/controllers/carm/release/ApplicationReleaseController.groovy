package carm.release

import carm.application.Application

class ApplicationReleaseController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def applicationReleaseService

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [applicationReleaseInstanceList: applicationReleaseService.list(params), applicationReleaseInstanceTotal: applicationReleaseService.count()]
    }

    def create = {
        def applicationInstance = Application.get(params.application.id)
        if (!applicationInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.application.id])}"
            redirect(action: "list")
        }
        else {
            def applicationReleaseInstance = new ApplicationRelease()

            // Pre-fill build instructions from the Application domain
            applicationReleaseInstance.buildInstructions = applicationInstance.buildInstructions

            // Attempt to predict the next release number
            applicationReleaseInstance.releaseNumber = applicationReleaseService.inferNextRelease(applicationInstance)

            applicationReleaseInstance.properties = params
            return [applicationReleaseInstance: applicationReleaseInstance]
        }
    }

    def save = {
        def applicationReleaseInstance = applicationReleaseService.create(params)

        if (!applicationReleaseInstance.hasErrors()) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'applicationRelease.label', default: 'ApplicationRelease'), applicationReleaseInstance.releaseNumber])}"
            redirect(controller: "application", action: "show", id: applicationReleaseInstance.application.id)
        }
        else {
            render(view: "create", model: [applicationReleaseInstance: applicationReleaseInstance])
        }
    }

    def show = {
        def applicationReleaseInstance = applicationReleaseService.get(params.id?.toLong())
        if (!applicationReleaseInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationRelease.label', default: 'ApplicationRelease'), params.id])}"
            redirect(action: "list")
        }
        else {
            [applicationReleaseInstance: applicationReleaseInstance]
        }
    }

    def edit = {
        def applicationReleaseInstance = applicationReleaseService.get(params.id?.toLong())
        if (!applicationReleaseInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationRelease.label', default: 'ApplicationRelease'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [applicationReleaseInstance: applicationReleaseInstance]
        }
    }

    def update = {
        def applicationReleaseInstance = applicationReleaseService.get(params.id?.toLong())
        if (applicationReleaseInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (applicationReleaseInstance.version > version) {
                    applicationReleaseInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'applicationRelease.label', default: 'ApplicationRelease')] as Object[], "Another user has updated this ApplicationRelease while you were editing")
                    render(view: "edit", model: [applicationReleaseInstance: applicationReleaseInstance])
                    return
                }
            }
            applicationReleaseService.update(applicationReleaseInstance, params)
            if (!applicationReleaseInstance.hasErrors() && applicationReleaseInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'applicationRelease.label', default: 'ApplicationRelease'), applicationReleaseInstance.releaseNumber])}"
                redirect(controller: "application", action: "show", id: applicationReleaseInstance.application.id)
            }
            else {
                render(view: "edit", model: [applicationReleaseInstance: applicationReleaseInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationRelease.label', default: 'ApplicationRelease'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def applicationReleaseInstance = applicationReleaseService.get(params.id?.toLong())
        if (applicationReleaseInstance) {
            try {
                def applicationId = applicationReleaseInstance.application.id
                def releaseNumber = applicationReleaseInstance.releaseNumber
                applicationReleaseService.delete(applicationReleaseInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'applicationRelease.label', default: 'ApplicationRelease'), releaseNumber])}"
                redirect(controller: "application", action: "show", id: applicationId)
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'applicationRelease.label', default: 'ApplicationRelease'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationRelease.label', default: 'ApplicationRelease'), params.id])}"
            redirect(action: "list")
        }
    }
}
