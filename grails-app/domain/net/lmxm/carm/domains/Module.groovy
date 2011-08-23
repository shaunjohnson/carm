package net.lmxm.carm.domains

class Module {
    String name
    String description
    ModuleType type
    Project project

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false)
        description(maxSize: 4000, nullable: true)
        type(nullable: false)
        project(nullable: false)
    }

    public String toString() {
        return "Module [name='$name', type='${type?.name}', project='${project?.name}']";
    }
}
