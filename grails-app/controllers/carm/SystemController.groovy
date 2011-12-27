package carm

class SystemController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [systemInstanceList: System.list(params), systemInstanceTotal: System.count()]
    }

    def create = {
        def systemInstance = new System()
        systemInstance.properties = params
        return [systemInstance: systemInstance]
    }

    def save = {
        def systemInstance = new System(params)
        if (systemInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'system.label', default: 'System'), systemInstance.id])}"
            redirect(action: "show", id: systemInstance.id)
        }
        else {
            render(view: "create", model: [systemInstance: systemInstance])
        }
    }

    def show = {
        def systemInstance = System.get(params.id)
        if (!systemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'system.label', default: 'System'), params.id])}"
            redirect(action: "list")
        }
        else {
            [systemInstance: systemInstance]
        }
    }

    def moveEnvDown = {
        def systemInstance = System.get(params.systemId)
        if (!systemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'system.label', default: 'System'), params.id])}"
            redirect(action: "list")
        }
        else {
            def index = params.index?.toInteger()
            def environments = systemInstance.environments

            if (index != null && (index + 1) < environments.size()) {
                def environment = environments[index]
                environments.remove(index)
                environments.add(index + 1, environment)
                systemInstance.save()
            }

            redirect(action: "show", id: systemInstance.id)
        }
    }

    def moveEnvUp = {
        def systemInstance = System.get(params.systemId)
        if (!systemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'system.label', default: 'System'), params.id])}"
            redirect(action: "list")
        }
        else {
            def index = params.index?.toInteger()
            def environments = systemInstance.environments

            if (index != null && index > 0) {
                def environment = environments[index]
                environments.remove(index)
                environments.add(index - 1, environment)
                systemInstance.save()
            }

            redirect(action: "show", id: systemInstance.id)
        }
    }

    def edit = {
        def systemInstance = System.get(params.id)
        if (!systemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'system.label', default: 'System'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [systemInstance: systemInstance]
        }
    }

    def update = {
        def systemInstance = System.get(params.id)
        if (systemInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (systemInstance.version > version) {

                    systemInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'system.label', default: 'System')] as Object[], "Another user has updated this System while you were editing")
                    render(view: "edit", model: [systemInstance: systemInstance])
                    return
                }
            }
            systemInstance.properties = params
            if (!systemInstance.hasErrors() && systemInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'system.label', default: 'System'), systemInstance.id])}"
                redirect(action: "show", id: systemInstance.id)
            }
            else {
                render(view: "edit", model: [systemInstance: systemInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'system.label', default: 'System'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def systemInstance = System.get(params.id)
        if (systemInstance) {
            try {
                systemInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'system.label', default: 'System'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'system.label', default: 'System'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'system.label', default: 'System'), params.id])}"
            redirect(action: "list")
        }
    }
}
