package net.lmxm.carm

import net.lmxm.carm.security.User

class SourceControlUser {
    String name
    String description
    SourceControlServer sourceControlServer
    User user

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false)
        description(maxSize: 4000, nullable: true)
        sourceControlServer(nullable: true)
        user(nullable: true)
    }

    static hasMany = [applicationRoles: ApplicationRole]

    public String toString() {
        return name
    }
}
