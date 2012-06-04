package carm.security

class UserGroup {
    String name
    String description

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(maxSize: 50, nullable: false)
        description(maxSize: 4000, nullable: true)
    }

    static hasMany = [users: User]
}
