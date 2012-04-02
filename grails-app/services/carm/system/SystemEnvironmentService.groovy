package carm.system

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.transaction.annotation.Transactional
import carm.application.Application
import carm.project.Project
import carm.exceptions.DomainInUseException

class SystemEnvironmentService {

    static transactional = false

    def activityTraceService
    def grailsApplication

    /**
     * Determines if the provided system is in use.
     *
     * @param system SystemEnvironment to test
     * @return True if the system is in use
     */
    boolean isInUse(SystemEnvironment system) {
        Application.countBySysEnvironment(system) > 0
    }

    /**
     * Returns a count of all SystemEnvironment objects.
     *
     * @return Total number of SystemEnvironment objects.
     */
    int count() {
        SystemEnvironment.count()
    }

    /**
     * Determines if the provided system can be deployed to. A system that has at least one server and at least one
     * environment can be deployed to.
     *
     * @param system SystemEnvironment object to test.
     * @return True if the system can be deployed to.
     */
    boolean canBeDeployedTo(SystemEnvironment system) {
        boolean hasServers = (system?.servers?.size() ?: 0) > 0
        boolean hasEnvironments = (system?.environments?.size() ?: 0) > 0

        return hasServers && hasEnvironments
    }

    /**
     * Creates and saves a new SystemEnvironment instance.
     *
     * @param params SystemEnvironment properties
     * @return newly created SystemEnvironment object
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    SystemEnvironment create(Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        SystemEnvironment system = new SystemEnvironment(params)
        system.save()

        log.debug "$prefix returning $system"

        system
    }

    /**
     * Deletes the provided SystemEnvironment object.
     *
     * @param system SystemEnvironment object to delete
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(SystemEnvironment system) {
        def prefix = "delete() :"

        log.debug "$prefix entered, system=$system"

        if (isInUse(system)) {
            log.error "$prefix SystemEnvironment is in use and cannot be deleted"
            throw new DomainInUseException()
        }

        system.delete()
        
        log.debug "$prefix leaving"
    }

    /**
     * Gets the SystemEnvironment object with the provided ID.
     *
     * @param id ID of SystemEnvironment object
     * @return Matching SystemEnvironment object
     */
    SystemEnvironment get(Serializable id) {
        SystemEnvironment.get(id)
    }

    /**
     * Lists out the most active system environments.
     *
     * @param params Query parameters
     * @return List of SystemEnvironment objects
     */
    List<SystemEnvironment> getMostActiveSystems(Map params) {
        def queryParams = [
                max: grailsApplication.config.ui.project.listMax,
                offset: params?.offset,
                sort: params?.sort,
                order: params?.order
        ]

        def activeIds = activityTraceService.getMostActiveSystemEnvironmentIds([ max: queryParams.max ])

        SystemEnvironment.findAllByIdInList(activeIds, queryParams)
    }

    /**
     * Gets a list of all SystemEnvironment objects.
     *
     * @param params Query parameters
     * @return List of SystemEnvironment objects
     */
    List<SystemEnvironment> list(Map params) {
        SystemEnvironment.list([
                max: grailsApplication.config.ui.systemEnvironment.listMax,
                offset: params?.offset,
                sort: params?.sort,
                order: params?.order
        ])
    }

    /**
     * Moves the SystemDeploymentEnvironment at the specific index for the provided SystemEnvironment down in the list, if possible.
     *
     * @param system SystemEnvironment to edit
     * @param index Index of SystemDeploymentEnvironment to move down
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void moveEnvironmentDown(SystemEnvironment system, int index) {
        def environments = system.environments

        if ((index + 1) < environments.size()) {
            def environment = environments[index]
            environments.remove(index)
            environments.add(index + 1, environment)
            system.save()
        }
    }

    /**
     * Moves the SystemDeploymentEnvironment at the specified index for the provided SystemEnvironment up in the list, if possible.
     *
     * @param system SystemEnvironment to edit
     * @param index Index of SystemDeploymentEnvironment to move up
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void moveEnvironmentUp(SystemEnvironment system, int index) {
        def environments = system.environments

        if (index > 0) {
            def environment = environments[index]
            environments.remove(index)
            environments.add(index - 1, environment)
            system.save()
        }
    }

    /**
     * Updates the provided SystemEnvironment object with the new properties.
     *
     * @param system SystemEnvironment to update
     * @param params New property values
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(SystemEnvironment system, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        system.properties = params

        log.debug "$prefix leaving"
    }

    /**
     * Finds all SystemEnvironment objects associated with the provided List of Project objects.
     *
     * @param projects List of Projects used for querying
     * @return List of SystemEnvironment objects
     */
    List<SystemEnvironment> findAllByProject(List<Project> projects) {
        if (!projects?.size()) {
            return []
        }

        return Application.executeQuery("""
            select distinct a.sysEnvironment
            from
                Application a
            where
                a.project in :projects
                and a.sysEnvironment is not null
            order by a.sysEnvironment.name
        """, [projects: projects]) as List<SystemEnvironment>
    }
}