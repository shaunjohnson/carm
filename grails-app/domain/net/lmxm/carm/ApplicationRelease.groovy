package net.lmxm.carm

class ApplicationRelease {
    Application application
    String description

    Date dateCreated
    Date lastUpdated

    static constraints = {
        application(nullable: true)
        description(maxSize: 4000, nullable: true)
    }
}
