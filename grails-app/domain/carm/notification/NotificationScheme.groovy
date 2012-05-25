package carm.notification

import org.apache.commons.lang.builder.HashCodeBuilder

class NotificationScheme {
    def activityTraceService

    String name
    String description

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false)
        description(maxSize: 4000, nullable: true)
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
