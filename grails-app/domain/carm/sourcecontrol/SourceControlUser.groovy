package carm.sourcecontrol

import carm.security.User
import carm.application.ApplicationRole
import org.apache.commons.lang.builder.HashCodeBuilder

class SourceControlUser {
    String name
    String description

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false, unique: true)
        description(maxSize: 4000, nullable: true)
        server(nullable: true)
        user(nullable: true)
    }

    static belongsTo = [server: SourceControlServer, user: User]

    static hasMany = [applicationRoles: ApplicationRole]

    static mapping = {
        sort "name"
    }

    public String toString() {
        return name
    }

    boolean equals(other) {
        if (!(other instanceof SourceControlUser)) {
            return false
        }

        other.name == name && other.server == server && other.user == user
    }

    int hashCode() {
        new HashCodeBuilder().append(name).append(server).append(user).toHashCode()
    }
}
