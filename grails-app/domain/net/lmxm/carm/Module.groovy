package net.lmxm.carm

class Module {
    String name
    String description
    ModuleType type
    Application application
    
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false)
        description(maxSize: 4000, nullable: true)
        type(nullable: false)
        application(nullable: false)
    }

    public String toString() {
        return name
    }
}
