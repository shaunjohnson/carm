package carm.deployment

import org.apache.commons.lang.builder.HashCodeBuilder

class ModuleDeploymentTestState implements Comparable<ModuleDeploymentTestState> {
    String name
    String description

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false, unique: true)
        description(maxSize: 4000, nullable: true)
    }

    static mapping = {
        sort "name"
    }

    public String toString() {
        return name
    }

    int compareTo(ModuleDeploymentTestState o) {
        return name.compareTo(o?.name)
    }

    boolean equals(other) {
        if (!(other instanceof ModuleDeploymentTestState)) {
            return false
        }

        other.name == name
    }

    int hashCode() {
        new HashCodeBuilder().append(name).toHashCode()
    }
}
