package carm.deployment

import carm.release.ModuleRelease
import org.apache.commons.lang.builder.HashCodeBuilder

class ModuleDeployment {
    ModuleRelease moduleRelease
    String deploymentInstructions
    ModuleDeploymentState deploymentState
    ModuleDeploymentTestState testState

    Date dateCreated
    Date lastUpdated

    static constraints = {
        applicationDeployment(nullable: false)
        deploymentInstructions(nullable: true)
        moduleRelease(nullable: false)
        deploymentState(maxSize: 50, nullable: false, blank: false)
        testState(nullable: true)
    }

    static belongsTo = [applicationDeployment: ApplicationDeployment]
    
    static mapping = {
        deploymentInstructions type: 'text'
    }
    
    String toString() {
        "Module Deployment : $applicationDeployment - $deploymentState - $testState"
    }

    boolean equals(other) {
        if (!(other instanceof ModuleDeployment)) {
            return false
        }

        other.moduleRelease == moduleRelease && other.deploymentState == deploymentState && other.testState == testState
    }

    int hashCode() {
        new HashCodeBuilder().append(moduleRelease).append(deploymentState).append(testState).toHashCode()
    }
}
