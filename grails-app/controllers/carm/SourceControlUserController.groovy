package carm

class SourceControlUserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [sourceControlUserInstanceList: SourceControlUser.list(params), sourceControlUserInstanceTotal: SourceControlUser.count()]
    }

    def create = {
        def serverInstance = SourceControlServer.get(params.sourceControlServer.id)
        if (!serverInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlServer.label', default: 'SourceControlServer'), params.sourceControlServer.id])}"
            redirect(action: "list")
        }
        else {
            def sourceControlUserInstance = new SourceControlUser()
            sourceControlUserInstance.properties = params
            return [sourceControlUserInstance: sourceControlUserInstance]
        }
    }

    def save = {
        def sourceControlUserInstance = new SourceControlUser(params)
        if (sourceControlUserInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'sourceControlUser.label', default: 'SourceControlUser'), sourceControlUserInstance.id])}"
            redirect(action: "show", id: sourceControlUserInstance.id)
        }
        else {
            render(view: "create", model: [sourceControlUserInstance: sourceControlUserInstance])
        }
    }

    def show = {
        def sourceControlUserInstance = SourceControlUser.get(params.id)
        if (!sourceControlUserInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlUser.label', default: 'SourceControlUser'), params.id])}"
            redirect(action: "list")
        }
        else {
            [sourceControlUserInstance: sourceControlUserInstance]
        }
    }

    def edit = {
        def sourceControlUserInstance = SourceControlUser.get(params.id)
        if (!sourceControlUserInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlUser.label', default: 'SourceControlUser'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [sourceControlUserInstance: sourceControlUserInstance]
        }
    }

    def update = {
        def sourceControlUserInstance = SourceControlUser.get(params.id)
        if (sourceControlUserInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (sourceControlUserInstance.version > version) {
                    
                    sourceControlUserInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'sourceControlUser.label', default: 'SourceControlUser')] as Object[], "Another user has updated this SourceControlUser while you were editing")
                    render(view: "edit", model: [sourceControlUserInstance: sourceControlUserInstance])
                    return
                }
            }
            sourceControlUserInstance.properties = params
            if (!sourceControlUserInstance.hasErrors() && sourceControlUserInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'sourceControlUser.label', default: 'SourceControlUser'), sourceControlUserInstance.id])}"
                redirect(action: "show", id: sourceControlUserInstance.id)
            }
            else {
                render(view: "edit", model: [sourceControlUserInstance: sourceControlUserInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlUser.label', default: 'SourceControlUser'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def sourceControlUserInstance = SourceControlUser.get(params.id)
        if (sourceControlUserInstance) {
            try {
                sourceControlUserInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'sourceControlUser.label', default: 'SourceControlUser'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'sourceControlUser.label', default: 'SourceControlUser'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlUser.label', default: 'SourceControlUser'), params.id])}"
            redirect(action: "list")
        }
    }
}
