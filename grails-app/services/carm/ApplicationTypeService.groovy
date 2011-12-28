package carm

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.transaction.annotation.Transactional

class ApplicationTypeService {

    static transactional = false

    int count() {
        ApplicationType.count()
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ApplicationType create(Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        ApplicationType applicationType = new ApplicationType(params)
        applicationType.save()

        log.debug "$prefix returning $applicationType"

        applicationType
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(ApplicationType applicationType) {
        applicationType.delete()
    }

    ApplicationType get(long id) {
        ApplicationType.get id
    }

    List<ApplicationType> list(Map params) {
        ApplicationType.list params
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(ApplicationType applicationType, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        applicationType.properties = params

        log.debug "$prefix leaving"
    }
}
