package carm.module

import org.springframework.transaction.annotation.Transactional
import org.springframework.security.access.prepost.PreAuthorize
import carm.project.Project

class ModuleService {
    
    static transactional = false

    /**
     * Determines if the module is deployable. An module must be associated with a system system server in order to
     * be deployable.
     *
     * @param module Module to test
     * @return True if the module can be deployed
     */
    boolean isDeployable(Module module) {
        return module?.systemServers?.size() > 0
    }

    /**
     * Returns a count of all Module objects.
     *
     * @return Total number of Module objects.
     */
    int count() {
        Module.count()
    }

    /**
     * Creates and saves a new Module instance.
     *
     * @param project Parent project used for security
     * @param params Module properties
     * @return newly created Module object
     */
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

    /**
     * Deletes the provided Module object.
     *
     * @param project Parent project used for security
     * @param module Module object to delete
     */
    @Transactional
    @PreAuthorize("hasPermission(#project, admin)")
    void delete(Project project, Module module) {
        def prefix = "delete() :"

        log.debug "$prefix entered, module=$module"

        module.delete()
        
        log.debug "$prefix leaving"
    }

    /**
     * Gets the Module object with the provided ID.
     *
     * @param id ID of Module object
     * @return Matching Module object
     */
    Module get(Serializable id) {
        Module.get(id)
    }

    /**
     * Gets a list of all Module objects.
     *
     * @param params Query parameters
     * @return List of Module objects
     */
    List<Module> list(Map params) {
        Module.list(params)
    }

    /**
     * Updates the provided Module object with the new properties.
     *
     * @param project Parent project used for security
     * @param module Module to update
     * @param params New property values
     */
    @Transactional
    @PreAuthorize("hasPermission(#project, admin)")
    void update(Project project, Module module, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        module.properties = params

        log.debug "$prefix leaving"
    }
}
