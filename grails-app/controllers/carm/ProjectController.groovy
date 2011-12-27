package carm

import grails.plugins.springsecurity.Secured
import carm.security.User
import org.springframework.security.acls.domain.BasePermission

@Secured(['ROLE_ADMIN'])
class ProjectController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def carmSecurityService
    def projectService
    def springSecurityService

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [projectInstanceList: projectService.list(params), projectInstanceTotal: projectService.count()]
    }

    def create = {
        def projectInstance = new Project()
        projectInstance.properties = params
        def projectManagers = [springSecurityService.authentication.name]
        return [projectInstance: projectInstance, projectManagers: projectManagers, projectManagerList: User.listOrderByUsername()]
    }

    def save = {
        def projectInstance = projectService.create(params)
        if (!projectInstance.hasErrors()) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'project.label', default: 'Project'), projectInstance.id])}"
            redirect(action: "show", id: projectInstance.id)
        }
        else {
            render(view: "create", model: [projectInstance: projectInstance, projectManagers: params.projectManagers, projectManagerList: User.listOrderByUsername()])
        }
    }

    def show = {
        def projectInstance = projectService.get(params.id?.toLong())
        if (!projectInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
            redirect(action: "list")
        }
        else {
            [projectInstance: projectInstance,
                    applicationsGrouped: projectService.getApplicationsGroupedByType(projectInstance)]
        }
    }

    def edit = {
        def projectInstance = projectService.get(params.id?.toLong())
        if (!projectInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
            redirect(action: "list")
        }
        else {
            def projectManagers = carmSecurityService.findAllPrincipalsByDomainAndPermission(projectInstance, BasePermission.ADMINISTRATION)
            return [projectInstance: projectInstance, projectManagers: projectManagers, projectManagerList: User.listOrderByUsername()]
        }
    }

    def update = {
        def projectInstance = projectService.get(params.id?.toLong())
        if (projectInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (projectInstance.version > version) {
                    projectInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'project.label', default: 'Project')] as Object[], "Another user has updated this Project while you were editing")
                    render(view: "edit", model: [projectInstance: projectInstance, projectManagerList: User.listOrderByUsername()])
                    return
                }
            }
            projectService.update(projectInstance, params)
            if (!projectInstance.hasErrors() && projectInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'project.label', default: 'Project'), projectInstance.id])}"
                redirect(action: "show", id: projectInstance.id)
            }
            else {
                render(view: "edit", model: [projectInstance: projectInstance, projectManagerList: User.listOrderByUsername()])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def projectInstance = projectService.get(params.id?.toLong())
        if (projectInstance) {
            try {
                projectService.delete(projectInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
            redirect(action: "list")
        }
    }
}
