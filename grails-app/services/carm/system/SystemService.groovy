package carm.system

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.transaction.annotation.Transactional
import carm.application.Application

class SystemService {

    static transactional = false

    /**
     * Determines if the provided system is in use.
     *
     * @param system System to test
     * @return True if the system is in use
     */
    boolean isInUse(System system) {
        Application.findAllBySystem(system).size() > 0
    }

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

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(System system) {
        system.delete()
    }

    System get(long id) {
        System.get id
    }

    List<System> list(Map params) {
        System.list params
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void moveEnvironmentDown(System system, int index) {
        def environments = system.environments

        if (index != null && (index + 1) < environments.size()) {
            def environment = environments[index]
            environments.remove(index)
            environments.add(index + 1, environment)
            system.save()
        }
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void moveEnvironmentUp(System system, int index) {
        def environments = system.environments

        if (index != null && index > 0) {
            def environment = environments[index]
            environments.remove(index)
            environments.add(index - 1, environment)
            system.save()
        }
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void update(System system, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        system.properties = params

        log.debug "$prefix leaving"
    }
}