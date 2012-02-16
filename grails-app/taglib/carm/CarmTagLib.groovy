package carm

import carm.Project
import carm.Application
import carm.ApplicationRelease
import carm.ApplicationReleaseTestState
import carm.Module
import carm.SystemComponent

import carm.SourceControlRepository
import carm.System
import carm.ProjectCategory
import carm.ModuleType
import carm.ApplicationType
import carm.SourceControlServer
import carm.SourceControlRole
import carm.ApplicationRole
import org.joda.time.DateTime
import org.joda.time.Period

class CarmTagLib {

    static namespace = "carm"

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
     * Renders a page header using the provided input. If the action is currently set to show then display the beanname,
     * otherwise show the label text defined for the action.
     *
     * attrs.action - Action used for determining the label text
     * attrs.entityName - Name of the domain/bean class
     * attrs.beanName - Name of the bean displayed on the show view
     */
    def pageHeaderLabel = { attrs ->
        def action = attrs.action
        def entityName = attrs.entityName
        def beanName = attrs.beanName

        if (action == 'show') {
            out << "<h1>$beanName</h1>"
        }
        else if (action == 'listReleases') {
            def headerText = message(code: "default.${action}.label", args: [beanName])
            out << "<h1>$headerText</h1>"
        }
        else {
            def headerText = message(code: "default.${action}.label", args: [entityName])
            out << "<h1>$headerText</h1>"
        }
    }

    /**
     * Generates a link button using the same parameters that are used with links.
     */
    def button = { attrs, body ->
        out << "<button class='button' onclick='window.location=\"" << createLink(attrs) << "\"'>" << body() << "</button>"
    }

    /**
     * Formats a Date using a short format. This ia a helper taglib that makes it possible to control the format of
     * dates (not date and time) from a central location.
     */
    def formatDateOnly = { attrs ->
        def date = attrs.date

        out << '<span style="white-space: nowrap;">' << formatDate(type: "date", style: "medium", date: date) << '</span>'
    }

    /**
     * Formats period of time from the provided DateTime to now.
     */
    def formatDateTimePeriod = { attrs ->
        DateTime value = attrs.value
        def styleClass = attrs.class

        out << '<span class="' << styleClass << '" title="' << joda.format(value: value) << '">'
        out << joda.formatPeriod(value: new Period(value, new DateTime()))
        out << '&nbsp;' << message(code: "ago.label", default: "ago")
        out << "</span>"
    }

    /**
     * Formats a SourceControlRepository as a link.
     *
     * attrs.application - Application instance
     * attrs.repository - Source Control Repository instance
     * attrs.server - Source Control Server instance
     */
    def formatSourceControl = { attrs, body ->
        def application = attrs.application
        def repository = attrs.repository
        def server = attrs.server

        if (application) {
            def sourceControlRepository = application.sourceControlRepository
            def url = "${sourceControlRepository.server.url}${sourceControlRepository.path}${application.sourceControlPath ?: ''}"
            out << "<a href='$url' target='_blank'>$url</a>"
        }
        else if (repository) {
            out << link(controller: "sourceControlRepository", action: "show", id: repository.id) { repository.name.encodeAsHTML() }

            def url = "${repository.server.url}${repository.path}"
            out << " (<a href='$url' target='_blank'>$url</a>)"
        }
        else if (server) {
            out << link(controller: "sourceControlServer", action: "show", id: server.id) { server.name.encodeAsHTML() }

            def url = "${server.url}"
            out << " (<a href='$url' target='_blank'>$url</a>)"
        }
        else {
            out << '<span style="color: red;">You must specify application, repository or server for formatSourceControl!</span>'
        }
    }

    /**
     * Renders the body of the tag if the provided domain is not in use.
     *
     * attrs.domain - Domain to test for use
     */
    def ifNotInUse = { attrs, body ->
        def domain = attrs.domain

        if (domain instanceof ApplicationReleaseTestState) {
            if (ApplicationRelease.findAllByTestState(domain).size() == 0) {
                out << body()
            }
        }
        else if (domain instanceof ApplicationType) {
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
        else if (domain instanceof SourceControlRole) {
            if (ApplicationRole.findAllByRole(domain).size() == 0) {
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
            out << '<span style="color: red;">Domain is not supported by the ifNotInUse tag!</span>'
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
            out << message(code: "projectDoesNotHaveAnyOwners.message", default: "This project does not have any owners.")
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

    /**
     * Creates a "Show/Hide Details" link and hides details by default (on page load).
     *
     * attrs.sectionId - Client ID of the section to show/hide details within (optional)
     * attrs.entityName - Name of the entity being shown (optional)
     */
    def showHideDetails = { attrs ->
        def sectionId = attrs.sectionId
        def entityName = attrs.entityName ?: ''

        out << render(template: "/common/showHideDetails", model: [sectionId: sectionId, entityName: entityName])
    }

    private outputAttrs(attrs) {
        def writer = getOut()
        attrs.each { k, v ->
            writer << " $k=\"${v.encodeAsHTML()}\""
        }
    }

    def label = { attrs, body ->
        out << '<label" ' << outputAttrs(attrs) << '>' << body()

        if (attrs.required) {
            out << '<img src="' << resource(dir: 'images', file: 'required_star.gif') << '" alt="required" />'
        }

        out << '</label>'
    }

    def requiredLabelMessage = { attrs, body ->
        out << '<p class="requiredFieldsMessage">' << message(code: 'requiredFields.message')
        out << '<img src="' << resource(dir: 'images', file: 'required_star.gif')
        out << '" alt="required" /></p>'
    }

    /**
     * Renders the provided text String preserving newlines by converting them to HTML breaks.
     *
     * attrs.value - Text string to render
     */
    def plainText = { attrs ->
        def value = attrs.value

        out << value?.encodeAsHTML()?.replace('\n', '<br/>\n')
    }
}