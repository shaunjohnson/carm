package carm

class ModuleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [moduleInstanceList: Module.list(params), moduleInstanceTotal: Module.count()]
    }

    def create = {
        def moduleInstance = new Module()
        moduleInstance.properties = params
        return [moduleInstance: moduleInstance]
    }

    def save = {
        def moduleInstance = new Module(params)
        if (moduleInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'module.label', default: 'Module'), moduleInstance.name])}"
            redirect(controller: "application", action: "show", id: moduleInstance.application.id)
        }
        else {
            render(view: "create", model: [moduleInstance: moduleInstance])
        }
    }

    def show = {
        def moduleInstance = Module.get(params.id)
        if (!moduleInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'module.label', default: 'Module'), params.id])}"
            redirect(action: "list")
        }
        else {
            [moduleInstance: moduleInstance]
        }
    }

    def edit = {
        def moduleInstance = Module.get(params.id)
        if (!moduleInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'module.label', default: 'Module'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [moduleInstance: moduleInstance]
        }
    }

    def update = {
        def moduleInstance = Module.get(params.id)
        if (moduleInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (moduleInstance.version > version) {
                    
                    moduleInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'module.label', default: 'Module')] as Object[], "Another user has updated this Module while you were editing")
                    render(view: "edit", model: [moduleInstance: moduleInstance])
                    return
                }
            }
            moduleInstance.properties = params
            if (!moduleInstance.hasErrors() && moduleInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'module.label', default: 'Module'), moduleInstance.name])}"
                redirect(action: "show", id: moduleInstance.id)
            }
            else {
                render(view: "edit", model: [moduleInstance: moduleInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'module.label', default: 'Module'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def moduleInstance = Module.get(params.id)
        if (moduleInstance) {
            def applicationId = moduleInstance.application.id
            try {
                def name = moduleInstance.name
                moduleInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'module.label', default: 'Module'), name])}"
                redirect(controller: "application", action: "show", id: applicationId)
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'module.label', default: 'Module'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'module.label', default: 'Module'), params.id])}"
            redirect(action: "list")
        }
    }
}
