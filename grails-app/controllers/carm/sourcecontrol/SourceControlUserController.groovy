package carm.sourcecontrol

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import carm.security.User

class SourceControlUserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def sourceControlServerService
    def sourceControlUserService
    def userService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        [
                sourceControlUserInstanceList: sourceControlUserService.list(params),
                sourceControlUserInstanceTotal: sourceControlUserService.count()
        ]
    }

    @Secured(['ROLE_ADMIN'])
    def create() {
        def serverInstance = sourceControlServerService.get(params.server?.id)
        if (!serverInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlServer.label', default: 'SourceControlServer'), params.server?.id])}"
            redirect(action: "list")
        }
        else {
            def sourceControlUserInstance = new SourceControlUser()
            sourceControlUserInstance.properties = params

            [
                    sourceControlUserInstance: sourceControlUserInstance,
                    userList: userService.listAll()
            ]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def save() {
        def sourceControlUserInstance = sourceControlUserService.create(params)
        if (!sourceControlUserInstance.hasErrors()) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'sourceControlUser.label', default: 'SourceControlUser'), sourceControlUserInstance.name])}"
            redirect(controller: "sourceControlServer", action: "show", id: sourceControlUserInstance.server.id)
        }
        else {
            render(view: "create", model: [sourceControlUserInstance: sourceControlUserInstance])
        }
    }

    def show() {
        def sourceControlUserInstance = sourceControlUserService.get(params.id)
        if (!sourceControlUserInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlUser.label', default: 'SourceControlUser'), params.id])}"
            redirect(action: "list")
        }
        else {
            [sourceControlUserInstance: sourceControlUserInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def edit() {
        def sourceControlUserInstance = sourceControlUserService.get(params.id)
        if (!sourceControlUserInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlUser.label', default: 'SourceControlUser'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    sourceControlUserInstance: sourceControlUserInstance,
                    userList: userService.listAll()
            ]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def update() {
        def sourceControlUserInstance = sourceControlUserService.get(params.id)
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
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlUser.label', default: 'SourceControlUser'), params.id])}"
            redirect(action: "list")
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete() {
        def sourceControlUserInstance = sourceControlUserService.get(params.id)
        if (sourceControlUserInstance) {
            def name = sourceControlUserInstance.name
            def sourceControlServerId = sourceControlUserInstance.server.id

            try {
                sourceControlUserService.delete(sourceControlUserInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'sourceControlUser.label', default: 'SourceControlUser'), name])}"
                redirect(controller: "sourceControlServer", action: "show", id: sourceControlServerId)
            }
            catch (DataIntegrityViolationException e) {
                flash.error = "${message(code: 'default.not.deleted.message', args: [message(code: 'sourceControlUser.label', default: 'SourceControlUser'), name])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'sourceControlUser.label', default: 'SourceControlUser'), params.id])}"
            redirect(action: "list")
        }
    }
}
