package carm.release

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.transaction.annotation.Transactional
import carm.exceptions.DomainInUseException

class ApplicationReleaseTestStateService {

    static transactional = false

    /**
     * Determines if the provided test state is in use.
     *
     * @param testState Test state to test
     * @return True if the test state is in use
     */
    boolean isInUse(ApplicationReleaseTestState testState) {
         ApplicationRelease.findAllByTestState(testState).size() > 0
    }

    /**
     * Returns a count of all ApplicationReleaseTestState objects.
     *
     * @return Total number of ApplicationReleaseTestState objects.
     */
    int count() {
        ApplicationReleaseTestState.count()
    }

    /**
     * Creates and saves a new ApplicationReleaseTestState instance.
     *
     * @param params ApplicationReleaseTestState properties
     * @return newly created ApplicationReleaseTestState object
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ApplicationReleaseTestState create(Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        ApplicationReleaseTestState applicationReleaseTestState = new ApplicationReleaseTestState(params)
        applicationReleaseTestState.save()

        log.debug "$prefix returning $applicationReleaseTestState"

        applicationReleaseTestState
    }

    /**
     * Deletes the provided ApplicationReleaseTestState object.
     *
     * @param applicationReleaseTestState ApplicationReleaseTestState object to delete
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(ApplicationReleaseTestState applicationReleaseTestState) {
        if (isInUse(applicationReleaseTestState)) {
            throw new DomainInUseException()
        }

        applicationReleaseTestState.delete()
    }

    /**
     * Gets the ApplicationReleaseTestState object with the provided ID.
     *
     * @param id ID of ApplicationReleaseTestState object
     * @return Matching ApplicationReleaseTestState object
     */
    ApplicationReleaseTestState get(long id) {
        ApplicationReleaseTestState.get(id)
    }

    /**
     * Gets a list of all ApplicationReleaseTestState objects.
     *
     * @param params Query parameters
     * @return List of ApplicationReleaseTestState objects
     */
    List<ApplicationReleaseTestState> list(Map params) {
        ApplicationReleaseTestState.list(params)
    }

    /**
     * Updates the provided ApplicationReleaseTestState object with the new properties.
     *
     * @param applicationReleaseTestState ApplicationReleaseTestState to update
     * @param params New property values
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(ApplicationReleaseTestState applicationReleaseTestState, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        applicationReleaseTestState.properties = params

        log.debug "$prefix leaving"
    }
}
