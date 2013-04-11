package carm.project

import org.apache.commons.lang.builder.HashCodeBuilder

/**
 * Configuration type of project categories.
 */
class ProjectCategory {
    String name
    String description

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name minSize: 2, maxSize: 50, blank: false, unique: true
        description maxSize: 4000, nullable: true
    }

    static mapping = {
        sort "name"
    }

    public String toString() {
        return name
    }

    boolean equals(other) {
        if (!(other instanceof ProjectCategory)) {
            return false
        }

        other.name == name
    }

    int hashCode() {
        new HashCodeBuilder().append(name).toHashCode()
    }
}
