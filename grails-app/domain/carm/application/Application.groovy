package carm.application

import carm.sourcecontrol.SourceControlRepository
import carm.project.Project
import carm.system.System
import carm.module.Module
import carm.release.ApplicationRelease

class Application {
    def activityTraceService

    String name
    String description
    ApplicationType type
    SourceControlRepository sourceControlRepository
    String sourceControlPath
    System system
    String buildInstructions
    String deployInstructions

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false, nullable: false)
        description(maxSize: 4000, nullable: true)
        type(nullable: false)
        project(nullable: false)
        sourceControlRepository(nullable: false)
        sourceControlPath(maxSize: 200, nullable: true)
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
