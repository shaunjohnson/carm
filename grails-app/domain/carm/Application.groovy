package carm

class Application {
    String name
    String description
    ApplicationType type
    SourceControlRepository sourceControlRepository
    String sourceControlPath
    System system
    String deployInstructions
    
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false)
        description(maxSize: 4000, nullable: true)
        type(nullable: false)
        project(nullable: false)
        sourceControlRepository(nullable: false)
        sourceControlPath(maxSize: 200, nullable: true)
        system(nullable: true)
        deployInstructions(maxSize: 4000, nullable: true)
    }

    static belongsTo = [project: Project]

    static hasMany = [applicationRoles: ApplicationRole, modules: Module, releases: ApplicationRelease]

    static mapping = {
        sort "name"
    }

    public String toString() {
        return name
    }
}
