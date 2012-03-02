package carm

class ModuleDeployment {
    ModuleRelease moduleRelease
    String deploymentInstructions
    String deploymentState
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
}
