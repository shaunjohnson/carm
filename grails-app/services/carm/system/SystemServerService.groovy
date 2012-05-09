package carm.system

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.transaction.annotation.Transactional
import carm.module.Module
import carm.exceptions.DomainInUseException

class SystemServerService {

    static transactional = false

    def grailsApplication

    /**
     * Determines if the provided server is in use.
     *
     * @param server Server to test
     * @return True if the server is in use
     */
    boolean isInUse(SystemServer server) {
        def moduleCount = Module.executeQuery(
                'select count(m) from Module m where :server in elements(m.systemServers)',
                [server: server])[0]

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

        SystemServer systemServer = new SystemServer(params)
        systemServer.description = systemServer.description?.trim()
        systemServer.save()

        log.debug "$prefix returning $systemServer"

        systemServer
    }

    /**
     * Deletes the provided SystemServer.
     *
     * @param systemServer SystemServer to delete
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(SystemServer systemServer) {
        def prefix = "delete() :"

        log.debug "$prefix entered, systemServer=$systemServer"

        if (isInUse(systemServer)) {
            log.error "$prefix SystemEnvironment server is in use and cannot be deleted"
            throw new DomainInUseException()
        }

        systemServer.delete()
        
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
        SystemServer.list([
                max: grailsApplication.config.ui.systemServer.listMax,
                offset: params?.offset,
                sort: params?.sort,
                order: params?.order
        ])
    }

    /**
     * Updates the provided SystemServer with the new properties.
     *
     * @param systemServer SystemServer to update
     * @param params New properties
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(SystemServer systemServer, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        systemServer.properties = params
        systemServer.description = systemServer.description?.trim()

        log.debug "$prefix leaving"
    }
}