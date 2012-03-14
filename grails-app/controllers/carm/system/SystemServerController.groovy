package carm.system

import grails.plugins.springsecurity.Secured
import carm.exceptions.DomainInUseException
import org.springframework.dao.DataIntegrityViolationException

class SystemServerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def activityTraceService
    def systemServerService
    def systemService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [systemComponentInstanceList: systemServerService.list(params), systemComponentInstanceTotal: systemServerService.count()]
    }

    @Secured(['ROLE_ADMIN'])
    def create() {
        def systemInstance = systemService.get(params.system?.id)
        if (!systemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'system.label', default: 'System'), params.system?.id])}"
            redirect(action: "list")
        }
        else {
            def systemComponentInstance = new SystemServer()
            systemComponentInstance.properties = params
            return [systemComponentInstance: systemComponentInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def save() {
        def systemComponentInstance = systemServerService.create(params)
        if (!systemComponentInstance.hasErrors()) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'systemComponent.label', default: 'SystemServer'), systemComponentInstance.name])}"
            redirect(controller: "system", action: "show", id: systemComponentInstance.system.id)
        }
        else {
            render(view: "create", model: [systemComponentInstance: systemComponentInstance])
        }
    }

    def show() {
        def systemComponentInstance = systemServerService.get(params.id)
        if (!systemComponentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemComponent.label', default: 'SystemServer'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    activityList: activityTraceService.listActivityBySystemComponent(systemComponentInstance, [:]),
                    systemComponentInstance: systemComponentInstance
            ]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def edit() {
        def systemComponentInstance = systemServerService.get(params.id)
        if (!systemComponentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemComponent.label', default: 'SystemServer'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [systemComponentInstance: systemComponentInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def update() {
        def systemComponentInstance = systemServerService.get(params.id)
        if (systemComponentInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (systemComponentInstance.version > version) {
                    systemComponentInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'systemComponent.label', default: 'SystemServer')] as Object[], "Another user has updated this SystemServer while you were editing")
                    render(view: "edit", model: [systemComponentInstance: systemComponentInstance])
                    return
                }
            }
            systemServerService.update(systemComponentInstance, params)
            if (!systemComponentInstance.hasErrors() && systemComponentInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'systemComponent.label', default: 'SystemServer'), systemComponentInstance.name])}"
                redirect(action: "show", id: systemComponentInstance.id)
            }
            else {
                render(view: "edit", model: [systemComponentInstance: systemComponentInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemComponent.label', default: 'SystemServer'), params.id])}"
            redirect(action: "list")
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete() {
        def systemComponentInstance = systemServerService.get(params.id)
        if (systemComponentInstance) {
            def systemId = systemComponentInstance.system.id
            try {
                def name = systemComponentInstance.name
                systemServerService.delete(systemComponentInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'systemComponent.label', default: 'SystemServer'), name])}"
                redirect(controller: "system", action: "show", id: systemId)
            }
            catch (DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'systemComponent.label', default: 'SystemServer'), params.id])}"
                redirect(action: "show", id: params.id)
            }
            catch (DomainInUseException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'systemComponent.label', default: 'SystemServer'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemComponent.label', default: 'SystemServer'), params.id])}"
            redirect(action: "list")
        }
    }

    def listActivity() {
        def systemComponentInstance = systemServerService.get(params.id)
        if (!systemComponentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemComponent.label', default: 'SystemServer'), params.id])}"
            redirect(action: "list")
        }
        else {
            params.max = Math.min(params.max ? params.int('max') : 10, 100)

            [
                    domainInstance: systemComponentInstance,
                    activityList: activityTraceService.listActivityBySystemComponent(systemComponentInstance, params),
                    activityTotal: activityTraceService.countActivityBySystemComponent(systemComponentInstance)
            ]
        }
    }
}
