package carm.release

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.transaction.annotation.Transactional

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

    int count() {
        ApplicationReleaseTestState.count()
    }

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

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(ApplicationReleaseTestState applicationReleaseTestState) {
        applicationReleaseTestState.delete()
    }

    ApplicationReleaseTestState get(long id) {
        ApplicationReleaseTestState.get id
    }

    List<ApplicationReleaseTestState> list(Map params) {
        ApplicationReleaseTestState.list params
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(ApplicationReleaseTestState applicationReleaseTestState, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        applicationReleaseTestState.properties = params

        log.debug "$prefix leaving"
    }
}
