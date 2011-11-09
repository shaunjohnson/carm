package carm

import carm.security.User

class SourceControlUser {
    String name
    String description

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false)
        description(maxSize: 4000, nullable: true)
        sourceControlServer(nullable: true)
        user(nullable: true)
    }

    static belongsTo = [sourceControlServer: SourceControlServer, user: User]

    static hasMany = [applicationRoles: ApplicationRole]

    public String toString() {
        return name
    }
}
