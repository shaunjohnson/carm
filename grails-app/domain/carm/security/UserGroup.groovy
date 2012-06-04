package carm.security

class UserGroup {
    String name

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(maxSize: 50, nullable: false)
    }

    static hasMany = [users: User]
}
