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
        [systemEnvironmentInstanceList: systemDeploymentEnvironmentService.list(params), systemEnvironmentInstanceTotal: systemDeploymentEnvironmentService.count()]
    }

    @Secured(['ROLE_ADMIN'])
    def create() {
        def systemInstance = systemService.get(params.system?.id)
        if (!systemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'system.label', default: 'System'), params.system?.id])}"
            redirect(action: "list")
        }
        else {
            def systemEnvironmentInstance = new SystemDeploymentEnvironment()
            systemEnvironmentInstance.properties = params
            return [systemEnvironmentInstance: systemEnvironmentInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def save() {
        def systemEnvironmentInstance = systemDeploymentEnvironmentService.create(params)
        if (systemEnvironmentInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'systemEnvironment.label', default: 'SystemDeploymentEnvironment'), systemEnvironmentInstance.name])}"
            redirect(controller: "system", action: "show", id: systemEnvironmentInstance.system.id)
        }
        else {
            render(view: "create", model: [systemEnvironmentInstance: systemEnvironmentInstance])
        }
    }

    def show() {
        def systemEnvironmentInstance = systemDeploymentEnvironmentService.get(params.id)
        if (!systemEnvironmentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemDeploymentEnvironment'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    systemEnvironmentInstance: systemEnvironmentInstance,
                    applicationsGrouped: applicationService.findAllBySystemGroupedByType(systemEnvironmentInstance.system),
                    latestDeployments: applicationDeploymentService.findAllLatestCompletedDeploymentsBySystemEnvironment(systemEnvironmentInstance)
            ]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def edit() {
        def systemEnvironmentInstance = systemDeploymentEnvironmentService.get(params.id)
        if (!systemEnvironmentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemDeploymentEnvironment'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [systemEnvironmentInstance: systemEnvironmentInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def update() {
        def systemEnvironmentInstance = systemDeploymentEnvironmentService.get(params.id)
        if (systemEnvironmentInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (systemEnvironmentInstance.version > version) {
                    systemEnvironmentInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'systemEnvironment.label', default: 'SystemDeploymentEnvironment')] as Object[], "Another user has updated this SystemDeploymentEnvironment while you were editing")
                    render(view: "edit", model: [systemEnvironmentInstance: systemEnvironmentInstance])
                    return
                }
            }
            systemDeploymentEnvironmentService.update(systemEnvironmentInstance, params)
            if (!systemEnvironmentInstance.hasErrors() && systemEnvironmentInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'systemEnvironment.label', default: 'SystemDeploymentEnvironment'), systemEnvironmentInstance.name])}"
                redirect(action: "show", id: systemEnvironmentInstance.id)
            }
            else {
                render(view: "edit", model: [systemEnvironmentInstance: systemEnvironmentInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemDeploymentEnvironment'), params.id])}"
            redirect(action: "list")
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete() {
        def systemEnvironmentInstance = systemDeploymentEnvironmentService.get(params.id)
        if (systemEnvironmentInstance) {
            def systemId = systemEnvironmentInstance.system.id
            try {
                def name = systemEnvironmentInstance.name
                systemDeploymentEnvironmentService.delete(systemEnvironmentInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'systemEnvironment.label', default: 'SystemDeploymentEnvironment'), name])}"
                redirect(controller: "system", action: "show", id: systemId)
            }
            catch (DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'systemEnvironment.label', default: 'SystemDeploymentEnvironment'), params.id])}"
                redirect(action: "show", id: params.id)
            }
            catch (DomainInUseException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'systemEnvironment.label', default: 'SystemDeploymentEnvironment'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemDeploymentEnvironment'), params.id])}"
            redirect(action: "list")
        }
    }
}
