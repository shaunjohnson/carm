package carm.sourcecontrol

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.transaction.annotation.Transactional

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

    int count() {
        SourceControlServer.count()
    }

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

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(SourceControlServer sourceControlServer) {
        sourceControlServer.delete()
    }

    SourceControlServer get(long id) {
        SourceControlServer.get id
    }

    List<SourceControlServer> list(Map params) {
        SourceControlServer.list params
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(SourceControlServer sourceControlServer, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        sourceControlServer.properties = params

        log.debug "$prefix leaving"
    }
}