package carm

import carm.project.Project
import org.springframework.security.acls.domain.BasePermission

class CarmSecurityTagLib {
    static namespace = "carmsec"

    def carmSecurityService

    /**
     * Renders the tag body if the current user is a project owner for the provided object.
     */
    def isProjectOwner = { attrs, body ->
        Project project = null

        if (attrs.project) {
            project = attrs.project
        }
        else if (attrs.application) {
            project = attrs.application?.project
        }
        else if (attrs.applicationDeployment) {
            project = attrs.applicationDeployment?.applicationRelease?.application?.project
        }
        else if (attrs.applicationRelease) {
            project = attrs.applicationRelease?.application?.project
        }
        else if (attrs.module) {
            project = attrs.module?.application?.project
        }

        if (project) {
            out << sec.permitted(className: 'carm.Project', id: project.id, permission: BasePermission.ADMINISTRATION) {
                body()
            }
        }
    }
}
