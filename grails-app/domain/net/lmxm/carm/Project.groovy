package net.lmxm.carm

class Project {
    String name
    String description
    
    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false, unique: true)
        description(maxSize: 4000, nullable: true)
    }
    
    static hasMany = [applications: Application]

    public String toString() {
        return name
    }
}
