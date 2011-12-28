package carm

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.transaction.annotation.Transactional

class SourceControlRepositoryService {

    static transactional = false

    int count() {
        SourceControlRepository.count()
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    SourceControlRepository create(Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        SourceControlRepository sourceControlRepository = new SourceControlRepository(params)
        sourceControlRepository.save()

        log.debug "$prefix returning $sourceControlRepository"

        sourceControlRepository
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(SourceControlRepository sourceControlRepository) {
        sourceControlRepository.delete()
    }

    SourceControlRepository get(long id) {
        SourceControlRepository.get id
    }

    List<SourceControlRepository> list(Map params) {
        SourceControlRepository.list params
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(SourceControlRepository sourceControlRepository, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        sourceControlRepository.properties = params

        log.debug "$prefix leaving"
    }
}