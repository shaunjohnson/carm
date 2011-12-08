package carm

class HomeController {
    def projectService
    
    def index = {
        [applicationReleaseInstanceList: ApplicationRelease.listOrderByApplication(),
                projectInstanceList: Project.listOrderByName(),
                systemInstanceList: System.listOrderByName()]
    }
}
