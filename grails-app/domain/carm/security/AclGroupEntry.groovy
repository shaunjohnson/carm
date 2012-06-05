package carm.security

class AclGroupEntry {
    Date dateCreated
    UserGroup userGroup

    static constraints = { }

    static mapping = {
        version: false
    }

    static belongsTo = [aclEntity: AclEntity]
}
