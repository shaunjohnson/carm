package carm.security

import org.apache.commons.lang.builder.HashCodeBuilder

/**
 * Application level security role.
 */
class Role {
    String authority
    
    Date dateCreated
    Date lastUpdated

    static mapping = {
        cache true
        sort "authority"
    }

    static constraints = {
        authority  blank: false, unique: true
    }

    String toString() {
        "Role : $authority"
    }
    
    boolean equals(other) {
        if (!(other instanceof Role)) {
            return false
        }

        other.authority == authority
    }

    int hashCode() {
        new HashCodeBuilder().append(authority).toHashCode()
    }
}
