package carm.system

import org.apache.commons.lang.builder.HashCodeBuilder

class System {
    def activityTraceService

    String name
    String description
    List environments

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false, unique: true)
        description(maxSize: 4000, nullable: true)
    }

    static hasMany = [components: SystemComponent, environments: SystemEnvironment]

    static mapping = {
        sort "name"
    }

    public String toString() {
        return name
    }

    boolean equals(other) {
        if (!(other instanceof System)) {
            return false
        }

        other.name == name
    }

    int hashCode() {
        new HashCodeBuilder().append(name).toHashCode()
    }

    def afterInsert() {
        activityTraceService?.systemCreated(this)
    }

    def beforeDelete() {
        activityTraceService?.systemDeleted(this)
    }

    def afterUpdate() {
        activityTraceService?.systemUpdated(this)
    }
}
