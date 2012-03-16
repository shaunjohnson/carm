package carm.release

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.transaction.annotation.Transactional
import carm.exceptions.DomainInUseException

class ApplicationReleaseTestStateService {

    static transactional = false

    def grailsApplication

    /**
     * Determines if the provided test state is in use.
     *
     * @param testState Test state to test
     * @return True if the test state is in use
     */
    boolean isInUse(ApplicationReleaseTestState testState) {
         ApplicationRelease.countByTestState(testState) > 0
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
        def prefix = "delete() :"

        log.debug "$prefix entered, applicationReleaseTestState=$applicationReleaseTestState"

        if (isInUse(applicationReleaseTestState)) {
            log.error "Application release test state is in use and cannot be deleted"
            throw new DomainInUseException()
        }

        applicationReleaseTestState.delete()
        
        log.debug "$prefix leaving"
    }

    /**
     * Gets the ApplicationReleaseTestState object with the provided ID.
     *
     * @param id ID of ApplicationReleaseTestState object
     * @return Matching ApplicationReleaseTestState object
     */
    ApplicationReleaseTestState get(Serializable id) {
        ApplicationReleaseTestState.get(id)
    }

    /**
     * Gets a list of all ApplicationReleaseTestState objects.
     *
     * @param params Query parameters
     * @return List of ApplicationReleaseTestState objects
     */
    List<ApplicationReleaseTestState> list(Map params) {
        ApplicationReleaseTestState.list([
                max: grailsApplication.config.ui.applicationReleaseTestState.listMax,
                offset: params?.offset,
                sort: params?.sort,
                order: params?.order
        ])
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
