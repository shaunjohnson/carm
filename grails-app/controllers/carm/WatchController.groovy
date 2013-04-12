package carm

class WatchController {
    def watchService

    def index() { }

    def ajaxAddApplicationToWatches() {
        watchService.addApplicationToCurrentUserWatches(params.id)
        render ""
    }

    def ajaxAddProjectToWatches() {
        watchService.addProjectToCurrentUserWatches(params.id)
        render ""
    }

    def ajaxRemoveApplicationFromWatches() {
        watchService.removeApplicationFromCurrentUserWatches(params.id)
        render ""
    }

    def ajaxRemoveProjectFromWatches() {
        watchService.removeProjectFromCurrentUserWatches(params.id)
        render ""
    }
}
