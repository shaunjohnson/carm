package carm

class SourceControlRoleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [sourceControlRoleInstanceList: SourceControlRole.list(params), sourceControlRoleInstanceTotal: SourceControlRole.count()]
    }

    def create = {
        def sourceControlRoleInstance = new SourceControlRole()
        sourceControlRoleInstance.properties = params
        return [sourceControlRoleInstance: sourceControlRoleInstance]
    }

    def save = {
        def sourceControlRoleInstance = new SourceControlRole(params)
        if (sourceControlRoleInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'sourceControlRole.label', default: 'SourceControlRole'), sourceControlRoleInstance.id])}"
            redirect(action: "show", id: sourceControlRoleInstance.id)
        }
        else {
            render(view: "create", model: [sourceControlRoleInstance: sourceControlRoleInstance])
        }
    }

    def show = {
        def sourceControlRoleInstance = SourceControlRole.get(params.id)
        if (!sourceControlRoleInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlRole.label', default: 'SourceControlRole'), params.id])}"
            redirect(action: "list")
        }
        else {
            [sourceControlRoleInstance: sourceControlRoleInstance]
        }
    }

    def edit = {
        def sourceControlRoleInstance = SourceControlRole.get(params.id)
        if (!sourceControlRoleInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlRole.label', default: 'SourceControlRole'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [sourceControlRoleInstance: sourceControlRoleInstance]
        }
    }

    def update = {
        def sourceControlRoleInstance = SourceControlRole.get(params.id)
        if (sourceControlRoleInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (sourceControlRoleInstance.version > version) {
                    
                    sourceControlRoleInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'sourceControlRole.label', default: 'SourceControlRole')] as Object[], "Another user has updated this SourceControlRole while you were editing")
                    render(view: "edit", model: [sourceControlRoleInstance: sourceControlRoleInstance])
                    return
                }
            }
            sourceControlRoleInstance.properties = params
            if (!sourceControlRoleInstance.hasErrors() && sourceControlRoleInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'sourceControlRole.label', default: 'SourceControlRole'), sourceControlRoleInstance.id])}"
                redirect(action: "show", id: sourceControlRoleInstance.id)
            }
            else {
                render(view: "edit", model: [sourceControlRoleInstance: sourceControlRoleInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlRole.label', default: 'SourceControlRole'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def sourceControlRoleInstance = SourceControlRole.get(params.id)
        if (sourceControlRoleInstance) {
            try {
                sourceControlRoleInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'sourceControlRole.label', default: 'SourceControlRole'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'sourceControlRole.label', default: 'SourceControlRole'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlRole.label', default: 'SourceControlRole'), params.id])}"
            redirect(action: "list")
        }
    }
}
