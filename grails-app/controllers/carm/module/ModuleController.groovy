package carm.module

import org.springframework.dao.DataIntegrityViolationException

class ModuleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]

    def activityTraceService
    def applicationService
    def grailsApplication
    def moduleService
    def moduleTypeService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        [
                moduleInstanceList: moduleService.list(params),
                moduleInstanceTotal: moduleService.count()
        ]
    }

    def create() {
        def applicationInstance = applicationService.get(params.application?.id)
        if (!applicationInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.application?.id])}"
            redirect(action: "list")
        }
        else {
            def moduleInstance = new Module()
            moduleInstance.properties = params

            [
                    moduleInstance: moduleInstance,
                    moduleTypeList: moduleTypeService.list()
            ]
        }
    }

    def save() {
        def applicationInstance = applicationService.get(params.application.id)
        if (!applicationInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.application.id])}"
            redirect(action: "list")
        }
        else {
            def moduleInstance = moduleService.create(applicationInstance.project, params)
            if (!moduleInstance.hasErrors()) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'module.label', default: 'Module'), moduleInstance.name])}"
                redirect(controller: "application", action: "show", id: moduleInstance.application.id)
            }
            else {
                render(view: "create", model:
                        [
                                moduleInstance: moduleInstance,
                                moduleTypeList: moduleTypeService.list()
                        ])
            }
        }
    }

    def show() {
        def moduleInstance = moduleService.get(params.id)
        if (!moduleInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'module.label', default: 'Module'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    moduleInstance: moduleInstance,
                    activityList: activityTraceService.listActivityByModule(moduleInstance, [:])
            ]
        }
    }

    def edit() {
        def moduleInstance = moduleService.get(params.id)
        if (!moduleInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'module.label', default: 'Module'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    moduleInstance: moduleInstance,
                    moduleTypeList: moduleTypeService.list()
            ]
        }
    }

    def update() {
        def moduleInstance = moduleService.get(params.id)
        if (moduleInstance) {
            if (params.version) {
                def version = params.version.toLong()

                if (moduleInstance.version > version) {
                    moduleInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'module.label', default: 'Module')] as Object[], "Another user has updated this Module while you were editing")
                    render(view: "edit", model: [
                            moduleInstance: moduleInstance,
                            moduleTypeList: moduleTypeService.list()
                    ])
                    return
                }
            }

            def applicationInstance = applicationService.get(params.application.id)
            if (!applicationInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.application.id])}"
                redirect(action: "list")
            }
            else {
                moduleService.update(applicationInstance.project, moduleInstance, params)
                if (!moduleInstance.hasErrors() && moduleInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'module.label', default: 'Module'), moduleInstance.name])}"
                    redirect(action: "show", id: moduleInstance.id)
                }
                else {
                    render(view: "edit", model: [
                            moduleInstance: moduleInstance,
                            moduleTypeList: moduleTypeService.list()
                    ])
                }
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'module.label', default: 'Module'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete() {
        def moduleInstance = moduleService.get(params.id)
        if (moduleInstance) {
            def applicationId = moduleInstance.application.id
            try {
                def name = moduleInstance.name
                moduleService.delete(moduleInstance.application.project, moduleInstance)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'module.label', default: 'Module'), name])}"
                redirect(controller: "application", action: "show", id: applicationId)
            }
            catch (DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'module.label', default: 'Module'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'module.label', default: 'Module'), params.id])}"
            redirect(action: "list")
        }
    }

    def listActivity() {
        def moduleInstance = moduleService.get(params.id)
        if (!moduleInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'module.label', default: 'Module'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    domainInstance: moduleInstance,
                    activityList: activityTraceService.listActivityByModule(moduleInstance, params),
                    activityTotal: activityTraceService.countActivityByModule(moduleInstance)
            ]
        }
    }

    def ajaxShowMoreActivity() {
        def moduleInstance = moduleService.get(params.id)
        def activityList = []

        if (!moduleInstance) {
            activityList = activityTraceService.listActivityByModule(moduleInstance, params)
        }

        render(template: "/common/activityBlock", model: [activityList: activityList])
    }
}
