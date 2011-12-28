package carm

import org.springframework.transaction.annotation.Transactional
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission

class ProjectCategoryService {

    static transactional = false

    int count() {
        ProjectCategory.count()
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ProjectCategory create(Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        ProjectCategory projectCategory = new ProjectCategory(params)
        projectCategory.save()

        log.debug "$prefix returning $ProjectCategory"

        projectCategory
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(ProjectCategory projectCategory) {
        projectCategory.delete()
    }

    ProjectCategory get(long id) {
        ProjectCategory.get id
    }

    List<ProjectCategory> list(Map params) {
        ProjectCategory.list params
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(ProjectCategory projectCategory, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        projectCategory.properties = params

        log.debug "$prefix leaving"
    }
}