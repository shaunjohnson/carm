package carm

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.transaction.annotation.Transactional

class ProjectService {
    static transactional = false

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
}
