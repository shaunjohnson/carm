package carm.security

import carm.exceptions.NotFoundException
import org.springframework.transaction.annotation.Transactional
import org.springframework.security.access.prepost.PreAuthorize
import carm.exceptions.CarmRuntimeException

class CarmSecurityService {

    static transactional = false

    def permissionEvaluator
    def springSecurityService

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteAllAclsByUser(User user) {
        AclUserEntry.executeUpdate("delete from AclUserEntry where user = :user", [user: user])
    }

    /**
     * Adds a new permission for the provided domain object.
     *
     * @param domainObject Domain object to which to grant access.
     * @param username User to be granted access
     * @param permission Permission to grant user
     */
    @PreAuthorize("hasPermission(#domainObject, admin)")
    @Transactional
    void addPermission(domainObject, String username, CarmPermission permission) {
        def prefix = "addPermission() :"

        log.debug "$prefix entered. domainObject=$domainObject, username=$username, permission=$permission"

        if (!User.findByUsername(username)) {
            log.error "$prefix user $username does not exist"
            throw new CarmRuntimeException("User $username does not exist")
        }

//        aclUtilService.addPermission domainObject, username, permission

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
    @PreAuthorize("hasPermission(#domainObject, admin)")
    @Transactional
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
    @PreAuthorize("hasPermission(#domainObject, admin)")
    @Transactional
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
    @Transactional
    @PreAuthorize("hasPermission(#domainObject, admin)")
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
     * Find all principals for a specific domain object for the specified permission
     *
     * @param domainObject Domain object for which ACLs are defined.
     * @param permission Permission used to filter ACLs
     * @return List of
     */
    List<String> findAllPrincipalsByDomainAndPermission(domainObject, CarmPermission permission) {
        def prefix = "findAllPrincipalsByDomainAndPermission() : "

        log.debug "$prefix entered. domainObject=$domainObject, permission=$permission"

        List<String> principals = []

//        try {
//            def acl = aclUtilService.readAcl(domainObject)
//
//            log.debug "$prefix found ${acl.entries.size()} ACL entries for $domainObject"
//
//            acl.entries.eachWithIndex { entry, i ->
//                if (entry.permission.equals(permission)) {
//                    principals.add(entry.sid.principal)
//                }
//            }
//        }
//        catch (NotFoundException e) {
//            log.debug "$prefix NotFoundException caught looking up users for domain object"
//        }

        log.debug "$prefix returning ${principals.size()} principals"

        principals.sort()
    }

    /**
     * Find all users by username in list.
     *
     * @param usernames Username list used for filtering
     * @return List of User objects
     */
    List<User> findAllUsersByUsernameInList(List<String> usernames) {
        User.findAllByUsernameInList(usernames)
    }

    /**
     * Finds a user by username.
     *
     * @param username Username for user to find
     * @return Matching User object
     */
    User findUserByUsername(String username) {
        User.findByUsername(username)
    }
    
    /**
     * Finds all user email addresses by username in list
     *
     * @param usernames Username list used for filtering
     * @return List of email addresses
     */
    List<String> findAllEmailByUsernameInList(List<String> usernames) {
        findAllUsersByUsernameInList(usernames)*.email
    }
    
    /**
     * Gets the current User.
     *
     * @return Current User object.
     */
    User getCurrentUser() {
        (User)springSecurityService.currentUser
    }
    
    /**
     * Gets the current username (principle).
     *
     * @return Current username
     */
    String getCurrentUsername() {
        springSecurityService.isLoggedIn() ? springSecurityService.principal.username : "Unknown"
    }

    /**
     * Determines if the current user has the specified permission on the provided object.
     *
     * @param domainObject Object to test
     * @param permission Permission to test
     * @return True if the current user has the permission on this domain object
     */
    boolean hasPermission(domainObject, String permission) {
        // aclUtilService.hasPermission(springSecurityService.authentication, domainObject, permission)
        permissionEvaluator.hasPermission(springSecurityService.authentication, domainObject, permission)

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
