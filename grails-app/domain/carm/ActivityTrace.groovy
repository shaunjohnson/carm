package carm

import carm.enums.ActivityAction

class ActivityTrace {
    String oid
    String username
    ActivityAction action
    String objectName
    Long objectId
    String objectType
    Date dateOccurred

    static constraints = {
        oid(blank: false, maxSize: 100, minSize: 3, nullable: false)
        username(blank: false, maxSize: 100, minSize: 1, nullable: false)
        action(blank: false, maxSize: 100, minSize: 1, nullable: false)
        objectId(nullable: false)
        objectName(blank: false, maxSize: 100, minSize: 1, nullable: false)
        objectType(blank: false, maxSize: 100, minSize: 1, nullable: false)
        dateOccurred(nullable: false)
    }

    static mapping = {
        sort "dateOccurred":"desc"
        version false
    }
}
