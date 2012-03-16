package carm.release

import grails.plugins.springsecurity.Secured
import carm.exceptions.DomainInUseException
import org.springframework.dao.DataIntegrityViolationException

class ApplicationReleaseTestStateController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def applicationReleaseTestStateService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        [
                applicationReleaseTestStateInstanceList: applicationReleaseTestStateService.list(params),
                applicationReleaseTestStateInstanceTotal: applicationReleaseTestStateService.count()
        ]
    }

    @Secured(['ROLE_ADMIN'])
    def create() {
        def applicationReleaseTestStateInstance = new ApplicationReleaseTestState()
        applicationReleaseTestStateInstance.properties = params
        return [applicationReleaseTestStateInstance: applicationReleaseTestStateInstance]
    }

    @Secured(['ROLE_ADMIN'])
    def save() {
        def applicationReleaseTestStateInstance = applicationReleaseTestStateService.create(params)
        if (!applicationReleaseTestStateInstance.hasErrors()) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'applicationReleaseTestState.label', default: 'ApplicationReleaseTestState'), applicationReleaseTestStateInstance.name])}"
            redirect(action: "show", id: applicationReleaseTestStateInstance.id)
        }
        else {
            render(view: "create", model: [applicationReleaseTestStateInstance: applicationReleaseTestStateInstance])
        }
    }

    def show() {
        def applicationReleaseTestStateInstance = applicationReleaseTestStateService.get(params.id)
        if (!applicationReleaseTestStateInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationReleaseTestState.label', default: 'ApplicationReleaseTestState'), params.id])}"
            redirect(action: "list")
        }
        else {
            [applicationReleaseTestStateInstance: applicationReleaseTestStateInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def edit() {
        def applicationReleaseTestStateInstance = applicationReleaseTestStateService.get(params.id)
        if (!applicationReleaseTestStateInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationReleaseTestState.label', default: 'ApplicationReleaseTestState'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [applicationReleaseTestStateInstance: applicationReleaseTestStateInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def update() {
        def applicationReleaseTestStateInstance = applicationReleaseTestStateService.get(params.id)
        if (applicationReleaseTestStateInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (applicationReleaseTestStateInstance.version > version) {
                    
                    applicationReleaseTestStateInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'applicationReleaseTestState.label', default: 'ApplicationReleaseTestState')] as Object[], "Another user has updated this ApplicationReleaseTestState while you were editing")
                    render(view: "edit", model: [applicationReleaseTestStateInstance: applicationReleaseTestStateInstance])
                    return
                }
            }
            applicationReleaseTestStateService.update(applicationReleaseTestStateInstance, params)
            if (!applicationReleaseTestStateInstance.hasErrors() && applicationReleaseTestStateInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'applicationReleaseTestState.label', default: 'ApplicationReleaseTestState'), applicationReleaseTestStateInstance.name])}"
                redirect(action: "show", id: applicationReleaseTestStateInstance.id)
            }
            else {
                render(view: "edit", model: [applicationReleaseTestStateInstance: applicationReleaseTestStateInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationReleaseTestState.label', default: 'ApplicationReleaseTestState'), params.id])}"
            redirect(action: "list")
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete() {
        def applicationReleaseTestStateInstance = applicationReleaseTestStateService.get(params.id)
        if (applicationReleaseTestStateInstance) {
            try {
                def name = applicationReleaseTestStateInstance.name
                applicationReleaseTestStateService.delete(applicationReleaseTestStateInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'applicationReleaseTestState.label', default: 'ApplicationReleaseTestState'), name])}"
                redirect(action: "list")
            }
            catch (DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'applicationReleaseTestState.label', default: 'ApplicationReleaseTestState'), params.id])}"
                redirect(action: "show", id: params.id)
            }
            catch (DomainInUseException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'applicationReleaseTestState.label', default: 'ApplicationReleaseTestState'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationReleaseTestState.label', default: 'ApplicationReleaseTestState'), params.id])}"
            redirect(action: "list")
        }
    }
}
