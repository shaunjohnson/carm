package net.lmxm.carm

class HomeController {
    def index = {
        [applicationReleaseInstanceList: ApplicationRelease.listOrderByApplication(),
                projectInstanceList: Project.listOrderByName()]
    }

    def showApplication = {
        def applicationInstance = Application.get(params.id)
        if (!applicationInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'application.label', default: 'Application'), params.id])}"
            redirect(action: "index")
        }
        else {
            [applicationInstance: applicationInstance]
        }
    }

    def showModule = {
        def moduleInstance = Module.get(params.id)
        if (!moduleInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'module.label', default: 'Module'), params.id])}"
            redirect(action: "index")
        }
        else {
            [moduleInstance: moduleInstance]
        }
    }

    def showProject = {
        def projectInstance = Project.get(params.id)
        if (!projectInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])}"
            redirect(action: "index")
        }
        else {
            [projectInstance: projectInstance]
        }
    }

    def showSystem = {
        def systemInstance = System.get(params.id)
        if (!systemInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'system.label', default: 'System'), params.id])}"
            redirect(action: "index")
        }
        else {
            [systemInstance: systemInstance]
        }
    }

    def showSystemComponent = {
        def systemComponentInstance = SystemComponent.get(params.id)
        if (!systemComponentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemComponent.label', default: 'SystemComponent'), params.id])}"
            redirect(action: "index")
        }
        else {
            [systemComponentInstance: systemComponentInstance]
        }
    }

    def showSystemEnvironment = {
        def systemEnvironmentInstance = SystemEnvironment.get(params.id)
        if (!systemEnvironmentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment'), params.id])}"
            redirect(action: "index")
        }
        else {
            [systemEnvironmentInstance: systemEnvironmentInstance]
        }
    }
}
