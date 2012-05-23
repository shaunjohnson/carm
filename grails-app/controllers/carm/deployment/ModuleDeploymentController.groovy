package carm.deployment

class ModuleDeploymentController {

    def moduleDeploymentService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        [
                moduleDeploymentInstanceList: moduleDeploymentService.list(params),
                moduleDeploymentInstanceTotal: moduleDeploymentService.count()
        ]
    }

    def show() {
        def moduleDeploymentInstance = moduleDeploymentService.get(params.id)
        if (!moduleDeploymentInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'moduleDeployment.label', default: 'Module Deployment'), params.id])}"
            redirect(action: "list")
        }
        else {
            [moduleDeploymentInstance: moduleDeploymentInstance]
        }
    }
}
