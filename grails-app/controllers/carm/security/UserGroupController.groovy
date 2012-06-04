package carm.security

import carm.exceptions.DomainInUseException
import carm.system.SystemEnvironment
import grails.plugins.springsecurity.Secured
import org.apache.commons.lang.time.DateUtils
import org.springframework.dao.DataIntegrityViolationException

import java.text.SimpleDateFormat

class UserGroupController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def userGroupService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        [
                userGroupInstanceList: userGroupService.list(params),
                userGroupInstanceTotal: userGroupService.count()
        ]
    }

    @Secured(['ROLE_ADMIN'])
    def create() {
        def userGroupInstance = new UserGroup()
        userGroupInstance.properties = params

        [
                userGroupInstance: userGroupInstance
        ]
    }

    @Secured(['ROLE_ADMIN'])
    def save() {
        def userGroupInstance = userGroupService.create(params)
        if (!userGroupInstance.hasErrors()) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'userGroup.label', default: 'User Group'), userGroupInstance.name])}"
            redirect(action: "show", id: userGroupInstance.id)
        }
        else {
            render(view: "create", model: [userGroupInstance: userGroupInstance])
        }
    }

    def show() {
        def userGroupInstance = userGroupService.get(params.id)
        if (!userGroupInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'userGroup.label', default: 'User Group'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    userGroupInstance: userGroupInstance
            ]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def edit() {
        def userGroupInstance = userGroupService.get(params.id)
        if (!userGroupInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'userGroup.label', default: 'User Group'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    userGroupInstance: userGroupInstance
            ]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def update() {
        def userGroupInstance = userGroupService.get(params.id)
        if (userGroupInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (userGroupInstance.version > version) {
                    userGroupInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'userGroup.label', default: 'User Group')] as Object[], "Another user has updated this User Group while you were editing")
                    render(view: "edit", model: [userGroupInstance: userGroupInstance])
                    return
                }
            }
            userGroupService.update(userGroupInstance, params)
            if (!userGroupInstance.hasErrors() && userGroupInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'userGroup.label', default: 'User Group'), userGroupInstance.name])}"
                redirect(action: "show", id: userGroupInstance.id)
            }
            else {
                render(view: "edit", model: [userGroupInstance: userGroupInstance])
            }
        }
        else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'userGroup.label', default: 'User Group'), params.id])}"
            redirect(action: "list")
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete() {
        def systemEnvironmentInstance = userGroupService.get(params.id)
        if (systemEnvironmentInstance) {
            def name = systemEnvironmentInstance.name

            try {
                userGroupService.delete(systemEnvironmentInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'userGroup.label', default: 'User Group'), name])}"
                redirect(action: "list")
            }
            catch (DataIntegrityViolationException e) {
                flash.error = "${message(code: 'default.not.deleted.message', args: [message(code: 'userGroup.label', default: 'User Group'), name])}"
                redirect(action: "show", id: params.id)
            }
            catch (DomainInUseException e) {
                flash.error = "${message(code: 'default.not.deleted.message', args: [message(code: 'userGroup.label', default: 'User Group'), name])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'userGroup.label', default: 'User Group'), params.id])}"
            redirect(action: "list")
        }
    }
}
