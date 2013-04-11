package carm.security

/**
 * An ACL entity, whose name value links a object, permission with users and/or groups. The value of the name field
 * contains both the type of access and the ID of the object to which this level of access is applied. The users/groups
 * associated with this entity are permitted this level of access to the object.
 */
class AclEntity {
    String name

    Date dateCreated

    static constraints = {
        name minSize: 2, maxSize: 50, blank: false, index: 'Name_Idx'
    }

    static hasMany = [userEntries: AclUserEntry, groupEntries: AclGroupEntry]
}
