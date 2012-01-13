package carm

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.transaction.annotation.Transactional

class ModuleDeploymentTestStateService {

    static transactional = false

    int count() {
        ModuleDeploymentTestState.count()
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ModuleDeploymentTestState create(Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        ModuleDeploymentTestState moduleDeploymentTestState = new ModuleDeploymentTestState(params)
        moduleDeploymentTestState.save()

        log.debug "$prefix returning $moduleDeploymentTestState"

        moduleDeploymentTestState
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(ModuleDeploymentTestState moduleDeploymentTestState) {
        moduleDeploymentTestState.delete()
    }

    ModuleDeploymentTestState get(long id) {
        ModuleDeploymentTestState.get id
    }

    List<ModuleDeploymentTestState> list(Map params) {
        ModuleDeploymentTestState.list params
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(ModuleDeploymentTestState moduleDeploymentTestState, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        moduleDeploymentTestState.properties = params

        log.debug "$prefix leaving"
    }
}
