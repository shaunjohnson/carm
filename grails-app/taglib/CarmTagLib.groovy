import carm.Project
import carm.Application
import carm.Module
import carm.SystemComponent
import carm.SystemEnvironment

class CarmTagLib {
    def carmSecurityService

    /**
     * Outputs a page header and breadcrumbs.
     *
     * attrs.domain - Domain object being displayed
     */
    def header = { attrs, body ->
        def domain = attrs.domain
        def spacer = '<span class="spacer"> > </span>'

        out << "<h1>${domain.name}</h1>"
        out << '<div class="breadcrumbs">'
        out << link(title: 'Home Page', uri: '/') { 'Home' }

        if (domain instanceof Application) {
            out << spacer << link(title: 'Show Project', id: domain.project.id, controller: 'project', action: 'show') {
                domain.project.name
            }
        }
        else if (domain instanceof Module) {
            out << spacer << link(title: 'Show Project', id: domain.application.project.id, controller: 'project', action: 'show') {
                domain.application.project.name
            }
            out << spacer << link(title: 'Show Application', id: domain.application.id, controller: 'application', action: 'show') {
                domain.application.name
            }
        }
        else if (domain instanceof SystemComponent || domain instanceof SystemEnvironment) {
            out << spacer << link(title: 'Show System', id: domain.system.id, controller: 'system', action: 'show') {
                domain.system.name
            }
        }

        out << spacer << domain.name << '</div>'
    }

    /**
     * Formats a SourceControlRepository as a link.
     *
     * attrs.sourceControlRepository - SourceControlRespository instance
     */
    def formatSourceControlRepository = { attrs, body ->
        def sourceControlRepository = attrs.sourceControlRepository

        if (sourceControlRepository) {
            def name = sourceControlRepository.server.name
            def url = "${sourceControlRepository.server.url}${sourceControlRepository.path}"

            out << "$name (<a href='$url' target='_blank'>$url</a>)"
        }
    }

    /**
     * Lists all users with specified permission for the provided domain object. Outputs a static message if there are
     * no users granted the permission to the provided domain object.
     *
     * attrs.domainObject - Domain object to lookup
     * attrs.permission - Permission used to locate permitted users
     */
    def listUsersWithPermission = { attrs ->
        def domainObject = attrs.domainObject
        def permission = attrs.permission

        def principals = carmSecurityService.findAllPrincipalsByDomainAndPermission(domainObject, permission)

        if (principals.size()) {
            out << "<ul>"
            principals.each { out << "<li>$it</li>" }
            out << "</ul>"
        }
        else {
            out << "No matching ACLs were found"
        }
    }

    /**
     * Creates a "move down" link.
     *
     * attrs.controller - Controller to invoke
     * attrs.action - Action to invoke
     * attrs.id - ID of domain object
     * attrs.params - Parameter map
     */
    def moveDown = { attrs ->
        def controller = attrs.controller
        def action = attrs.action
        def id = attrs.id
        def params = attrs.params

        out << link(class: 'reorderLink', title: 'Move Down', id: id,
                controller: controller, action: action, params: params) { '&#9660' }
    }

    /**
     * Creates a "move Up" link.
     *
     * attrs.controller - Controller to invoke
     * attrs.action - Action to invoke
     * attrs.id - ID of domain object
     * attrs.params - Parameter map
     */
    def moveUp = { attrs ->
        def controller = attrs.controller
        def action = attrs.action
        def id = attrs.id
        def params = attrs.params

        out << link(class: 'reorderLink', title: 'Move Up', id: id,
                controller: controller, action: action, params: params) { '&#9650' }
    }
}
