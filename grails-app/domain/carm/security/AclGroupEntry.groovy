package carm.security

class AclGroupEntry {
    Date dateCreated

    static constraints = { }

    static mapping = {
        version: false
    }

    static belongsTo = [aclEntity: AclEntity, userGroup: UserGroup]
}
