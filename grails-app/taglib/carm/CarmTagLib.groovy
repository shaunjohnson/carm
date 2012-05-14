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

import carm.release.ModuleRelease

import java.text.SimpleDateFormat

class CarmTagLib {

    static namespace = "carm"

    def activityTraceService
    def applicationDeploymentService
    def applicationReleaseService
    def applicationReleaseTestStateService
    def applicationTypeService
    def carmSecurityService
    def favoriteService
    def moduleDeploymentTestStateService
    def moduleReleaseService
    def moduleTypeService
    def projectCategoryService
    def sourceControlRepositoryService
    def sourceControlRoleService
    def sourceControlServerService
    def springSecurityService
    def systemServerService
    def systemDeploymentEnvironmentService
    def systemEnvironmentService
    def watchService

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
            else if (activity.objectType == activityTraceService.APPLICATION_DEPLOYMENT_TYPE && applicationDeploymentService.exists(activity.objectId)) {
                controller = "applicationDeployment"
                title = message(code: "showApplicationDeployment.label", default: "Show Application Deployment")
            }
            else if (activity.objectType == activityTraceService.APPLICATION_RELEASE_TYPE && applicationReleaseService.exists(activity.objectId)) {
                controller = "applicationRelease"
                title = message(code: "showApplicationRelease.label", default: "Show Application Release")
            }
            else if (activity.objectType == activityTraceService.MODULE_TYPE && Module.exists(activity.objectId)) {
                controller = "module"
                title = message(code: "showModule.label", default: "Show Module")
            }
            else if (activity.objectType == activityTraceService.PROJECT_TYPE && Project.exists(activity.objectId)) {
                controller = "project"
                title = message(code: "showProject.label", default: "Show Project")
            }
            else if (activity.objectType == activityTraceService.SYSTEM_DEPLOYMENT_ENVIRONMENT_TYPE && SystemDeploymentEnvironment.exists(activity.objectId)) {
                controller = "systemDeploymentEnvironment"
                title = message(code: "showSystemDeploymentEnvironment.label", default: "Show Deployment Environment")
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

        def action = actionName
        def controller = controllerName

        if (controller == 'administration') {
            out << carm.pageHeaderLabel(action: "show", beanName: pageName, entity: domain)
        }
        else if (controller == 'application') {
            def entityName = message(code: 'application.label', default: 'Application')

            out << carm.pageHeaderLabel(beanName: domain?.name, entityName: entityName, entity: domain)

            out << bc.breadcrumbs(null) {
                if (action == 'list') {
                    out << bc.listApplications(isFirst: true)
                }
                else {
                    out << bc.listProjects(isFirst: true)
                    out << bc.showProject(project: domain.project)

                    if (action == 'show') {
                        out << bc.showApplication(application: domain)
                    }
                    else if (action == 'create' || action == 'save') {
                        out << bc.createLabel(entityName: entityName)
                    }
                    else if (action == 'edit' || action == 'update') {
                        out << bc.showApplication(application: domain)
                        out << bc.editLabel(entityName: entityName)
                    }
                    else if (action == 'listActivity') {
                        out << bc.showApplication(application: domain)
                        out << bc.label(code: "allActivity.label", args: [entityName])
                    }
                    else if (action == 'listReleases') {
                        out << bc.showApplication(application: domain)
                        out << bc.label(code: "allReleases.label", args: [entityName])
                    }
                    else if (action == 'showFullHistory') {
                        out << bc.showApplication(application: domain)
                        out << bc.label(code: "showFullHistory.label", args: [entityName])
                    }
                }
            }
        }
        else if (controller == 'applicationDeployment') {
            def entityName = message(code: 'applicationDeployment.label', default: 'Application Deployment')

            out << carm.pageHeaderLabel(beanName: message(code: 'pageHeader.applicationDeployment.label',
                    args: [domain?.applicationRelease?.application?.name, domain?.applicationRelease?.releaseNumber, domain?.deploymentEnvironment]),
                    entityName: entityName, entity: domain)

            out << bc.breadcrumbs(null) {
                if (action != 'list') {
                    out << bc.listProjects(isFirst: true)
                    out << bc.showProject(project: domain.applicationRelease.application.project)
                    out << bc.showApplication(application: domain.applicationRelease.application)
                    out << bc.showApplicationRelease(applicationRelease: domain.applicationRelease)

                    if (action == 'show') {
                        out << bc.showApplicationDeployment(applicationDeployment: domain)
                    }
                    else if (action == 'create' || action == 'save' || action == 'redeploy') {
                        out << bc.createLabel(entityName: entityName)
                    }
                    else if (action == 'edit' || action == 'update') {
                        out << bc.showApplicationDeployment(applicationDeployment: domain)
                        out << bc.editLabel(entityName: entityName)
                    }
                }
            }
        }
        else if (controller == 'applicationRelease') {
            def entityName = message(code: 'applicationRelease.label', default: 'Application Release')

            carm: pageHeaderLabel(beanName: message(code: 'pageHeader.applicationRelease.label', args: [domain?.application?.name, domain?.releaseNumber]),
                    entityName: entityName, entity: domain)

            out << bc.breadcrumbs(null) {
                if (action != 'list') {
                    out << bc.listProjects(isFirst: true)
                    out << bc.showProject(project: domain.application.project)
                    out << bc.showApplication(application: domain.application)

                    if (action == 'show') {
                        out << bc.showApplicationRelease(applicationRelease: domain)
                    }
                    else if (action == 'create' || action == 'save') {
                        out << bc.createLabel(entityName: entityName)
                    }
                    else if (action == 'edit' || action == 'update') {
                        out << bc.showApplicationRelease(applicationRelease: domain)
                        out << bc.editLabel(entityName: entityName)
                    }
                    else if (action == 'listActivity') {
                        out << bc.showApplicationRelease(applicationRelease: domain)
                        out << bc.label(code: "allActivity.label", args: [entityName])
                    }
                }
            }
        }
        else if (controller == 'applicationReleaseHistory') {
            def entityName = message(code: 'applicationReleaseHistory.label', default: 'Application Release History')

            if (action == 'list') {
                out << carm.pageHeaderLabel(beanName: domain, entityName: entityName, entity: domain)
            }
            else {
                out << carm.pageHeaderLabel(beanName: formatDate(date: domain.dateCreated), entityName: entityName, entity: domain)
            }

            out << bc.breadcrumbs(null) {
                if (action != 'list') {
                    out << bc.listProjects(isFirst: true)
                    out << bc.showProject(project: domain.applicationRelease.application.project)
                    out << bc.showApplication(application: domain.applicationRelease.application)
                    out << bc.showApplicationRelease(applicationRelease: domain.applicationRelease)

                    if (action == 'show') {
                        out << bc.showApplicationReleaseHistory(applicationReleaseHistory: domain)
                    }
                }
            }
        }
        else if (controller == 'applicationReleaseTestState') {
            def entityName = message(code: 'applicationReleaseTestState.label', default: 'Application Release Test State')

            out << carm.pageHeaderLabel(beanName: domain?.name, entityName: entityName, entity: domain)

            out << bc.breadcrumbs(null) {
                out << bc.administration(isFirst: true)
                out << bc.listApplicationReleaseTestStates()

                if (action == 'show') {
                    out << bc.link(controller: "applicationReleaseTestState", action: "show",
                            title: "Show Application Release Test State", text: domain.name, id: domain.id)
                }
                else if (action == 'create' || action == 'save') {
                    out << bc.createLabel(entityName: entityName)
                }
                else if (action == 'edit' || action == 'update') {
                    out << bc.link(controller: "applicationReleaseTestState", action: "show",
                            title: "Show Application Release Test State", text: domain.name, id: domain.id)
                    out << bc.editLabel(entityName: entityName)
                }
            }
        }
        else if (controller == 'applicationType') {
            def entityName = message(code: 'applicationType.label', default: 'Application Type')

            out << carm.pageHeaderLabel(beanName: domain?.name, entityName: entityName, entity: domain)

            out << bc.breadcrumbs(null) {
                out << bc.administration(isFirst: true)
                out << bc.listApplicationTypes()

                if (action == 'show') {
                    out << bc.showApplicationType(applicationType: domain)
                }
                else if (action == 'create' || action == 'save') {
                    out << bc.createLabel(entityName: entityName)
                }
                else if (action == 'edit' || action == 'update') {
                    out << bc.showApplicationType(applicationType: domain)
                    out << bc.editLabel(entityName: entityName)
                }
            }
        }
        else if (controller ==~ /.*home/) {
            out << carm.pageHeaderLabel(action: "show", beanName: pageName, entity: domain)
            out << bc.breadcrumbs()
        }
        else if (controller == 'login') {
            out << carm.pageHeaderLabel(action: "show", beanName: pageName, entity: domain)
            out << bc.breadcrumbs()
        }
        else if (controller == 'module') {
            def entityName = message(code: 'module.label', default: 'Module')

            out << carm.pageHeaderLabel(beanName: domain?.name, entityName: entityName, entity: domain)

            out << bc.breadcrumbs(null) {
                if (action == 'list') {
                    out << bc.listModules(isFirst: true)
                }
                else {
                    out << bc.showProject(project: domain.application.project, isFirst: true)
                    out << bc.showApplication(application: domain.application)

                    if (action == 'show') {
                        out << bc.showModule(module: domain)
                    }
                    else if (action == 'create' || action == 'save') {
                        out << bc.createLabel(entityName: entityName)
                    }
                    else if (action == 'edit' || action == 'update') {
                        out << bc.showModule(module: domain)
                        out << bc.editLabel(entityName: entityName)
                    }
                    else if (action == 'listActivity') {
                        out << bc.showModule(module: domain)
                        out << bc.label(code: "allActivity.label", args: [entityName])
                    }
                }
            }
        }
        else if (controller == 'moduleDeployment') {
            def entityName = message(code: 'moduleDeployment.label', default: 'Module Deployment')
            def applicationDeployment = domain?.applicationDeployment
            def applicationRelease = applicationDeployment?.applicationRelease

            out << carm.pageHeaderLabel(
                    beanName: message(code: 'pageHeader.moduleDeployment.label',
                            args: [applicationRelease?.application?.name, applicationRelease?.releaseNumber, applicationDeployment?.deploymentEnvironment]),
                    entityName: entityName, entity: domain)

            out << bc.breadcrumbs(null) {
                if (action == 'show') {
                    out << bc.listProjects(isFirst: true)
                    out << bc.showProject(project: applicationRelease.application.project)
                    out << bc.showApplication(application: applicationRelease.application)
                    out << bc.showApplicationRelease(applicationRelease: applicationRelease)
                    out << bc.showApplicationDeployment(applicationDeployment: applicationDeployment)
                    out << bc.showModuleDeployment(moduleDeployment: domain)
                }
            }
        }
        else if (controller == 'moduleDeploymentTestState') {
            def entityName = message(code: 'moduleDeploymentTestState.label', default: 'Module Deployment Test State')

            out << carm.pageHeaderLabel(beanName: domain?.name, entityName: entityName, entity: domain)

            out << bc.breadcrumbs(null) {
                out << bc.administration(isFirst: true)
                out << bc.listModuleDeploymentTestStates()

                if (action == 'show') {
                    out << bc.showModuleDeploymentTestState(moduleDeploymentTestState: domain)
                }
                else if (action == 'create' || action == 'save') {
                    out << bc.createLabel(entityName: entityName)
                }
                else if (action == 'edit' || action == 'update') {
                    out << bc.showModuleDeploymentTestState(moduleDeploymentTestState: domain)
                    out << bc.editLabel(entityName: entityName)
                }
            }
        }
        else if (controller == 'moduleType') {
            def entityName = message(code: 'moduleType.label', default: 'Module Type')

            out << carm.pageHeaderLabel(beanName: domain?.name, entityName: entityName, entity: domain)

            out << bc.breadcrumbs(null) {
                out << bc.administration(isFirst: true)
                out << bc.listModuleTypes()

                if (action == 'show') {
                    out << bc.showModuleType(moduleType: domain)
                }
                else if (action == 'create' || action == 'save') {
                    out << bc.createLabel(entityName: entityName)
                }
                else if (action == 'edit' || action == 'update') {
                    out << bc.showModuleType(moduleType: domain)
                    out << bc.editLabel(entityName: entityName)
                }
            }
        }
        else if (controller == 'project') {
            def entityName = message(code: 'project.label', default: 'Project')

            out << carm.pageHeaderLabel(beanName: domain?.name, entityName: entityName, entity: domain)

            out << bc.breadcrumbs(null) {
                if (action != 'list') {
                    out <<  bc.listProjects(isFirst: true)

                    if (action == 'show') {
                        out << bc.showProject(project: domain)
                    }
                    else if (action == 'create' || action == 'save') {
                        out << bc.createLabel(entityName: entityName)
                    }
                    else if (action == 'edit' || action == 'update') {
                        out << bc.showProject(project: domain)
                        out << bc.editLabel(entityName: entityName)
                    }
                    else if (action == 'listActivity') {
                        out << bc.showProject(project: domain)
                        out << bc.label(code: "allActivity.label", args: [entityName])
                    }
                }
            }
        }
        else if (controller == 'projectCategory') {
            def entityName = message(code: 'projectCategory.label', default: 'Project Category')

            out << carm.pageHeaderLabel(beanName: domain?.name, entityName: entityName, entity: domain)

            out << bc.breadcrumbs(null) {
                out << bc.administration(isFirst: true)
                out << bc.listProjectCategories()

                if (action == 'show') {
                    out << bc.showProjectCategory(projectCategory: domain)
                }
                else if (action == 'create' || action == 'save') {
                    out << bc.createLabel(entityName: entityName)
                }
                else if (action == 'edit' || action == 'update') {
                    out << bc.showProjectCategory(projectCategory: domain)
                    out << bc.editLabel(entityName: entityName)
                }
            }
        }
        else if (controller == 'sourceControlRepository') {
            def entityName = message(code: 'sourceControlRepository.label', default: 'Source Control Repository')

            out << carm.pageHeaderLabel(beanName: domain?.name, entityName: entityName, entity: domain)

            out << bc.breadcrumbs(null) {
                out << bc.administration(isFirst: true)

                if (action == 'list') {
                    out << bc.listSourceControlRepositories()
                }
                else {
                    out << bc.listSourceControlServers()
                    out << bc.showSourceControlServer(sourceControlServer: domain.server)

                    if (action == 'show') {
                        out << bc.showSourceControlRepository(sourceControlRepository: domain)
                    }
                    else if (action == 'create' || action == 'save') {
                        out << bc.createLabel(entityName: entityName)
                    }
                    else if (action == 'edit' || action == 'update') {
                        out << bc.showSourceControlRepository(sourceControlRepository: domain)
                        out << bc.editLabel(entityName: entityName)
                    }
                }
            }
        }
        else if (controller == 'sourceControlRole') {
            def entityName = message(code: 'sourceControlRole.label', default: 'Source Control Role')

            out << carm.pageHeaderLabel(beanName: domain?.name, entityName: entityName, entity: domain)

            out << bc.breadcrumbs(null) {
                out << bc.administration(isFirst: true)
                out << bc.listSourceControlRoles()

                if (action == 'show') {
                    out << bc.showSourceControlRole(sourceControlRole: domain)
                }
                else if (action == 'create' || action == 'save') {
                    out << bc.createLabel(entityName: entityName)
                }
                else if (action == 'edit' || action == 'update') {
                    out << bc.showSourceControlRole(sourceControlRole: domain)
                    out << bc.editLabel(entityName: entityName)
                }
            }
        }
        else if (controller == 'sourceControlServer') {
            def entityName = message(code: 'sourceControlServer.label', default: 'Source Control Server')

            out << carm.pageHeaderLabel(beanName: domain?.name, entityName: entityName, entity: domain)

            out << bc.breadcrumbs(null) {
                out << bc.administration(isFirst: true)
                out << bc.listSourceControlServers()

                if (action == 'show') {
                    out << bc.showSourceControlServer(sourceControlServer: domain)
                }
                else if (action == 'create' || action == 'save') {
                    out << bc.createLabel(entityName: entityName)
                }
                else if (action == 'edit' || action == 'update') {
                    out << bc.showSourceControlServer(sourceControlServer: domain)
                    out << bc.editLabel(entityName: entityName)
                }
            }
        }
        else if (controller == 'sourceControlUser') {
            def entityName = message(code: 'sourceControlUser.label', default: 'Source Control User')

            out << carm.pageHeaderLabel(beanName: domain?.name, entityName: entityName, entity: domain)

            out << bc.breadcrumbs(null) {
                out << bc.administration(isFirst: true)

                if (action == 'list') {
                    out << bc.listSourceControlUsers()
                }
                else {
                    out << bc.listSourceControlServers()
                    out << bc.showSourceControlServer(sourceControlServer: domain.server)

                    if (action == 'show') {
                        out << bc.showSourceControlUser(sourceControlUser: domain)
                    }
                    else if (action == 'create' || action == 'save') {
                        out << bc.createLabel(entityName: entityName)
                    }
                    else if (action == 'edit' || action == 'update') {
                        out << bc.showSourceControlUser(sourceControlUser: domain)
                        out << bc.editLabel(entityName: entityName)
                    }
                }
            }
        }
        else if (controller == 'systemDeploymentEnvironment') {
            def entityName = message(code: 'systemDeploymentEnvironment.label', default: 'SystemEnvironment Deployment Environment')

            out << carm.pageHeaderLabel(beanName: domain?.name, entityName: entityName, entity: domain)

            out << bc.breadcrumbs(null) {
                if (action != 'list') {
                    out << bc.listSystemEnvironments(isFirst: true)
                    out << bc.showSystemEnvironment(systemEnvironment: domain.sysEnvironment)

                    if (action == 'show') {
                        out << bc.showSystemDeploymentEnvironment(systemDeploymentEnvironment: domain)
                    }
                    else if (action == 'create' || action == 'save') {
                        out << bc.createLabel(entityName: entityName)
                    }
                    else if (action == 'edit' || action == 'update') {
                        out << bc.showSystemDeploymentEnvironment(systemDeploymentEnvironment: domain)
                        out << bc.editLabel(entityName: entityName)
                    }
                    else if (action == 'listActivity') {
                        out << bc.showSystemDeploymentEnvironment(systemDeploymentEnvironment: domain)
                        out << bc.label(code: "allActivity.label", args: [entityName])
                    }
                }
            }
        }
        else if (controller == 'systemEnvironment') {
            def entityName = message(code: 'systemEnvironment.label', default: 'SystemEnvironment')

            out << carm.pageHeaderLabel(beanName: domain?.name, entityName: entityName, entity: domain)

            out << bc.breadcrumbs(null) {
                if (action != 'list') {
                    out << bc.listSystemEnvironments(isFirst: true)

                    if (action == 'show') {
                        out << bc.showSystemEnvironment(systemEnvironment: domain)
                    }
                    else if (action == 'create' || action == 'save') {
                        out << bc.createLabel(entityName: entityName)
                    }
                    else if (action == 'edit' || action == 'update') {
                        out << bc.showSystemEnvironment(systemEnvironment: domain)
                        out << bc.editLabel(entityName: entityName)
                    }
                    else if (action == 'listActivity') {
                        out << bc.showSystemEnvironment(systemEnvironment: domain)
                        out << bc.label(code: "allActivity.label", args: [entityName])
                    }
                    else if (action == 'completedDeployments') {
                        out << bc.showSystemEnvironment(systemEnvironment: domain)
                        out << bc.completedDeploymentsLabel(entityName: entityName)
                    }
                    else if (action == 'upcomingDeployments') {
                        out << bc.showSystemEnvironment(systemEnvironment: domain)
                        out << bc.upcomingDeploymentsLabel(entityName: entityName)
                    }
                }
            }
        }
        else if (controller == 'systemServer') {
            def entityName = message(code: 'systemServer.label', default: 'SystemEnvironment Server')

            out << carm.pageHeaderLabel(beanName: domain?.name, entityName: entityName, entity: domain)

            out << bc.breadcrumbs(null) {
                if (action != 'list') {
                    out << bc.listSystemEnvironments(isFirst: true)
                    out << bc.showSystemEnvironment(systemEnvironment: domain.sysEnvironment)

                    if (action == 'show') {
                        out << bc.showSystemServer(systemServer: domain)
                    }
                    else if (action == 'create' || action == 'save') {
                        out << bc.createLabel(entityName: entityName)
                    }
                    else if (action == 'edit' || action == 'update') {
                        out << bc.showSystemServer(systemServer: domain)
                        out << bc.editLabel(entityName: entityName)
                    }
                    else if (action == 'listActivity') {
                        out << bc.showSystemServer(systemServer: domain)
                        out << bc.label(code: "allActivity.label", args: [entityName])
                    }
                }
            }
        }
        else if (controller == 'user') {
            def entityName = message(code: 'user.label', default: 'User')

            out << carm.pageHeaderLabel(beanName: domain?.fullName, entityName: entityName, entity: domain)

            out << bc.breadcrumbs(null) {
                out << bc.administration(isFirst: true)
                out << bc.listUsers()

                if (action == 'list') {
                    // Do nothing
                }
                else if (action == 'listActivity') {
                    out << bc.showUser(user: domain)
                    out << bc.label(code: "allActivity.label", args: [entityName])
                }
                else {
                    out << bc.showUser(user: domain)
                }
            }
        }
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
        def action = attrs.action ?: actionName
        def entity = attrs.entity
        def entityName = attrs.entityName
        def beanName = attrs.beanName

        // Override action if necessary
        if (action == 'redeploy') {
            action = 'create'
        }

        out << '<h1><div style="float: left;">'

        if (action == 'show') {
            out << beanName
        }
        else if (action == 'listReleases') {
            def headerText = message(code: "default.${action}.label", args: [beanName])
            out << headerText
        }
        else {
            def headerText = message(code: "default.${action}.label", args: [entityName])
            out << headerText
        }

        out << '</div>'

        if (action == 'show' && springSecurityService.isLoggedIn()) {
            if (controllerName == 'application') {
                out << render(template: "/favorite/favorites",
                        model: [type: "Application", isFavorite: favoriteService.isApplicationFavoriteByCurrentUser(entity)])

                out << render(template: "/watch/watches",
                        model: [type: "Application", isWatched: watchService.isApplicationWatchedByCurrentUser(entity)])
            }
            else if (controllerName == 'project') {
                out << render(template: "/favorite/favorites",
                        model: [type: "Project", isFavorite: favoriteService.isProjectFavoriteByCurrentUser(entity)])

                out << render(template: "/watch/watches",
                        model: [type: "Project", isWatched: watchService.isProjectWatchedByCurrentUser(entity)])
            }
        }

        out << '<div class="clearing"></div></h1>'
    }

    def showMore = { attrs ->
        def controller = attrs.controller
        def action = attrs.action
        def id = attrs.id
        def max = attrs.max
        def step = attrs.step
        def clientId = "showMore_${controller}_${action}_${id}"
        def appendId = attrs.appendId

        out << render(template: "/common/showMore", model: [clientId: clientId, controller: controller, action: action, id: id, max: max, step: step, appendId: appendId])
    }

    /**
     * Generates a link button using the same parameters that are used with links.
     */
    def button = { attrs, body ->
        out << "<button class='button' onclick='window.location=\"" << createLink(attrs) << "\"'>" << body() << "</button>"
    }

    def formDividerRow = { attrs ->
        out << '<tr><td colspan="2"><hr class="divider" style="margin: 1em 0;"/></td></tr>'
    }

    def formFooter = { attrs, body ->
        out << '<tfoot>'
        out << formDividerRow()
        out << '<tr><td colspan="2">'
        out << '<div style="float: right;">' << body() << '</div>'
        out << '<div class="clearing"></div>'
        out << '</td></tr><tfoot>'
    }

    def formatApplicationDeploymentState = { attrs ->
        def deploymentState = attrs.deploymentState

        if (deploymentState) {
            out << message(code: "carm.deployment.ApplicationDeploymentState.${deploymentState}", default: deploymentState.toString())
        }
    }

    def formatBinariesPath = { attrs ->
        def applicationRelease = attrs.applicationRelease
        def url = applicationRelease.application.binariesPath.replaceAll(/\$\{releaseNumber\}/, applicationRelease.releaseNumber)

        out << link(url: url, target: "_blank") { url }
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
        Period period = new Period(value, new DateTime())

        out << '<span class="' << styleClass << '" title="' << joda.format(value: value) << '">'

        if (period.years > 0) {
            out << joda.formatPeriod(value: period, fields: "years, months")
        }
        else if (period.months > 0) {
            out << joda.formatPeriod(value: period, fields: "months, days")
        }
        else if (period.days > 0) {
            out << joda.formatPeriod(value: period, fields: "days")
        }
        else if (period.hours > 0) {
            out << joda.formatPeriod(value: period, fields: "hours, minutes")
        }
        else if (period.minutes > 0) {
            out << joda.formatPeriod(value: period, fields: "minutes, seconds")
        }
        else {
            out << joda.formatPeriod(value: period, fields: "seconds")
        }

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

    def formatSystemEnvironment = { attrs ->
        out << render(template: "/common/systemEnvironment", model: [systemEnvironment: attrs.systemEnvironment])
    }

    /**
     * Formats the current user fullName.
     */
    def formatCurrentUser = { attrs ->
        out << carmSecurityService.currentUser.fullName.encodeAsHTML()
    }

    /**
     * Formats a user as the user's full name if possible, otherwise the username is outputted
     *
     * attrs.username - Username of User to format
     */
    def formatUser = { attrs ->
        def username = attrs.username

        out << (carmSecurityService.findUserByUsername(username)?.fullName ?: username).encodeAsHTML()
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

    def label = { attrs, body ->
        out << '<label '

        attrs.each { k, v ->
            out << " $k=\"${v.encodeAsHTML()}\""
        }

        out << '>' << body()

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
        ModuleRelease moduleRelease = attrs.moduleRelease

        def deployable = false
        if (applicationRelease) {
            deployable = applicationReleaseService.isDeployable(applicationRelease)
        }
        else if (moduleRelease) {
            deployable = moduleReleaseService.isDeployable(moduleRelease)
        }

        if (deployable) {
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

    /**
     * Renders a highlight widget
     */
    def highlight = { attrs, body ->
        def message = attrs.message
        def display = attrs.display ?: 'block'
        def id = attrs.id

        out << render(template: '/common/highlightWidget', model: [display: display, id: id, message: message])
    }

    def userInfoDropdown = {
        out << render(template: "/common/userInfoDropdown", model: [
                username: carmSecurityService.currentUsername,
                favorites: favoriteService.findAllByCurrentUser()])
    }

    def datePicker = {attrs, body ->
        def out = out
        def name = attrs.name    //The name attribute is required for the tag to work seamlessly with grails
        def id = attrs.id ?: name
        def minDate = attrs.minDate
        def showDay = attrs.showDay

        def dateFormat = message(code: "java.datepicker.format")
        Calendar calendar = Calendar.instance
        if (attrs.value) {
            calendar.setTime(attrs.value)
        }

        def value = new SimpleDateFormat(dateFormat).format(calendar.getTime())

        //Create date text field and supporting hidden text fields need by grails
        out.println "<input type=\"text\" name=\"${name}\" id=\"${id}\" value=\"${value}\" maxlength=\"${dateFormat.length()}\" size=\"${dateFormat.length()}\" />"
        out.println "<input type=\"hidden\" name=\"${name}_day\" id=\"${id}_day\" value=\"${calendar.get(Calendar.DAY_OF_MONTH)}\" />"
        out.println "<input type=\"hidden\" name=\"${name}_month\" id=\"${id}_month\" value=\"${calendar.get(Calendar.MONTH) + 1}\" />"
        out.println "<input type=\"hidden\" name=\"${name}_year\" id=\"${id}_year\" value=\"${calendar.get(Calendar.YEAR)}\" />"

        //Code to parse selected date into hidden fields required by grails
        out.println "<script type=\"text/javascript\"> \$(document).ready(function(){"
        out.println "\$(\"#${name}\").datepicker({"
        out.println "onClose: function(dateText, inst) {"
        out.println "\$(\"#${name}_month\").attr(\"value\",new Date(dateText).getMonth() + 1);"
        out.println "\$(\"#${name}_day\").attr(\"value\",new Date(dateText).getDate());"
        out.println "\$(\"#${name}_year\").attr(\"value\",new Date(dateText).getFullYear());"
        out.println "}"

        //If you want to customize using the jQuery UI events add an if block an attribute as follows
        if (minDate != null) {
            out.println ","
            out.println "minDate: ${minDate}"
        }

        out.println ","
        out.println "dateFormat: '" << message(code: "jQuery.datepicker.format") << "'"

        if (showDay != null) {
            out.println ","
            out.println "beforeShowDay: function(date){"
            out.println "var day = date.getDay();"
            out.println "return [day == ${showDay},\"\"];"
            out.println "}"
        }

        out.println "});"
        out.println "})</script>"

    }
}