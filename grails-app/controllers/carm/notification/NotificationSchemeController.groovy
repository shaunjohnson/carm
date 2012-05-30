package carm.notification

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import carm.exceptions.DomainInUseException
import carm.security.User

class NotificationSchemeController {
    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def notificationService
    def notificationSchemeService
    def userService

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
    def deleteNotification() {
        def notificationInstance = notificationSchemeService.getNotification(params.id)
        def notificationSchemeId = notificationInstance.notificationScheme.id
        if (notificationInstance) {
            NotificationEvent notificationEvent = notificationInstance.notificationEvent
            NotificationRecipientType recipientType = notificationInstance.recipientType

            try {
                notificationSchemeService.deleteNotification(notificationInstance)
                flash.message = message(code: 'notification.deleted.message', args: [
                        message(code: "carm.notification.NotificationRecipientType.${recipientType.name()}", default: recipientType.name()),
                        message(code: "carm.notification.NotificationEvent.${notificationEvent.name()}", default: notificationEvent.name())])

                redirect(action: "show", id: notificationSchemeId)
            }
            catch (DataIntegrityViolationException e) {
                flash.error = "${message(code: 'default.not.deleted.message', args: [message(code: 'notification.label', default: 'Notification'), params.id])}"
                redirect(action: "show", id: notificationSchemeId)
            }
            catch (DomainInUseException e) {
                flash.error = "${message(code: 'default.not.deleted.message', args: [message(code: 'notification.label', default: 'Notification'), params.id])}"
                redirect(action: "show", id: notificationSchemeId)
            }
        }
        else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'notification.label', default: 'Notification'), params.id])}"
            redirect(action: "list")
        }
    }

    private generateNotificationEventList() {
        NotificationEvent.values().collect {
            [
                    key: it.key,
                    name: message(code: "carm.notification.NotificationEvent.${it.key}", default: it.name())
            ]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def addNotification() {
        def notificationSchemeInstance = notificationSchemeService.get(params.id)
        if (notificationSchemeInstance) {
            def notificationInstance = new Notification()
            notificationInstance.notificationScheme = notificationSchemeInstance
            notificationInstance.properties = params

            [
                    notificationInstance: notificationInstance,
                    notificationSchemeInstance: notificationSchemeInstance,
                    notificationEventList: generateNotificationEventList(),
                    groupList: [],
                    userList: userService.listAll()
            ]
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
            flash.message = message(code: 'notification.created.message', args: [
                    message(code: "carm.notification.NotificationRecipientType.${notificationInstance.recipientType.name()}", default: notificationInstance.recipientType.name()),
                    message(code: "carm.notification.NotificationEvent.${notificationInstance.notificationEvent.name()}", default: notificationInstance.notificationEvent.name())])
            redirect(action: "show", id: notificationInstance.notificationScheme.id)
        }
        else {
            render(view: "addNotification", model: [
                    notificationInstance: notificationInstance,
                    notificationSchemeInstance: notificationInstance.notificationScheme,
                    notificationEventList: generateNotificationEventList(),
                    groupList: [],
                    userList: userService.listAll()
            ])
        }
    }

    @Secured(['ROLE_ADMIN'])
    def copy() {
        def existingNotificationScheme = notificationSchemeService.get(params.id)

        if (existingNotificationScheme) {
            def notificationSchemeInstance = notificationSchemeService.copy(existingNotificationScheme)

            if (!notificationSchemeInstance.hasErrors()) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'notificationScheme.label', default: 'NotificationScheme'), notificationSchemeInstance.name])}"
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
}
