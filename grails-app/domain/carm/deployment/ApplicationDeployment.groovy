package carm.deployment

import carm.release.ApplicationRelease

import carm.security.User
import org.apache.commons.lang.builder.HashCodeBuilder
import carm.system.SystemDeploymentEnvironment

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

    static belongsTo = [applicationRelease: ApplicationRelease, sysEnvironment: SystemDeploymentEnvironment]

    static hasMany = [moduleDeployments: ModuleDeployment]

    static mapping = {
        deploymentInstructions type: 'text'
    }

    String toString() {
        "Application Deployment : $applicationRelease to $sysEnvironment - $deploymentState"
    }

    boolean equals(other) {
        if (!(other instanceof ApplicationDeployment)) {
            return false
        }

        other.applicationRelease == applicationRelease && other.requestedDeploymentDate == requestedDeploymentDate &&
                other.completedDeploymentDate == completedDeploymentDate && other.deploymentState == deploymentState
    }

    int hashCode() {
        def builder = new HashCodeBuilder()
        builder.append(applicationRelease).append(requestedDeploymentDate)
        builder.append(completedDeploymentDate).append(deploymentState)
        builder.toHashCode()
    }
}
