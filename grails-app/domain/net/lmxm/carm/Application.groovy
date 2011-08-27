package net.lmxm.carm

class Application {
    String name
    String description
    ApplicationType type
    Project project
    SourceControlServer sourceControlServer
    String sourceControlPath
    System system
    
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false)
        description(maxSize: 4000, nullable: true)
        type(nullable: false)
        project(nullable: false)
        sourceControlServer(nullable: true)
        sourceControlPath(maxSize: 200, nullable: true)
        system(nullable: true)
    }
    
    static hasMany = [applicationRoles: ApplicationRole, modules: Module]

    public String toString() {
        return name
    }
}
