package carm.notification

import carm.security.User
import org.apache.commons.lang.builder.HashCodeBuilder
import org.apache.commons.lang.StringUtils

class Notification {
    def activityTraceService

    NotificationEvent notificationEvent
    NotificationRecipientType recipientType
    User user

    // TODO User group notification is not possible until user groups are implemented
    // UserGroup userGroup

    String emailAddress

    static constraints = {
        notificationEvent(nullable: false)
        recipientType(nullable: false, validator: { val, obj ->
            if (val == NotificationRecipientType.CURRENT_USER || val == NotificationRecipientType.PROJECT_ADMINISTRATORS ||
                    val == NotificationRecipientType.APPLICATION_WATCHERS || val == NotificationRecipientType.PROJECT_WATCHERS) {
                return !(obj.notificationScheme.notifications.find { it.recipientType == val && it.notificationEvent == obj.notificationEvent })
            }
        })

        user(nullable: true, unique: ['notificationScheme', 'notificationEvent'], validator: { val, obj ->
            if (obj.recipientType == NotificationRecipientType.USER) {
                return val != null
            }

            return true
        })

//        userGroup(validator: { val, obj ->
//            if (obj.recipientType == NotificationRecipientType.GROUP) {
//                return val != null
//            }
//
//            return true
//        })

        emailAddress(maxSize: 255, nullable: true, unique: ['notificationScheme', 'notificationEvent'], validator: { val, obj ->
            if (obj.recipientType == NotificationRecipientType.EMAIL_ADDRESS) {
                return StringUtils.isNotBlank(val)
            }

            return true
        })
    }

    static belongsTo = [notificationScheme: NotificationScheme]

    public String toString() {
        return notificationEvent.name() + " " + recipientType
    }

    boolean equals(other) {
        if (!(other instanceof Notification)) {
            return false
        }

        other.notificationEvent == notificationEvent && other.recipientType == recipientType &&
                other.user == user && other.emailAddress == emailAddress
    }

    int hashCode() {
        new HashCodeBuilder().append(notificationEvent).append(recipientType).append(user).append(emailAddress).toHashCode()
    }

    def afterInsert() {
        activityTraceService?.notificationCreated(this)
    }

    def beforeDelete() {
        activityTraceService?.notificationDeleted(this)
    }

    def afterUpdate() {
        activityTraceService?.notificationUpdated(this)
    }
}
