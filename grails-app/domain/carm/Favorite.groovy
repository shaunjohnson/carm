package carm

import carm.security.User
import carm.application.Application
import carm.project.Project

/**
 * A favorite is a way for users to bookmark projects or applications.
 */
class Favorite {
    Application application
    Project project

    static constraints = {
        application nullable: true
        project nullable: true
    }

    static belongsTo = [user: User]

    static mapping = {
        version false
    }

    String getName() {
        application?.name ?: project.name
    }
}
