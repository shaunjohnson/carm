package carm

class HomeController {
    def projectService
    
    def index = {
        [
                applicationReleaseInstanceList: ApplicationRelease.listOrderByApplication(),
                projectCategoryList: ProjectCategory.listOrderByName(),
                systemInstanceList: System.listOrderByName()
        ]
    }
}
