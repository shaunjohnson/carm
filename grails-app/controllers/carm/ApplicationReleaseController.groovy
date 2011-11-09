package carm

class ApplicationReleaseController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [applicationReleaseInstanceList: ApplicationRelease.list(params), applicationReleaseInstanceTotal: ApplicationRelease.count()]
    }

    def create = {
        def applicationReleaseInstance = new ApplicationRelease()
        applicationReleaseInstance.properties = params
        return [applicationReleaseInstance: applicationReleaseInstance]
    }

    def save = {
        def applicationReleaseInstance = new ApplicationRelease(params)
        if (applicationReleaseInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'applicationRelease.label', default: 'ApplicationRelease'), applicationReleaseInstance.id])}"
            redirect(action: "show", id: applicationReleaseInstance.id)
        }
        else {
            render(view: "create", model: [applicationReleaseInstance: applicationReleaseInstance])
        }
    }

    def show = {
        def applicationReleaseInstance = ApplicationRelease.get(params.id)
        if (!applicationReleaseInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationRelease.label', default: 'ApplicationRelease'), params.id])}"
            redirect(action: "list")
        }
        else {
            [applicationReleaseInstance: applicationReleaseInstance]
        }
    }

    def edit = {
        def applicationReleaseInstance = ApplicationRelease.get(params.id)
        if (!applicationReleaseInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationRelease.label', default: 'ApplicationRelease'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [applicationReleaseInstance: applicationReleaseInstance]
        }
    }

    def update = {
        def applicationReleaseInstance = ApplicationRelease.get(params.id)
        if (applicationReleaseInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (applicationReleaseInstance.version > version) {
                    
                    applicationReleaseInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'applicationRelease.label', default: 'ApplicationRelease')] as Object[], "Another user has updated this ApplicationRelease while you were editing")
                    render(view: "edit", model: [applicationReleaseInstance: applicationReleaseInstance])
                    return
                }
            }
            applicationReleaseInstance.properties = params
            if (!applicationReleaseInstance.hasErrors() && applicationReleaseInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'applicationRelease.label', default: 'ApplicationRelease'), applicationReleaseInstance.id])}"
                redirect(action: "show", id: applicationReleaseInstance.id)
            }
            else {
                render(view: "edit", model: [applicationReleaseInstance: applicationReleaseInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationRelease.label', default: 'ApplicationRelease'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def applicationReleaseInstance = ApplicationRelease.get(params.id)
        if (applicationReleaseInstance) {
            try {
                applicationReleaseInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'applicationRelease.label', default: 'ApplicationRelease'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'applicationRelease.label', default: 'ApplicationRelease'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationRelease.label', default: 'ApplicationRelease'), params.id])}"
            redirect(action: "list")
        }
    }
}
