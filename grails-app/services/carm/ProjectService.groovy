package carm

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.security.acls.model.Permission
import org.springframework.transaction.annotation.Transactional

class ProjectService {

    static transactional = false

    def aclPermissionFactory
    def aclService
    def aclUtilService
    def springSecurityService

    void addPermission(Project project, String username, int permission) {
        addPermission project, username, aclPermissionFactory.buildFromMask(permission)
    }

    @PreAuthorize("hasPermission(#project, admin)")
    @Transactional
    void addPermission(Project project, String username, Permission permission) {
        aclUtilService.addPermission project, username, permission
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Project create(Map params) {
        Project project = new Project(params)
        project.save()

        // Grant the current principal administrative permission
        addPermission project, springSecurityService.authentication.name, BasePermission.ADMINISTRATION

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
        project.properties = params
    }

    @Transactional
    @PreAuthorize("hasPermission(#project, delete) or hasPermission(#project, admin)")
    void delete(Project project) {
        project.delete()

        // Delete the ACL information as well
        aclUtilService.deleteAcl project
    }

    @Transactional
    @PreAuthorize("hasPermission(#project, admin)")
    void deletePermission(Project project, String username, Permission permission) {
        def acl = aclUtilService.readAcl(project)

        // Remove all permissions associated with this particular recipient (string equality to KISS)
        acl.entries.eachWithIndex { entry, i ->
            if (entry.sid.equals(recipient) && entry.permission.equals(permission)) {
                acl.deleteAce i
            }
        }

        aclService.updateAcl acl
    }
}
