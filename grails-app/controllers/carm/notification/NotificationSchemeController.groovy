package carm.notification

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import carm.exceptions.DomainInUseException

class NotificationSchemeController {
    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def notificationService
    def notificationSchemeService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        [
                notificationSchemeInstanceList: notificationSchemeService.list(params),
                notificationSchemeInstanceTotal: notificationSchemeService.count()
        ]
    }

    @Secured(['ROLE_ADMIN'])
    def create() {
        def notificationSchemeInstance = new NotificationScheme()
        notificationSchemeInstance.properties = params
        return [notificationSchemeInstance: notificationSchemeInstance]
    }

    @Secured(['ROLE_ADMIN'])
    def save() {
        def notificationSchemeInstance = notificationSchemeService.create(params)
        if (!notificationSchemeInstance.hasErrors()) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'notificationScheme.label', default: 'NotificationScheme'), notificationSchemeInstance.name])}"
            redirect(action: "show", id: notificationSchemeInstance.id)
        }
        else {
            render(view: "create", model: [notificationSchemeInstance: notificationSchemeInstance])
        }
    }

    def show() {
        def notificationSchemeInstance = notificationSchemeService.get(params.id)
        if (!notificationSchemeInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'notificationScheme.label', default: 'NotificationScheme'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    notificationSchemeInstance: notificationSchemeInstance,
                    notificationsGroupsByEvent: notificationService.findAllNotificationsBySchemeGroupedByEvent(notificationSchemeInstance)
            ]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def edit() {
        def notificationSchemeInstance = notificationSchemeService.get(params.id)
        if (!notificationSchemeInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'notificationScheme.label', default: 'NotificationScheme'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [notificationSchemeInstance: notificationSchemeInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def update() {
        def notificationSchemeInstance = notificationSchemeService.get(params.id)
        if (notificationSchemeInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (notificationSchemeInstance.version > version) {

                    notificationSchemeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'notificationScheme.label', default: 'NotificationScheme')] as Object[], "Another user has updated this NotificationScheme while you were editing")
                    render(view: "edit", model: [notificationSchemeInstance: notificationSchemeInstance])
                    return
                }
            }
            notificationSchemeService.update(notificationSchemeInstance, params)
            if (!notificationSchemeInstance.hasErrors() && notificationSchemeInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'notificationScheme.label', default: 'NotificationScheme'), notificationSchemeInstance.name])}"
                redirect(action: "show", id: notificationSchemeInstance.id)
            }
            else {
                render(view: "edit", model: [notificationSchemeInstance: notificationSchemeInstance])
            }
        }
        else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'notificationScheme.label', default: 'NotificationScheme'), params.id])}"
            redirect(action: "list")
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete() {
        def notificationSchemeInstance = notificationSchemeService.get(params.id)
        if (notificationSchemeInstance) {
            def name = notificationSchemeInstance.name

            try {
                notificationSchemeService.delete(notificationSchemeInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'notificationScheme.label', default: 'NotificationScheme'), name])}"
                redirect(action: "list")
            }
            catch (DataIntegrityViolationException e) {
                flash.error = "${message(code: 'default.not.deleted.message', args: [message(code: 'notificationScheme.label', default: 'NotificationScheme'), name])}"
                redirect(action: "show", id: params.id)
            }
            catch (DomainInUseException e) {
                flash.error = "${message(code: 'default.not.deleted.message', args: [message(code: 'notificationScheme.label', default: 'NotificationScheme'), name])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'notificationScheme.label', default: 'NotificationScheme'), params.id])}"
            redirect(action: "list")
        }
    }

    @Secured(['ROLE_ADMIN'])
    def addNotification() {
        def notificationSchemeInstance = notificationSchemeService.get(params.id)
        if (notificationSchemeInstance) {
            def notificationInstance = new Notification()
            notificationInstance.notificationScheme = notificationSchemeInstance
            notificationInstance.properties = params
            return [notificationInstance: notificationInstance]
        }
        else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'notificationScheme.label', default: 'NotificationScheme'), params.id])}"
            redirect(action: "list")
        }
    }

    @Secured(['ROLE_ADMIN'])
    def saveNotification() {
        def notificationInstance = notificationSchemeService.createNotification(params)
        if (!notificationInstance.hasErrors()) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'notificationScheme.label', default: 'NotificationScheme'), notificationInstance.id])}"
            redirect(action: "show", id: notificationInstance.notificationScheme.id)
        }
        else {
            render(view: "addNotification", model: [notificationInstance: notificationInstance])
        }
    }
}
