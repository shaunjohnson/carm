package carm.security

class AclEntity {
    String name

    Date dateCreated

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false, index: 'Name_Idx')
    }

    static hasMany = [userEntries: AclUserEntry, groupEntries: AclGroupEntry]
}
