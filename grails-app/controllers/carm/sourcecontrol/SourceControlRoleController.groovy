package carm.sourcecontrol

import grails.plugins.springsecurity.Secured
import carm.exceptions.DomainInUseException
import org.springframework.dao.DataIntegrityViolationException

class SourceControlRoleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def sourceControlRoleService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        [
                sourceControlRoleInstanceList: sourceControlRoleService.list(params),
                sourceControlRoleInstanceTotal: sourceControlRoleService.count()
        ]
    }

    @Secured(['ROLE_ADMIN'])
    def create() {
        def sourceControlRoleInstance = new SourceControlRole()
        sourceControlRoleInstance.properties = params
        return [sourceControlRoleInstance: sourceControlRoleInstance]
    }

    @Secured(['ROLE_ADMIN'])
    def save() {
        def sourceControlRoleInstance = sourceControlRoleService.create(params)
        if (!sourceControlRoleInstance.hasErrors()) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'sourceControlRole.label', default: 'SourceControlRole'), sourceControlRoleInstance.name])}"
            redirect(action: "show", id: sourceControlRoleInstance.id)
        }
        else {
            render(view: "create", model: [sourceControlRoleInstance: sourceControlRoleInstance])
        }
    }

    def show() {
        def sourceControlRoleInstance = sourceControlRoleService.get(params.id)
        if (!sourceControlRoleInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlRole.label', default: 'SourceControlRole'), params.id])}"
            redirect(action: "list")
        }
        else {
            [sourceControlRoleInstance: sourceControlRoleInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def edit() {
        def sourceControlRoleInstance = sourceControlRoleService.get(params.id)
        if (!sourceControlRoleInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlRole.label', default: 'SourceControlRole'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [sourceControlRoleInstance: sourceControlRoleInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def update() {
        def sourceControlRoleInstance = sourceControlRoleService.get(params.id)
        if (sourceControlRoleInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (sourceControlRoleInstance.version > version) {
                    
                    sourceControlRoleInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'sourceControlRole.label', default: 'SourceControlRole')] as Object[], "Another user has updated this SourceControlRole while you were editing")
                    render(view: "edit", model: [sourceControlRoleInstance: sourceControlRoleInstance])
                    return
                }
            }
            sourceControlRoleService.update(sourceControlRoleInstance, params)
            if (!sourceControlRoleInstance.hasErrors() && sourceControlRoleInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'sourceControlRole.label', default: 'SourceControlRole'), sourceControlRoleInstance.name])}"
                redirect(action: "show", id: sourceControlRoleInstance.id)
            }
            else {
                render(view: "edit", model: [sourceControlRoleInstance: sourceControlRoleInstance])
            }
        }
        else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlRole.label', default: 'SourceControlRole'), params.id])}"
            redirect(action: "list")
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete() {
        def sourceControlRoleInstance = sourceControlRoleService.get(params.id)
        if (sourceControlRoleInstance) {
            def name = sourceControlRoleInstance.name

            try {
                sourceControlRoleService.delete(sourceControlRoleInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'sourceControlRole.label', default: 'SourceControlRole'), name])}"
                redirect(action: "list")
            }
            catch (DataIntegrityViolationException e) {
                flash.error = "${message(code: 'default.not.deleted.message', args: [message(code: 'sourceControlRole.label', default: 'SourceControlRole'), name])}"
                redirect(action: "show", id: params.id)
            }
            catch (DomainInUseException e) {
                flash.error = "${message(code: 'default.not.deleted.message', args: [message(code: 'sourceControlRole.label', default: 'SourceControlRole'), name])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlRole.label', default: 'SourceControlRole'), params.id])}"
            redirect(action: "list")
        }
    }
}
