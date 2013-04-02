package carm.release

import carm.application.Application
import carm.deployment.ApplicationDeployment
import carm.security.User
import org.apache.commons.lang.builder.HashCodeBuilder

class ApplicationRelease {
    def activityTraceService

    String releaseNumber
    String changeLog
    String buildInstructions
    String buildPath
    ApplicationReleaseState releaseState
    ApplicationReleaseTestState testState

    // Submitted
    Date dateSubmitted
    User submittedBy

    // Assigned
    Date dateAssigned
    User assignedTo

    // Tested
    Date dateTested
    User testedBy

    Date dateCreated
    Date lastUpdated

    static constraints = {
        releaseNumber(maxSize: 20, nullable: false, blank: false, unique: 'application')
        changeLog(nullable: true)
        buildInstructions(nullable: true)
        buildPath(maxSize: 100, nullable: true)
        releaseState(maxSize: 50, nullable: false, blank: false)
        application(nullable: false)
        testState(nullable: true)
        dateSubmitted(nullable: true)
        submittedBy(nullable: true)
        dateAssigned(nullable: true)
        assignedTo(nullable: true)
        dateTested(nullable: true)
        testedBy(nullable: true)
    }

    static belongsTo = [application: Application]

    static hasMany = [moduleReleases: ModuleRelease, deployments: ApplicationDeployment, histories: ApplicationReleaseHistory]

    static mapping = {
        sort "dateCreated": "desc"
        buildInstructions type: 'text'
        changeLog type: 'text'
    }

    public String toString() {
        return "${releaseNumber} : ${application}"
    }

    boolean equals(other) {
        if (!(other instanceof ApplicationRelease)) {
            return false
        }

        other.application == application && other.releaseNumber == releaseNumber
    }

    int hashCode() {
        new HashCodeBuilder().append(application).append(releaseNumber).toHashCode()
    }

    def afterInsert() {
        activityTraceService?.applicationReleaseCreated(this)

        // TODO temporarily mark an application release as completed
        activityTraceService?.applicationReleaseCompleted(this)
    }

    def beforeDelete() {
        activityTraceService?.applicationReleaseDeleted(this)
    }

    def afterUpdate() {
        activityTraceService?.applicationReleaseUpdated(this)
    }
}
