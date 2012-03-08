package carm.application

import grails.plugins.springsecurity.Secured
import carm.exceptions.DomainInUseException

class ApplicationTypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def applicationTypeService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [applicationTypeInstanceList: applicationTypeService.list(params), applicationTypeInstanceTotal: applicationTypeService.count()]
    }

    @Secured(['ROLE_ADMIN'])
    def create() {
        def applicationTypeInstance = new ApplicationType()
        applicationTypeInstance.properties = params
        return [applicationTypeInstance: applicationTypeInstance]
    }

    @Secured(['ROLE_ADMIN'])
    def save() {
        def applicationTypeInstance = applicationTypeService.create(params)
        if (!applicationTypeInstance.hasErrors()) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'applicationType.label', default: 'ApplicationType'), applicationTypeInstance.name])}"
            redirect(action: "show", id: applicationTypeInstance.id)
        }
        else {
            render(view: "create", model: [applicationTypeInstance: applicationTypeInstance])
        }
    }

    def show() {
        def applicationTypeInstance = applicationTypeService.get(params.id?.toLong())
        if (!applicationTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationType.label', default: 'ApplicationType'), params.id])}"
            redirect(action: "list")
        }
        else {
            [applicationTypeInstance: applicationTypeInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def edit() {
        def applicationTypeInstance = applicationTypeService.get(params.id?.toLong())
        if (!applicationTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationType.label', default: 'ApplicationType'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [applicationTypeInstance: applicationTypeInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def update() {
        def applicationTypeInstance = applicationTypeService.get(params.id?.toLong())
        if (applicationTypeInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (applicationTypeInstance.version > version) {
                    
                    applicationTypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'applicationType.label', default: 'ApplicationType')] as Object[], "Another user has updated this ApplicationType while you were editing")
                    render(view: "edit", model: [applicationTypeInstance: applicationTypeInstance])
                    return
                }
            }
            applicationTypeService.update(applicationTypeInstance, params)
            if (!applicationTypeInstance.hasErrors() && applicationTypeInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'applicationType.label', default: 'ApplicationType'), applicationTypeInstance.name])}"
                redirect(action: "show", id: applicationTypeInstance.id)
            }
            else {
                render(view: "edit", model: [applicationTypeInstance: applicationTypeInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationType.label', default: 'ApplicationType'), params.id])}"
            redirect(action: "list")
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete() {
        def applicationTypeInstance = applicationTypeService.get(params.id?.toLong())
        if (applicationTypeInstance) {
            try {
                def name = applicationTypeInstance.name
                applicationTypeService.delete(applicationTypeInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'applicationType.label', default: 'ApplicationType'), name])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'applicationType.label', default: 'ApplicationType'), params.id])}"
                redirect(action: "show", id: params.id)
            }
            catch (DomainInUseException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'applicationType.label', default: 'ApplicationType'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationType.label', default: 'ApplicationType'), params.id])}"
            redirect(action: "list")
        }
    }
}
