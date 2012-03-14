package carm.system

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.transaction.annotation.Transactional
import carm.deployment.ApplicationDeployment
import carm.exceptions.DomainInUseException

class SystemDeploymentEnvironmentService {

    static transactional = false

    /**
     * Determines if the provided environment is in use.
     *
     * @param environment Environment to test
     * @return True if the environment is in use
     */
    boolean isInUse(SystemDeploymentEnvironment environment) {
        ApplicationDeployment.countBySysEnvironment(environment) > 0
    }

    /**
     * Gets the total count of all SystemDeploymentEnvironment objects.
     *
     * @return Count of all SystemDeploymentEnvironment objects.
     */
    int count() {
        SystemDeploymentEnvironment.count()
    }

    /**
     * Creates and saves a new SystemDeploymentEnvironment using the provided properties.
     *
     * @param params SystemDeploymentEnvironment properties
     * @return Newly created SystemDeploymentEnvironment
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    SystemDeploymentEnvironment create(Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        SystemDeploymentEnvironment systemEnvironment = new SystemDeploymentEnvironment(params)

        // Explicitly adding to the System due to "bug" in Grails. This is necessary since system.environments is a list
        def system = systemEnvironment.system
        system.addToEnvironments(systemEnvironment)

        systemEnvironment.save()

        log.debug "$prefix returning $systemEnvironment"

        systemEnvironment
    }

    /**
     * Deletes the provided SystemDeploymentEnvironment object.
     *
     * @param systemEnvironment SystemDeploymentEnvironment object to delete
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(SystemDeploymentEnvironment systemEnvironment) {
        def prefix = "delete() :"

        log.debug "$prefix entered, systemEnvironment=$systemEnvironment"

        if (isInUse(systemEnvironment)) {
            log.error "$prefix System environment is in use and cannot be deleted"
            throw new DomainInUseException()
        }

        systemEnvironment.delete()
        
        log.debug "$prefix leaving"
    }

    /**
     * Gets the SystemDeploymentEnvironment with the provided ID.
     *
     * @param id ID of SystemDeploymentEnvironment to load.
     * @return Matching SystemDeploymentEnvironment object
     */
    SystemDeploymentEnvironment get(Serializable id) {
        SystemDeploymentEnvironment.get(id)
    }

    /**
     * Gets list of all SystemDeploymentEnvironment objects.
     *
     * @param params Query parameters
     * @return List of SystemDeploymentEnvironment objects
     */
    List<SystemDeploymentEnvironment> list(Map params) {
        SystemDeploymentEnvironment.list(params)
    }

    /**
     * Updates the provided SystemDeploymentEnvironment with the new properties.
     *
     * @param systemEnvironment SystemDeploymentEnvironment to update
     * @param params New properties
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(SystemDeploymentEnvironment systemEnvironment, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        systemEnvironment.properties = params

        log.debug "$prefix leaving"
    }
}