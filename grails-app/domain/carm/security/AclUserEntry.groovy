package carm.security

class AclUserEntry {
    Date dateCreated

    static constraints = { }

    static mapping = {
        version: false
    }

    static belongsTo = [aclEntity: AclEntity, user: User]
}
