package carm.module

import carm.application.Application
import carm.system.SystemServer
import org.apache.commons.lang.builder.HashCodeBuilder

class Module {
    def activityTraceService

    String name
    String description
    ModuleType type
    String deployInstructions
    
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false)
        description(maxSize: 4000, nullable: true)
        type(nullable: false)
        application(nullable: false)
        deployInstructions(nullable: true)
    }

    static belongsTo = [application: Application]

    static hasMany = [systemComponents: SystemServer]

    static mapping = {
        sort "name"
        deployInstructions type: 'text'
    }

    public String toString() {
        return name
    }

    boolean equals(other) {
        if (!(other instanceof Module)) {
            return false
        }

        other.name == name && other.type == type
    }

    int hashCode() {
        new HashCodeBuilder().append(name).append(type).toHashCode()
    }

    def afterInsert() {
        activityTraceService?.moduleCreated(this)
    }

    def beforeDelete() {
        activityTraceService?.moduleDeleted(this)
    }

    def afterUpdate() {
        activityTraceService?.moduleUpdated(this)
    }
}
