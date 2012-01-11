package carm

class SourceControlRepositoryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def sourceControlRepositoryService
    def sourceControlServerService

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [sourceControlRepositoryInstanceList: sourceControlRepositoryService.list(params), sourceControlRepositoryInstanceTotal: sourceControlRepositoryService.count()]
    }

    def create = {
        def serverInstance = sourceControlServerService.get(params.server?.id?.toLong())
        if (!serverInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlServer.label', default: 'SourceControlServer'), params.server.id])}"
            redirect(action: "list")
        }
        else {
            def sourceControlRepositoryInstance = new SourceControlRepository()
            sourceControlRepositoryInstance.properties = params
            return [sourceControlRepositoryInstance: sourceControlRepositoryInstance]
        }
    }

    def save = {
        def sourceControlRepositoryInstance = sourceControlRepositoryService.create(params)
        if (!sourceControlRepositoryInstance.hasErrors()) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'sourceControlRepository.label', default: 'SourceControlRepository'), sourceControlRepositoryInstance.name])}"
            redirect(action: "show", id: sourceControlRepositoryInstance.id)
        }
        else {
            render(view: "create", model: [sourceControlRepositoryInstance: sourceControlRepositoryInstance])
        }
    }

    def show = {
        def sourceControlRepositoryInstance = sourceControlRepositoryService.get(params.id?.toLong())
        if (!sourceControlRepositoryInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlRepository.label', default: 'SourceControlRepository'), params.id])}"
            redirect(action: "list")
        }
        else {
            [sourceControlRepositoryInstance: sourceControlRepositoryInstance]
        }
    }

    def edit = {
        def sourceControlRepositoryInstance = sourceControlRepositoryService.get(params.id?.toLong())
        if (!sourceControlRepositoryInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlRepository.label', default: 'SourceControlRepository'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [sourceControlRepositoryInstance: sourceControlRepositoryInstance]
        }
    }

    def update = {
        def sourceControlRepositoryInstance = sourceControlRepositoryService.get(params.id?.toLong())
        if (sourceControlRepositoryInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (sourceControlRepositoryInstance.version > version) {
                    
                    sourceControlRepositoryInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'sourceControlRepository.label', default: 'SourceControlRepository')] as Object[], "Another user has updated this SourceControlRepository while you were editing")
                    render(view: "edit", model: [sourceControlRepositoryInstance: sourceControlRepositoryInstance])
                    return
                }
            }
            sourceControlRepositoryService.update(sourceControlRepositoryInstance, params)
            if (!sourceControlRepositoryInstance.hasErrors() && sourceControlRepositoryInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'sourceControlRepository.label', default: 'SourceControlRepository'), sourceControlRepositoryInstance.name])}"
                redirect(action: "show", id: sourceControlRepositoryInstance.id)
            }
            else {
                render(view: "edit", model: [sourceControlRepositoryInstance: sourceControlRepositoryInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlRepository.label', default: 'SourceControlRepository'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def sourceControlRepositoryInstance = sourceControlRepositoryService.get(params.id?.toLong())
        if (sourceControlRepositoryInstance) {
            try {
                def name = sourceControlRepositoryInstance.name
                sourceControlRepositoryService.delete(sourceControlRepositoryInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'sourceControlRepository.label', default: 'SourceControlRepository'), name])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'sourceControlRepository.label', default: 'SourceControlRepository'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlRepository.label', default: 'SourceControlRepository'), params.id])}"
            redirect(action: "list")
        }
    }
}
