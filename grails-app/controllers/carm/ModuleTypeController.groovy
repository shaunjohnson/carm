package carm

import grails.plugins.springsecurity.Secured

class ModuleTypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]
    
    def moduleTypeService

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [moduleTypeInstanceList: moduleTypeService.list(params), moduleTypeInstanceTotal: moduleTypeService.count()]
    }

    @Secured(['ROLE_ADMIN'])
    def create = {
        def moduleTypeInstance = new ModuleType()
        moduleTypeInstance.properties = params
        return [moduleTypeInstance: moduleTypeInstance]
    }

    @Secured(['ROLE_ADMIN'])
    def save = {
        def moduleTypeInstance = moduleTypeService.create(params)
        if (!moduleTypeInstance.hasErrors()) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'moduleType.label', default: 'ModuleType'), moduleTypeInstance.id])}"
            redirect(action: "show", id: moduleTypeInstance.id)
        }
        else {
            render(view: "create", model: [moduleTypeInstance: moduleTypeInstance])
        }
    }

    def show = {
        def moduleTypeInstance = moduleTypeService.get(params.id?.toLong())
        if (!moduleTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'moduleType.label', default: 'ModuleType'), params.id])}"
            redirect(action: "list")
        }
        else {
            [moduleTypeInstance: moduleTypeInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def edit = {
        def moduleTypeInstance = moduleTypeService.get(params.id?.toLong())
        if (!moduleTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'moduleType.label', default: 'ModuleType'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [moduleTypeInstance: moduleTypeInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def update = {
        def moduleTypeInstance = moduleTypeService.get(params.id?.toLong())
        if (moduleTypeInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (moduleTypeInstance.version > version) {
                    
                    moduleTypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'moduleType.label', default: 'ModuleType')] as Object[], "Another user has updated this ModuleType while you were editing")
                    render(view: "edit", model: [moduleTypeInstance: moduleTypeInstance])
                    return
                }
            }
            moduleTypeService.update(moduleTypeInstance, params)
            if (!moduleTypeInstance.hasErrors() && moduleTypeInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'moduleType.label', default: 'ModuleType'), moduleTypeInstance.id])}"
                redirect(action: "show", id: moduleTypeInstance.id)
            }
            else {
                render(view: "edit", model: [moduleTypeInstance: moduleTypeInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'moduleType.label', default: 'ModuleType'), params.id])}"
            redirect(action: "list")
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete = {
        def moduleTypeInstance = moduleTypeService.get(params.id?.toLong())
        if (moduleTypeInstance) {
            try {
                moduleTypeService.delete(moduleTypeInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'moduleType.label', default: 'ModuleType'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'moduleType.label', default: 'ModuleType'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'moduleType.label', default: 'ModuleType'), params.id])}"
            redirect(action: "list")
        }
    }
}
