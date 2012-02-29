package carm

import org.apache.commons.lang.builder.EqualsBuilder

class SystemEnvironment {
    String name
    String description

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false)
        description(maxSize: 4000, nullable: true)
        system(nullable: false)
    }

    static belongsTo = [system: System]

    static mapping = {
        sort "name"
    }

    public String toString() {
        return name
    }

    boolean equals(def o) {
        if (is(o)) return true
        if (o instanceof SystemEnvironment) {
            return id == o.id
        }
        return false
    }
}
