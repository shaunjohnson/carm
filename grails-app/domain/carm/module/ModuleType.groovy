package carm.module

import org.apache.commons.lang.builder.HashCodeBuilder

/**
 * Configurable type of module.
 */
class ModuleType {
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
        if (!(other instanceof ModuleType)) {
            return false
        }

        other.name == name
    }

    int hashCode() {
        new HashCodeBuilder().append(name).toHashCode()
    }
}
