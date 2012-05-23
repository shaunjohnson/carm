package carm.sourcecontrol

import carm.exceptions.DomainInUseException
import org.springframework.dao.DataIntegrityViolationException

class SourceControlRepositoryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def sourceControlRepositoryService
    def sourceControlServerService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        [
                sourceControlRepositoryInstanceList: sourceControlRepositoryService.list(params),
                sourceControlRepositoryInstanceTotal: sourceControlRepositoryService.count()
        ]
    }

    def create() {
        def serverInstance = sourceControlServerService.get(params.server?.id)
        if (!serverInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlServer.label', default: 'SourceControlServer'), params.server?.id])}"
            redirect(action: "list")
        }
        else {
            def sourceControlRepositoryInstance = new SourceControlRepository()
            sourceControlRepositoryInstance.properties = params
            return [sourceControlRepositoryInstance: sourceControlRepositoryInstance]
        }
    }

    def save() {
        def sourceControlRepositoryInstance = sourceControlRepositoryService.create(params)
        if (!sourceControlRepositoryInstance.hasErrors()) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'sourceControlRepository.label', default: 'SourceControlRepository'), sourceControlRepositoryInstance.name])}"
            redirect(controller: "sourceControlServer", action: "show", id: sourceControlRepositoryInstance.server.id)
        }
        else {
            render(view: "create", model: [sourceControlRepositoryInstance: sourceControlRepositoryInstance])
        }
    }

    def show() {
        def sourceControlRepositoryInstance = sourceControlRepositoryService.get(params.id)
        if (!sourceControlRepositoryInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlRepository.label', default: 'SourceControlRepository'), params.id])}"
            redirect(action: "list")
        }
        else {
            [sourceControlRepositoryInstance: sourceControlRepositoryInstance]
        }
    }

    def edit() {
        def sourceControlRepositoryInstance = sourceControlRepositoryService.get(params.id)
        if (!sourceControlRepositoryInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlRepository.label', default: 'SourceControlRepository'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [sourceControlRepositoryInstance: sourceControlRepositoryInstance]
        }
    }

    def update() {
        def sourceControlRepositoryInstance = sourceControlRepositoryService.get(params.id)
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
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlRepository.label', default: 'SourceControlRepository'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete() {
        def sourceControlRepositoryInstance = sourceControlRepositoryService.get(params.id)
        if (sourceControlRepositoryInstance) {
            def name = sourceControlRepositoryInstance.name
            def sourceControlServerId = sourceControlRepositoryInstance.server.id

            try {
                sourceControlRepositoryService.delete(sourceControlRepositoryInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'sourceControlRepository.label', default: 'SourceControlRepository'), name])}"
                redirect(controller: "sourceControlServer", action: "show", id: sourceControlServerId)
            }
            catch (DataIntegrityViolationException e) {
                flash.error = "${message(code: 'default.not.deleted.message', args: [message(code: 'sourceControlRepository.label', default: 'SourceControlRepository'), name])}"
                redirect(action: "show", id: params.id)
            }
            catch (DomainInUseException e) {
                flash.error = "${message(code: 'default.not.deleted.message', args: [message(code: 'sourceControlRepository.label', default: 'SourceControlRepository'), name])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlRepository.label', default: 'SourceControlRepository'), params.id])}"
            redirect(action: "list")
        }
    }
}
