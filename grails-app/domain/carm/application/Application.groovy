package carm.application

import carm.sourcecontrol.SourceControlRepository
import carm.project.Project
import carm.system.SystemEnvironment
import carm.module.Module
import carm.release.ApplicationRelease
import org.apache.commons.lang.builder.HashCodeBuilder
import carm.notification.NotificationScheme

import static carm.utils.CarmStringUtils.removeEmptyParagraphs

/**
 * Application represents a single application that is tracked within CARM.
 */
class Application {
    def activityTraceService

    String name
    String description
    ApplicationType type
    SourceControlRepository sourceControlRepository
    String sourceControlPath
    SystemEnvironment sysEnvironment
    String buildInstructions
    String binariesPath
    String deployInstructions
    NotificationScheme notificationScheme

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name minSize: 2, maxSize: 50, blank: false, nullable: false, unique: true
        description maxSize: 4000, nullable: true
        type nullable: false
        project nullable: false
        sourceControlRepository nullable: false
        sourceControlPath maxSize: 200, nullable: true, unique: 'sourceControlRepository'
        sysEnvironment nullable: true
        buildInstructions nullable: true
        binariesPath maxSize:  200
        deployInstructions nullable: true
        notificationScheme nullable: false
    }

    static belongsTo = [project: Project]

    static hasMany = [modules: Module, releases: ApplicationRelease]

    static mapping = {
        sort "name"
        buildInstructions type: 'text'
        deployInstructions type: 'text'
    }

    public void setBuildInstructions(String buildInstructions) {
        this.buildInstructions = removeEmptyParagraphs(buildInstructions)
    }

    public void setDeployInstructions(String deploymentInstructions) {
        this.deployInstructions = removeEmptyParagraphs(deploymentInstructions)
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
