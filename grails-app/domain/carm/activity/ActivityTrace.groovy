package carm.activity

import org.joda.time.DateTime
import org.apache.commons.lang.builder.HashCodeBuilder

/**
 * An activity trace represents a single activity record for some object.
 */
class ActivityTrace {
    String oid
    String username
    ActivityAction action
    String objectName
    Long objectId
    String objectType
    DateTime dateOccurred

    static constraints = {
        oid blank: false, maxSize: 100, minSize: 3, nullable: false
        username blank: false, maxSize: 100, minSize: 1, nullable: false
        action blank: false, maxSize: 100, minSize: 1, nullable: false
        objectId nullable: false
        objectName blank: false, maxSize: 100, minSize: 1, nullable: false
        objectType blank: false, maxSize: 100, minSize: 1, nullable: false
        dateOccurred nullable: false
    }

    static mapping = {
        version false
        sort "dateOccurred": "desc"
    }

    String toString() {
        "Activity Trace : $username $action $objectName with ID $objectId on $dateOccurred"
    }

    boolean equals(other) {
        if (!(other instanceof ActivityTrace)) {
            return false
        }

        other.oid == oid && other.username == username && other.action == action && other.objectName == objectName &&
                other.objectId == objectId && other.objectType == objectType
    }

    int hashCode() {
        new HashCodeBuilder().append(oid).append(username).append(action).append(objectName).append(objectId).append(objectType).toHashCode()
    }
}
