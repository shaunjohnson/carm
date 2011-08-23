package net.lmxm.carm.domains

class Project {
    String name
    String description

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false, unique: true)
        description(maxSize: 4000, nullable: true)
    }

    public String toString() {
        return "Project [name='$name']";
    }
}
