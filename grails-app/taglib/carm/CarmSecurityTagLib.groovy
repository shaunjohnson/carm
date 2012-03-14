package carm

class CarmSecurityTagLib {
    static namespace = "carmsec"

    def projectService

    /**
     * Renders the tag body if the current user is a project owner for the provided object.
     */
    def isProjectOwner = { attrs, body ->
        def project = null

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

        if (projectService.isProjectOwner(project)) {
            out << body()
        }
    }
}
