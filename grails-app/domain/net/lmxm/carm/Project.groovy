package net.lmxm.carm

class Project {
    String name
    String description

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false, unique: true)
        description(maxSize: 4000, nullable: true)
    }
    
    static hasMany = [modules:Module]

    public String toString() {
        return name
    }
}
