package carm

class Module {
    String name
    String description
    ModuleType type
    SystemComponent systemComponent
    String deployInstructions
    
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false)
        description(maxSize: 4000, nullable: true)
        type(nullable: false)
        application(nullable: false)
        systemComponent(nullable: true)
        deployInstructions(maxSize: 4000, nullable: true)
    }

    static belongsTo = [application: Application]

    public String toString() {
        return name
    }
}
