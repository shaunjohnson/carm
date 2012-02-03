package carm

class CarmBreadcrumbTagLib {
    static namespace = 'bc'

    def administration = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "administration", text: "Administration", isFirst: isFirst)
    }

    def breadcrumbs = { attrs, body ->
        out << '<div class="breadcrumbs">' << body() << '</div>'
    }

    /**
     * Renders a link within the breadcrumbs prefixed by the default link separator. If isFirst is set to true then
     * the link separator is suppressed.
     *
     * attrs.isFirst - Flag indicating whether the link is the first. Default is  false.
     * attrs.controller - Name of controller
     * attrs.action - Name of action
     * attrs.title - Link title
     * attrs.text - Link text. Will be encoded as HTML.
     * attrs.uri - Link URI
     */
    def link = { attrs ->
        def isFirst = attrs.isFirst ?: false
        def controller = attrs.controller
        def action = attrs.action
        def id = attrs.id
        def title = attrs.title
        def text = attrs.text
        def uri = attrs.uri

        if (!isFirst) {
            out << '<span class="spacer"> &raquo; </span>'
        }

        out << g.link(uri: uri, controller: controller, action: action, id: id, title: title) { text.encodeAsHTML() }
    }

    /**
     * Renders breadcrumb text prefixed by the default link separator.
     *
     * attrs.code - Message code
     * attrs.code - Message args
     */
    def text = { attrs ->
        def code = attrs.code
        def args = attrs.args

        out << '<span class="spacer"> &raquo; </span>' << message(code: code, args: args)
    }

    def createLabel = { attrs ->
        def entityName = attrs.entityName
        def isFirst = attrs.isFirst
        out << text(code: "default.create.label", args: [entityName], isFirst: isFirst)
    }

    def editLabel = { attrs ->
        def entityName = attrs.entityName
        def isFirst = attrs.isFirst
        out << text(code: "default.edit.label", args: [entityName], isFirst: isFirst)
    }

    def listApplications = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "application", action: "list", title: "Show All Applications", text: "Applications",
                isFirst: isFirst)
    }

    def listApplicationDeployments = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "applicationDeployment", action: "list", title: "Show All Application Deployments",
                text: "Application Deployments", isFirst: isFirst)
    }

    def listApplicationReleases = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "applicationRelease", action: "list", title: "Show All Application Releases",
                text: "Application Releases", isFirst: isFirst)
    }

    def listApplicationReleaseTestStates = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "applicationReleaseTestState", action: "list", title: "Show All Application Release Test States",
                text: "Application Release Test States", isFirst: isFirst)
    }

    def listApplicationTypes = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "applicationType", action: "list", title: "Show All Application Types",
                text: "Application Types", isFirst: isFirst)
    }

    def listModules = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "module", action: "list", title: "Show All Modules", text: "Modules",
                isFirst: isFirst)
    }

    def listModuleTypes = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "moduleType", action: "list", title: "Show All Module Types", text: "Module Types",
                isFirst: isFirst)
    }

    def listProjects = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "project", action: "list", title: "Show All Projects", text: "Projects",
                isFirst: isFirst)
    }

    def listProjectCategories = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "projectCategory", action: "list", title: "Show All Project Categories",
                text: "Project Categories", isFirst: isFirst)
    }

    def listSourceControlRepositories = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "sourceControlRepository", action: "list", title: "Show All Source Control Repositories",
                text: "Source Control Repositories", isFirst: isFirst)
    }

    def listSourceControlRoles = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "sourceControlRole", action: "list", title: "Show All Source Control Roles",
                text: "Source Control Roles", isFirst: isFirst)
    }

    def listSourceControlServers = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "sourceControlServer", action: "list", title: "Show All Source Control Servers",
                text: "Source Control Servers", isFirst: isFirst)
    }

    def listSourceControlUsers = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "sourceControlUser", action: "list", title: "Show All Source Control Users",
                text: "Source Control Users", isFirst: isFirst)
    }

    def listSystems = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "system", action: "list", title: "Show All Systems",
                text: "Systems", isFirst: isFirst)
    }

    def listSystemComponents = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "systemComponent", action: "list", title: "Show All System Components",
                text: "System Components", isFirst: isFirst)
    }

    def listSystemEnvironments = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "systemEnvironment", action: "list", title: "Show All System Environments",
                text: "System Environments", isFirst: isFirst)
    }

    def showApplication = { attrs ->
        def application = attrs.application
        def isFirst = attrs.isFirst
        out << link(controller: "application", action: "show", title: "Show Application",
                text: application.name, id: application.id, isFirst: isFirst)
    }

    def showApplicationDeployment = { attrs ->
        def applicationDeployment = attrs.applicationDeployment
        def isFirst = attrs.isFirst
        def text = "Release ${applicationDeployment.applicationRelease.releaseNumber} to ${applicationDeployment.environment.name}"
        out << link(controller: "applicationDeployment", action: "show", title: "Show Application Deployment",
                text: text, id: applicationDeployment.id, isFirst: isFirst)
    }

    def showApplicationRelease = { attrs ->
        def applicationRelease = attrs.applicationRelease
        def isFirst = attrs.isFirst
        out << link(controller: "applicationRelease", action: "show", title: "Show Application Release",
                text: "Release ${applicationRelease.releaseNumber}", id: applicationRelease.id, isFirst: isFirst)
    }

    def showApplicationType = { attrs ->
        def applicationType = attrs.applicationType
        def isFirst = attrs.isFirst
        out << link(controller: "applicationType", action: "show", title: "Show Application Type",
                text: applicationType.name, id: applicationType.id, isFirst: isFirst)
    }

    def showModule = { attrs ->
        def module = attrs.module
        def isFirst = attrs.isFirst
        out << link(controller: "module", action: "show", title: "Show Module",
                text: module.name, id: module.id, isFirst: isFirst)
    }

    def showModuleType = { attrs ->
        def moduleType = attrs.moduleType
        def isFirst = attrs.isFirst
        out << link(controller: "moduleType", action: "show", title: "Show Module Type",
                text: moduleType.name, id: moduleType.id, isFirst: isFirst)
    }

    def showProject = { attrs ->
        def project = attrs.project
        def isFirst = attrs.isFirst
        out << link(controller: "project", action: "show", title: "Show Project",
                text: project.name, id: project.id, isFirst: isFirst)
    }

    def showProjectCategory = { attrs ->
        def projectCategory = attrs.projectCategory
        def isFirst = attrs.isFirst
        out << link(controller: "projectCategory", action: "show", title: "Show Project Category",
                text: projectCategory.name, id: projectCategory.id, isFirst: isFirst)
    }

    def showSourceControlRepository = { attrs ->
        def sourceControlRepository = attrs.sourceControlRepository
        def isFirst = attrs.isFirst
        out << link(controller: "sourceControlRepository", action: "show", title: "Show Source Control Repository",
                text: sourceControlRepository.name, id: sourceControlRepository.id, isFirst: isFirst)
    }

    def showSourceControlRole = { attrs ->
        def sourceControlRole = attrs.sourceControlRole
        def isFirst = attrs.isFirst
        out << link(controller: "sourceControlRole", action: "show", title: "Show Source Control Role",
                text: sourceControlRole.name, id: sourceControlRole.id, isFirst: isFirst)
    }

    def showSourceControlServer = { attrs ->
        def sourceControlServer = attrs.sourceControlServer
        def isFirst = attrs.isFirst
        out << link(controller: "sourceControlServer", action: "show", title: "Show Source Control Server",
                text: sourceControlServer.name, id: sourceControlServer.id, isFirst: isFirst)
    }

    def showSourceControlUser = { attrs ->
        def sourceControlUser = attrs.sourceControlUser
        def isFirst = attrs.isFirst
        out << link(controller: "sourceControlUser", action: "show", title: "Show Source Control User",
                text: sourceControlUser.name, id: sourceControlUser.id, isFirst: isFirst)
    }

    def showSystem = { attrs ->
        def system = attrs.system
        def isFirst = attrs.isFirst
        out << link(controller: "system", action: "show", title: "Show System",
                text: system.name, id: system.id, isFirst: isFirst)
    }

    def showSystemComponent = { attrs ->
        def systemComponent = attrs.systemComponent
        def isFirst = attrs.isFirst
        out << link(controller: "systemComponent", action: "show", title: "Show System Component",
                text: systemComponent.name, id: systemComponent.id, isFirst: isFirst)
    }

    def showSystemEnvironment = { attrs ->
        def systemEnvironment = attrs.systemEnvironment
        def isFirst = attrs.isFirst
        out << link(controller: "systemEnvironment", action: "show", title: "Show System Environment",
                text: systemEnvironment.name, id: systemEnvironment.id, isFirst: isFirst)
    }
}
