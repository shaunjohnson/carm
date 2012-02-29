package carm

import carm.enums.ApplicationDeploymentState

class ApplicationDeploymentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def applicationDeploymentService

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [applicationDeploymentInstanceList: ApplicationDeployment.list(params), applicationDeploymentInstanceTotal: ApplicationDeployment.count()]
    }

    def create = {
        def applicationReleaseInstance = ApplicationRelease.get(params.applicationRelease.id)
        if (!applicationReleaseInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationRelease.label', default: 'Application Release'), params.applicationRelease.id])}"
            redirect(action: "list")
        }
        else {
            def applicationDeploymentInstance = new ApplicationDeployment()

            // Attempt to predict the next release number
            applicationDeploymentInstance.sysEnvironment = applicationDeploymentService.inferNextEnvironment(applicationReleaseInstance)

            applicationDeploymentInstance.properties = params
            return [applicationDeploymentInstance: applicationDeploymentInstance]
        }
    }

    def save = {
        def applicationDeploymentInstance = new ApplicationDeployment(params)

        applicationDeploymentInstance.deploymentState = ApplicationDeploymentState.DRAFT

        if (applicationDeploymentInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment'), applicationDeploymentInstance.id])}"
            redirect(action: "show", id: applicationDeploymentInstance.id)
        }
        else {
            render(view: "create", model: [applicationDeploymentInstance: applicationDeploymentInstance])
        }
    }

    def show = {
        def applicationDeploymentInstance = ApplicationDeployment.get(params.id)
        if (!applicationDeploymentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment'), params.id])}"
            redirect(action: "list")
        }
        else {
            [applicationDeploymentInstance: applicationDeploymentInstance]
        }
    }

    def edit = {
        def applicationDeploymentInstance = ApplicationDeployment.get(params.id)
        if (!applicationDeploymentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [applicationDeploymentInstance: applicationDeploymentInstance]
        }
    }

    def update = {
        def applicationDeploymentInstance = ApplicationDeployment.get(params.id)
        if (applicationDeploymentInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (applicationDeploymentInstance.version > version) {

                    applicationDeploymentInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment')] as Object[], "Another user has updated this ApplicationDeployment while you were editing")
                    render(view: "edit", model: [applicationDeploymentInstance: applicationDeploymentInstance])
                    return
                }
            }
            applicationDeploymentInstance.properties = params
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

    def delete = {
        def applicationDeploymentInstance = ApplicationDeployment.get(params.id)
        if (applicationDeploymentInstance) {
            try {
                applicationDeploymentInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationDeployment.label', default: 'ApplicationDeployment'), params.id])}"
            redirect(action: "list")
        }
    }
}
