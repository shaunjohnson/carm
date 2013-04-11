package carm.notification

import org.apache.commons.lang.builder.HashCodeBuilder

/**
 * A notification scheme is used to configure which notifications are sent for particular events and to which
 * receivers. An application may be configured to utilize a notification scheme to notify users of events that occur.
 */
class NotificationScheme {
    def activityTraceService

    String name
    String description

    static constraints = {
        name minSize: 2, maxSize: 50, blank: false
        description maxSize: 4000, nullable: true
    }

    static hasMany = [notifications: Notification]

    static mapping = {
        sort "name"
    }

    public String toString() {
        return name
    }

    boolean equals(other) {
        if (!(other instanceof NotificationScheme)) {
            return false
        }

        other.name == name
    }

    int hashCode() {
        new HashCodeBuilder().append(name).toHashCode()
    }

    def afterInsert() {
        activityTraceService?.notificationSchemeCreated(this)
    }

    def beforeDelete() {
        activityTraceService?.notificationSchemeDeleted(this)
    }

    def afterUpdate() {
        activityTraceService?.notificationSchemeUpdated(this)
    }
}
