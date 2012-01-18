package carm

import carm.enums.ApplicationReleaseState

class ApplicationRelease {
    String releaseNumber
    String changeLog
    String buildPath
    ApplicationReleaseState releaseState
    ApplicationReleaseTestState testState

    Date dateCreated
    Date lastUpdated

    static constraints = {
        releaseNumber(maxSize: 20, nullable: false, blank: false)
        changeLog(nullable: true)
        buildPath(maxSize:  100, nullable: true)
        releaseState(maxSize: 50, nullable: false, blank: false)
        application(nullable: false)
        testState(nullable: true)
    }

    static belongsTo = [application: Application]

    static hasMany = [moduleReleases: ModuleRelease, deployments: ApplicationDeployment, histories: ApplicationReleaseHistory]

    static mapping = {
        sort "dateCreated":"desc"
        changeLog type: 'text'
    }
}
