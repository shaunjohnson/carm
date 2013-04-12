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
import org.springframework.web.servlet.support.RequestContextUtils
import carm.notification.NotificationRecipientType
import carm.notification.NotificationScheme
import carm.security.UserGroup

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
    def notificationSchemeService
    def projectCategoryService
    def projectService
    def sourceControlRepositoryService
    def sourceControlRoleService
    def sourceControlServerService
    def springSecurityService
    def systemServerService
    def systemDeploymentEnvironmentService
    def systemEnvironmentService
    def userGroupService
    def userService
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

    def deleteLink = { attrs, body ->
        def id = attrs.id
        def title = attrs.title
        def controller = attrs.controller ?: controllerName
        def action = attrs.action ?: "delete"
        def showText = attrs.showText ? attrs.boolean('showText') : true

        out << "<a href=\"#\" title=\"" << title << "\" onclick=\"return displayDelete('"
        out << createLink(action: action, id: id)
        out << "');\">"
        out << body()

        if (showText) {
            out << message(code: "default.button.delete.label", default: "Delete")
        }

        out << "</a>"
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

            out << carm.pageHeaderLabel(beanName: message(code: 'pageHeader.applicationRelease.label', args: [domain?.application?.name, domain?.releaseNumber]),
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
                    out << bc.showApplicationReleaseTestState(applicationReleaseTestState: domain)
                }
                else if (action == 'create' || action == 'save') {
                    out << bc.createLabel(entityName: entityName)
                }
                else if (action == 'edit' || action == 'update') {
                    out << bc.showApplicationReleaseTestState(applicationReleaseTestState: domain)
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
                    out << bc.listProjects(isFirst: true)
                    out << bc.showProject(project: domain.application.project)
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
        else if (controller == 'notificationScheme') {
            def entityName = message(code: 'notificationScheme.label', default: 'Notification Scheme')

            out << carm.pageHeaderLabel(beanName: domain?.name, entityName: entityName, entity: domain)

            out << bc.breadcrumbs(null) {
                out << bc.administration(isFirst: true)
                out << bc.listNotificationSchemes()

                if (action == 'show') {
                    out << bc.showNotificationScheme(notificationScheme: domain)
                }
                else if (action == 'create' || action == 'save') {
                    out << bc.createLabel(entityName: entityName)
                }
                else if (action == 'edit' || action == 'update') {
                    out << bc.showNotificationScheme(notificationScheme: domain)
                    out << bc.editLabel(entityName: entityName)
                }
                else if (action == 'addNotification' || action == 'saveNotification') {
                    out << bc.showNotificationScheme(notificationScheme: domain)
                    out << bc.addNotificationLabel(entityName: entityName)
                }
            }
        }
        else if (controller == 'project') {
            def entityName = message(code: 'project.label', default: 'Project')

            out << carm.pageHeaderLabel(beanName: domain?.name, entityName: entityName, entity: domain)

            out << bc.breadcrumbs(null) {
                if (action != 'list') {
                    out << bc.listProjects(isFirst: true)

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
                    else if (action == 'deployments') {
                        out << bc.showSystemEnvironment(systemEnvironment: domain)
                        out << bc.deploymentsLabel(entityName: entityName)
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
        else if (controller == 'userGroup') {
            def entityName = message(code: 'userGroup.label', default: 'User Group')

            out << carm.pageHeaderLabel(beanName: domain?.name, entityName: entityName, entity: domain)

            out << bc.breadcrumbs(null) {
                out << bc.administration(isFirst: true)
                out << bc.listUserGroups()

                if (action == 'show') {
                    out << bc.showUserGroup(userGroup: domain)
                }
                else if (action == 'create' || action == 'save') {
                    out << bc.createLabel(entityName: entityName)
                }
                else if (action == 'edit' || action == 'update') {
                    out << bc.showUserGroup(userGroup: domain)
                    out << bc.editLabel(entityName: entityName)
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

        out << '<div class="row">'
        out << '<div class="span8"><h1 style="margin-bottom: 0.25em;">'

        if (action == 'show') {
            out << beanName
        }
        else if (action == 'listReleases') {
            def headerText = message(code: "default.${action}.label", args: [beanName])
            out << headerText
        }
//        else if (entity instanceof Application && (action == 'create' || action == 'save')) {
//            out << "Add Application to ${entity.project.name}"
//        }
        else if (action == 'deployments' && controllerName == 'systemEnvironment') {
            def headerText = message(code: "pageHeader.systemEnvironmentDeployments.label", args: [beanName])
            out << headerText
        }
        else {
            def headerText = message(code: "default.${action}.label", args: [entityName])
            out << headerText
        }

        out << '</h1></div><div class="span4" style="margin-top: 5px;">'

        if (springSecurityService.isLoggedIn()) {
            if (controllerName == 'application') {
                out << render(template: "/application/actions", model: [applicationInstance: entity])
            }
            else if (controllerName == 'applicationDeployment') {
                out << render(template: "/applicationDeployment/actions", model: [applicationDeploymentInstance: entity])
            }
            else if (controllerName == 'applicationRelease') {
                out << render(template: "/applicationRelease/actions", model: [applicationReleaseInstance: entity])
            }
            else if (controllerName == 'applicationReleaseTestState') {
                out << render(template: "/applicationReleaseTestState/actions", model: [applicationReleaseTestStateInstance: entity])
            }
            else if (controllerName == 'applicationType') {
                out << render(template: "/applicationType/actions", model: [applicationTypeInstance: entity])
            }
            else if (controllerName == 'module') {
                out << render(template: "/module/actions", model: [moduleInstance: entity])
            }
            else if (controllerName == 'moduleDeploymentTestState') {
                out << render(template: "/moduleDeploymentTestState/actions", model: [moduleDeploymentTestStateInstance: entity])
            }
            else if (controllerName == 'moduleType') {
                out << render(template: "/moduleType/actions", model: [moduleTypeInstance: entity])
            }
            else if (controllerName == 'notificationScheme') {
                out << render(template: "/notificationScheme/actions", model: [notificationSchemeInstance: entity])
            }
            else if (controllerName == 'project') {
                out << render(template: "/project/actions", model: [projectInstance: entity])
            }
            else if (controllerName == 'projectCategory') {
                out << render(template: "/projectCategory/actions", model: [projectCategoryInstance: entity])
            }
            else if (controllerName == 'sourceControlRepository') {
                out << render(template: "/sourceControlRepository/actions", model: [sourceControlRepositoryInstance: entity])
            }
            else if (controllerName == 'sourceControlRole') {
                out << render(template: "/sourceControlRole/actions", model: [sourceControlRoleInstance: entity])
            }
            else if (controllerName == 'sourceControlServer') {
                out << render(template: "/sourceControlServer/actions", model: [sourceControlServerInstance: entity])
            }
            else if (controllerName == 'sourceControlUser') {
                out << render(template: "/sourceControlUser/actions", model: [sourceControlUserInstance: entity])
            }
            else if (controllerName == 'systemEnvironment') {
                out << render(template: "/systemEnvironment/actions", model: [systemEnvironmentInstance: entity])
            }
            else if (controllerName == 'systemDeploymentEnvironment') {
                out << render(template: "/systemDeploymentEnvironment/actions", model: [systemDeploymentEnvironmentInstance: entity])
            }
            else if (controllerName == 'systemServer') {
                out << render(template: "/systemServer/actions", model: [systemServerInstance: entity])
            }
            else if (controllerName == 'user') {
                out << render(template: "/user/actions", model: [userInstance: entity])
            }
            else if (controllerName == 'userGroup') {
                out << render(template: "/userGroup/actions", model: [userGroupInstance: entity])
            }
        }

        out << '</div></div>'
    }

    def userFavoritesMenu = { attrs ->
        def favorites = favoriteService.findAllByCurrentUser()

        if (favorites.size()) {
            out << render(template: "/common/userFavoritesMenu", model: [favorites: favorites])
        }
    }

    def favoriteActions = { attrs ->
        def entity = attrs.entity

        if (actionName == 'show') {
            if (entity instanceof Application) {
                out << render(template: "/favorite/favorites",
                        model: [type: "Application", isFavorite: favoriteService.isApplicationFavoriteByCurrentUser(entity)])
            }
            else if (entity instanceof Project) {
                out << render(template: "/favorite/favorites",
                        model: [type: "Project", isFavorite: favoriteService.isProjectFavoriteByCurrentUser(entity)])
            }
        }
    }

    def watchActions = { attrs ->
        def entity = attrs.entity

        if (actionName == 'show') {
            if (entity instanceof Application) {
                out << render(template: "/watch/watches",
                        model: [type: "Application", isWatched: watchService.isApplicationWatchedByCurrentUser(entity)])
            }
            else if (entity instanceof Project) {
                out << render(template: "/watch/watches",
                        model: [type: "Project", isWatched: watchService.isProjectWatchedByCurrentUser(entity)])
            }
        }
    }

    def showMore = { attrs ->
        def controller = attrs.controller
        def action = attrs.action
        def id = attrs.id
        def offset = attrs.offset ?: 0
        def max = attrs.max
        def step = attrs.step
        boolean mini = attrs.boolean('mini')
        def clientId = "showMore_${controller}_${action}_${id}"
        def appendId = attrs.appendId

        out << render(template: "/common/showMore", model: [clientId: clientId, controller: controller, action: action,
                id: id, offset: offset, max: max, step: step, appendId: appendId, styleClass: attrs.styleClass,
                style: attrs.style, mini: mini])
    }

    def formDividerRow = { attrs ->
        out << '<tr><td colspan="2"><hr/></td></tr>'
    }

    def formatApplicationDeploymentState = { attrs ->
        def deploymentState = attrs.deploymentState

        if (deploymentState) {
            out << message(code: "carm.deployment.ApplicationDeploymentState.${deploymentState}", default: deploymentState.toString())
        }
    }

    def formatApplicationReleaseState = { attrs ->
        def releaseState = attrs.releaseState

        if (releaseState) {
            out << message(code: "carm.release.ApplicationReleaseState.${releaseState}", default: releaseState.toString())
        }
    }

    /**
     * Renders a copy to clipboard icon that copies the text in the element with client ID of targetId.
     */
    def copyToClipboard = { attrs ->
        out << render(template: "/common/copyToClipboard", model: [targetId: attrs.targetId])
    }

    def formatBinariesPath = { attrs ->
        def applicationRelease = attrs.applicationRelease
        def url = applicationRelease.application.binariesPath.replaceAll(/\$\{releaseNumber\}/, applicationRelease.releaseNumber)

        out << link(elementId: attrs.elementId, url: url, target: "_blank") { url }
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

    def formatNotification = { attrs ->
        def notification = attrs.notification

        NotificationRecipientType recipientType = notification.recipientType

        switch (recipientType) {
            case NotificationRecipientType.CURRENT_USER:
                out << message(code: "carm.notification.NotificationRecipientType.${recipientType}", default: recipientType.name())
                break;
            case NotificationRecipientType.PROJECT_ADMINISTRATORS:
                out << message(code: "carm.notification.NotificationRecipientType.${recipientType}", default: recipientType.name())
                break;
            case NotificationRecipientType.GROUP:
                out << message(code: "carm.notification.NotificationRecipientType.${recipientType}", default: recipientType.name())
                // out << " (" << notification.userGroup << ")"
                out << " (User Group Name)"
                break;
            case NotificationRecipientType.USER:
                out << message(code: "carm.notification.NotificationRecipientType.${recipientType}", default: recipientType.name())
                out << " ("
                out << g.link(controller: "user", action: "show", id: notification.user.id) { notification.user.fullName }
                out << ")"
                break;
            case NotificationRecipientType.EMAIL_ADDRESS:
                out << message(code: "carm.notification.NotificationRecipientType.${recipientType}", default: recipientType.name())
                out << " (" << notification.emailAddress << ")"
                break;
            case NotificationRecipientType.APPLICATION_WATCHERS:
                out << message(code: "carm.notification.NotificationRecipientType.${recipientType}", default: recipientType.name())
                break;
            case NotificationRecipientType.PROJECT_WATCHERS:
                out << message(code: "carm.notification.NotificationRecipientType.${recipientType}", default: recipientType.name())
                break;
            default:
                out << recipientType.name()
        }
    }

    def formatNotificationEvent = { attrs ->
        def notificationEvent = attrs.notificationEvent
        out << message(code: "carm.notification.NotificationEvent.${notificationEvent}", default: notificationEvent.name())
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
        out << userService.currentUser.fullName.encodeAsHTML()
    }

    /**
     * Formats a user as the user's full name if possible, otherwise the username is outputted
     *
     * attrs.username - Username of User to format
     */
    def formatUser = { attrs ->
        def username = attrs.username

        out << (userService.findByUsername(username)?.fullName ?: username).encodeAsHTML()
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
        else if (domain instanceof NotificationScheme) {
            isInUse = notificationSchemeService.isInUse(domain)
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
        else if (domain instanceof UserGroup) {
            isInUse = userGroupService.isInUse(domain)
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

        def deployable
        if (applicationRelease) {
            deployable = applicationReleaseService.isDeployable(applicationRelease)
        }
        else if (moduleRelease) {
            deployable = moduleReleaseService.isDeployable(moduleRelease)
        }
        else {
            throw new IllegalArgumentException("Only application and module release supported")
        }

        if (deployable) {
            out << body()
        }
    }

    def isNotDeployable = { attrs, body ->
        ApplicationRelease applicationRelease = attrs.applicationRelease
        ModuleRelease moduleRelease = attrs.moduleRelease

        def notDeployable
        if (applicationRelease) {
            notDeployable = !applicationReleaseService.isDeployable(applicationRelease)
        }
        else if (moduleRelease) {
            notDeployable = !moduleReleaseService.isDeployable(moduleRelease)
        }
        else {
            throw new IllegalArgumentException("Only application and module release supported")
        }

        if (notDeployable) {
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
    def alertWarning = { attrs, body ->
        def message = attrs.message
        def display = attrs.display ?: 'block'
        def id = attrs.id

        out << render(template: '/common/alertWarning', model: [display: display, id: id, message: message])
    }

    def datePicker = {attrs, body ->
        def out = out
        def name = attrs.name    //The name attribute is required for the tag to work seamlessly with grails
        def id = attrs.id ?: name
        def minDate = attrs.minDate
        def showDay = attrs.showDay
        def todayLink = attrs.boolean('todayLink')

        def dateFormat = message(code: "java.datepicker.format")
        Calendar calendar = Calendar.instance
        if (attrs.value) {
            calendar.setTime(attrs.value)
        }

        def value = new SimpleDateFormat(dateFormat).format(calendar.getTime())

        //Create date text field and supporting hidden text fields need by grails
        out.println "<input class=\"span2\" type=\"text\" name=\"${name}\" id=\"${id}\" value=\"${value}\" maxlength=\"${dateFormat.length()}\" size=\"${dateFormat.length()}\" />"
        out.println "<input type=\"hidden\" name=\"${name}_day\" id=\"${id}_day\" value=\"${calendar.get(Calendar.DAY_OF_MONTH)}\" />"
        out.println "<input type=\"hidden\" name=\"${name}_month\" id=\"${id}_month\" value=\"${calendar.get(Calendar.MONTH) + 1}\" />"
        out.println "<input type=\"hidden\" name=\"${name}_year\" id=\"${id}_year\" value=\"${calendar.get(Calendar.YEAR)}\" />"

        if (todayLink) {
            out.println "<a href=\"#\" onclick=\"jQuery('#" << name << "').datepicker('setDate', '+0d'); return false;" << '">(today)</a>'
        }

        //Code to parse selected date into hidden fields required by grails
        out.println "<script type=\"text/javascript\"> \$(document).ready(function(){"

        // Add onchange handler
        out.println "\$(\"#${id}\").change(function(e) {"
        out.println "var date = new Date(jQuery(this).val());"
        out.println "\$(\"#${name}_month\").attr(\"value\", date.getMonth() + 1);"
        out.println "\$(\"#${name}_day\").attr(\"value\", date.getDate());"
        out.println "\$(\"#${name}_year\").attr(\"value\", date.getFullYear());"
        out.println "});"

        // Add date picker
        out.println "\$(\"#${id}\").datepicker({"
        out.println "onSelect: function(dateText, inst) {"
        out.println "var date = new Date(dateText);"
        out.println "\$(\"#${name}_month\").attr(\"value\", date.getMonth() + 1);"
        out.println "\$(\"#${name}_day\").attr(\"value\", date.getDate());"
        out.println "\$(\"#${name}_year\").attr(\"value\", date.getFullYear());"
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

    def formSection = { attrs, body ->
        def legend = attrs.legend

        out << '<fieldset><legend>'

        if (legend) {
            out << legend
        }

        out << '</legend>' << body() << '</fieldset>'
    }

    def formButtons = { attrs, body ->
        out << '<div class="form-actions">' << body() << '</div>'
    }

    /*
    * This g:paginate tag fix is based on:
    * https://github.com/grails/grails-core/blob/master/grails-plugin-gsp/src/main/groovy/org/codehaus/groovy/grails/plugins/web/taglib/RenderTagLib.groovy
    */

    /**
     * Creates next/previous links to support pagination for the current controller.<br/>
     *
     * &lt;g:paginate total="${Account.count()}" /&gt;<br/>
     *
     * @emptyTag
     *
     * @attr total REQUIRED The total number of results to paginate
     * @attr action the name of the action to use in the link, if not specified the default action will be linked
     * @attr controller the name of the controller to use in the link, if not specified the current controller will be linked
     * @attr id The id to use in the link
     * @attr params A map containing request parameters
     * @attr prev The text to display for the previous link (defaults to "Previous" as defined by default.paginate.prev property in I18n messages.properties)
     * @attr next The text to display for the next link (defaults to "Next" as defined by default.paginate.next property in I18n messages.properties)
     * @attr max The number of records displayed per page (defaults to 10). Used ONLY if params.max is empty
     * @attr maxsteps The number of steps displayed for pagination (defaults to 10). Used ONLY if params.maxsteps is empty
     * @attr offset Used only if params.offset is empty
     * @attr fragment The link fragment (often called anchor tag) to use
     */
    def paginate = { attrs ->
//        def configTabLib = grailsApplication.config.grails.plugins.twitterbootstrap.fixtaglib
//        if (!configTabLib) {
//            def renderTagLib = grailsApplication.mainContext.getBean('org.codehaus.groovy.grails.plugins.web.taglib.RenderTagLib')
//            renderTagLib.paginate.call(attrs)
//            return
//        }

        // Added to prevent pagination when there are no other pages
        if (attrs.int('total') < attrs.int('max')) {
            return
        }

        def writer = out
        if (attrs.total == null) {
            throwTagError("Tag [paginate] is missing required attribute [total]")
        }
        def messageSource = grailsAttributes.messageSource
        def locale = RequestContextUtils.getLocale(request)

        def total = attrs.int('total') ?: 0
        def action = (attrs.action ? attrs.action : (params.action ? params.action : "list"))
        def offset = params.int('offset') ?: 0
        def max = params.int('max')
        def maxsteps = (attrs.int('maxsteps') ?: 10)

        if (!offset) offset = (attrs.int('offset') ?: 0)
        if (!max) max = (attrs.int('max') ?: 10)

        def linkParams = [:]
        if (attrs.params) linkParams.putAll(attrs.params)
        linkParams.offset = offset - max
        linkParams.max = max
        if (params.sort) linkParams.sort = params.sort
        if (params.order) linkParams.order = params.order

        def linkTagAttrs = [action: action]
        if (attrs.controller) {
            linkTagAttrs.controller = attrs.controller
        }
        if (attrs.id != null) {
            linkTagAttrs.id = attrs.id
        }
        if (attrs.fragment != null) {
            linkTagAttrs.fragment = attrs.fragment
        }
        linkTagAttrs.params = linkParams

        // determine paging variables
        def steps = maxsteps > 0
        int currentstep = (offset / max) + 1
        int firststep = 1
        int laststep = Math.round(Math.ceil(total / max))

        writer << '<div class="pagination"><ul>'
        // display previous link when not on firststep
        if (currentstep > firststep) {
            linkParams.offset = offset - max
            writer << '<li class="prev">'
            writer << link(linkTagAttrs.clone()) {
                (attrs.prev ?: messageSource.getMessage('paginate.prev', null, messageSource.getMessage('default.paginate.prev', null, 'Previous', locale), locale))
            }
            writer << '</li>'
        }
        else {
            writer << '<li class="prev disabled">'
            writer << '<span>'
            writer << (attrs.prev ?: messageSource.getMessage('paginate.prev', null, messageSource.getMessage('default.paginate.prev', null, 'Previous', locale), locale))
            writer << '</span>'
            writer << '</li>'
        }

        // display steps when steps are enabled and laststep is not firststep
        if (steps && laststep > firststep) {
            linkTagAttrs.class = 'step'

            // determine begin and endstep paging variables
            int beginstep = currentstep - Math.round(maxsteps / 2) + (maxsteps % 2)
            int endstep = currentstep + Math.round(maxsteps / 2) - 1

            if (beginstep < firststep) {
                beginstep = firststep
                endstep = maxsteps
            }
            if (endstep > laststep) {
                beginstep = laststep - maxsteps + 1
                if (beginstep < firststep) {
                    beginstep = firststep
                }
                endstep = laststep
            }

            // display firststep link when beginstep is not firststep
            if (beginstep > firststep) {
                linkParams.offset = 0
                writer << '<li>'
                writer << link(linkTagAttrs.clone()) {firststep.toString()}
                writer << '</li>'
                writer << '<li class="disabled"><span>...</span></li>'
            }

            // display paginate steps
            (beginstep..endstep).each { i ->
                if (currentstep == i) {
                    writer << "<li class=\"active\">"
                    writer << "<span>${i}</span>"
                    writer << "</li>";
                }
                else {
                    linkParams.offset = (i - 1) * max
                    writer << "<li>";
                    writer << link(linkTagAttrs.clone()) {i.toString()}
                    writer << "</li>";
                }
            }

            // display laststep link when endstep is not laststep
            if (endstep < laststep) {
                writer << '<li class="disabled"><span>...</span></li>'
                linkParams.offset = (laststep - 1) * max
                writer << '<li>'
                writer << link(linkTagAttrs.clone()) { laststep.toString() }
                writer << '</li>'
            }
        }

        // display next link when not on laststep
        if (currentstep < laststep) {
            linkParams.offset = offset + max
            writer << '<li class="next">'
            writer << link(linkTagAttrs.clone()) {
                (attrs.next ? attrs.next : messageSource.getMessage('paginate.next', null, messageSource.getMessage('default.paginate.next', null, 'Next', locale), locale))
            }
            writer << '</li>'
        }
        else {
            linkParams.offset = offset + max
            writer << '<li class="next disabled">'
            writer << '<span>'
            writer << (attrs.next ? attrs.next : messageSource.getMessage('paginate.next', null, messageSource.getMessage('default.paginate.next', null, 'Next', locale), locale))
            writer << '</span>'
            writer << '</li>'
        }

        writer << '</ul></div>'
    }

    def upcomingIndicator = { attrs ->
        def applicationDeployment = attrs.applicationDeployment

        if (applicationDeploymentService.isUpcoming(applicationDeployment)) {
            out << '<span class="label label-important" title="'
            out << message(code: 'upcoming.label')
            out << '"><i class="icon-flag icon-white"></i></span>'
        }
    }
}