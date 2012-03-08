package carm.sourcecontrol

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.transaction.annotation.Transactional

class SourceControlUserService {

    static transactional = false

    /**
     * Returns a count of all SourceControlUser objects.
     *
     * @return Total number of SourceControlUser objects.
     */
    int count() {
        SourceControlUser.count()
    }

    /**
     * Creates and saves a new SourceControlUser instance.
     *
     * @param params SourceControlUser properties
     * @return newly created SourceControlUser object
     */
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

    /**
     * Deletes the provided SourceControlUser object.
     *
     * @param sourceControlUser SourceControlUser object to delete
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(SourceControlUser sourceControlUser) {
        sourceControlUser.delete()
    }

    /**
     * Gets the SourceControlUser object with the provided ID.
     *
     * @param id ID of SourceControlUser object
     * @return Matching SourceControlUser object
     */
    SourceControlUser get(long id) {
        SourceControlUser.get(id)
    }

    /**
     * Gets a list of all SourceControlUser objects.
     *
     * @param params Query parameters
     * @return List of SourceControlUser objects
     */
    List<SourceControlUser> list(Map params) {
        SourceControlUser.list(params)
    }

    /**
     * Updates the provided SourceControlUser object with the new properties.
     *
     * @param sourceControlUser SourceControlUser to update
     * @param params New property values
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(SourceControlUser sourceControlUser, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        sourceControlUser.properties = params

        log.debug "$prefix leaving"
    }
}