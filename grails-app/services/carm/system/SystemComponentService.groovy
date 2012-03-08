package carm.system

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.transaction.annotation.Transactional
import grails.gorm.DetachedCriteria
import carm.module.Module

class SystemComponentService {

    static transactional = false

    /**
     * Determines if the provided component is in use.
     *
     * @param component Component to test
     * @return True if the component is in use
     */
    boolean isInUse(SystemComponent component) {
        def moduleCount = Module.executeQuery(
                'select count(m) from Module m where :component in elements(m.systemComponents)',
                [component: component])[0]

        moduleCount > 0
    }

    /**
     * Returns a count of all SystemComponent objects.
     *
     * @return Count of all SystemComponent objects
     */
    int count() {
        SystemComponent.count()
    }

    /**
     * Creates a new SystemComponent using the provided properties.
     *
     * @param params SystemComponent properties
     * @return Newly created SystemComponent
     */
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

    /**
     * Deletes the provided SystemComponent.
     *
     * @param systemComponent SystemComponent to delete
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(SystemComponent systemComponent) {
        systemComponent.delete()
    }

    /**
     * Gets the SystemComponent with the provided ID.
     *
     * @param id ID of the SystemComponent to get
     * @return Matching SystemComponent
     */
    SystemComponent get(long id) {
        SystemComponent.get(id)
    }

    /**
     * Gets a list of all SystemComponent objects.
     *
     * @param params Query parameters
     * @return List of SystemComponent objects
     */
    List<SystemComponent> list(Map params) {
        SystemComponent.list(params)
    }

    /**
     * Updates the provided SystemComponent with the new properties.
     *
     * @param systemComponent SystemComponent to update
     * @param params New properties
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(SystemComponent systemComponent, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        systemComponent.properties = params

        log.debug "$prefix leaving"
    }
}