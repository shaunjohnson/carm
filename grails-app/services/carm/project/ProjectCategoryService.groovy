package carm.project

import org.springframework.transaction.annotation.Transactional
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import carm.exceptions.DomainInUseException

class ProjectCategoryService {

    static transactional = false

    /**
     * Determines if the provided category is in use.
     *
     * @param category Category to test
     * @return True if the category is in use
     */
    boolean isInUse(ProjectCategory category) {
        Project.findAllByCategory(category).size() > 0
    }

    /**
     * Returns a count of all ProjectCategory objects.
     *
     * @return Total number of ProjectCategory objects.
     */
    int count() {
        ProjectCategory.count()
    }

    /**
     * Creates and saves a new ProjectCategory instance.
     *
     * @param params ProjectCategory properties
     * @return newly created ProjectCategory object
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ProjectCategory create(Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        ProjectCategory projectCategory = new ProjectCategory(params)
        projectCategory.save()

        log.debug "$prefix returning $projectCategory"

        projectCategory
    }

    /**
     * Deletes the provided ProjectCategory object.
     *
     * @param projectCategory ProjectCategory object to delete
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(ProjectCategory projectCategory) {
        if (isInUse(projectCategory)) {
            throw new DomainInUseException()
        }

        projectCategory.delete()
    }

    /**
     * Gets the ProjectCategory object with the provided ID.
     *
     * @param id ID of ProjectCategory object
     * @return Matching ProjectCategory object
     */
    ProjectCategory get(long id) {
        ProjectCategory.get(id)
    }

    /**
     * Gets a list of all ProjectCategory objects.
     *
     * @param params Query parameters
     * @return List of ProjectCategory objects
     */
    List<ProjectCategory> list(Map params) {
        ProjectCategory.list(params)
    }

    /**
     * Updates the provided ProjectCategory object with the new properties.
     *
     * @param projectCategory ProjectCategory to update
     * @param params New property values
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(ProjectCategory projectCategory, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        projectCategory.properties = params

        log.debug "$prefix leaving"
    }
}
