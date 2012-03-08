package carm.module

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.transaction.annotation.Transactional

class ModuleTypeService {

    static transactional = false

    /**
     * Determines if the provided type is in use.
     *
     * @param type Type to test
     * @return True if the type is in use
     */
    boolean isInUse(ModuleType type) {
        Module.findAllByType(type).size() > 0
    }

    /**
     * Returns a count of all ModuleType objects.
     *
     * @return Total number of ModuleType objects.
     */
    int count() {
        ModuleType.count()
    }

    /**
     * Creates and saves a new ModuleType instance.
     *
     * @param params ModuleType properties
     * @return newly created Module object
     */
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

    /**
     * Deletes the provided ModuleType object.
     *
     * @param moduleType ModuleType object to delete
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(ModuleType moduleType) {
        moduleType.delete()
    }

    /**
     * Gets the ModuleType object with the provided ID.
     *
     * @param id ID of ModuleType object
     * @return Matching ModuleType object
     */
    ModuleType get(long id) {
        ModuleType.get(id)
    }

    /**
     * Gets a list of all ModuleType objects.
     *
     * @param params Query parameters
     * @return List of ModuleType objects
     */
    List<ModuleType> list(Map params) {
        ModuleType.list(params)
    }

    /**
     * Updates the provided ModuleType object with the new properties.
     *
     * @param moduleType ModuleType to update
     * @param params New property values
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(ModuleType moduleType, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        moduleType.properties = params

        log.debug "$prefix leaving"
    }
}
