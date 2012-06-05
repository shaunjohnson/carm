package carm.security

import carm.exceptions.NotFoundException
import org.springframework.transaction.annotation.Transactional
import org.springframework.security.access.prepost.PreAuthorize
import carm.exceptions.CarmRuntimeException

class CarmSecurityService {

    static transactional = false

    def permissionEvaluator
    def springSecurityService

    /**
     * Adds a new permission for the provided domain object.
     *
     * @param domainObject Domain object to which to grant access.
     * @param principals Users to be granted access
     * @param permission Permission to grant user
     */
    void createPermissions(domainObject, principals, CarmPermission permission) {
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
    void deleteAllPermissions(domainObject) {
        def prefix = "deleteAllPermissions() :"

        log.debug "$prefix entered. domainObject=$domainObject"

//        aclUtilService.deleteAcl(domainObject)

        log.debug "$prefix leaving"
    }

    /**
     * Deletes all permissions for the provided domain object, filtered by the provided permission.
     *
     * @param domainObject Domain object to delete ACL
     * @param permission Permission to be deleted
     */
    void deleteAllPermissions(domainObject, CarmPermission permission) {
        def prefix = "deleteAllPermissions() :"

        log.debug "$prefix entered. domainObject=$domainObject, permission=$permission"

//        def acl = aclUtilService.readAcl(domainObject)
//
//        // Remove all ACLs for this object with this permission
//        acl.entries.eachWithIndex { entry, i ->
//            if (entry.permission.equals(permission)) {
//                acl.deleteAce i
//            }
//        }
//
//        aclService.updateAcl acl

        log.debug "$prefix leaving"
    }

    /**
     * Deletes all permissions for the provided domain object, filtered by the provided permission.
     *
     * @param domainObject Domain object form which permissions are revoked
     * @param username User to revoke access
     * @param permission Permission to be revoked
     */
    void deletePermission(domainObject, username, CarmPermission permission) {
        def prefix = "deletePermission() :"

        log.debug "$prefix entered. domainObject=$domainObject, username=$username, permission=$permission"

//        def acl = aclUtilService.readAcl(domainObject)
//
//        // Remove all permissions associated with this particular recipient (string equality to KISS)
//        acl.entries.eachWithIndex { entry, i ->
//            if (entry.sid.equals(username) && entry.permission.equals(permission)) {
//                acl.deleteAce i
//            }
//        }
//
//        aclService.updateAcl acl

        log.debug "$prefix leaving"
    }

    /**
     * Determines if the current user has the specified permission on the provided object.
     *
     * @param domainObject Object to test
     * @param permission Permission to test
     * @return True if the current user has the permission on this domain object
     */
    boolean hasPermission(domainObject, CarmPermission permission) {
        permissionEvaluator.hasPermission(springSecurityService.authentication, domainObject, permission.name())
    }

    /**
     * Replaces existing permissions with new ones for the provided principals.
     *
     * @param domainObject Domain object to which to grant access.
     * @param principals Users to be granted access
     * @param permission Permission to grant user
     * @return
     */
    def updatePermissions(domainObject, principals, CarmPermission permission) {
        def prefix = "updatePermissions() :"

        log.debug "$prefix entered. domainObject=$domainObject, principals=$principals, permission=$permission"

        if (principals) {
            deleteAllPermissions(domainObject)

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
