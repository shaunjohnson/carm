package carm

import carm.project.Project

class HomeController {
    def activityTraceService
    def projectService
    def systemEnvironmentService

    def index() {
        List<Project> myProjects = projectService.getAllProjectsWhereOwner().sort { it.category.name <=> it.name }

        def projectCategoryList = [:]
        myProjects.each { project ->
            if (projectCategoryList[project.category]) {
                projectCategoryList[project.category] << project
            }
            else {
                projectCategoryList[project.category] = [ project ]
            }
        }

        [
                projectCategoryList: projectCategoryList,
                systemInstanceList: systemEnvironmentService.findAllByProject(myProjects),
                activityList: activityTraceService.listActivityByRoot([:]),
                pendingTasks: projectService.findAllPendingTasks(myProjects)
        ]
    }

    def listActivity() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [
                activityList: activityTraceService.listActivityByRoot(params),
                activityTotal: activityTraceService.countActivityByRoot()
        ]
    }
}
