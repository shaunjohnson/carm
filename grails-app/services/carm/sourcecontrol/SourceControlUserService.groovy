package carm.sourcecontrol

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.transaction.annotation.Transactional

class SourceControlUserService {

    static transactional = false

    int count() {
        SourceControlUser.count()
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    SourceControlUser create(Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        SourceControlUser sourceControlUser = new SourceControlUser(params)
        sourceControlUser.save()

        log.debug "$prefix returning $sourceControlUser"

        sourceControlUser
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(SourceControlUser sourceControlUser) {
        sourceControlUser.delete()
    }

    SourceControlUser get(long id) {
        SourceControlUser.get id
    }

    List<SourceControlUser> list(Map params) {
        SourceControlUser.list params
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(SourceControlUser sourceControlUser, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        sourceControlUser.properties = params

        log.debug "$prefix leaving"
    }
}