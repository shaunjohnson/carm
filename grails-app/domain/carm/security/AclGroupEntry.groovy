package carm.security

/**
 * Links a user group to an ACL entity (and object).
 */
class AclGroupEntry {
    Date dateCreated
    UserGroup userGroup

    static constraints = {}

    static mapping = {
        version: false
    }

    static belongsTo = [aclEntity: AclEntity]
}
