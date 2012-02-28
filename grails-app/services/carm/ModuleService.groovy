package carm

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
}
