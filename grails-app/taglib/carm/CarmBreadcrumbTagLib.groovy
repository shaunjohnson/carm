package carm

class CarmBreadcrumbTagLib {
    static namespace = 'bc'

    /**
     * Renders an Adminsitration link.
     */
    def administration = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "administration", isFirst: isFirst,
                text: message(code: "administration.label"))
    }

    /**
     * Renders a breadcrumbs section.
     */
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

    /**
     * Renders a create link.
     */
    def createLabel = { attrs ->
        def entityName = attrs.entityName
        def isFirst = attrs.isFirst
        out << text(code: "default.create.label", args: [entityName], isFirst: isFirst)
    }

    /**
     * Renders an edit label.
     */
    def editLabel = { attrs ->
        def entityName = attrs.entityName
        def isFirst = attrs.isFirst
        out << text(code: "default.edit.label", args: [entityName], isFirst: isFirst)
    }

    /**
     * Renders a basic label.
     */
    def label = { attrs ->
        def code = attrs.code
        def args = attrs.args
        def isFirst = attrs.isFirst
        out << text(code: code, args: args, isFirst: isFirst)
    }

    /**
     * Renders a list applications link.
     */
    def listApplications = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "application", action: "list", isFirst: isFirst,
                title: message(code: "showAllApplications.label"),
                text: message(code: "applications.label"))
    }

    /**
     * Renders a list application deployments link.
     */
    def listApplicationDeployments = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "applicationDeployment", action: "list", isFirst: isFirst,
                title: message(code: "showAllApplicationDeployments.label"),
                text: message(code: "applicationDeployments.label"))
    }

    /**
     * Renders a list application releases link.
     */
    def listApplicationReleases = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "applicationRelease", action: "list", isFirst: isFirst,
                title: message(code: "showAllApplicationReleases.label"),
                text: message(code: "applicationReleases.label"))
    }

    /**
     * Renders a list application release test states link.
     */
    def listApplicationReleaseTestStates = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "applicationReleaseTestState", action: "list", isFirst: isFirst,
                title: message(code: "showAllApplicationReleaseTestStates.label"),
                text: message(code: "applicationReleaseTestStates.label"))
    }

    /**
     * Renders a list application types link.
     */
    def listApplicationTypes = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "applicationType", action: "list", isFirst: isFirst,
                title: message(code: "showAllApplicationTypes.label"),
                text: message(code: "applicationTypes.label"))
    }

    /**
     * Renders a list modules link.
     */
    def listModules = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "module", action: "list", isFirst: isFirst,
                title: message(code: "showAllModules.label"),
                text: message(code: "modules.label"))
    }

    /**
     * Renders a list module deployment test states link.
     */
    def listModuleDeploymentTestStates = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "moduleDeploymentTestState", action: "list", isFirst: isFirst,
                title: message(code: "showAllModuleDeploymentTestStates.label"),
                text: message(code: "moduleDeploymentTestState.label"))
    }

    /**
     * Renders a list module types link.
     */
    def listModuleTypes = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "moduleType", action: "list", isFirst: isFirst,
                title: message(code: "showAllModuleTypes.label"),
                text: message(code: "moduleTypes.label"))
    }

    /**
     * Renders a list projects link.
     */
    def listProjects = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "project", action: "list", isFirst: isFirst,
                title: message(code: "showAllProjects.label"),
                text: message(code: "projects.label"))
    }

    /**
     * Renders a list project categories link.
     */
    def listProjectCategories = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "projectCategory", action: "list", isFirst: isFirst,
                title: message(code: "showAllProjectCategories.label"),
                text: message(code: "projectCategories.label"))
    }

    /**
     * Renders a list source control repositories link.
     */
    def listSourceControlRepositories = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "sourceControlRepository", action: "list", isFirst: isFirst,
                title: message(code: "showAllSourceControlRepositories.label"),
                text: message(code: "sourceControlRepositories.label"))
    }

    /**
     * Renders a list source control roles link.
     */
    def listSourceControlRoles = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "sourceControlRole", action: "list", isFirst: isFirst,
                title: message(code: "showAllSourceControlRoles.label"),
                text: message(code: "sourceControlRoles.label"))
    }

    /**
     * Renders a list source control servers link.
     */
    def listSourceControlServers = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "sourceControlServer", action: "list", isFirst: isFirst,
                title: message(code: "showAllSourceControlServers.label"),
                text: message(code: "sourceControlServers.label"))
    }

    /**
     * Renders a list source control users link.
     */
    def listSourceControlUsers = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "sourceControlUser", action: "list", isFirst: isFirst,
                title: message(code: "showAllSourceControlUsers.label"),
                text: message(code: "sourceControlUsers.label"))
    }

    /**
     * Renders a list system environments link.
     */
    def listSystemDeploymentEnvironments = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "systemDeploymentEnvironment", action: "list", isFirst: isFirst,
                title: message(code: "showAllSystemDeploymentEnvironments.label"),
                text: message(code: "systemDeploymentEnvironments.label"))
    }

    /**
     * Renders a list system environments link.
     */
    def listSystemEnvironments = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "systemEnvironment", action: "list", isFirst: isFirst,
                title: message(code: "showAllSystemEnvironments.label"),
                text: message(code: "systemEnvironments.label"))
    }

    /**
     * Renders a list system servers link.
     */
    def listSystemServers = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "systemServer", action: "list", isFirst: isFirst,
                title: message(code: "showAllSystemServers.label"),
                text: message(code: "systemServers.label"))
    }

    /**
     * Renders a list users link.
     */
    def listUsers = { attrs ->
        def isFirst = attrs.isFirst
        out << link(controller: "user", action: "list", isFirst: isFirst,
                title: message(code: "showAllUsers.label"),
                text: message(code: "users.label"))
    }

    /**
     * Renders a show application link.
     */
    def showApplication = { attrs ->
        def application = attrs.application
        def isFirst = attrs.isFirst
        out << link(controller: "application", action: "show",
                title: message(code: "showApplication.label"),
                text: application.name, id: application.id, isFirst: isFirst)
    }

    /**
     * Renders a show application deployment link.
     */
    def showApplicationDeployment = { attrs ->
        def applicationDeployment = attrs.applicationDeployment
        def isFirst = attrs.isFirst
        def text = message(code: "breadcrumbs.applicationDeployment.label",
                args: [applicationDeployment.applicationRelease.releaseNumber, applicationDeployment.deploymentEnvironment.name])
        out << link(controller: "applicationDeployment", action: "show",
                title: message(code: "showApplicationDeployment.label"),
                text: text, id: applicationDeployment.id, isFirst: isFirst)
    }

    /**
     * Renders a show application release link.
     */
    def showApplicationRelease = { attrs ->
        def applicationRelease = attrs.applicationRelease
        def isFirst = attrs.isFirst
        def text = message(code: "breadcrumbs.applicationRelease.label", args: [applicationRelease.releaseNumber])
        out << link(controller: "applicationRelease", action: "show",
                title: message(code: "showApplicationRelease.label"),
                text: text, id: applicationRelease.id, isFirst: isFirst)
    }

    /**
     * Renders a show application release link.
     */
    def showApplicationReleaseHistory = { attrs ->
        def applicationReleaseHistory = attrs.applicationReleaseHistory
        def isFirst = attrs.isFirst
        def text = formatDate(date: applicationReleaseHistory.dateCreated)
        out << link(controller: "applicationReleaseHistory", action: "show",
                title: message(code: "showApplicationReleaseHistory.label"),
                text: text, id: applicationReleaseHistory.id, isFirst: isFirst)
    }

    /**
     * Renders a show application type link.
     */
    def showApplicationType = { attrs ->
        def applicationType = attrs.applicationType
        def isFirst = attrs.isFirst
        out << link(controller: "applicationType", action: "show",
                title: message(code: "showApplicationType.label"),
                text: applicationType.name, id: applicationType.id, isFirst: isFirst)
    }

    /**
     * Renders a show module link.
     */
    def showModule = { attrs ->
        def module = attrs.module
        def isFirst = attrs.isFirst
        out << link(controller: "module", action: "show",
                title: message(code: "showModule.label"),
                text: module.name, id: module.id, isFirst: isFirst)
    }

    /**
     * Renders a show module deployment link.
     */
    def showModuleDeployment = { attrs ->
        def moduleDeployment = attrs.moduleDeployment
        def isFirst = attrs.isFirst
        out << link(controller: "moduleDeployment", action: "show",
                title: message(code: "showModuleDeployment.label"),
                text: moduleDeployment.moduleRelease.module.name, id: moduleDeployment.id, isFirst: isFirst)
    }

    /**
     * Renders a show module deployment test state link.
     */
    def showModuleDeploymentTestState = { attrs ->
        def moduleDeploymentTestState = attrs.moduleDeploymentTestState
        def isFirst = attrs.isFirst
        out << link(controller: "moduleDeploymentTestState", action: "show",
                title: message(code: "showModuleDeploymentTestState.label"),
                text: moduleDeploymentTestState.name, id: moduleDeploymentTestState.id, isFirst: isFirst)
    }

    /**
     * Renders a show module type link.
     */
    def showModuleType = { attrs ->
        def moduleType = attrs.moduleType
        def isFirst = attrs.isFirst
        out << link(controller: "moduleType", action: "show",
                title: message(code: "showModuleType.label"),
                text: moduleType.name, id: moduleType.id, isFirst: isFirst)
    }

    /**
     * Renders a show project link.
     */
    def showProject = { attrs ->
        def project = attrs.project
        def isFirst = attrs.isFirst
        out << link(controller: "project", action: "show",
                title: message(code: "showProject.label"),
                text: project.name, id: project.id, isFirst: isFirst)
    }

    /**
     * Renders a show project category link.
     */
    def showProjectCategory = { attrs ->
        def projectCategory = attrs.projectCategory
        def isFirst = attrs.isFirst
        out << link(controller: "projectCategory", action: "show",
                title: message(code: "showProjectCategory.label"),
                text: projectCategory.name, id: projectCategory.id, isFirst: isFirst)
    }

    /**
     * Renders a show source control repository link.
     */
    def showSourceControlRepository = { attrs ->
        def sourceControlRepository = attrs.sourceControlRepository
        def isFirst = attrs.isFirst
        out << link(controller: "sourceControlRepository", action: "show",
                title: message(code: "showSourceControlRepository.label"),
                text: sourceControlRepository.name, id: sourceControlRepository.id, isFirst: isFirst)
    }

    /**
     * Renders a show source control role link.
     */
    def showSourceControlRole = { attrs ->
        def sourceControlRole = attrs.sourceControlRole
        def isFirst = attrs.isFirst
        out << link(controller: "sourceControlRole", action: "show",
                title: message(code: "showSourceControlRole.label"),
                text: sourceControlRole.name, id: sourceControlRole.id, isFirst: isFirst)
    }

    /**
     * Renders a show source control server link.
     */
    def showSourceControlServer = { attrs ->
        def sourceControlServer = attrs.sourceControlServer
        def isFirst = attrs.isFirst
        out << link(controller: "sourceControlServer", action: "show",
                title: message(code: "showSourceControlServer.label"),
                text: sourceControlServer.name, id: sourceControlServer.id, isFirst: isFirst)
    }

    /**
     * Renders a show source control user link.
     */
    def showSourceControlUser = { attrs ->
        def sourceControlUser = attrs.sourceControlUser
        def isFirst = attrs.isFirst
        out << link(controller: "sourceControlUser", action: "show",
                title: message(code: "showSourceControlUser.label"),
                text: sourceControlUser.name, id: sourceControlUser.id, isFirst: isFirst)
    }

    /**
     * Renders a show system environment link.
     */
    def showSystemDeploymentEnvironment = { attrs ->
        def systemDeploymentEnvironment = attrs.systemDeploymentEnvironment
        def isFirst = attrs.isFirst
        out << link(controller: "systemDeploymentEnvironment", action: "show",
                title: message(code: "showSystemDeploymentEnvironment.label"),
                text: systemDeploymentEnvironment.name,
                id: systemDeploymentEnvironment.id, isFirst: isFirst)
    }

    /**
     * Renders a show system environment link.
     */
    def showSystemEnvironment = { attrs ->
        def systemEnvironment = attrs.systemEnvironment
        def isFirst = attrs.isFirst
        out << link(controller: "systemEnvironment", action: "show",
                title: message(code: "showSystemEnvironment.label"),
                text: systemEnvironment.name, id: systemEnvironment.id, isFirst: isFirst)
    }

    /**
     * Renders a show system server link.
     */
    def showSystemServer = { attrs ->
        def systemServer = attrs.systemServer
        def isFirst = attrs.isFirst
        out << link(controller: "systemServer", action: "show",
                title: message(code: "showSystemServer.label"),
                text: systemServer.name, id: systemServer.id, isFirst: isFirst)
    }

    /**
     * Renders a show user link.
     */
    def showUser = { attrs ->
        def user = attrs.user
        def isFirst = attrs.isFirst
        out << link(controller: "user", action: "show",
                title: message(code: "showUser.label"),
                text: user.fullName, id: user.id, isFirst: isFirst)
    }

    /**
     * Renders an upcoming deployments label.
     */
    def upcomingDeploymentsLabel = { attrs ->
        def entityName = attrs.entityName
        def isFirst = attrs.isFirst
        out << text(code: "default.upcomingDeployments.label", args: [entityName], isFirst: isFirst)
    }
}
