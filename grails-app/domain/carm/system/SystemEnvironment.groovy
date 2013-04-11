package carm.system

import org.apache.commons.lang.builder.HashCodeBuilder

/**
 * A system environment represents all servers and deployment environments into which applications are deployed.
 */
class SystemEnvironment {
    def activityTraceService

    String name
    String description
    List environments

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name minSize: 2, maxSize: 50, blank: false, unique: true
        description maxSize: 4000, nullable: true
    }

    static hasMany = [servers: SystemServer, environments: SystemDeploymentEnvironment]

    static mapping = {
        sort "name"
    }

    public String toString() {
        return name
    }

    boolean equals(other) {
        if (!(other instanceof SystemEnvironment)) {
            return false
        }

        other.name == name
    }

    int hashCode() {
        new HashCodeBuilder().append(name).toHashCode()
    }

    def afterInsert() {
        activityTraceService?.systemEnvironmentCreated(this)
    }

    def beforeDelete() {
        activityTraceService?.systemEnvironmentDeleted(this)
    }

    def afterUpdate() {
        activityTraceService?.systemEnvironmentUpdated(this)
    }
}
