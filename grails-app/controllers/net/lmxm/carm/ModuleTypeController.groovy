package net.lmxm.carm

class ModuleTypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [moduleTypeInstanceList: ModuleType.list(params), moduleTypeInstanceTotal: ModuleType.count()]
    }

    def create = {
        def moduleTypeInstance = new ModuleType()
        moduleTypeInstance.properties = params
        return [moduleTypeInstance: moduleTypeInstance]
    }

    def save = {
        def moduleTypeInstance = new ModuleType(params)
        if (moduleTypeInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'moduleType.label', default: 'ModuleType'), moduleTypeInstance.id])}"
            redirect(action: "show", id: moduleTypeInstance.id)
        }
        else {
            render(view: "create", model: [moduleTypeInstance: moduleTypeInstance])
        }
    }

    def show = {
        def moduleTypeInstance = ModuleType.get(params.id)
        if (!moduleTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'moduleType.label', default: 'ModuleType'), params.id])}"
            redirect(action: "list")
        }
        else {
            [moduleTypeInstance: moduleTypeInstance]
        }
    }

    def edit = {
        def moduleTypeInstance = ModuleType.get(params.id)
        if (!moduleTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'moduleType.label', default: 'ModuleType'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [moduleTypeInstance: moduleTypeInstance]
        }
    }

    def update = {
        def moduleTypeInstance = ModuleType.get(params.id)
        if (moduleTypeInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (moduleTypeInstance.version > version) {
                    
                    moduleTypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'moduleType.label', default: 'ModuleType')] as Object[], "Another user has updated this ModuleType while you were editing")
                    render(view: "edit", model: [moduleTypeInstance: moduleTypeInstance])
                    return
                }
            }
            moduleTypeInstance.properties = params
            if (!moduleTypeInstance.hasErrors() && moduleTypeInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'moduleType.label', default: 'ModuleType'), moduleTypeInstance.id])}"
                redirect(action: "show", id: moduleTypeInstance.id)
            }
            else {
                render(view: "edit", model: [moduleTypeInstance: moduleTypeInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'moduleType.label', default: 'ModuleType'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def moduleTypeInstance = ModuleType.get(params.id)
        if (moduleTypeInstance) {
            try {
                moduleTypeInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'moduleType.label', default: 'ModuleType'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'moduleType.label', default: 'ModuleType'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'moduleType.label', default: 'ModuleType'), params.id])}"
            redirect(action: "list")
        }
    }
}
