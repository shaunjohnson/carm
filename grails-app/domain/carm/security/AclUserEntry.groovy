package carm.security

/**
 * Links a user to an ACL entity (and object).
 */
class AclUserEntry {
    Date dateCreated
    User user

    static constraints = {}

    static mapping = {
        version: false
    }

    static belongsTo = [aclEntity: AclEntity]
}
