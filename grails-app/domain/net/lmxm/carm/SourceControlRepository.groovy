package net.lmxm.carm

class SourceControlRepository {
    String name
    String description
    SourceControlServer server
    String path

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false)
        description(maxSize: 4000, nullable: true)
        server(nullable: false)
        path(blank: false)
    }

    public String toString() {
        return name
    }
}
