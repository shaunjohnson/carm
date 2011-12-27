import carm.Project
import carm.Application
import carm.Module
import carm.SystemComponent
import carm.SystemEnvironment
import carm.SourceControlRepository
import carm.System
import carm.ProjectCategory
import carm.ModuleType
import carm.ApplicationType
import carm.SourceControlServer

class CarmTagLib {
    def carmSecurityService

    /**
     * Outputs a page header and breadcrumbs.
     *
     * attrs.domain - Domain object being displayed
     */
    def header = { attrs, body ->
        def domain = attrs.domain
        def pageName = attrs.pageName

        out << render(template: "/common/header", model: [domain: domain, pageName: pageName])
    }

    /**
     * Renders a link within the page header prefixed by the default link separator. If isFirst is set to true then
     * the link separator is suppressed.
     *
     * attrs.isFirst - Flag indicating whether the link is the first. Default is  false.
     * attrs.controller - Name of controller
     * attrs.action - Name of action
     * attrs.title - Link title
     * attrs.text - Link text. Will be encoded as HTML.
     * attrs.uri - Link URI
     */
    def headerLink = { attrs ->
        def isFirst = attrs.isFirst ?: false
        def controller = attrs.controller
        def action = attrs.action
        def id = attrs.id
        def title = attrs.title
        def text = attrs.text
        def uri = attrs.uri

        if (!isFirst) {
            out << '<span class="spacer"> &gt; </span>'
        }

        out << link(uri: uri, controller: controller, action: action, id: id, title: title) { text.encodeAsHTML() }
    }

    /**
     * Renders header text prefixed by the default link separator.
     *
     * attrs.code - Message code
     * attrs.code - Message args
     */
    def headerText = { attrs ->
        def code = attrs.code
        def args = attrs.args

        out << '<span class="spacer"> &gt; </span>' << message(code: code, args: args)
    }

    /**
     * Renders a page header using the provided input. If the action is currently set to show then display the beanname,
     * otherwise show the label text defined for the action.
     *
     * attrs.action - Action used for determining the label text
     * attrs.entityName - Name of the domain/bean class
     * attrs.beanName - Name of the bean displayed on the show view
     */
    def pageHeader = { attrs ->
        def action = attrs.action
        def entityName = attrs.entityName
        def beanName = attrs.beanName

        if (action == 'show') {
            out << "<h1>$beanName</h1>"
        }
        else {
            def headerText = message(code: "default.${action}.label", args: [entityName])
            out << "<h1>$headerText</h1>"
        }
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
     * Renders the body of the tag if the provided domain is not in use.
     *
     * attrs.domain - Domain to test for use
     */
    def ifNotInUse = { attrs, body ->
        def domain = attrs.domain
        
        if (domain instanceof ApplicationType) {
            if (Application.findAllByType(domain).size() == 0) {
                out << body()
            }
        }
        else if (domain instanceof ModuleType) {
            if (Module.findAllByType(domain).size() == 0) {
                out << body()
            }
        }
        else if (domain instanceof ProjectCategory) {
            if (Project.findAllByCategory(domain).size() == 0) {
                out << body()
            }
        }
        else if (domain instanceof SourceControlRepository) {
            if (Application.findAllBySourceControlRepository(domain).size() == 0) {
                out << body()
            }
        }
        else if (domain instanceof SourceControlServer) {
            def applicationCount = Application.executeQuery('select count(a) from Application a where a.sourceControlRepository.server = ?', [domain])[0]

            if (applicationCount == 0) {
                out << body()
            }
        }
        else if (domain instanceof System) {
            if (Application.findAllBySystem(domain).size() == 0) {
                out << body()
            }
        }
        else if (domain instanceof SystemComponent) {
//            if (Module.findAllByComponent(domain).size() == 0) {
                out << body()
//            }
        }
        else {
            out << '<span style="color: red;">Domain is not supported!</span>'
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
