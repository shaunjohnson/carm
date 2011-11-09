package carm

class ApplicationTypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [applicationTypeInstanceList: ApplicationType.list(params), applicationTypeInstanceTotal: ApplicationType.count()]
    }

    def create = {
        def applicationTypeInstance = new ApplicationType()
        applicationTypeInstance.properties = params
        return [applicationTypeInstance: applicationTypeInstance]
    }

    def save = {
        def applicationTypeInstance = new ApplicationType(params)
        if (applicationTypeInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'applicationType.label', default: 'ApplicationType'), applicationTypeInstance.id])}"
            redirect(action: "show", id: applicationTypeInstance.id)
        }
        else {
            render(view: "create", model: [applicationTypeInstance: applicationTypeInstance])
        }
    }

    def show = {
        def applicationTypeInstance = ApplicationType.get(params.id)
        if (!applicationTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationType.label', default: 'ApplicationType'), params.id])}"
            redirect(action: "list")
        }
        else {
            [applicationTypeInstance: applicationTypeInstance]
        }
    }

    def edit = {
        def applicationTypeInstance = ApplicationType.get(params.id)
        if (!applicationTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationType.label', default: 'ApplicationType'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [applicationTypeInstance: applicationTypeInstance]
        }
    }

    def update = {
        def applicationTypeInstance = ApplicationType.get(params.id)
        if (applicationTypeInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (applicationTypeInstance.version > version) {
                    
                    applicationTypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'applicationType.label', default: 'ApplicationType')] as Object[], "Another user has updated this ApplicationType while you were editing")
                    render(view: "edit", model: [applicationTypeInstance: applicationTypeInstance])
                    return
                }
            }
            applicationTypeInstance.properties = params
            if (!applicationTypeInstance.hasErrors() && applicationTypeInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'applicationType.label', default: 'ApplicationType'), applicationTypeInstance.id])}"
                redirect(action: "show", id: applicationTypeInstance.id)
            }
            else {
                render(view: "edit", model: [applicationTypeInstance: applicationTypeInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationType.label', default: 'ApplicationType'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def applicationTypeInstance = ApplicationType.get(params.id)
        if (applicationTypeInstance) {
            try {
                applicationTypeInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'applicationType.label', default: 'ApplicationType'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'applicationType.label', default: 'ApplicationType'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationType.label', default: 'ApplicationType'), params.id])}"
            redirect(action: "list")
        }
    }
}
