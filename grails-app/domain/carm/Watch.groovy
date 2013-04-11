package carm

import carm.application.Application
import carm.security.User
import carm.project.Project

/**
 * A watch provides a way for users to indicate they want to be informed of activity for an application or project.
 */
class Watch {
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
}
