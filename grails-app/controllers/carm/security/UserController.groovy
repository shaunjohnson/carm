package carm.security

class UserController {

    def userService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        [
                userInstanceList: userService.list(params),
                userInstanceTotal: userService.count()
        ]
    }

    def show() {
        def userInstance

        if (params.id) {
            userInstance = userService.get(params.id)
            if (!userInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
                redirect(action: "list")
            }
        }
        else if (params.username) {
            println "load by ${params.username}"
            userInstance = userService.getByUsername(params.username)
            if (!userInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.username])}"
                redirect(action: "list")
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), null])}"
            redirect(action: "list")
        }

        [userInstance: userInstance]
    }
}
