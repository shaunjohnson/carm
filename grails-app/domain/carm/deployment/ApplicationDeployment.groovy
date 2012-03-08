package carm.deployment

import carm.release.ApplicationRelease
import carm.system.SystemEnvironment
import carm.security.User

class ApplicationDeployment {
    String deploymentInstructions
    Date requestedDeploymentDate
    Date completedDeploymentDate
    ApplicationDeploymentState deploymentState

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
        applicationRelease(nullable: false)
        sysEnvironment(nullable: false)
        deploymentInstructions(nullable: true)
        requestedDeploymentDate(nullable: false)
        completedDeploymentDate(nullable: true)
        deploymentState(maxSize: 50, nullable: false, blank: false)
        dateSubmitted(nullable: true)
        submittedBy(nullable: true)
        dateAssigned(nullable: true)
        assignedTo(nullable: true)
        dateTested(nullable: true)
        testedBy(nullable: true)
    }

    static belongsTo = [applicationRelease: ApplicationRelease, sysEnvironment: SystemEnvironment]

    static hasMany = [moduleDeployments: ModuleDeployment]

    static mapping = {
        deploymentInstructions type: 'text'
    }
}
