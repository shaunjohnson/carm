package carm.system

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.transaction.annotation.Transactional
import carm.application.Application
import carm.project.Project
import carm.exceptions.DomainInUseException

class SystemService {

    static transactional = false

    /**
     * Determines if the provided system is in use.
     *
     * @param system System to test
     * @return True if the system is in use
     */
    boolean isInUse(System system) {
        Application.countBySystem(system) > 0
    }

    /**
     * Returns a count of all System objects.
     *
     * @return Total number of System objects.
     */
    int count() {
        System.count()
    }

    /**
     * Determines if the provided system can be deployed to. A system that has at least one component and at least one
     * environment can be deployed to.
     *
     * @param system System object to test.
     * @return True if the system can be deployed to.
     */
    boolean canBeDeployedTo(System system) {
        boolean hasComponents = (system?.components?.size() ?: 0) > 0
        boolean hasEnvironments = (system?.environments?.size() ?: 0) > 0

        return hasComponents && hasEnvironments
    }

    /**
     * Creates and saves a new System instance.
     *
     * @param params System properties
     * @return newly created System object
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    System create(Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        System system = new System(params)
        system.save()

        log.debug "$prefix returning $system"

        system
    }

    /**
     * Deletes the provided System object.
     *
     * @param system System object to delete
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(System system) {
        if (isInUse(system)) {
            throw new DomainInUseException()
        }

        system.delete()
    }

    /**
     * Gets the System object with the provided ID.
     *
     * @param id ID of System object
     * @return Matching System object
     */
    System get(Serializable id) {
        System.get(id)
    }

    /**
     * Gets a list of all System objects.
     *
     * @param params Query parameters
     * @return List of System objects
     */
    List<System> list(Map params) {
        System.list(params)
    }

    /**
     * Moves the SystemEnvironment at the specific index for the provided System down in the list, if possible.
     *
     * @param system System to edit
     * @param index Index of SystemEnvironment to move down
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void moveEnvironmentDown(System system, int index) {
        def environments = system.environments

        if ((index + 1) < environments.size()) {
            def environment = environments[index]
            environments.remove(index)
            environments.add(index + 1, environment)
            system.save()
        }
    }

    /**
     * Moves the SystemEnvironment at the specified index for the provided System up in the list, if possible.
     *
     * @param system System to edit
     * @param index Index of SystemEnvironment to move up
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void moveEnvironmentUp(System system, int index) {
        def environments = system.environments

        if (index > 0) {
            def environment = environments[index]
            environments.remove(index)
            environments.add(index - 1, environment)
            system.save()
        }
    }

    /**
     * Updates the provided System object with the new properties.
     *
     * @param system System to update
     * @param params New property values
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(System system, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        system.properties = params

        log.debug "$prefix leaving"
    }

    /**
     * Finds all System objects associated with the provided List of Project objects.
     *
     * @param projects List of Projects used for querying
     * @return List of System objects
     */
    List<System> findAllByProject(List<Project> projects) {
        if (!projects?.size()) {
            return []
        }

        return Application.executeQuery("""
            select distinct a.system
            from
                Application a
            where
                a.project in :projects
                and a.system is not null
            order by a.system.name
        """, [projects: projects]) as List<System>
    }
}