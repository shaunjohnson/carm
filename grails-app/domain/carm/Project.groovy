package carm

class Project {
    def activityTraceService

    String name
    String description
    ProjectCategory category
    
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false, unique: true)
        description(maxSize: 4000, nullable: true)
        category(nullable: false)
    }
    
    static hasMany = [applications: Application]

    static mapping = {
        sort "name"
    }

    public String toString() {
        return name
    }

    def afterInsert() {
        activityTraceService.projectCreated(this)
    }

    def beforeDelete() {
        activityTraceService.projectDeleted(this)
    }

    def afterUpdate() {
        activityTraceService.projectUpdated(this)
    }
}
