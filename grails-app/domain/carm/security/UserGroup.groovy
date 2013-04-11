package carm.security

/**
 * Application level user group.
 */
class UserGroup {
    String name
    String description

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name maxSize: 50, nullable: false, unique: true
        description maxSize: 4000, nullable: true
    }

    static hasMany = [users: User]

    String toString() {
        name
    }
}
