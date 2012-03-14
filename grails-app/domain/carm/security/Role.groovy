package carm.security

import org.apache.commons.lang.builder.HashCodeBuilder

class Role {
    String authority
    
    Date dateCreated
    Date lastUpdated

    static mapping = {
        cache true
    }

    static constraints = {
        authority blank: false, unique: true
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
