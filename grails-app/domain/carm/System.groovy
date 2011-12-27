package carm

class System {
    String name
    String description
    List environments

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false, unique: true)
        description(maxSize: 4000, nullable: true)
    }

    static hasMany = [components: SystemComponent, environments: SystemEnvironment]

    static mapping = {
        sort "name"
    }

    public String toString() {
        return name
    }
}
