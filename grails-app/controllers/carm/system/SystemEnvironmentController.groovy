package carm.system

import grails.plugins.springsecurity.Secured
import carm.exceptions.DomainInUseException
import org.springframework.dao.DataIntegrityViolationException

class SystemEnvironmentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def activityTraceService
    def applicationDeploymentService
    def applicationService
    def systemEnvironmentService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [
                systemInstanceList: systemEnvironmentService.list(params),
                systemInstanceTotal: systemEnvironmentService.count()
        ]
    }

    @Secured(['ROLE_ADMIN'])
    def create() {
        def systemInstance = new SystemEnvironment()
        systemInstance.properties = params
        return [systemInstance: systemInstance]
    }

    @Secured(['ROLE_ADMIN'])
    def save() {
        def systemInstance = systemEnvironmentService.create(params)
        if (!systemInstance.hasErrors()) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), systemInstance.name])}"
            redirect(action: "show", id: systemInstance.id)
        }
        else {
            render(view: "create", model: [systemInstance: systemInstance])
        }
    }

    def show() {
        def systemInstance = systemEnvironmentService.get(params.id)
        if (!systemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    activityList: activityTraceService.listActivityBySystemEnvironment(systemInstance, [:]),
                    systemInstance: systemInstance,
                    applicationsGrouped: applicationService.findAllBySystemGroupedByType(systemInstance),
                    latestDeployments: applicationDeploymentService.findAllLatestCompletedDeploymentsBySystem(systemInstance)
            ]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def moveEnvDown() {
        def systemInstance = systemEnvironmentService.get(params.systemId)
        if (!systemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
            redirect(action: "list")
        }
        else {
            systemEnvironmentService.moveEnvironmentDown(systemInstance, params.index?.toInteger())

            redirect(action: "show", id: systemInstance.id)
        }
    }

    @Secured(['ROLE_ADMIN'])
    def moveEnvUp() {
        def systemInstance = systemEnvironmentService.get(params.systemId)
        if (!systemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
            redirect(action: "list")
        }
        else {
            systemEnvironmentService.moveEnvironmentUp(systemInstance, params.index?.toInteger())

            redirect(action: "show", id: systemInstance.id)
        }
    }

    @Secured(['ROLE_ADMIN'])
    def edit() {
        def systemInstance = systemEnvironmentService.get(params.id)
        if (!systemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [systemInstance: systemInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def update() {
        def systemInstance = systemEnvironmentService.get(params.id)
        if (systemInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (systemInstance.version > version) {

                    systemInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'systemEnvironment.label', default: 'SystemEnvironment')] as Object[], "Another user has updated this SystemEnvironment while you were editing")
                    render(view: "edit", model: [systemInstance: systemInstance])
                    return
                }
            }
            systemEnvironmentService.update(systemInstance, params)
            if (!systemInstance.hasErrors() && systemInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), systemInstance.name])}"
                redirect(action: "show", id: systemInstance.id)
            }
            else {
                render(view: "edit", model: [systemInstance: systemInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
            redirect(action: "list")
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete() {
        def systemInstance = systemEnvironmentService.get(params.id)
        if (systemInstance) {
            try {
                def name = systemInstance.name
                systemEnvironmentService.delete(systemInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), name])}"
                redirect(action: "list")
            }
            catch (DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
                redirect(action: "show", id: params.id)
            }
            catch (DomainInUseException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
            redirect(action: "list")
        }
    }

    def upcomingDeployments() {
        def systemInstance = systemEnvironmentService.get(params.id)
        if (systemInstance) {
            def applicationDeploymentInstanceList = applicationDeploymentService.findAllUpcomingBySystem(systemInstance)

            def applicationDeploymentsGrouped = [:]
            applicationDeploymentInstanceList.each { deployment ->
                def dateGroup = applicationDeploymentsGrouped[deployment.requestedDeploymentDate]
                if (!dateGroup) {
                    dateGroup = [:]
                    applicationDeploymentsGrouped[deployment.requestedDeploymentDate] = dateGroup
                }

                def envGroup = dateGroup[deployment.deploymentEnvironment]
                if (!envGroup) {
                    envGroup = [:]
                    dateGroup[deployment.deploymentEnvironment] = envGroup
                }

                def typeList = envGroup[deployment.applicationRelease.application.type]
                if (!typeList) {
                    typeList = []
                    envGroup[deployment.applicationRelease.application.type] = typeList
                }

                typeList.add(deployment)
            }

            [
                    applicationDeploymentsGrouped: applicationDeploymentsGrouped,
                    systemInstance: systemInstance
            ]
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
            redirect(action: "list")
        }
    }

    def listActivity() {
        def systemInstance = systemEnvironmentService.get(params.id)
        if (!systemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
            redirect(action: "list")
        }
        else {
            params.max = Math.min(params.max ? params.int('max') : 10, 100)

            [
                    domainInstance: systemInstance,
                    activityList: activityTraceService.listActivityBySystemEnvironment(systemInstance, params),
                    activityTotal: activityTraceService.countActivityBySystemEnvironment(systemInstance)
            ]
        }
    }
}
