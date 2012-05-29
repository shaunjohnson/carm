package carm.notification

import carm.security.User
import org.apache.commons.lang.builder.HashCodeBuilder

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
        recipientType(nullable: false)
        user(nullable: true)
        emailAddress(maxSize: 255, nullable: true)
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
