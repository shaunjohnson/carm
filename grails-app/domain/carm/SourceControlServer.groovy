package carm

import carm.enums.SourceControlServerType

class SourceControlServer {
    String name
    String description
    SourceControlServerType type
    String url

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(blank: false, minSize: 2, maxSize: 50, nullable: false, unique: true)
        description(maxSize: 4000, nullable: true)
        type(null: false)
        url(maxSize: 200, nullable: false, blank: false)
    }

    static hasMany = [repositories: SourceControlRepository, users: SourceControlUser]

    static mapping = {
        sort "name"
    }

    public String toString() {
        return name
    }
}
