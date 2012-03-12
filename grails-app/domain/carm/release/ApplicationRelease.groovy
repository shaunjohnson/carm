package carm.release

import carm.application.Application
import carm.deployment.ApplicationDeployment
import carm.security.User

class ApplicationRelease {
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
}
