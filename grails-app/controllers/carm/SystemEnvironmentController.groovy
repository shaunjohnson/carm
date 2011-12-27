package carm

class SystemEnvironmentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [systemEnvironmentInstanceList: SystemEnvironment.list(params), systemEnvironmentInstanceTotal: SystemEnvironment.count()]
    }

    def create = {
        def systemInstance = System.get(params.system.id)
        if (!systemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'system.label', default: 'System'), params.system.id])}"
            redirect(action: "list")
        }
        else {
            def systemEnvironmentInstance = new SystemEnvironment()
            systemEnvironmentInstance.properties = params
            return [systemEnvironmentInstance: systemEnvironmentInstance]
        }
    }

    def save = {
        def systemEnvironmentInstance = new SystemEnvironment(params)
        def system = systemEnvironmentInstance.system
        system.addToEnvironments(systemEnvironmentInstance)

        if (systemEnvironmentInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), systemEnvironmentInstance.id])}"
            redirect(action: "show", id: systemEnvironmentInstance.id)
        }
        else {
            render(view: "create", model: [systemEnvironmentInstance: systemEnvironmentInstance])
        }
    }

    def show = {
        def systemEnvironmentInstance = SystemEnvironment.get(params.id)
        if (!systemEnvironmentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
            redirect(action: "list")
        }
        else {
            [systemEnvironmentInstance: systemEnvironmentInstance]
        }
    }

    def edit = {
        def systemEnvironmentInstance = SystemEnvironment.get(params.id)
        if (!systemEnvironmentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [systemEnvironmentInstance: systemEnvironmentInstance]
        }
    }

    def update = {
        def systemEnvironmentInstance = SystemEnvironment.get(params.id)
        if (systemEnvironmentInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (systemEnvironmentInstance.version > version) {
                    
                    systemEnvironmentInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'systemEnvironment.label', default: 'SystemEnvironment')] as Object[], "Another user has updated this SystemEnvironment while you were editing")
                    render(view: "edit", model: [systemEnvironmentInstance: systemEnvironmentInstance])
                    return
                }
            }
            systemEnvironmentInstance.properties = params
            if (!systemEnvironmentInstance.hasErrors() && systemEnvironmentInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), systemEnvironmentInstance.id])}"
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

    def delete = {
        def systemEnvironmentInstance = SystemEnvironment.get(params.id)
        if (systemEnvironmentInstance) {
            def systemId = systemEnvironmentInstance.system.id
            try {
                systemEnvironmentInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
                redirect(controller: "system", action: "show", id: systemId)
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
            redirect(action: "list")
        }
    }
}
