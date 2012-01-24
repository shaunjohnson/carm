package carm

class HomeController {
    def projectService
    
    def index = {
        def categories = Project.executeQuery("select distinct p.category from Project p order by p.category.name")

        [
                applicationReleaseInstanceList: ApplicationRelease.listOrderByApplication(),
                projectCategoryList: categories,
                systemInstanceList: System.listOrderByName()
        ]
    }
}
