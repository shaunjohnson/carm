package carm

import carm.system.System
import carm.project.Project
import carm.release.ApplicationRelease
import carm.project.ProjectCategory
import carm.application.Application

class HomeController {
    def activityTraceService
    def projectService
    def systemService

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
                applicationReleaseInstanceList: ApplicationRelease.listOrderByApplication(),
                projectCategoryList: projectCategoryList,
                systemInstanceList: systemService.findAllByProject(myProjects),
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
