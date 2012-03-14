package carm.application

import carm.sourcecontrol.SourceControlRole
import carm.sourcecontrol.SourceControlUser
import org.apache.commons.lang.builder.HashCodeBuilder

class ApplicationRole {
    SourceControlRole role

    static belongsTo = [application: Application, sourceControlUser: SourceControlUser]

    static constraints = {
        role(nullable: false)
        application(nullable: false)
        sourceControlUser(nullable: false)
    }

    public String toString() {
        return "$sourceControlUser ($role)"
    }

    boolean equals(other) {
        if (!(other instanceof ApplicationRole)) {
            return false
        }

        other.role == role && other.application == application && other.sourceControlUser == sourceControlUser
    }

    int hashCode() {
        new HashCodeBuilder().append(role).append(application).append(sourceControlUser).toHashCode()
    }
}
