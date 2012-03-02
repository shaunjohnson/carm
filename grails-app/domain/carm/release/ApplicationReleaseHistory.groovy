package carm.release

class ApplicationReleaseHistory {
    String summary
    String comments
    String username
    
    Date dateCreated
    Date lastUpdated
    
    static constraints = {
        applicationRelease(nullable: false)
        summary(maxSize: 255, nullable: false, blank: false)
        comments(nullable: true)
        username(maxSize: 255, nullable: false, blank: false)
    }

    static belongsTo = [applicationRelease: ApplicationRelease]

    static mapping = {
        sort "dateCreated":"desc"
        comments type: 'text'
    }
}
