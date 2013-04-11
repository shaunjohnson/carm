package carm.system

import org.apache.commons.lang.builder.HashCodeBuilder

/**
 * A system deployment environment represents one of multiple stages of deployment within a system. For example,
 * development, test and production are typical deployment environments within a system.
 */
class SystemDeploymentEnvironment {
    def activityTraceService

    String name
    String description

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name minSize: 2, maxSize: 50, blank: false
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
        if (!(other instanceof SystemDeploymentEnvironment)) {
            return false
        }

        other.name == name && other.sysEnvironment == sysEnvironment
    }

    int hashCode() {
        new HashCodeBuilder().append(name).append(sysEnvironment).toHashCode()
    }

    def afterInsert() {
        activityTraceService?.systemDeploymentEnvironmentCreated(this)
    }

    def beforeDelete() {
        activityTraceService?.systemDeploymentEnvironmentDeleted(this)
    }

    def afterUpdate() {
        activityTraceService?.systemDeploymentEnvironmentUpdated(this)
    }
}
