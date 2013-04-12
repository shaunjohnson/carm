package carm.deployment

import carm.release.ApplicationRelease

import carm.security.User
import org.apache.commons.lang.builder.HashCodeBuilder
import carm.system.SystemDeploymentEnvironment

import static carm.utils.CarmStringUtils.removeEmptyParagraphs

/**
 * Represents a deployment of an application release to a system deployment environment.
 */
class ApplicationDeployment {
    def activityTraceService

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
        applicationRelease nullable: false
        deploymentEnvironment nullable: false
        deploymentInstructions nullable: true
        requestedDeploymentDate nullable: false
        completedDeploymentDate nullable: true
        deploymentState maxSize: 50, nullable: false, blank: false
        dateSubmitted nullable: true
        submittedBy nullable: true
        dateAssigned nullable: true
        assignedTo nullable: true
        dateTested nullable: true
        testedBy nullable: true

        moduleDeployments(validator: { val, obj ->
            val.size() > 0 && val.find { it.deploymentState == ModuleDeploymentState.DEPLOYED }
        })
    }

    static belongsTo = [applicationRelease: ApplicationRelease, deploymentEnvironment: SystemDeploymentEnvironment]

    static hasMany = [moduleDeployments: ModuleDeployment]

    static mapping = {
        deploymentInstructions type: 'text'
    }

    public void setDeploymentInstructions(String deploymentInstructions) {
        this.deploymentInstructions = removeEmptyParagraphs(deploymentInstructions)
    }

    String toString() {
        "Application Deployment : $applicationRelease to $deploymentEnvironment - $deploymentState"
    }

    boolean equals(other) {
        if (!(other instanceof ApplicationDeployment)) {
            return false
        }

        other.applicationRelease == applicationRelease && other.requestedDeploymentDate == requestedDeploymentDate &&
                other.completedDeploymentDate == completedDeploymentDate && other.deploymentState == deploymentState &&
                other.deploymentEnvironment == deploymentEnvironment
    }

    int hashCode() {
        def builder = new HashCodeBuilder()
        builder.append(applicationRelease).append(requestedDeploymentDate)
        builder.append(completedDeploymentDate).append(deploymentState).append(deploymentEnvironment)
        builder.toHashCode()
    }

    def afterInsert() {
        activityTraceService?.applicationDeploymentCreated(this)

        // TODO temporarily mark an application deployment as completed
        activityTraceService.applicationDeploymentCompleted(this)
    }

    def beforeDelete() {
        activityTraceService?.applicationDeploymentDeleted(this)
    }

    def afterUpdate() {
        activityTraceService?.applicationDeploymentUpdated(this)
    }
}
