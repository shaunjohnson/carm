package carm.sourcecontrol

import org.apache.commons.lang.builder.HashCodeBuilder

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

    boolean equals(other) {
        if (!(other instanceof SourceControlServer)) {
            return false
        }

        other.name == name && other.type == type && other.url == url
    }

    int hashCode() {
        new HashCodeBuilder().append(name).append(type).append(url).toHashCode()
    }
}
