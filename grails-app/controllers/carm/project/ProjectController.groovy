package carm.project

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

class ProjectController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def activityTraceService
    def applicationReleaseService
    def applicationService
    def carmSecurityService
    def projectService
    def projectCategoryService
    def springSecurityService
    def userGroupService
    def userService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        [
                projectInstanceList: projectService.list(params),
                projectInstanceTotal: projectService.count()
        ]
    }

    def create() {
        def projectInstance = new Project()
        projectInstance.properties = params

        [
                projectCategoryList: projectCategoryService.list(),
                projectInstance: projectInstance
        ]
    }

    def save() {
        def projectInstance = projectService.create(params)
        if (!projectInstance.hasErrors()) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'project.label', default: 'Project'), projectInstance.name])}"
            redirect(action: "show", id: projectInstance.id)
        }
        else {
            render(view: "create", model: [
                    projectCategoryList: projectCategoryService.list(),
                    projectInstance: projectInstance
            ])
        }
    }

    def show() {
        def projectInstance = projectService.get(params.id)
        if (!projectInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    projectInstance: projectInstance,
                    activityList: activityTraceService.listActivityByProject(projectInstance, [:]),
                    activityCount: activityTraceService.countActivityByProject(projectInstance),
                    applicationsGrouped: applicationService.findAllByProjectGroupedByType(projectInstance),
                    pendingTasks: projectService.findAllPendingTasks(projectInstance),
                    projectAdministratorGroups: projectService.findAllProjectAdministratorGroups(projectInstance),
                    projectAdministratorUsers: projectService.findAllProjectAdministratorUsers(projectInstance)
            ]
        }
    }

    def edit() {
        def projectInstance = projectService.get(params.id)
        if (!projectInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    projectCategoryList: projectCategoryService.list(),
                    projectInstance: projectInstance
            ]
        }
    }

    def update() {
        def projectInstance = projectService.get(params.id)
        if (projectInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (projectInstance.version > version) {
                    projectInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'project.label', default: 'Project')] as Object[], "Another user has updated this Project while you were editing")
                    render(view: "edit", model: [
                            projectCategoryList: projectCategoryService.list(),
                            projectInstance: projectInstance])
                    return
                }
            }
            projectService.update(projectInstance, params)
            if (!projectInstance.hasErrors() && projectInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'project.label', default: 'Project'), projectInstance.name])}"
                redirect(action: "show", id: projectInstance.id)
            }
            else {
                render(view: "edit", model: [
                        projectCategoryList: projectCategoryService.list(),
                        projectInstance: projectInstance])
            }
        }
        else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete() {
        def projectInstance = projectService.get(params.id)
        if (projectInstance) {
            def name = projectInstance.name

            try {
                projectService.delete(projectInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'project.label', default: 'Project'), name])}"
                redirect(action: "list")
            }
            catch (DataIntegrityViolationException e) {
                flash.error = "${message(code: 'default.not.deleted.message', args: [message(code: 'project.label', default: 'Project'), name])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
            redirect(action: "list")
        }
    }

    def listActivity() {
        def projectInstance = projectService.get(params.id)
        if (!projectInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    domainInstance: projectInstance,
                    activityList: activityTraceService.listActivityByProject(projectInstance, params),
                    activityTotal: activityTraceService.countActivityByProject(projectInstance)
            ]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def addAdministrator() {
        def projectInstance = projectService.get(params.id)
        if (!projectInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    projectInstance: projectInstance,
                    userGroupList: userGroupService.listAll(),
                    userList: userService.listAll()
            ]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def addAdministratorSave() {
        def projectInstance = projectService.get(params.id)
        if (projectInstance) {
            if (params.groupId) {
                projectService.addAdministratorGroup(projectInstance, userGroupService.get(params.groupId))
            }
            else if (params.userId) {
                projectService.addAdministratorUser(projectInstance, userService.get(params.userId))
            }

            if (!projectInstance.hasErrors()) {
                flash.message = "${message(code: 'default.addedAdministrator.message', args: [message(code: 'project.label', default: 'Project'), projectInstance.name])}"
                redirect(action: "show", id: projectInstance.id)
            }
            else {
                render(view: "addAdministrator", model: [
                        projectInstance: projectInstance,
                        userGroupList: userGroupService.listAll(),
                        userList: userService.listAll()
                ])
            }
        }
        else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'userGroup.label', default: 'User Group'), params.id])}"
            redirect(action: "list")
        }
    }

    @Secured(['ROLE_ADMIN'])
    def ajaxRemoveAdministratorGroup() {
        def projectInstance = projectService.get(params.id)
        if (projectInstance) {
            projectService.removeAdministratorGroup(projectInstance, params.userGroupId)
        }

        render ""
    }

    @Secured(['ROLE_ADMIN'])
    def ajaxRemoveAdministratorUser() {
        def projectInstance = projectService.get(params.id)
        if (projectInstance) {
            projectService.removeAdministratorUser(projectInstance, params.userId)
        }

        render ""
    }

    def ajaxShowMoreActivity() {
        def projectInstance = projectService.get(params.id)
        def activityList = []

        if (projectInstance) {
            activityList = activityTraceService.listActivityByProject(projectInstance, params)
        }

        render(template: "/common/activityBlock", model: [activityList: activityList])
    }
}
