package carm

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.transaction.annotation.Transactional

class SystemEnvironmentService {

    static transactional = false

    int count() {
        SystemEnvironment.count()
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    SystemEnvironment create(Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        SystemEnvironment systemEnvironment = new SystemEnvironment(params)

        def system = systemEnvironment.system
        system.addToEnvironments(systemEnvironment)

        systemEnvironment.save()

        log.debug "$prefix returning $systemEnvironment"

        systemEnvironment
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(SystemEnvironment systemEnvironment) {
        systemEnvironment.delete()
    }

    SystemEnvironment get(long id) {
        SystemEnvironment.get id
    }

    List<SystemEnvironment> list(Map params) {
        SystemEnvironment.list params
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(SystemEnvironment systemEnvironment, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        systemEnvironment.properties = params

        log.debug "$prefix leaving"
    }
}