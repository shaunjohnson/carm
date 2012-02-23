package carm

class ApplicationDeployment {
    String deploymentInstructions
    Date requestedDeploymentDate
    Date completedDeploymentDate
    String deploymentState

    Date dateCreated
    Date lastUpdated

    static constraints = {
        applicationRelease(nullable: false)
        sysEnvironment(nullable: false)
        deploymentInstructions(nullable: true)
        requestedDeploymentDate(nullable: false)
        completedDeploymentDate(nullable: true)
        deploymentState(maxSize: 50, nullable: false, blank: false)
    }

    static belongsTo = [applicationRelease: ApplicationRelease, sysEnvironment: SystemEnvironment]

    static hasMany = [moduleDeployments: ModuleDeployment]

    static mapping = {
        deploymentInstructions type: 'text'
    }
}
