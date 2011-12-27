package carm

class SourceControlServerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [sourceControlServerInstanceList: SourceControlServer.list(params), sourceControlServerInstanceTotal: SourceControlServer.count()]
    }

    def create = {
        def sourceControlServerInstance = new SourceControlServer()
        sourceControlServerInstance.properties = params
        return [sourceControlServerInstance: sourceControlServerInstance]
    }

    def save = {
        def sourceControlServerInstance = new SourceControlServer(params)
        if (sourceControlServerInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'sourceControlServer.label', default: 'SourceControlServer'), sourceControlServerInstance.id])}"
            redirect(action: "show", id: sourceControlServerInstance.id)
        }
        else {
            render(view: "create", model: [sourceControlServerInstance: sourceControlServerInstance])
        }
    }

    def show = {
        def sourceControlServerInstance = SourceControlServer.get(params.id)
        if (!sourceControlServerInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlServer.label', default: 'SourceControlServer'), params.id])}"
            redirect(action: "list")
        }
        else {
            [sourceControlServerInstance: sourceControlServerInstance]
        }
    }

    def edit = {
        def sourceControlServerInstance = SourceControlServer.get(params.id)
        if (!sourceControlServerInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlServer.label', default: 'SourceControlServer'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [sourceControlServerInstance: sourceControlServerInstance]
        }
    }

    def update = {
        def sourceControlServerInstance = SourceControlServer.get(params.id)
        if (sourceControlServerInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (sourceControlServerInstance.version > version) {
                    
                    sourceControlServerInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'sourceControlServer.label', default: 'SourceControlServer')] as Object[], "Another user has updated this SourceControlServer while you were editing")
                    render(view: "edit", model: [sourceControlServerInstance: sourceControlServerInstance])
                    return
                }
            }
            sourceControlServerInstance.properties = params
            if (!sourceControlServerInstance.hasErrors() && sourceControlServerInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'sourceControlServer.label', default: 'SourceControlServer'), sourceControlServerInstance.id])}"
                redirect(action: "show", id: sourceControlServerInstance.id)
            }
            else {
                render(view: "edit", model: [sourceControlServerInstance: sourceControlServerInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlServer.label', default: 'SourceControlServer'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def sourceControlServerInstance = SourceControlServer.get(params.id)
        if (sourceControlServerInstance) {
            try {
                sourceControlServerInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'sourceControlServer.label', default: 'SourceControlServer'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'sourceControlServer.label', default: 'SourceControlServer'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlServer.label', default: 'SourceControlServer'), params.id])}"
            redirect(action: "list")
        }
    }
}
