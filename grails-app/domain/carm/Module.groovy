package carm

class Module {
    transient activityTraceService

    String name
    String description
    ModuleType type
    String deployInstructions
    
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false)
        description(maxSize: 4000, nullable: true)
        type(nullable: false)
        application(nullable: false)
        deployInstructions(nullable: true)
    }

    static belongsTo = [application: Application]

    static hasMany = [systemComponents: SystemComponent]

    static mapping = {
        sort "name"
        deployInstructions type: 'text'
    }

    public String toString() {
        return name
    }

    def afterInsert() {
        activityTraceService.moduleCreated(this)
    }

    def beforeDelete() {
        activityTraceService.moduleDeleted(this)
    }

    def afterUpdate() {
        activityTraceService.moduleUpdated(this)
    }
}
