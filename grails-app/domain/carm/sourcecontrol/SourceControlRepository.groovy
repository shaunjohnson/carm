package carm.sourcecontrol

class SourceControlRepository {
    String name
    String description
    String path

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name(minSize: 2, maxSize: 50, blank: false)
        description(maxSize: 4000, nullable: true)
        server(nullable: false)
        path(blank: false, maxSize: 200)
    }

    static belongsTo = [server: SourceControlServer]

    static mapping = {
        sort "name"
    }

    public String toString() {
        return name
    }
}
