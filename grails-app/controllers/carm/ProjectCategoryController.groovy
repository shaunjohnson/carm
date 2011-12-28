package carm

class ProjectCategoryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def projectCategoryService

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [projectCategoryInstanceList: projectCategoryService.list(params), projectCategoryInstanceTotal: projectCategoryService.count()]
    }

    def create = {
        def projectCategoryInstance = new ProjectCategory()
        projectCategoryInstance.properties = params
        return [projectCategoryInstance: projectCategoryInstance]
    }

    def save = {
        def projectCategoryInstance =projectCategoryService.create(params)
        if (projectCategoryInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'projectCategory.label', default: 'ProjectCategory'), projectCategoryInstance.id])}"
            redirect(action: "show", id: projectCategoryInstance.id)
        }
        else {
            render(view: "create", model: [projectCategoryInstance: projectCategoryInstance])
        }
    }

    def show = {
        def projectCategoryInstance = projectCategoryService.get(params.id?.toLong())
        if (!projectCategoryInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projectCategory.label', default: 'ProjectCategory'), params.id])}"
            redirect(action: "list")
        }
        else {
            [projectCategoryInstance: projectCategoryInstance]
        }
    }

    def edit = {
        def projectCategoryInstance = projectCategoryService.get(params.id?.toLong())
        if (!projectCategoryInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projectCategory.label', default: 'ProjectCategory'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [projectCategoryInstance: projectCategoryInstance]
        }
    }

    def update = {
        def projectCategoryInstance = projectCategoryService.get(params.id?.toLong())
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
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'projectCategory.label', default: 'ProjectCategory'), projectCategoryInstance.id])}"
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

    def delete = {
        def projectCategoryInstance = projectCategoryService.get(params.id?.toLong())
        if (projectCategoryInstance) {
            try {
                projectCategoryService.delete(projectCategoryInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'projectCategory.label', default: 'ProjectCategory'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'projectCategory.label', default: 'ProjectCategory'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projectCategory.label', default: 'ProjectCategory'), params.id])}"
            redirect(action: "list")
        }
    }
}
