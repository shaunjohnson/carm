package carm.system

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.transaction.annotation.Transactional
import carm.deployment.ApplicationDeployment
import carm.exceptions.DomainInUseException

class SystemEnvironmentService {

    static transactional = false

    /**
     * Determines if the provided environment is in use.
     *
     * @param environment Environment to test
     * @return True if the environment is in use
     */
    boolean isInUse(SystemEnvironment environment) {
        ApplicationDeployment.findAllBySysEnvironment(environment).size() > 0
        return false
    }

    /**
     * Gets the total count of all SystemEnvironment objects.
     *
     * @return Count of all SystemEnvironment objects.
     */
    int count() {
        SystemEnvironment.count()
    }

    /**
     * Creates and saves a new SystemEnvironment using the provided properties.
     *
     * @param params SystemEnvironment properties
     * @return Newly created SystemEnvironment
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    SystemEnvironment create(Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        SystemEnvironment systemEnvironment = new SystemEnvironment(params)

        // Explicitly adding to the System due to "bug" in Grails. This is necessary since system.environments is a list
        def system = systemEnvironment.system
        system.addToEnvironments(systemEnvironment)

        systemEnvironment.save()

        log.debug "$prefix returning $systemEnvironment"

        systemEnvironment
    }

    /**
     * Deletes the provided SystemEnvironment object.
     *
     * @param systemEnvironment SystemEnvironment object to delete
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(SystemEnvironment systemEnvironment) {
        if (isInUse(systemEnvironment)) {
            throw new DomainInUseException()
        }

        systemEnvironment.delete()
    }

    /**
     * Gets the SystemEnvironment with the provided ID.
     *
     * @param id ID of SystemEnvironment to load.
     * @return Matching SystemEnvironment object
     */
    SystemEnvironment get(Serializable id) {
        SystemEnvironment.get(id)
    }

    /**
     * Gets list of all SystemEnvironment objects.
     *
     * @param params Query parameters
     * @return List of SystemEnvironment objects
     */
    List<SystemEnvironment> list(Map params) {
        SystemEnvironment.list(params)
    }

    /**
     * Updates the provided SystemEnvironment with the new properties.
     *
     * @param systemEnvironment SystemEnvironment to update
     * @param params New properties
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(SystemEnvironment systemEnvironment, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        systemEnvironment.properties = params

        log.debug "$prefix leaving"
    }
}