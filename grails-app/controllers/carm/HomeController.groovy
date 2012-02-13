package carm

class HomeController {
    def activityTraceService
    def projectService
    
    def index = {
        def categories = Project.executeQuery("select distinct p.category from Project p order by p.category.name")

        [
                applicationReleaseInstanceList: ApplicationRelease.listOrderByApplication(),
                projectCategoryList: categories,
                systemInstanceList: System.listOrderByName(),
                activityList: activityTraceService.listRootActivity()
        ]
    }
}
