package carm

import carm.system.System
import carm.project.Project
import carm.release.ApplicationRelease

class HomeController {
    def activityTraceService
    def projectService

    def index = {
        def categories = Project.executeQuery("select distinct p.category from Project p order by p.category.name")

        [
                applicationReleaseInstanceList: ApplicationRelease.listOrderByApplication(),
                projectCategoryList: categories,
                systemInstanceList: System.listOrderByName(),
                activityList: activityTraceService.listActivityByRoot([:])
        ]
    }

    def listActivity = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [
                activityList: activityTraceService.listActivityByRoot(params),
                activityTotal: activityTraceService.countActivityByRoot()
        ]
    }
}
