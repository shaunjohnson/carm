package carm

class ModuleDeploymentController {

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [moduleDeploymentInstanceList: ModuleDeployment.list(params), moduleDeploymentInstanceTotal: ModuleDeployment.count()]
    }

    def show = {
        def moduleDeploymentInstance = ModuleDeployment.get(params.id?.toLong())
        if (!moduleDeploymentInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'moduleDeployment.label', default: 'Module Deployment'), params.id])}"
            redirect(action: "list")
        }
        else {
            [moduleDeploymentInstance: moduleDeploymentInstance]
        }
    }
}
