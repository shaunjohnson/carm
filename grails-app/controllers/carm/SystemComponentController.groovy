package carm

class SystemComponentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [systemComponentInstanceList: SystemComponent.list(params), systemComponentInstanceTotal: SystemComponent.count()]
    }

    def create = {
        def systemInstance = System.get(params.system.id)
        if (!systemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'system.label', default: 'System'), params.system.id])}"
            redirect(action: "list")
        }
        else {
            def systemComponentInstance = new SystemComponent()
            systemComponentInstance.properties = params
            return [systemComponentInstance: systemComponentInstance]
        }
    }

    def save = {
        def systemComponentInstance = new SystemComponent(params)
        if (systemComponentInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'systemComponent.label', default: 'SystemComponent'), systemComponentInstance.id])}"
            redirect(action: "show", id: systemComponentInstance.id)
        }
        else {
            render(view: "create", model: [systemComponentInstance: systemComponentInstance])
        }
    }

    def show = {
        def systemComponentInstance = SystemComponent.get(params.id)
        if (!systemComponentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemComponent.label', default: 'SystemComponent'), params.id])}"
            redirect(action: "list")
        }
        else {
            [systemComponentInstance: systemComponentInstance]
        }
    }

    def edit = {
        def systemComponentInstance = SystemComponent.get(params.id)
        if (!systemComponentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemComponent.label', default: 'SystemComponent'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [systemComponentInstance: systemComponentInstance]
        }
    }

    def update = {
        def systemComponentInstance = SystemComponent.get(params.id)
        if (systemComponentInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (systemComponentInstance.version > version) {
                    
                    systemComponentInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'systemComponent.label', default: 'SystemComponent')] as Object[], "Another user has updated this SystemComponent while you were editing")
                    render(view: "edit", model: [systemComponentInstance: systemComponentInstance])
                    return
                }
            }
            systemComponentInstance.properties = params
            if (!systemComponentInstance.hasErrors() && systemComponentInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'systemComponent.label', default: 'SystemComponent'), systemComponentInstance.id])}"
                redirect(action: "show", id: systemComponentInstance.id)
            }
            else {
                render(view: "edit", model: [systemComponentInstance: systemComponentInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemComponent.label', default: 'SystemComponent'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def systemComponentInstance = SystemComponent.get(params.id)
        if (systemComponentInstance) {
            try {
                systemComponentInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'systemComponent.label', default: 'SystemComponent'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'systemComponent.label', default: 'SystemComponent'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemComponent.label', default: 'SystemComponent'), params.id])}"
            redirect(action: "list")
        }
    }
}
