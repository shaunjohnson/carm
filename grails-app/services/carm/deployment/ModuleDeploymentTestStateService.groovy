package carm.deployment

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.transaction.annotation.Transactional
import carm.exceptions.DomainInUseException

class ModuleDeploymentTestStateService {

    static transactional = false

    /**
     * Determines if the provided test state is in use.
     *
     * @param testState Test state to test
     * @return True if the test state is in use
     */
    boolean isInUse(ModuleDeploymentTestState testState) {
        ModuleDeployment.countByTestState(testState) > 0
    }

    /**
     * Returns a count of all ModuleDeploymentTestState objects.
     *
     * @return Total number of ModuleDeploymentTestState objects.
     */
    int count() {
        ModuleDeploymentTestState.count()
    }

    /**
     * Creates and saves a new ModuleDeploymentTestState instance.
     *
     * @param params ModuleDeploymentTestState properties
     * @return newly created Module object
     */
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

    /**
     * Deletes the provided ModuleDeploymentTestState object.
     *
     * @param moduleDeploymentTestState ModuleDeploymentTestState object to delete
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(ModuleDeploymentTestState moduleDeploymentTestState) {
        def prefix = "delete() :"

        log.debug "$prefix entered, moduleDeploymentTestState=$moduleDeploymentTestState"

        if (isInUse(moduleDeploymentTestState)) {
            log.error "$prefix Module deployment test state is in use and cannot be deleted"
            throw new DomainInUseException()
        }

        moduleDeploymentTestState.delete()
        
        log.debug "$prefix leaving"
    }

    /**
     * Gets the ModuleDeploymentTestState object with the provided ID.
     *
     * @param id ID of ModuleDeploymentTestState object
     * @return Matching ModuleDeploymentTestState object
     */
    ModuleDeploymentTestState get(Serializable id) {
        ModuleDeploymentTestState.get(id)
    }

    /**
     * Gets a list of all ModuleDeploymentTestState objects.
     *
     * @param params Query parameters
     * @return List of ModuleDeploymentTestState objects
     */
    List<ModuleDeploymentTestState> list(Map params) {
        ModuleDeploymentTestState.list(params)
    }

    /**
     * Updates the provided ModuleDeploymentTestState object with the new properties.
     *
     * @param moduleDeploymentTestState ModuleDeploymentTestState to update
     * @param params New property values
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(ModuleDeploymentTestState moduleDeploymentTestState, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        moduleDeploymentTestState.properties = params

        log.debug "$prefix leaving"
    }
}
