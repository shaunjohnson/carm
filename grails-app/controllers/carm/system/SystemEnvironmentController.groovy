package carm.system

import grails.plugins.springsecurity.Secured

class SystemEnvironmentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def applicationService
    def applicationDeploymentService
    def systemService
    def systemEnvironmentService

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [systemEnvironmentInstanceList: systemEnvironmentService.list(params), systemEnvironmentInstanceTotal: systemEnvironmentService.count()]
    }

    @Secured(['ROLE_ADMIN'])
    def create = {
        def systemInstance = systemService.get(params.system.id?.toLong())
        if (!systemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'system.label', default: 'System'), params.system.id])}"
            redirect(action: "list")
        }
        else {
            def systemEnvironmentInstance = new SystemEnvironment()
            systemEnvironmentInstance.properties = params
            return [systemEnvironmentInstance: systemEnvironmentInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def save = {
        def systemEnvironmentInstance = systemEnvironmentService.create(params)
        if (systemEnvironmentInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), systemEnvironmentInstance.name])}"
            redirect(controller: "system", action: "show", id: systemEnvironmentInstance.system.id)
        }
        else {
            render(view: "create", model: [systemEnvironmentInstance: systemEnvironmentInstance])
        }
    }

    def show = {
        def systemEnvironmentInstance = systemEnvironmentService.get(params.id?.toLong())
        if (!systemEnvironmentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
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
    def edit = {
        def systemEnvironmentInstance = systemEnvironmentService.get(params.id?.toLong())
        if (!systemEnvironmentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [systemEnvironmentInstance: systemEnvironmentInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def update = {
        def systemEnvironmentInstance = systemEnvironmentService.get(params.id?.toLong())
        if (systemEnvironmentInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (systemEnvironmentInstance.version > version) {
                    systemEnvironmentInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'systemEnvironment.label', default: 'SystemEnvironment')] as Object[], "Another user has updated this SystemEnvironment while you were editing")
                    render(view: "edit", model: [systemEnvironmentInstance: systemEnvironmentInstance])
                    return
                }
            }
            systemEnvironmentService.update(systemEnvironmentInstance, params)
            if (!systemEnvironmentInstance.hasErrors() && systemEnvironmentInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), systemEnvironmentInstance.name])}"
                redirect(action: "show", id: systemEnvironmentInstance.id)
            }
            else {
                render(view: "edit", model: [systemEnvironmentInstance: systemEnvironmentInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
            redirect(action: "list")
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete = {
        def systemEnvironmentInstance = systemEnvironmentService.get(params.id?.toLong())
        if (systemEnvironmentInstance) {
            def systemId = systemEnvironmentInstance.system.id
            try {
                def name = systemEnvironmentInstance.name
                systemEnvironmentService.delete(systemEnvironmentInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), name])}"
                redirect(controller: "system", action: "show", id: systemId)
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
            redirect(action: "list")
        }
    }
}
