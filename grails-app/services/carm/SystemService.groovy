package carm

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.transaction.annotation.Transactional

class SystemService {

    static transactional = false

    int count() {
        System.count()
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    System create(Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        System system = new System(params)
        system.save()

        log.debug "$prefix returning $system"

        system
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(System system) {
        system.delete()
    }

    System get(long id) {
        System.get id
    }

    List<System> list(Map params) {
        System.list params
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void moveEnvironmentDown(System system, int index) {
        def environments = system.environments

        if (index != null && (index + 1) < environments.size()) {
            def environment = environments[index]
            environments.remove(index)
            environments.add(index + 1, environment)
            system.save()
        }
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void moveEnvironmentUp(System system, int index) {
        def environments = system.environments

        if (index != null && index > 0) {
            def environment = environments[index]
            environments.remove(index)
            environments.add(index - 1, environment)
            system.save()
        }
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(System system, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        system.properties = params

        log.debug "$prefix leaving"
    }
}