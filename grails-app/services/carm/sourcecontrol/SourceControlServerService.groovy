package carm.sourcecontrol

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.transaction.annotation.Transactional
import carm.application.Application
import carm.exceptions.DomainInUseException

class SourceControlServerService {

    static transactional = false

    /**
     * Determines if the provided server is in use.
     *
     * @param server Server to test
     * @return True if the server is in use
     */
    boolean isInUse(SourceControlServer server) {
        def applicationCount = Application.executeQuery(
                'select count(a) from Application a where a.sourceControlRepository.server = ?',
                [server])[0]

        applicationCount > 0
    }

    /**
     * Returns a count of all SourceControlServer objects.
     *
     * @return Total number of SourceControlServer objects.
     */
    int count() {
        SourceControlServer.count()
    }

    /**
     * Creates and saves a new SourceControlServer instance.
     *
     * @param params SourceControlServer properties
     * @return newly created SourceControlServer object
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    SourceControlServer create(Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        SourceControlServer sourceControlServer = new SourceControlServer(params)
        sourceControlServer.save()

        log.debug "$prefix returning $sourceControlServer"

        sourceControlServer
    }

    /**
     * Deletes the provided SourceControlServer object.
     *
     * @param sourceControlServer SourceControlServer object to delete
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(SourceControlServer sourceControlServer) {
        def prefix = "delete() :"

        log.debug "$prefix entered, sourceControlServer=$sourceControlServer"

        if (isInUse(sourceControlServer)) {
            log.debug "$prefix Source control server is in use and cannot be deleted"
            throw new DomainInUseException()
        }

        sourceControlServer.delete()
        
        log.debug "$prefix leaving"
    }

    /**
     * Gets the SourceControlServer object with the provided ID.
     *
     * @param id ID of SourceControlServer object
     * @return Matching SourceControlServer object
     */
    SourceControlServer get(Serializable id) {
        SourceControlServer.get(id)
    }

    /**
     * Gets a list of all SourceControlServer objects.
     *
     * @param params Query parameters
     * @return List of SourceControlServer objects
     */
    List<SourceControlServer> list(Map params) {
        SourceControlServer.list(params)
    }

    /**
     * Updates the provided SourceControlServer object with the new properties.
     *
     * @param sourceControlServer SourceControlServer to update
     * @param params New property values
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(SourceControlServer sourceControlServer, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        sourceControlServer.properties = params

        log.debug "$prefix leaving"
    }
}