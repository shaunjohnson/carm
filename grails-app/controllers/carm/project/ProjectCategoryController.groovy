package carm.project

import grails.plugins.springsecurity.Secured
import carm.exceptions.DomainInUseException
import org.springframework.dao.DataIntegrityViolationException

class ProjectCategoryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def projectCategoryService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        [
                projectCategoryInstanceList: projectCategoryService.list(params),
                projectCategoryInstanceTotal: projectCategoryService.count()
        ]
    }

    @Secured(['ROLE_ADMIN'])
    def create() {
        def projectCategoryInstance = new ProjectCategory()
        projectCategoryInstance.properties = params
        return [projectCategoryInstance: projectCategoryInstance]
    }

    @Secured(['ROLE_ADMIN'])
    def save() {
        def projectCategoryInstance = projectCategoryService.create(params)
        if (!projectCategoryInstance.hasErrors()) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'projectCategory.label', default: 'ProjectCategory'), projectCategoryInstance.name])}"
            redirect(action: "show", id: projectCategoryInstance.id)
        }
        else {
            render(view: "create", model: [projectCategoryInstance: projectCategoryInstance])
        }
    }

    def show() {
        def projectCategoryInstance = projectCategoryService.get(params.id)
        if (!projectCategoryInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projectCategory.label', default: 'ProjectCategory'), params.id])}"
            redirect(action: "list")
        }
        else {
            [projectCategoryInstance: projectCategoryInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def edit() {
        def projectCategoryInstance = projectCategoryService.get(params.id)
        if (!projectCategoryInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projectCategory.label', default: 'ProjectCategory'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [projectCategoryInstance: projectCategoryInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def update() {
        def projectCategoryInstance = projectCategoryService.get(params.id)
        if (projectCategoryInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (projectCategoryInstance.version > version) {
                    projectCategoryInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'projectCategory.label', default: 'ProjectCategory')] as Object[], "Another user has updated this ProjectCategory while you were editing")
                    render(view: "edit", model: [projectCategoryInstance: projectCategoryInstance])
                    return
                }
            }
            projectCategoryService.update(projectCategoryInstance, params)
            if (!projectCategoryInstance.hasErrors() && projectCategoryInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'projectCategory.label', default: 'ProjectCategory'), projectCategoryInstance.name])}"
                redirect(action: "show", id: projectCategoryInstance.id)
            }
            else {
                render(view: "edit", model: [projectCategoryInstance: projectCategoryInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projectCategory.label', default: 'ProjectCategory'), params.id])}"
            redirect(action: "list")
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete() {
        def projectCategoryInstance = projectCategoryService.get(params.id)
        if (projectCategoryInstance) {
            def name = projectCategoryInstance.name

            try {
                projectCategoryService.delete(projectCategoryInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'projectCategory.label', default: 'ProjectCategory'), name])}"
                redirect(action: "list")
            }
            catch (DataIntegrityViolationException e) {
                flash.error = "${message(code: 'default.not.deleted.message', args: [message(code: 'projectCategory.label', default: 'ProjectCategory'), name])}"
                redirect(action: "show", id: params.id)
            }
            catch (DomainInUseException e) {
                flash.error = "${message(code: 'default.not.deleted.message', args: [message(code: 'projectCategory.label', default: 'ProjectCategory'), name])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projectCategory.label', default: 'ProjectCategory'), params.id])}"
            redirect(action: "list")
        }
    }
}
