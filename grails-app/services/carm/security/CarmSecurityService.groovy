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
     * Determines if the current user has the specified permission on the provided object.
     *
     * @param domainObject Object to test
     * @param permission Permission to test
     * @return True if the current user has the permission on this domain object
     */
    boolean hasPermission(domainObject, CarmPermission permission) {
        permissionEvaluator.hasPermission(springSecurityService.authentication, domainObject, permission.name())
    }
}
