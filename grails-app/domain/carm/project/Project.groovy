package carm.project

import carm.application.Application
import org.apache.commons.lang.builder.HashCodeBuilder

class Project {
    def activityTraceService

    String name
    String description
    ProjectCategory category
    
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false, unique: true)
        description(maxSize: 4000, nullable: true)
        category(nullable: false)
    }
    
    static hasMany = [applications: Application]

    static mapping = {
        sort "name"
    }

    public String toString() {
        return name
    }

    boolean equals(other) {
        if (!(other instanceof Project)) {
            return false
        }

        other.name == name && other.category == category
    }

    int hashCode() {
        new HashCodeBuilder().append(name).append(category).toHashCode()
    }

    def afterInsert() {
        activityTraceService?.projectCreated(this)
    }

    def beforeDelete() {
        activityTraceService?.projectDeleted(this)
    }

    def afterUpdate() {
        activityTraceService?.projectUpdated(this)
    }
}
