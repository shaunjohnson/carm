package carm

class ApplicationDeployment {
    SystemEnvironment systemEnvironment
    String deploymentInstructions
    Date requestedDeploymentDate
    Date completedDeploymentDate
    String deploymentState

    Date dateCreated
    Date lastUpdated

    static constraints = {
        applicationRelease(nullable: false)
        systemEnvironment(nullable: false)
        deploymentInstructions(nullable: true)
        requestedDeploymentDate(nullable: false)
        completedDeploymentDate(nullable: true)
        deploymentState(maxSize: 50, nullable: false, blank: false)
    }

    static belongsTo = [applicationRelease: ApplicationRelease]

    static hasMany = [moduleDeployments: ModuleDeployment]

    static mapping = {
        deploymentInstructions type: 'text'
    }
}
