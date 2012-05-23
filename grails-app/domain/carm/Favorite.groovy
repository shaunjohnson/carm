package carm

import carm.security.User
import carm.application.Application
import carm.project.Project

class Favorite {

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

    String getName() {
        if (application) {
            return application.name
        }
        else if (project) {
            return project.name
        }
        else {
            return null
        }
    }
}
