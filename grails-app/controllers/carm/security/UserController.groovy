package carm.security

class UserController {

    def activityTraceService
    def carmSecurityService
    def favoriteService
    def userService
    def watchService

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
                flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
                redirect(action: "list")
            }
        }
        else if (params.username) {
            println "load by ${params.username}"
            userInstance = userService.getByUsername(params.username)
            if (!userInstance) {
                flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.username])}"
                redirect(action: "list")
            }
        }
        else {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), null])}"
            redirect(action: "list")
        }

        [
                userInstance: userInstance,
                activityList: activityTraceService.listActivityByUser(userInstance, [:]),
                activityCount: activityTraceService.countActivityByUser(userInstance),
                favorites: favoriteService.findAllByUser(userInstance),
                isCurrentUser: (carmSecurityService.currentUsername == userInstance.username),
                watches: watchService.findAllByUser(userInstance),
        ]
    }

    def listActivity() {
        def userInstance = userService.get(params.id)
        if (!userInstance) {
            flash.error = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
        else {
            [
                    domainInstance: userInstance,
                    activityList: activityTraceService.listActivityByUser(userInstance, params),
                    activityTotal: activityTraceService.countActivityByUser(userInstance)
            ]
        }
    }

    def ajaxShowMoreActivity() {
        def userInstance = userService.get(params.id)
        def activityList = []

        if (userInstance) {
            activityList = activityTraceService.listActivityByUser(userInstance, params)
        }

        render(template: "/common/activityBlock", model: [activityList: activityList])
    }

    def ajaxDeleteAllFavorites() {
        favoriteService.deleteAllFromCurrentUser()
        render ""
    }

    def ajaxDeleteAllWatches() {
        watchService.deleteAllFromCurrentUser()
        render ""
    }

    def ajaxRemoveFromFavorites() {
        favoriteService.deleteFromCurrentUserById(params.id)
        render ""
    }

    def ajaxRemoveFromWatches() {
        watchService.deleteFromCurrentUserById(params.id)
        render ""
    }
}
