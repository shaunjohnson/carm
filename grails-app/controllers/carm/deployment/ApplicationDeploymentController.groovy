package carm.deployment

import carm.release.ApplicationRelease
import carm.exceptions.DomainInUseException
import org.springframework.dao.DataIntegrityViolationException

class ApplicationDeploymentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def activityTraceService
    def applicationDeploymentService
    def applicationReleaseService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        [
                applicationDeploymentInstanceList: applicationDeploymentService.list(params),
                applicationDeploymentInstanceTotal: applicationDeploymentService.count()
        ]
    }

    def create() {
        def applicationReleaseInstance = applicationReleaseService.get(params.applicationRelease?.id)
        if (!applicationReleaseInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationRelease.label', default: 'Application Release'), params.applicationRelease?.id])}"
            redirect(action: "list")
        }
        else {
            [
                    applicationDeploymentInstance: applicationDeploymentService.newApplicationDeployment(applicationReleaseInstance),
                    existingDeployments: applicationDeploymentService.findAllByApplicationReleaseOrderByEnvironment(applicationReleaseInstance)
            ]
        }
    }

    def save() {
        ApplicationRelease applicationReleaseInstance = applicationReleaseService.get(params.applicationRelease?.id)
        if (!applicationReleaseInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationRelease.label', default: 'Application Release'), params.applicationRelease?.id])}"
            redirect(action: "list")
        }
        else {
            def applicationDeploymentInstance = applicationDeploymentService.create(applicationReleaseInstance.application.project, params)
            if (!applicationDeploymentInstance.hasErrors()) {
                def application = applicationReleaseInstance.application.name
                def releaseNumber = applicationReleaseInstance.releaseNumber
                def envirnoment = applicationDeploymentInstance.deploymentEnvironment.name

                flash.message = "${message(code: 'applicationDeployment.created.message', default: 'Deployment of {0} Release {1} to {2} created', args: [application, releaseNumber, envirnoment])}"
                redirect(controller: "application", action: "show", id: applicationDeploymentInstance.applicationRelease.application.id)
            }
            else {
                render(view: "create", model: [applicationDeploymentInstance: applicationDeploymentInstance])
            }
        }
    }

    def show() {
        def applicationDeploymentInstance = applicationDeploymentService.get(params.id)
        if (!applicationDeploymentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    activityList: activityTraceService.listActivityByApplicationDeployment(applicationDeploymentInstance, [:]),
                    activityCount: activityTraceService.countActivityByApplicationDeployment(applicationDeploymentInstance),
                    applicationDeploymentInstance: applicationDeploymentInstance,
                    applicationDeploymentList: applicationDeploymentService.findAllByApplicationReleaseOrderByRequestedDate(applicationDeploymentInstance.applicationRelease),
                    nextEnvironment: applicationDeploymentService.inferNextEnvironment(applicationDeploymentInstance.applicationRelease)
            ]
        }
    }

    def edit() {
        def applicationDeploymentInstance = applicationDeploymentService.get(params.id)
        if (!applicationDeploymentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [applicationDeploymentInstance: applicationDeploymentInstance]
        }
    }

    def update() {
        def applicationDeploymentInstance = applicationDeploymentService.get(params.id)
        if (applicationDeploymentInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (applicationDeploymentInstance.version > version) {

                    applicationDeploymentInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment')] as Object[], "Another user has updated this ApplicationDeployment while you were editing")
                    render(view: "edit", model: [applicationDeploymentInstance: applicationDeploymentInstance])
                    return
                }
            }
            applicationDeploymentService.update(applicationDeploymentInstance.applicationRelease.application.project, applicationDeploymentInstance, params)
            if (!applicationDeploymentInstance.hasErrors() && applicationDeploymentInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment'), applicationDeploymentInstance.id])}"
                redirect(action: "show", id: applicationDeploymentInstance.id)
            }
            else {
                render(view: "edit", model: [applicationDeploymentInstance: applicationDeploymentInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete() {
        def applicationDeploymentInstance = applicationDeploymentService.get(params.id)
        if (applicationDeploymentInstance) {
            ApplicationRelease applicationRelease = applicationDeploymentInstance.applicationRelease
            try {
                applicationDeploymentService.delete(applicationRelease.application.project, applicationDeploymentInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment'), params.id])}"
                redirect(controller: "applicationRelease", action: "show", id: applicationRelease.id)
            }
            catch (DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment'), params.id])}"
                redirect(action: "show", id: params.id)
            }
            catch (DomainInUseException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment'), params.id])}"
            redirect(action: "list")
        }
    }

    def listActivity() {
        def applicationDeploymentInstance = applicationDeploymentService.get(params.id)
        if (!applicationDeploymentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationDeployment.label', default: 'Application Deployment'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    domainInstance: applicationDeploymentInstance,
                    activityList: activityTraceService.listActivityByApplicationDeployment(applicationDeploymentInstance, params),
                    activityTotal: activityTraceService.countActivityByApplicationDeployment(applicationDeploymentInstance)
            ]
        }
    }

    def ajaxShowMoreActivity() {
        def applicationDeploymentInstance = applicationDeploymentService.get(params.id)
        def activityList = []

        if (!applicationDeploymentInstance) {
            activityList = activityTraceService.listActivityByApplicationDeployment(applicationDeploymentInstance, params)
        }

        render(template: "/common/activityBlock", model: [activityList: activityList])
    }

    def redeploy() {
        def applicationDeploymentInstance = applicationDeploymentService.get(params.id)
        if (!applicationDeploymentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationDeployment.label', default: 'Application Deployment'), params.id])}"
            redirect(action: "list")
        }
        else {
            def project = applicationDeploymentInstance.applicationRelease.application.project
            def newApplicationDeploymentInstance = applicationDeploymentService.redeploy(project, applicationDeploymentInstance)
            def existingDeployments = applicationDeploymentService.findAllByApplicationReleaseOrderByEnvironment(applicationDeploymentInstance.applicationRelease)

            render(view: "create", model: [
                    applicationDeploymentInstance: newApplicationDeploymentInstance,
                    existingDeployments: existingDeployments
            ])
        }
    }
}
