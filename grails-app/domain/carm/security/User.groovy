package carm.security

import carm.sourcecontrol.SourceControlUser
import org.apache.commons.lang.builder.HashCodeBuilder
import carm.Favorite

/**
 * Application level user object.
 */
class User {
    transient springSecurityService

    String username
    String password
    String email
    String fullName
    boolean enabled
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    Date dateCreated
    Date lastUpdated

    static constraints = {
        username blank: false, unique: true, index: 'Name_Idx'
        password blank: false
        fullName blank: false, nullable: false
        email maxSize: 255
    }

    static mapping = {
        password column: '`password`'
        sort "fullName"
    }

    static hasMany = [sourceControlUsers: SourceControlUser, favorites: Favorite]

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role } as Set
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService.encodePassword(password)
    }

    public String toString() {
        return fullName + " (" + username + ")"
    }

    boolean equals(other) {
        if (!(other instanceof User)) {
            return false
        }

        other.username == username
    }

    int hashCode() {
        new HashCodeBuilder().append(username).toHashCode()
    }
}
