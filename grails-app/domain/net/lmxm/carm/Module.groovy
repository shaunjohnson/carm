package net.lmxm.carm

class Module {
    String name
    String description
    ModuleType type
    Project project
    SourceControlServer sourceControlServer
    String sourceControlPath

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false)
        description(maxSize: 4000, nullable: true)
        type(nullable: false)
        project(nullable: false)
        sourceControlServer(nullable: true)
        sourceControlPath(maxSize: 200, nullable: true)
    }

    public String toString() {
        return name
    }
}
