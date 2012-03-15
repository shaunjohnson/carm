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
                systemEnvironmentInstanceList: systemEnvironmentService.list(params),
                systemEnvironmentInstanceTotal: systemEnvironmentService.count()
        ]
    }

    @Secured(['ROLE_ADMIN'])
    def create() {
        def systemEnvironmentInstance = new SystemEnvironment()
        systemEnvironmentInstance.properties = params

        [
                systemEnvironmentInstance: systemEnvironmentInstance
        ]
    }

    @Secured(['ROLE_ADMIN'])
    def save() {
        def systemEnvironmentInstance = systemEnvironmentService.create(params)
        if (!systemEnvironmentInstance.hasErrors()) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), systemEnvironmentInstance.name])}"
            redirect(action: "show", id: systemEnvironmentInstance.id)
        }
        else {
            render(view: "create", model: [systemEnvironmentInstance: systemEnvironmentInstance])
        }
    }

    def show() {
        def systemEnvironmentInstance = systemEnvironmentService.get(params.id)
        if (!systemEnvironmentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    activityList: activityTraceService.listActivityBySystemEnvironment(systemEnvironmentInstance, [:]),
                    systemEnvironmentInstance: systemEnvironmentInstance,
                    applicationsGrouped: applicationService.findAllBySystemEnvironmentGroupedByType(systemEnvironmentInstance),
                    latestDeployments: applicationDeploymentService.findAllLatestCompletedDeploymentsBySystemEnvironment(systemEnvironmentInstance)
            ]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def moveEnvDown() {
        def systemEnvironmentInstance = systemEnvironmentService.get(params.systemId)
        if (!systemEnvironmentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
            redirect(action: "list")
        }
        else {
            systemEnvironmentService.moveEnvironmentDown(systemEnvironmentInstance, params.index?.toInteger())

            redirect(action: "show", id: systemEnvironmentInstance.id)
        }
    }

    @Secured(['ROLE_ADMIN'])
    def moveEnvUp() {
        def systemEnvironmentInstance = systemEnvironmentService.get(params.systemId)
        if (!systemEnvironmentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
            redirect(action: "list")
        }
        else {
            systemEnvironmentService.moveEnvironmentUp(systemEnvironmentInstance, params.index?.toInteger())

            redirect(action: "show", id: systemEnvironmentInstance.id)
        }
    }

    @Secured(['ROLE_ADMIN'])
    def edit() {
        def systemEnvironmentInstance = systemEnvironmentService.get(params.id)
        if (!systemEnvironmentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    systemEnvironmentInstance: systemEnvironmentInstance
            ]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def update() {
        def systemEnvironmentInstance = systemEnvironmentService.get(params.id)
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
    def delete() {
        def systemEnvironmentInstance = systemEnvironmentService.get(params.id)
        if (systemEnvironmentInstance) {
            try {
                def name = systemEnvironmentInstance.name
                systemEnvironmentService.delete(systemEnvironmentInstance)
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
        def systemEnvironmentInstance = systemEnvironmentService.get(params.id)
        if (systemEnvironmentInstance) {
            def applicationDeploymentInstanceList = applicationDeploymentService.findAllUpcomingBySystemEnvironment(systemEnvironmentInstance)

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
                    systemInstance: systemEnvironmentInstance
            ]
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
            redirect(action: "list")
        }
    }

    def listActivity() {
        def systemEnvironmentInstance = systemEnvironmentService.get(params.id)
        if (!systemEnvironmentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
            redirect(action: "list")
        }
        else {
            params.max = Math.min(params.max ? params.int('max') : 10, 100)

            [
                    domainInstance: systemEnvironmentInstance,
                    activityList: activityTraceService.listActivityBySystemEnvironment(systemEnvironmentInstance, params),
                    activityTotal: activityTraceService.countActivityBySystemEnvironment(systemEnvironmentInstance)
            ]
        }
    }
}
