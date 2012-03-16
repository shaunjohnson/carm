package carm.sourcecontrol

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.transaction.annotation.Transactional
import carm.application.ApplicationRole
import carm.exceptions.DomainInUseException

class SourceControlRoleService {

    static transactional = false

    def grailsApplication

    /**
     * Determines if the provided role is in use.
     *
     * @param role Role to test
     * @return True if the role is in use
     */
    boolean isInUse(SourceControlRole role) {
        ApplicationRole.countByRole(role) > 0
    }

    /**
     * Returns a count of all SourceControlRoles objects.
     *
     * @return Total number of SourceControlRole objects.
     */
    int count() {
        SourceControlRole.count()
    }

    /**
     * Creates and saves a new SourceControlRole instance.
     *
     * @param params SourceControlRole properties
     * @return newly created SourceControlRole object
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    SourceControlRole create(Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        SourceControlRole sourceControlRole = new SourceControlRole(params)
        sourceControlRole.save()

        log.debug "$prefix returning $sourceControlRole"

        sourceControlRole
    }

    /**
     * Deletes the provided SourceControlRole object.
     *
     * @param sourceControlRole SourceControlRole object to delete
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(SourceControlRole sourceControlRole) {
        def prefix = "delete() :"

        log.debug "$prefix entered, sourceControlRole=$sourceControlRole"

        if (isInUse(sourceControlRole)) {
            log.error "$prefix Source control role is in use and cannot be deleted"
            throw new DomainInUseException()
        }

        sourceControlRole.delete()
        
        log.debug "$prefix leaving"
    }

    /**
     * Gets the SourceControlRole object with the provided ID.
     *
     * @param id ID of SourceControlRole object
     * @return Matching SourceControlRole object
     */
    SourceControlRole get(Serializable id) {
        SourceControlRole.get(id)
    }

    /**
     * Gets a list of all SourceControlRole objects.
     *
     * @param params Query parameters
     * @return List of SourceControlRole objects
     */
    List<SourceControlRole> list(Map params) {
        SourceControlRole.list([
                max: grailsApplication.config.ui.sourceControlRole.listMax,
                offset: params?.offset,
                sort: params?.sort,
                order: params?.order
        ])
    }

    /**
     * Updates the provided SourceControlRole object with the new properties.
     *
     * @param sourceControlRole SourceControlRole to update
     * @param params New property values
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(SourceControlRole sourceControlRole, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        sourceControlRole.properties = params

        log.debug "$prefix leaving"
    }
}