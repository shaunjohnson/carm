package carm

import org.springframework.security.acls.model.NotFoundException
import org.springframework.security.acls.model.Permission
import org.springframework.security.acls.domain.BasePermission
import carm.security.User
import org.springframework.transaction.annotation.Transactional
import org.springframework.security.access.prepost.PreAuthorize

class CarmSecurityService {

    static transactional = false

    def aclService
    def aclUtilService

    @PreAuthorize("hasPermission(#domainObject, admin)")
    @Transactional
    void addPermission(domainObject, String username, Permission permission) {
        def prefix = "addPermission() :"

        log.debug "$prefix entered. domainObject=$domainObject, username=$username, permission=$permission"

        if (!User.findByUsername(username)) {
            log.error "$prefix user $username does not exist"
            throw new RuntimeException("User $username does not exist")
        }

        aclUtilService.addPermission domainObject, username, permission

        log.debug "$prefix permission added. leaving."
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    def createPermissions(domainObject, principals, Permission permission) {
        def prefix = "createPermissions() :"

        log.debug "$prefix entered. domainObject=$domainObject, principals=$principals, permission=$permission"

        if (principals) {
            if (principals instanceof String) {
                addPermission(domainObject, principals, permission)
            }
            else {
                principals.each {
                    addPermission(domainObject, it, permission)
                }
            }
        }

        log.debug "$prefix leaving"
    }

    @Transactional
    @PreAuthorize("hasPermission(#domainObject, admin)")
    void deletePermission(domainObject, principal, Permission permission) {
        def prefix = "deletePermission() :"

        log.debug "$prefix entered. domainObject=$domainObject, principal=$principal, permission=$permission"

        def acl = aclUtilService.readAcl(domainObject)

        // Remove all permissions associated with this particular recipient (string equality to KISS)
        acl.entries.eachWithIndex { entry, i ->
            if (entry.sid.equals(principal) && entry.permission.equals(permission)) {
                acl.deleteAce i
            }
        }

        aclService.updateAcl acl

        log.debug "$prefix leaving"
    }

    @PreAuthorize("hasPermission(#domainObject, admin)")
    @Transactional
    void deleteAllPermissions(domainObject, Permission permission) {
        def prefix = "deleteAllPermissions() :"

        log.debug "$prefix entered. domainObject=$domainObject, permission=$permission"

        def acl = aclUtilService.readAcl(domainObject)

        // Remove all ACLs for this object with this permission
        acl.entries.eachWithIndex { entry, i ->
            if (entry.permission.equals(permission)) {
                acl.deleteAce i
            }
        }

        aclService.updateAcl acl

        log.debug "$prefix leaving"
    }

    @PreAuthorize("hasPermission(#domainObject, admin)")
    @Transactional
    def updatePermissions(domainObject, principals, Permission permission) {
        def prefix = "updatePermissions() :"

        log.debug "$prefix entered. domainObject=$domainObject, principals=$principals, permission=$permission"

        if (principals) {
            deleteAllPermissions(domainObject, permission)

            if (principals instanceof String) {
                addPermission domainObject, principals, BasePermission.ADMINISTRATION
            }
            else {
                principals.each {
                    addPermission domainObject, it, BasePermission.ADMINISTRATION
                }
            }
        }

        log.debug "$prefix leaving"
    }

    /**
     * Find all principals for a specific domain object for the specified permission
     *
     * @param domainObject Domain object for which ACLs are defined.
     * @param permission Permission used to filter ACLs
     * @return List of
     */
    List<String> findAllPrincipalsByDomainAndPermission(domainObject, Permission permission) {
        def prefix = "findAllPrincipalsByDomainAndPermission() : "

        log.debug "$prefix entered. domainObject=$domainObject, permission=$permission"

        List<String> principals = []

        try {
            def acl = aclUtilService.readAcl(domainObject)

            log.debug "$prefix found ${acl.entries.size()} ACL entries for $domainObject"

            acl.entries.eachWithIndex { entry, i ->
                if (entry.permission.equals(permission)) {
                    principals.add(entry.sid.principal)
                }
            }
        }
        catch (NotFoundException e) {
            log.debug "$prefix NotFoundException caught looking up users for domain object"
        }

        log.debug "$prefix returning ${principals.size()} principals"

        principals
    }
}
