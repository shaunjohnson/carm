package carm

import carm.activity.ActivityAction
import carm.application.Application
import carm.application.ApplicationType
import carm.deployment.ModuleDeploymentTestState
import carm.module.Module
import carm.module.ModuleType
import carm.project.Project
import carm.project.ProjectCategory
import carm.release.ApplicationRelease
import carm.release.ApplicationReleaseTestState
import carm.sourcecontrol.SourceControlRepository
import carm.sourcecontrol.SourceControlRole
import carm.sourcecontrol.SourceControlServer

import org.joda.time.DateTime
import org.joda.time.Period
import carm.system.SystemServer
import carm.system.SystemDeploymentEnvironment
import carm.system.SystemEnvironment

class CarmTagLib {

    static namespace = "carm"

    def activityTraceService
    def applicationReleaseService
    def applicationReleaseTestStateService
    def applicationTypeService
    def carmSecurityService
    def moduleDeploymentTestStateService
    def moduleTypeService
    def projectCategoryService
    def sourceControlRepositoryService
    def sourceControlRoleService
    def sourceControlServerService
    def systemServerService
    def systemDeploymentEnvironmentService
    def systemEnvironmentService

    /**
     * Outputs an ActivityTrace object.
     *
     * attrs.activity - ActivityTrace object being outputted
     */
    def activityMessage = { attrs ->
        def activity = attrs.activity
        def activityMessage = message(code: "activityTrace.${activity.objectType}.${activity.action}", args: [activity.objectName])

        def controller = null;
        def title = null
        if (activity.action != ActivityAction.DELETED) {
            if (activity.objectType == activityTraceService.APPLICATION_TYPE && Application.exists(activity.objectId)) {
                controller = "application"
                title = message(code: "showApplication.label", default: "Show Application")
            }
            else if (activity.objectType == activityTraceService.MODULE_TYPE && Module.exists(activity.objectId)) {
                controller = "module"
                title = message(code: "showModule.label", default: "Show Module")
            }
            else if (activity.objectType == activityTraceService.PROJECT_TYPE && Project.exists(activity.objectId)) {
                controller = "project"
                title = message(code: "showProject.label", default: "Show Project")
            }
            else if (activity.objectType == activityTraceService.SYSTEM_ENVIRONMENT_TYPE && SystemEnvironment.exists(activity.objectId)) {
                controller = "systemEnvironment"
                title = message(code: "showSystemEnvironment.label", default: "Show Environment")
            }
            else if (activity.objectType == activityTraceService.SYSTEM_SERVER_TYPE && SystemServer.exists(activity.objectId)) {
                controller = "systemServer"
                title = message(code: "showSystemServer.label", default: "Show SystemEnvironment Server")
            }
        }

        if (controller) {
            out << link(controller: controller, action: "show", id: "${activity.objectId}", title: title) {
                activityMessage.encodeAsHTML()
            }
        }
        else {
            out << activityMessage.encodeAsHTML()
        }
    }

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

    def formatApplicationDeploymentState = { attrs ->
        def deploymentState = attrs.deploymentState
        out << message(code: "carm.deployment.ApplicationDeploymentState.${deploymentState}", default: deploymentState.toString())
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

    def formatModuleDeploymentState = { attrs ->
        def deploymentState = attrs.deploymentState
        out << message(code: "carm.deployment.ModuleDeploymentState.${deploymentState}", default: deploymentState.toString())
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
        def isInUse = true

        if (domain instanceof ApplicationReleaseTestState) {
            isInUse = applicationReleaseTestStateService.isInUse(domain)
        }
        else if (domain instanceof ApplicationRelease) {
            isInUse = applicationReleaseService.isInUse(domain)
        }
        else if (domain instanceof ApplicationType) {
            isInUse = applicationTypeService.isInUse(domain)
        }
        else if (domain instanceof ModuleDeploymentTestState) {
            isInUse = moduleDeploymentTestStateService.isInUse(domain)
        }
        else if (domain instanceof ModuleType) {
            isInUse = moduleTypeService.isInUse(domain)
        }
        else if (domain instanceof ProjectCategory) {
            isInUse = projectCategoryService.isInUse(domain)
        }
        else if (domain instanceof SourceControlRepository) {
            isInUse = sourceControlRepositoryService.isInUse(domain)
        }
        else if (domain instanceof SourceControlRole) {
            isInUse = sourceControlRoleService.isInUse(domain)
        }
        else if (domain instanceof SourceControlServer) {
            isInUse = sourceControlServerService.isInUse(domain)
        }
        else if (domain instanceof SystemEnvironment) {
            isInUse = systemEnvironmentService.isInUse(domain)
        }
        else if (domain instanceof SystemServer) {
            isInUse = systemServerService.isInUse(domain)
        }
        else if (domain instanceof SystemDeploymentEnvironment) {
            isInUse = systemDeploymentEnvironmentService.isInUse(domain)
        }
        else {
            out << '<span style="color: red;">Domain is not supported by the ifNotInUse tag!</span>'
            return
        }

        if (!isInUse) {
            out << body()
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
    def showHideDetails = { attrs, body ->
        def sectionId = attrs.sectionId
        def entityName = attrs.entityName ?: ''

        out << '<div style="height: 1.5em;">'

        out << '<div style="float: left; width: 50%;">'
        out << render(template: "/common/showHideDetails", model: [sectionId: sectionId, entityName: entityName])
        out << '</div>'

        out << '<div style="float: left; text-align: right; width: 50%;">'
        out << body()
        out << '</div>'

        out << '<div class="clearning"></div>'

        out << '</div>'
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

    /**
     * Renders the tag body if the application release can be deployed
     */
    def isDeployable = { attrs, body ->
        ApplicationRelease applicationRelease = attrs.applicationRelease

        if (applicationReleaseService.isDeployable(applicationRelease)) {
            out << body()
        }
    }

    /**
     * Renders the tag body if the application release can be submitted
     */
    def isSubmittable = { attrs, body ->
        ApplicationRelease applicationRelease = attrs.applicationRelease

        if (applicationReleaseService.isSubmittable(applicationRelease)) {
            out << body()
        }
    }
}