package carm

import grails.plugins.springsecurity.Secured

class SystemComponentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def systemComponentService
    def systemService

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [systemComponentInstanceList: SystemComponent.list(params), systemComponentInstanceTotal: SystemComponent.count()]
    }

    @Secured(['ROLE_ADMIN'])
    def create = {
        def systemInstance = systemService.get(params.system.id?.toLong())
        if (!systemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'system.label', default: 'System'), params.system.id])}"
            redirect(action: "list")
        }
        else {
            def systemComponentInstance = new SystemComponent()
            systemComponentInstance.properties = params
            return [systemComponentInstance: systemComponentInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def save = {
        def systemComponentInstance = systemComponentService.create(params)
        if (!systemComponentInstance.hasErrors()) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'systemComponent.label', default: 'SystemComponent'), systemComponentInstance.name])}"
            redirect(action: "show", id: systemComponentInstance.id)
        }
        else {
            render(view: "create", model: [systemComponentInstance: systemComponentInstance])
        }
    }

    def show = {
        def systemComponentInstance = systemComponentService.get(params.id?.toLong())
        if (!systemComponentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemComponent.label', default: 'SystemComponent'), params.id])}"
            redirect(action: "list")
        }
        else {
            [systemComponentInstance: systemComponentInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def edit = {
        def systemComponentInstance = systemComponentService.get(params.id?.toLong())
        if (!systemComponentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemComponent.label', default: 'SystemComponent'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [systemComponentInstance: systemComponentInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def update = {
        def systemComponentInstance = systemComponentService.get(params.id?.toLong())
        if (systemComponentInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (systemComponentInstance.version > version) {
                    systemComponentInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'systemComponent.label', default: 'SystemComponent')] as Object[], "Another user has updated this SystemComponent while you were editing")
                    render(view: "edit", model: [systemComponentInstance: systemComponentInstance])
                    return
                }
            }
            systemComponentService.update(systemComponentInstance, params)
            if (!systemComponentInstance.hasErrors() && systemComponentInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'systemComponent.label', default: 'SystemComponent'), systemComponentInstance.name])}"
                redirect(action: "show", id: systemComponentInstance.id)
            }
            else {
                render(view: "edit", model: [systemComponentInstance: systemComponentInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemComponent.label', default: 'SystemComponent'), params.id])}"
            redirect(action: "list")
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete = {
        def systemComponentInstance = systemComponentService.get(params.id?.toLong())
        if (systemComponentInstance) {
            def systemId = systemComponentInstance.system.id
            try {
                def name = systemComponentInstance.name
                systemComponentService.delete(systemComponentInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'systemComponent.label', default: 'SystemComponent'), name])}"
                redirect(controller: "system", action: "show", id: systemId)
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'systemComponent.label', default: 'SystemComponent'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemComponent.label', default: 'SystemComponent'), params.id])}"
            redirect(action: "list")
        }
    }
}
