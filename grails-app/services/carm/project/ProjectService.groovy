package carm.project

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.transaction.annotation.Transactional
import carm.application.Application
import carm.security.User

class ProjectService {
    static transactional = false

    def applicationDeploymentService
    def applicationReleaseService
    def carmSecurityService

    int count() {
        Project.count()
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Project create(Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        if (!params.projectOwners) {
            log.error "$prefix At least one project manager must be selected"
            throw new RuntimeException("At least one project manager must be selected")
        }

        Project project = new Project(params)
        project.save()

        // Grant the list of project owners administration permission
        if (!project.hasErrors()) {
            carmSecurityService.createPermissions(project, params.projectOwners, BasePermission.ADMINISTRATION)
        }

        log.debug "$prefix returning $project"

        project
    }

    @Transactional
    @PreAuthorize("hasPermission(#project, delete) or hasPermission(#project, admin)")
    void delete(Project project) {
        project.delete()

        carmSecurityService.deleteAllPermissions(project)
    }

    Project get(long id) {
        Project.get id
    }

    List<Project> list(Map params) {
        Project.list params
    }

    @Transactional
    @PreAuthorize("hasPermission(#project, write) or hasPermission(#project, admin)")
    void update(Project project, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        if (!params.projectOwners) {
            log.error "$prefix At least one project owner must be selected"
            throw new RuntimeException("At least one project owner must be selected")
        }

        project.properties = params

        // Grant the list of project owners administration permission
        if (!project.hasErrors()) {
            carmSecurityService.updatePermissions(project, params.projectOwners, BasePermission.ADMINISTRATION)
        }

        log.debug "$prefix leaving"
    }

    /**
     * Finds all pending deployments and releases for the provided Project.
     *
     * @param project Project used for filtering
     * @return List of ApplicationDeployment and ApplicationRelease objects ordered by dateCreated ascending
     */
    List findAllPendingTasks(Project project) {
        def pendingTasks = []

        pendingTasks.addAll applicationDeploymentService.findAllPendingDeploymentsByProject(project)
        pendingTasks.addAll applicationReleaseService.findAllPendingReleasesByProject(project)

        pendingTasks.sort { it.dateCreated }
    }

    @PostFilter("hasPermission(filterObject, admin)")
    List<Project> getAllProjectsWhereOwner(Map params) {
        Project.list params
    }

    List findAllPendingTasks(List<Project> projects) {
        def pendingTasks = []

        projects.each { project ->
            pendingTasks.addAll applicationDeploymentService.findAllPendingDeploymentsByProject(project)
            pendingTasks.addAll applicationReleaseService.findAllPendingReleasesByProject(project)
        }

        pendingTasks.sort { it.dateCreated }
    }

    List findAllPendingTasks() {
        def pendingTasks = []

        Project.list().each { project ->
            pendingTasks.addAll applicationDeploymentService.findAllPendingDeploymentsByProject(project)
            pendingTasks.addAll applicationReleaseService.findAllPendingReleasesByProject(project)
        }

        pendingTasks.sort { it.dateCreated }
    }
}
