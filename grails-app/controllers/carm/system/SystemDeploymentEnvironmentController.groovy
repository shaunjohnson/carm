package carm.system

import grails.plugins.springsecurity.Secured
import carm.exceptions.DomainInUseException
import org.springframework.dao.DataIntegrityViolationException

class SystemDeploymentEnvironmentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def activityTraceService
    def applicationService
    def applicationDeploymentService
    def systemEnvironmentService
    def systemDeploymentEnvironmentService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        [
                systemDeploymentEnvironmentInstanceList: systemDeploymentEnvironmentService.list(params),
                systemDeploymentEnvironmentInstanceTotal: systemDeploymentEnvironmentService.count()
        ]
    }

    @Secured(['ROLE_ADMIN'])
    def create() {
        def systemInstance = systemEnvironmentService.get(params.sysEnvironment?.id)
        if (!systemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.systemEnvironment?.id])}"
            redirect(action: "list")
        }
        else {
            def systemDeploymentEnvironmentInstance = new SystemDeploymentEnvironment()
            systemDeploymentEnvironmentInstance.properties = params

             [
                     systemDeploymentEnvironmentInstance: systemDeploymentEnvironmentInstance
             ]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def save() {
        def systemDeploymentEnvironmentInstance = systemDeploymentEnvironmentService.create(params)
        if (systemDeploymentEnvironmentInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'systemDeploymentEnvironment.label', default: 'SystemDeploymentEnvironment'), systemDeploymentEnvironmentInstance.name])}"
            redirect(controller: "systemEnvironment", action: "show", id: systemDeploymentEnvironmentInstance.sysEnvironment.id)
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
                    activityList: activityTraceService.listActivityBySystemDeploymentEnvironment(systemDeploymentEnvironmentInstance, [:]),
                    activityCount: activityTraceService.countActivityBySystemDeploymentEnvironment(systemDeploymentEnvironmentInstance),
                    systemDeploymentEnvironmentInstance: systemDeploymentEnvironmentInstance,
                    applicationsGrouped: applicationService.findAllBySystemEnvironmentGroupedByType(systemDeploymentEnvironmentInstance.sysEnvironment),
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
            def systemId = systemDeploymentEnvironmentInstance.sysEnvironment.id
            try {
                def name = systemDeploymentEnvironmentInstance.name
                systemDeploymentEnvironmentService.delete(systemDeploymentEnvironmentInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'systemDeploymentEnvironment.label', default: 'SystemDeploymentEnvironment'), name])}"
                redirect(controller: "systemEnvironment", action: "show", id: systemId)
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

    def listActivity() {
        def systemDeploymentEnvironmentInstance = systemDeploymentEnvironmentService.get(params.id)
        if (!systemDeploymentEnvironmentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemDeploymentEnvironment.label', default: 'SystemDeploymentEnvironment'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    domainInstance: systemDeploymentEnvironmentInstance,
                    activityList: activityTraceService.listActivityBySystemDeploymentEnvironment(systemDeploymentEnvironmentInstance, params),
                    activityTotal: activityTraceService.countActivityBySystemDeploymentEnvironment(systemDeploymentEnvironmentInstance)
            ]
        }
    }

    def ajaxShowMoreActivity() {
        def systemDeploymentEnvironmentInstance = systemDeploymentEnvironmentService.get(params.id)
        def activityList = []

        if (systemDeploymentEnvironmentInstance) {
            activityList = activityTraceService.listActivityBySystemDeploymentEnvironment(systemDeploymentEnvironmentInstance, params)
        }

        render(template: "/common/activityBlock", model: [activityList: activityList])
    }
}
