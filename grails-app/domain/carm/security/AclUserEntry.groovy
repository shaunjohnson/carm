package carm.security

class AclUserEntry {
    Date dateCreated
    User user

    static constraints = { }

    static mapping = {
        version: false
    }

    static belongsTo = [aclEntity: AclEntity]
}
