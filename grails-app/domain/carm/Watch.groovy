package carm

import carm.application.Application
import carm.security.User
import carm.project.Project

class Watch {

    Application application
    Project project

    static constraints = {
        application(nullable: true)
        project(nullable: true)
    }

    static belongsTo = [user: User]

    static mapping = {
        version false
    }
}
