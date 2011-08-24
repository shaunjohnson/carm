package net.lmxm.carm

class Application {
    String name
    String description
    //ProjectType type
    Project project
    SourceControlServer sourceControlServer
    String sourceControlPath
    
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false)
        description(maxSize: 4000, nullable: true)
        //type(nullable: false)
        project(nullable: false)
        sourceControlServer(nullable: true)
        sourceControlPath(maxSize: 200, nullable: true)
    }
    
    static hasMany = [modules: Module]

    public String toString() {
        return name
    }
}
