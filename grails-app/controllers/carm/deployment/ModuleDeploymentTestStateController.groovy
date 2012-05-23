package carm.deployment

import grails.plugins.springsecurity.Secured
import carm.exceptions.DomainInUseException
import org.springframework.dao.DataIntegrityViolationException

class ModuleDeploymentTestStateController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def moduleDeploymentTestStateService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        [
                moduleDeploymentTestStateInstanceList: moduleDeploymentTestStateService.list(params),
                moduleDeploymentTestStateInstanceTotal: moduleDeploymentTestStateService.count()
        ]
    }

    @Secured(['ROLE_ADMIN'])
    def create() {
        def moduleDeploymentTestStateInstance = new ModuleDeploymentTestState()
        moduleDeploymentTestStateInstance.properties = params
        return [moduleDeploymentTestStateInstance: moduleDeploymentTestStateInstance]
    }

    @Secured(['ROLE_ADMIN'])
    def save() {
        def moduleDeploymentTestStateInstance = moduleDeploymentTestStateService.create(params)
        if (!moduleDeploymentTestStateInstance.hasErrors()) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'moduleDeploymentTestState.label', default: 'ModuleDeploymentTestState'), moduleDeploymentTestStateInstance.name])}"
            redirect(action: "show", id: moduleDeploymentTestStateInstance.id)
        }
        else {
            render(view: "create", model: [moduleDeploymentTestStateInstance: moduleDeploymentTestStateInstance])
        }
    }

    def show() {
        def moduleDeploymentTestStateInstance = moduleDeploymentTestStateService.get(params.id)
        if (!moduleDeploymentTestStateInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'moduleDeploymentTestState.label', default: 'ModuleDeploymentTestState'), params.id])}"
            redirect(action: "list")
        }
        else {
            [moduleDeploymentTestStateInstance: moduleDeploymentTestStateInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def edit() {
        def moduleDeploymentTestStateInstance = moduleDeploymentTestStateService.get(params.id)
        if (!moduleDeploymentTestStateInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'moduleDeploymentTestState.label', default: 'ModuleDeploymentTestState'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [moduleDeploymentTestStateInstance: moduleDeploymentTestStateInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def update() {
        def moduleDeploymentTestStateInstance = moduleDeploymentTestStateService.get(params.id)
        if (moduleDeploymentTestStateInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (moduleDeploymentTestStateInstance.version > version) {
                    
                    moduleDeploymentTestStateInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'moduleDeploymentTestState.label', default: 'ModuleDeploymentTestState')] as Object[], "Another user has updated this ModuleDeploymentTestState while you were editing")
                    render(view: "edit", model: [moduleDeploymentTestStateInstance: moduleDeploymentTestStateInstance])
                    return
                }
            }
            moduleDeploymentTestStateService.update(moduleDeploymentTestStateInstance, params)
            if (!moduleDeploymentTestStateInstance.hasErrors() && moduleDeploymentTestStateInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'moduleDeploymentTestState.label', default: 'ModuleDeploymentTestState'), moduleDeploymentTestStateInstance.name])}"
                redirect(action: "show", id: moduleDeploymentTestStateInstance.id)
            }
            else {
                render(view: "edit", model: [moduleDeploymentTestStateInstance: moduleDeploymentTestStateInstance])
            }
        }
        else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'moduleDeploymentTestState.label', default: 'ModuleDeploymentTestState'), params.id])}"
            redirect(action: "list")
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete() {
        def moduleDeploymentTestStateInstance = moduleDeploymentTestStateService.get(params.id)
        if (moduleDeploymentTestStateInstance) {
            def name = moduleDeploymentTestStateInstance.name

            try {
                moduleDeploymentTestStateService.delete(moduleDeploymentTestStateInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'moduleDeploymentTestState.label', default: 'ModuleDeploymentTestState'), name])}"
                redirect(action: "list")
            }
            catch (DataIntegrityViolationException e) {
                flash.error = "${message(code: 'default.not.deleted.message', args: [message(code: 'moduleDeploymentTestState.label', default: 'ModuleDeploymentTestState'), name])}"
                redirect(action: "show", id: params.id)
            }
            catch (DomainInUseException e) {
                flash.error = "${message(code: 'default.not.deleted.message', args: [message(code: 'moduleDeploymentTestState.label', default: 'ModuleDeploymentTestState'), name])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'moduleDeploymentTestState.label', default: 'ModuleDeploymentTestState'), params.id])}"
            redirect(action: "list")
        }
    }
}
