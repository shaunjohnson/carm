package carm.system

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.transaction.annotation.Transactional
import carm.module.Module
import carm.exceptions.DomainInUseException

class SystemServerService {

    static transactional = false

    /**
     * Determines if the provided component is in use.
     *
     * @param component Component to test
     * @return True if the component is in use
     */
    boolean isInUse(SystemServer component) {
        def moduleCount = Module.executeQuery(
                'select count(m) from Module m where :component in elements(m.systemComponents)',
                [component: component])[0]

        moduleCount > 0
    }

    /**
     * Returns a count of all SystemServer objects.
     *
     * @return Count of all SystemServer objects
     */
    int count() {
        SystemServer.count()
    }

    /**
     * Creates a new SystemServer using the provided properties.
     *
     * @param params SystemServer properties
     * @return Newly created SystemServer
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    SystemServer create(Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        SystemServer systemComponent = new SystemServer(params)
        systemComponent.save()

        log.debug "$prefix returning $systemComponent"

        systemComponent
    }

    /**
     * Deletes the provided SystemServer.
     *
     * @param systemComponent SystemServer to delete
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(SystemServer systemComponent) {
        def prefix = "delete() :"

        log.debug "$prefix entered, systemComponent=$systemComponent"

        if (isInUse(systemComponent)) {
            log.error "$prefix System component is in use and cannot be deleted"
            throw new DomainInUseException()
        }

        systemComponent.delete()
        
        log.debug "$prefix leaving"
    }

    /**
     * Gets the SystemServer with the provided ID.
     *
     * @param id ID of the SystemServer to get
     * @return Matching SystemServer
     */
    SystemServer get(Serializable id) {
        SystemServer.get(id)
    }

    /**
     * Gets a list of all SystemServer objects.
     *
     * @param params Query parameters
     * @return List of SystemServer objects
     */
    List<SystemServer> list(Map params) {
        SystemServer.list(params)
    }

    /**
     * Updates the provided SystemServer with the new properties.
     *
     * @param systemComponent SystemServer to update
     * @param params New properties
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(SystemServer systemComponent, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        systemComponent.properties = params

        log.debug "$prefix leaving"
    }
}