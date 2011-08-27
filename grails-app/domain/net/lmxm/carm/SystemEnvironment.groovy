package net.lmxm.carm

class SystemEnvironment {
    String name
    String description
    System system

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false, unique: true)
        description(maxSize: 4000, nullable: true)
        system(nullable: false)
    }

    public String toString() {
        return name
    }
}
