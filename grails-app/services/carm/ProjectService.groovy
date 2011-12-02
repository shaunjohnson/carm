package carm

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.security.acls.model.Permission
import org.springframework.transaction.annotation.Transactional
import carm.security.User

class ProjectService {
    static transactional = false

    def carmSecurityService
//    def aclPermissionFactory
    def aclUtilService

//    void addPermission(Project project, String username, int permission) {
//        addPermission project, username, aclPermissionFactory.buildFromMask(permission)
//    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Project create(Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        if (!params.projectManagers) {
            log.error "$prefix At least one project manager must be selected"
            throw new RuntimeException("At least one project manager must be selected")
        }

        Project project = new Project(params)
        project.save()

        // Grant the list of project managers administration permission
        if (!project.hasErrors()) {
            carmSecurityService.createPermissions(project, params.projectManagers, BasePermission.ADMINISTRATION)
        }

        log.debug "$prefix returning $project"

        project
    }

    //@PreAuthorize("hasPermission(#id, 'carm.Project', read) or hasPermission(#id, 'carm.Project', admin)")
    Project get(long id) {
        Project.get id
    }

    //@PostFilter("hasPermission(filterObject, read) or hasPermission(filterObject, admin)")
    List<Project> list(Map params) {
        Project.list params
    }

    int count() {
        Project.count()
    }

    @Transactional
    @PreAuthorize("hasPermission(#project, write) or hasPermission(#project, admin)")
    void update(Project project, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        if (!params.projectManagers) {
            log.error "$prefix At least one project manager must be selected"
            throw new RuntimeException("At least one project manager must be selected")
        }
        
        project.properties = params

        // Grant the list of project managers administration permission
        if (!project.hasErrors()) {
            carmSecurityService.updatePermissions(project, params.projectManagers, BasePermission.ADMINISTRATION)
        }

        log.debug "$prefix leaving"
    }

    @Transactional
    @PreAuthorize("hasPermission(#project, delete) or hasPermission(#project, admin)")
    void delete(Project project) {
        project.delete()

        // Delete the ACL information as well
        aclUtilService.deleteAcl project
    }
}
