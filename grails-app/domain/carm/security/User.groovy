package carm.security

import carm.sourcecontrol.SourceControlUser
import org.apache.commons.lang.builder.HashCodeBuilder
import carm.Favorite

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
        username(blank: false, unique: true)
        password(blank: false)
        fullName(blank: false, nullable: false)
    }

    static mapping = {
        password column: '`password`'
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
        return username
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
