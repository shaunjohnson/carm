package carm.system

import org.apache.commons.lang.builder.HashCodeBuilder

/**
 * A server which exists within each system deployment environment with a system.
 */
class SystemServer {
    def activityTraceService

    String name
    String description

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name minSize: 2, maxSize: 50, blank: false, unique: true
        description maxSize: 4000, nullable: true
        sysEnvironment nullable: false
    }

    static belongsTo = [sysEnvironment: SystemEnvironment]

    static mapping = {
        sort "name"
    }

    public String toString() {
        return name
    }

    boolean equals(other) {
        if (!(other instanceof SystemServer)) {
            return false
        }

        other.name == name && other.sysEnvironment == sysEnvironment
    }

    int hashCode() {
        new HashCodeBuilder().append(name).append(sysEnvironment).toHashCode()
    }

    def afterInsert() {
        activityTraceService?.systemServerCreated(this)
    }

    def beforeDelete() {
        activityTraceService?.systemServerDeleted(this)
    }

    def afterUpdate() {
        activityTraceService?.systemServerUpdated(this)
    }
}
