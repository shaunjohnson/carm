package carm

import carm.security.User
import carm.application.Application

class Favorite {

    Application application

    static constraints = {
        application(nullable: false)
    }

    static belongsTo = [user: User]

    static mapping = {
        version false
    }
}
