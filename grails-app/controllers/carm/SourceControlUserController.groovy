package carm

import grails.plugins.springsecurity.Secured

class SourceControlUserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def sourceControlServerService
    def sourceControlUserService

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [sourceControlUserInstanceList: sourceControlUserService.list(params), sourceControlUserInstanceTotal: sourceControlUserService.count()]
    }

    @Secured(['ROLE_ADMIN'])
    def create = {
        def serverInstance = sourceControlServerService.get(params.server?.id?.toLong())
        if (!serverInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlServer.label', default: 'SourceControlServer'), params.server.id])}"
            redirect(action: "list")
        }
        else {
            def sourceControlUserInstance = new SourceControlUser()
            sourceControlUserInstance.properties = params
            return [sourceControlUserInstance: sourceControlUserInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def save = {
        def sourceControlUserInstance = sourceControlUserService.create(params)
        if (!sourceControlUserInstance.hasErrors()) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'sourceControlUser.label', default: 'SourceControlUser'), sourceControlUserInstance.name])}"
            redirect(action: "show", id: sourceControlUserInstance.id)
        }
        else {
            render(view: "create", model: [sourceControlUserInstance: sourceControlUserInstance])
        }
    }

    def show = {
        def sourceControlUserInstance = sourceControlUserService.get(params.id?.toLong())
        if (!sourceControlUserInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlUser.label', default: 'SourceControlUser'), params.id])}"
            redirect(action: "list")
        }
        else {
            [sourceControlUserInstance: sourceControlUserInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def edit = {
        def sourceControlUserInstance = sourceControlUserService.get(params.id?.toLong())
        if (!sourceControlUserInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlUser.label', default: 'SourceControlUser'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [sourceControlUserInstance: sourceControlUserInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def update = {
        def sourceControlUserInstance = sourceControlUserService.get(params.id?.toLong())
        if (sourceControlUserInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (sourceControlUserInstance.version > version) {
                    sourceControlUserInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'sourceControlUser.label', default: 'SourceControlUser')] as Object[], "Another user has updated this SourceControlUser while you were editing")
                    render(view: "edit", model: [sourceControlUserInstance: sourceControlUserInstance])
                    return
                }
            }
            sourceControlUserService.update(sourceControlUserInstance, params)
            if (!sourceControlUserInstance.hasErrors() && sourceControlUserInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'sourceControlUser.label', default: 'SourceControlUser'), sourceControlUserInstance.name])}"
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

    @Secured(['ROLE_ADMIN'])
    def delete = {
        def sourceControlUserInstance = sourceControlUserService.get(params.id?.toLong())
        if (sourceControlUserInstance) {
            try {
                def name = sourceControlUserInstance.name
                sourceControlUserService.delete(sourceControlUserInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'sourceControlUser.label', default: 'SourceControlUser'), name])}"
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
