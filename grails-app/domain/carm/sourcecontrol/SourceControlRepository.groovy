package carm.sourcecontrol

import org.apache.commons.lang.builder.HashCodeBuilder

/**
 * A source control repository within a source control server.
 */
class SourceControlRepository {
    String name
    String description
    String path

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name minSize: 2, maxSize: 50, blank: false
        description maxSize: 4000, nullable: true
        server nullable: false
        path blank: false, maxSize: 200
    }

    static belongsTo = [server: SourceControlServer]

    static mapping = {
        sort "name"
    }

    public String toString() {
        return name
    }

    boolean equals(other) {
        if (!(other instanceof SourceControlRepository)) {
            return false
        }

        other.server == server && other.name == name && other.path == path
    }

    int hashCode() {
        new HashCodeBuilder().append(server).append(name).append(path).toHashCode()
    }
}
