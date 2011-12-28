package carm

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.transaction.annotation.Transactional

class SystemComponentService {

    static transactional = false

    int count() {
        SystemComponent.count()
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    SystemComponent create(Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        SystemComponent systemComponent = new SystemComponent(params)
        systemComponent.save()

        log.debug "$prefix returning $systemComponent"

        systemComponent
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(SystemComponent systemComponent) {
        systemComponent.delete()
    }

    SystemComponent get(long id) {
        SystemComponent.get id
    }

    List<SystemComponent> list(Map params) {
        SystemComponent.list params
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(SystemComponent systemComponent, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        systemComponent.properties = params

        log.debug "$prefix leaving"
    }
}