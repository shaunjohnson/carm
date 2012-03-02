package carm

import org.springframework.transaction.annotation.Transactional
import org.springframework.security.access.prepost.PreAuthorize

class ModuleService {
    
    static transactional = false

    /**
     * Determines if the module is deployable. An module must be associated with a system system component in order to
     * be deployable.
     *
     * @param module Module to test
     * @return True if the module can be deployed
     */
    boolean isDeployable(Module module) {
        return module?.systemComponents?.size() > 0
    }

    int count() {
        Module.count()
    }

    @Transactional
    @PreAuthorize("hasPermission(#project, admin)")
    Module create(Project project, Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        Module module = new Module(params)
        module.save()

        log.debug "$prefix returning $module"

        module
    }

    @Transactional
    @PreAuthorize("hasPermission(#project, admin)")
    void delete(Project project, Module module) {
        module.delete()
    }

    Module get(long id) {
        Module.get id
    }

    List<Module> list(Map params) {
        Module.list params
    }

    @Transactional
    @PreAuthorize("hasPermission(#project, admin)")
    void update(Project project, Module module, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        module.properties = params

        log.debug "$prefix leaving"
    }
}
