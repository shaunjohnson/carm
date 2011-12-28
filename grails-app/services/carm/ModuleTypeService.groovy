package carm

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.transaction.annotation.Transactional

class ModuleTypeService {

    static transactional = false

    int count() {
        ModuleType.count()
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ModuleType create(Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        ModuleType moduleType = new ModuleType(params)
        moduleType.save()

        log.debug "$prefix returning $moduleType"

        moduleType
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(ModuleType moduleType) {
        moduleType.delete()
    }

    ModuleType get(long id) {
        ModuleType.get id
    }

    List<ModuleType> list(Map params) {
        ModuleType.list params
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(ModuleType moduleType, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        moduleType.properties = params

        log.debug "$prefix leaving"
    }
}
