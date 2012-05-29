package carm.notification

import org.springframework.transaction.annotation.Transactional
import org.springframework.security.access.prepost.PreAuthorize
import carm.exceptions.DomainInUseException
import carm.application.Application

class NotificationSchemeService {

    static transactional = false

    def grailsApplication

    /**
     * Determines if the provided test state is in use.
     *
     * @param testState Test state to test
     * @return True if the test state is in use
     */
    boolean isInUse(NotificationScheme testState) {
        Application.countByNotificationScheme(testState) > 0
    }

    /**
     * Returns a count of all NotificationScheme objects.
     *
     * @return Total number of NotificationScheme objects.
     */
    int count() {
        NotificationScheme.count()
    }

    /**
     * Creates and saves a new NotificationScheme instance.
     *
     * @param params NotificationScheme properties
     * @return newly created NotificationScheme object
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    NotificationScheme create(Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        NotificationScheme notificationScheme = new NotificationScheme(params)
        notificationScheme.description = notificationScheme.description?.trim()
        notificationScheme.save()

        log.debug "$prefix returning $notificationScheme"

        notificationScheme
    }

    /**
     * Deletes the provided NotificationScheme object.
     *
     * @param notificationScheme NotificationScheme object to delete
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(NotificationScheme notificationScheme) {
        def prefix = "delete() :"

        log.debug "$prefix entered, notificationScheme=$notificationScheme"

        if (isInUse(notificationScheme)) {
            log.error "Application release test state is in use and cannot be deleted"
            throw new DomainInUseException()
        }

        notificationScheme.delete()

        log.debug "$prefix leaving"
    }

    /**
     * Gets the NotificationScheme object with the provided ID.
     *
     * @param id ID of NotificationScheme object
     * @return Matching NotificationScheme object
     */
    NotificationScheme get(Serializable id) {
        NotificationScheme.get(id)
    }

    /**
     * Gets a list of all NotificationScheme objects.
     *
     * @param params Query parameters
     * @return List of NotificationScheme objects
     */
    List<NotificationScheme> list(Map params) {
        NotificationScheme.list([
                max: grailsApplication.config.ui.notificationScheme.listMax,
                offset: params?.offset,
                sort: params?.sort,
                order: params?.order
        ])
    }

    /**
     * Updates the provided NotificationScheme object with the new properties.
     *
     * @param notificationScheme NotificationScheme to update
     * @param params New property values
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(NotificationScheme notificationScheme, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        notificationScheme.properties = params
        notificationScheme.description = notificationScheme.description?.trim()

        log.debug "$prefix leaving"
    }
}
