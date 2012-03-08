package carm.release

class ApplicationReleaseHistoryController {

    def applicationReleaseHistoryService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [
                applicationReleaseHistoryInstanceList: applicationReleaseHistoryService.list(params),
                applicationReleaseHistoryInstanceTotal: applicationReleaseHistoryService.count()
        ]
    }

    def show() {
        def applicationReleaseHistoryInstance = applicationReleaseHistoryService.get(params.id)
        if (!applicationReleaseHistoryInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'applicationReleaseHistory.label', default: 'Application Release History'), params.id])}"
            redirect(action: "list")
        }
        else {
            [applicationReleaseHistoryInstance: applicationReleaseHistoryInstance]
        }
    }
}
