package carm.application

import carm.sourcecontrol.SourceControlRepository
import carm.project.Project
import carm.system.SystemEnvironment
import carm.module.Module
import carm.release.ApplicationRelease
import org.apache.commons.lang.builder.HashCodeBuilder

class Application {
    def activityTraceService

    String name
    String description
    ApplicationType type
    SourceControlRepository sourceControlRepository
    String sourceControlPath
    SystemEnvironment system
    String buildInstructions
    String deployInstructions

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false, nullable: false, unique: true)
        description(maxSize: 4000, nullable: true)
        type(nullable: false)
        project(nullable: false)
        sourceControlRepository(nullable: false)
        sourceControlPath(maxSize: 200, nullable: true, unique: 'sourceControlRepository')
        system(nullable: true)
        buildInstructions(nullable: true)
        deployInstructions(nullable: true)
    }

    static belongsTo = [project: Project]

    static hasMany = [applicationRoles: ApplicationRole, modules: Module, releases: ApplicationRelease]

    static mapping = {
        sort "name"
        buildInstructions type: 'text'
        deployInstructions type: 'text'
    }

    public String toString() {
        return name
    }

    boolean equals(other) {
        if (!(other instanceof Application)) {
            return false
        }

        other.name == name && other.type == type && other.project == project
    }

    int hashCode() {
        new HashCodeBuilder().append(name).append(type).append(project).toHashCode()
    }

    def afterInsert() {
        activityTraceService?.applicationCreated(this)
    }

    def beforeDelete() {
        activityTraceService?.applicationDeleted(this)
    }

    def afterUpdate() {
        activityTraceService?.applicationUpdated(this)
    }
}
