package carm.system

import grails.plugins.springsecurity.Secured
import carm.exceptions.DomainInUseException
import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.lang.time.DateUtils
import org.joda.time.DateTime
import java.text.SimpleDateFormat

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
                    activityCount: activityTraceService.countActivityBySystemEnvironment(systemEnvironmentInstance),
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

    def ajaxCompletedDeployments() {
        def systemEnvironmentInstance = systemEnvironmentService.get(params.id)
        if (systemEnvironmentInstance) {
            def date = new SimpleDateFormat(message(code: 'java.datepicker.format')).parse(params.date)
            def applicationDeploymentsGrouped = applicationDeploymentService.findAllDeploymentsGroupedByDay(systemEnvironmentInstance, date)

            render(template: "completedDeployments", model: [applicationDeploymentsGrouped: applicationDeploymentsGrouped, date: date])
        }
        else {
            render "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
        }
    }

    def ajaxUpcomingDeployments() {
        def systemEnvironmentInstance = systemEnvironmentService.get(params.id)
        if (systemEnvironmentInstance) {
            def applicationDeploymentsGrouped = applicationDeploymentService.findAllUpcomingDeploymentsGroupedByDay(systemEnvironmentInstance)

            render(template: "upcomingDeployments", model: [applicationDeploymentsGrouped: applicationDeploymentsGrouped])
        }
        else {
            render "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
        }
    }

    def deployments() {
        def systemEnvironmentInstance = systemEnvironmentService.get(params.id)
        if (systemEnvironmentInstance) {
            def today = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH)
            [systemInstance: systemEnvironmentInstance, today: today]
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
            [
                    domainInstance: systemEnvironmentInstance,
                    activityList: activityTraceService.listActivityBySystemEnvironment(systemEnvironmentInstance, params),
                    activityTotal: activityTraceService.countActivityBySystemEnvironment(systemEnvironmentInstance)
            ]
        }
    }

    def ajaxShowMoreActivity() {
        def systemEnvironmentInstance = systemEnvironmentService.get(params.id)
        def activityList = []

        if (systemEnvironmentInstance) {
            activityList = activityTraceService.listActivityBySystemEnvironment(systemEnvironmentInstance, params)
        }

        render(template: "/common/activityBlock", model: [activityList: activityList])
    }
}
