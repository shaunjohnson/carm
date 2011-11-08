package net.lmxm.carm

class HomeController {
    def index = {
        [applicationReleaseInstanceList: ApplicationRelease.listOrderByApplication(),
                projectInstanceList: Project.listOrderByName()]
    }
}
