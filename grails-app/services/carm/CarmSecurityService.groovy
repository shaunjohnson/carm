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

    /**
     * Adds a new permission for the provided domain object.
     *
     * @param domainObject Domain object to which to grant access.
     * @param username User to be granted access
     * @param permission Permission to grant user
     */
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

    /**
     * Adds a new permission for the provided domain object.
     *
     * @param domainObject Domain object to which to grant access.
     * @param principals Users to be granted access
     * @param permission Permission to grant user
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void createPermissions(domainObject, principals, Permission permission) {
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

    /**
     * Deletes all permissions for a domain object. Effectively removing the ACL for the domain object.
     *
     * @param domainObject Domain object to delete ACL.
     */
    @PreAuthorize("hasPermission(#domainObject, admin)")
    @Transactional
    void deleteAllPermissions(domainObject) {
        def prefix = "deleteAllPermissions() :"

        log.debug "$prefix entered. domainObject=$domainObject"

        aclUtilService.deleteAcl(domainObject)

        log.debug "$prefix leaving"
    }

    /**
     * Deletes all permissions for the provided domain object, filtered by the provided permission.
     *
     * @param domainObject Domain object to delete ACL
     * @param permission Permission to be deleted
     */
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

    /**
     * Deletes all permissions for the provided domain object, filtered by the provided permission.
     *
     * @param domainObject Domain object form which permissions are revoked
     * @param username User to revoke access
     * @param permission Permission to be revoked
     */
    @Transactional
    @PreAuthorize("hasPermission(#domainObject, admin)")
    void deletePermission(domainObject, username, Permission permission) {
        def prefix = "deletePermission() :"

        log.debug "$prefix entered. domainObject=$domainObject, username=$username, permission=$permission"

        def acl = aclUtilService.readAcl(domainObject)

        // Remove all permissions associated with this particular recipient (string equality to KISS)
        acl.entries.eachWithIndex { entry, i ->
            if (entry.sid.equals(username) && entry.permission.equals(permission)) {
                acl.deleteAce i
            }
        }

        aclService.updateAcl acl

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

        principals.sort()
    }

    /**
     * Replaces existing permissions with new ones for the provided principals.
     *
     * @param domainObject Domain object to which to grant access.
     * @param principals Users to be granted access
     * @param permission Permission to grant user
     * @return
     */
    @PreAuthorize("hasPermission(#domainObject, admin)")
    @Transactional
    def updatePermissions(domainObject, principals, Permission permission) {
        def prefix = "updatePermissions() :"

        log.debug "$prefix entered. domainObject=$domainObject, principals=$principals, permission=$permission"

        if (principals) {
            deleteAllPermissions(domainObject, permission)

            if (principals instanceof String) {
                addPermission domainObject, principals, permission
            }
            else {
                principals.each {
                    addPermission domainObject, it, permission
                }
            }
        }

        log.debug "$prefix leaving"
    }
}
