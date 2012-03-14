package carm.system

import grails.plugins.springsecurity.Secured
import carm.exceptions.DomainInUseException
import org.springframework.dao.DataIntegrityViolationException

class SystemDeplymentEnvironmentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def applicationService
    def applicationDeploymentService
    def systemService
    def systemDeploymentEnvironmentService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [systemDeploymentEnvironmentInstanceList: systemDeploymentEnvironmentService.list(params), systemDeploymentEnvironmentInstanceTotal: systemDeploymentEnvironmentService.count()]
    }

    @Secured(['ROLE_ADMIN'])
    def create() {
        def systemInstance = systemService.get(params.system?.id)
        if (!systemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'system.label', default: 'System'), params.system?.id])}"
            redirect(action: "list")
        }
        else {
            def systemDeploymentEnvironmentInstance = new SystemDeploymentEnvironment()
            systemDeploymentEnvironmentInstance.properties = params
            return [systemDeploymentEnvironmentInstance: systemDeploymentEnvironmentInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def save() {
        def systemDeploymentEnvironmentInstance = systemDeploymentEnvironmentService.create(params)
        if (systemDeploymentEnvironmentInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'systemDeploymentEnvironment.label', default: 'SystemDeploymentEnvironment'), systemDeploymentEnvironmentInstance.name])}"
            redirect(controller: "system", action: "show", id: systemDeploymentEnvironmentInstance.system.id)
        }
        else {
            render(view: "create", model: [systemDeploymentEnvironmentInstance: systemDeploymentEnvironmentInstance])
        }
    }

    def show() {
        def systemDeploymentEnvironmentInstance = systemDeploymentEnvironmentService.get(params.id)
        if (!systemDeploymentEnvironmentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemDeploymentEnvironment.label', default: 'SystemDeploymentEnvironment'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    systemDeploymentEnvironmentInstance: systemDeploymentEnvironmentInstance,
                    applicationsGrouped: applicationService.findAllBySystemGroupedByType(systemDeploymentEnvironmentInstance.system),
                    latestDeployments: applicationDeploymentService.findAllLatestCompletedDeploymentsBySystemDeploymentEnvironment(systemDeploymentEnvironmentInstance)
            ]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def edit() {
        def systemDeploymentEnvironmentInstance = systemDeploymentEnvironmentService.get(params.id)
        if (!systemDeploymentEnvironmentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemDeploymentEnvironment.label', default: 'SystemDeploymentEnvironment'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [systemDeploymentEnvironmentInstance: systemDeploymentEnvironmentInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def update() {
        def systemDeploymentEnvironmentInstance = systemDeploymentEnvironmentService.get(params.id)
        if (systemDeploymentEnvironmentInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (systemDeploymentEnvironmentInstance.version > version) {
                    systemDeploymentEnvironmentInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'systemDeploymentEnvironment.label', default: 'SystemDeploymentEnvironment')] as Object[], "Another user has updated this SystemDeploymentEnvironment while you were editing")
                    render(view: "edit", model: [systemDeploymentEnvironmentInstance: systemDeploymentEnvironmentInstance])
                    return
                }
            }
            systemDeploymentEnvironmentService.update(systemDeploymentEnvironmentInstance, params)
            if (!systemDeploymentEnvironmentInstance.hasErrors() && systemDeploymentEnvironmentInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'systemDeploymentEnvironment.label', default: 'SystemDeploymentEnvironment'), systemDeploymentEnvironmentInstance.name])}"
                redirect(action: "show", id: systemDeploymentEnvironmentInstance.id)
            }
            else {
                render(view: "edit", model: [systemDeploymentEnvironmentInstance: systemDeploymentEnvironmentInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemDeploymentEnvironment.label', default: 'SystemDeploymentEnvironment'), params.id])}"
            redirect(action: "list")
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete() {
        def systemDeploymentEnvironmentInstance = systemDeploymentEnvironmentService.get(params.id)
        if (systemDeploymentEnvironmentInstance) {
            def systemId = systemDeploymentEnvironmentInstance.system.id
            try {
                def name = systemDeploymentEnvironmentInstance.name
                systemDeploymentEnvironmentService.delete(systemDeploymentEnvironmentInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'systemDeploymentEnvironment.label', default: 'SystemDeploymentEnvironment'), name])}"
                redirect(controller: "system", action: "show", id: systemId)
            }
            catch (DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'systemDeploymentEnvironment.label', default: 'SystemDeploymentEnvironment'), params.id])}"
                redirect(action: "show", id: params.id)
            }
            catch (DomainInUseException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'systemDeploymentEnvironment.label', default: 'SystemDeploymentEnvironment'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemDeploymentEnvironment.label', default: 'SystemDeploymentEnvironment'), params.id])}"
            redirect(action: "list")
        }
    }
}