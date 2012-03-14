package carm.security

import org.apache.commons.lang.builder.HashCodeBuilder

class UserRole implements Serializable {
    User user
    Role role
    
    Date dateCreated
    Date lastUpdated

    static mapping = {
        id composite: ['role', 'user']
        version false
    }
    
    String toString() {
        "User Role: user=$user, role=$role"
    }

    boolean equals(other) {
        if (!(other instanceof UserRole)) {
            return false
        }

        other.user?.id == user?.id && other.role?.id == role?.id
    }

    int hashCode() {
        def builder = new HashCodeBuilder()
        if (user) builder.append(user.id)
        if (role) builder.append(role.id)
        builder.toHashCode()
    }
}
