package carm

class SourceControlRepositoryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [sourceControlRepositoryInstanceList: SourceControlRepository.list(params), sourceControlRepositoryInstanceTotal: SourceControlRepository.count()]
    }

    def create = {
        def sourceControlRepositoryInstance = new SourceControlRepository()
        sourceControlRepositoryInstance.properties = params
        return [sourceControlRepositoryInstance: sourceControlRepositoryInstance]
    }

    def save = {
        def sourceControlRepositoryInstance = new SourceControlRepository(params)
        if (sourceControlRepositoryInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'sourceControlRepository.label', default: 'SourceControlRepository'), sourceControlRepositoryInstance.id])}"
            redirect(action: "show", id: sourceControlRepositoryInstance.id)
        }
        else {
            render(view: "create", model: [sourceControlRepositoryInstance: sourceControlRepositoryInstance])
        }
    }

    def show = {
        def sourceControlRepositoryInstance = SourceControlRepository.get(params.id)
        if (!sourceControlRepositoryInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlRepository.label', default: 'SourceControlRepository'), params.id])}"
            redirect(action: "list")
        }
        else {
            [sourceControlRepositoryInstance: sourceControlRepositoryInstance]
        }
    }

    def edit = {
        def sourceControlRepositoryInstance = SourceControlRepository.get(params.id)
        if (!sourceControlRepositoryInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlRepository.label', default: 'SourceControlRepository'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [sourceControlRepositoryInstance: sourceControlRepositoryInstance]
        }
    }

    def update = {
        def sourceControlRepositoryInstance = SourceControlRepository.get(params.id)
        if (sourceControlRepositoryInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (sourceControlRepositoryInstance.version > version) {
                    
                    sourceControlRepositoryInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'sourceControlRepository.label', default: 'SourceControlRepository')] as Object[], "Another user has updated this SourceControlRepository while you were editing")
                    render(view: "edit", model: [sourceControlRepositoryInstance: sourceControlRepositoryInstance])
                    return
                }
            }
            sourceControlRepositoryInstance.properties = params
            if (!sourceControlRepositoryInstance.hasErrors() && sourceControlRepositoryInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'sourceControlRepository.label', default: 'SourceControlRepository'), sourceControlRepositoryInstance.id])}"
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
        def sourceControlRepositoryInstance = SourceControlRepository.get(params.id)
        if (sourceControlRepositoryInstance) {
            try {
                sourceControlRepositoryInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'sourceControlRepository.label', default: 'SourceControlRepository'), params.id])}"
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
