package carm

class Application {
    String name
    String description
    ApplicationType type
    SourceControlRepository sourceControlRepository
    String sourceControlPath
    System system
    
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false)
        description(maxSize: 4000, nullable: true)
        type(nullable: false)
        project(nullable: false)
        sourceControlRepository(nullable: true)
        sourceControlPath(maxSize: 200, nullable: true)
        system(nullable: true)
    }

    static belongsTo = [project: Project]

    static hasMany = [applicationRoles: ApplicationRole, modules: Module, releases: ApplicationRelease]

    public String toString() {
        return name
    }
}
