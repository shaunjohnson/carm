package net.lmxm.carm

class ApplicationRelease {
    String releaseNumber
    String description

    Date dateCreated
    Date lastUpdated

    static constraints = {
        releaseNumber(maxSize: 20, nullable: false, blank: false)
        description(maxSize: 4000, nullable: true)
        application(nullable: true)
    }

    static belongsTo = [application: Application]
}
