package carm

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.transaction.annotation.Transactional

class ApplicationReleaseService {
    static transactional = false

    int count() {
        ApplicationRelease.count()
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ApplicationRelease create(Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        ApplicationRelease applicationRelease = new ApplicationRelease(params)
        applicationRelease.releaseState = "Submitted"
        applicationRelease.save()

        log.debug "$prefix returning $applicationRelease"

        applicationRelease
    }

    @Transactional
    // @PreAuthorize("hasPermission(#applicationRelease, delete) or hasPermission(#applicationRelease, admin)")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(ApplicationRelease applicationRelease) {
        applicationRelease.delete()
    }

    ApplicationRelease get(long id) {
        ApplicationRelease.get id
    }

    List<ApplicationRelease> list(Map params) {
        ApplicationRelease.list params
    }

    @Transactional
    // @PreAuthorize("hasPermission(#applicationRelease, write) or hasPermission(#applicationRelease, admin)")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(ApplicationRelease applicationRelease, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        applicationRelease.properties = params

        log.debug "$prefix leaving"
    }
}
