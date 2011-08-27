package net.lmxm.carm

class ApplicationRole {
    SourceControlRole role

    static belongsTo = [application: Application, sourceControlUser: SourceControlUser]

    static constraints = {
        role(nullable: false)
        application(nullable: false)
        sourceControlUser(nullable: false)
    }

    public String toString() {
        return "$sourceControlUser : $role : $application"
    }
}
