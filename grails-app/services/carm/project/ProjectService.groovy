package carm.project

import static carm.security.CarmPermission.PROJECT_ADMINISTRATOR

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.transaction.annotation.Transactional
import carm.exceptions.CarmRuntimeException
import carm.security.User
import carm.security.UserGroup

class ProjectService {
    static transactional = false

    def aclService
    def applicationDeploymentService
    def applicationReleaseService
    def carmSecurityService
    def favoriteService
    def grailsApplication
    def userService
    def watchService

    /**
     * Returns a count of all Project objects.
     *
     * @return Total number of Project objects.
     */
    int count() {
        Project.count()
    }

    /**
     * Creates and saves a new Project instance.
     *
     * @param params Project properties
     * @return newly created Project object
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Project create(Map params) {
        def prefix = "create() :"

        log.debug "$prefix entered"

        Project project = new Project(params)
        project.description = project.description?.trim()
        project.save()

        // Grant the current user project administrator access to the newly created project
        if (!project.hasErrors()) {
            aclService.addUserPermission(project, userService.currentUser, PROJECT_ADMINISTRATOR)
        }

        log.debug "$prefix returning $project"

        project
    }

    /**
     * Deletes the provided Project object.
     *
     * @param project Project object to delete
     */
    @Transactional
    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or hasPermission(#project, 'PROJECT_ADMINISTRATOR'))")
    void delete(Project project) {
        def prefix = "delete() :"

        log.debug "$prefix entered, project=$project"

        Project.withTransaction {
            def projectId = project.id
            aclService.deleteAllAclsByDomain(project, PROJECT_ADMINISTRATOR)
            favoriteService.deleteAllForProjectId(projectId)
            watchService.deleteAllForProjectId(projectId)
            project.delete()
        }

        log.debug "$prefix leaving"
    }

    /**
     * Gets the Project object with the provided ID.
     *
     * @param id ID of Project object
     * @return Matching Project object
     */
    @Transactional
    Project get(Serializable id) {
        Project.get(id)
    }

    /**
     * Gets a list of all Project objects.
     *
     * @param params Query parameters
     * @return List of Project objects
     */
    List<Project> list(Map params) {
        Project.list([
                max: grailsApplication.config.ui.project.listMax,
                offset: params?.offset,
                sort: params?.sort,
                order: params?.order
        ])
    }

    /**
     * Updates the provided Project object with the new properties.
     *
     * @param project Project to update
     * @param params New property values
     */
    @Transactional
    @PreAuthorize("isAuthenticated() and hasPermission(filterObject, 'PROJECT_ADMINISTRATOR')")
    void update(Project project, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        project.properties = params
        project.description = project.description?.trim()

        log.debug "$prefix leaving"
    }

    /**
     * Finds all pending deployments and releases for the provided Project.
     *
     * @param project Project used for filtering
     * @return List of ApplicationDeployment and ApplicationRelease objects ordered by dateCreated ascending
     */
    List findAllPendingTasks(Project project) {
        def pendingTasks = []

        pendingTasks.addAll applicationDeploymentService.findAllPendingDeploymentsByProject(project)
        pendingTasks.addAll applicationReleaseService.findAllPendingReleasesByProject(project)

        pendingTasks.sort { it.dateCreated }
    }

    /**
     * Gets a list of all Project objects where the current user is a project owner.
     *
     * @param params Query parameters
     * @return List of matching Project objects
     */
    @PostFilter("isAuthenticated() and hasPermission(filterObject, 'PROJECT_ADMINISTRATOR')")
    List<Project> getAllProjectsWhereOwner(Map params) {
        Project.list params
    }

    /**
     * Finds all pending deployments and releases for all applications in the provided projects.
     *
     * @param projects List of projects used for querying
     * @return List of ApplicationDeployment and ApplicationRelease objects
     */
    List findAllPendingTasks(List<Project> projects) {
        def pendingTasks = []

        projects.each { project ->
            pendingTasks.addAll applicationDeploymentService.findAllPendingDeploymentsByProject(project)
            pendingTasks.addAll applicationReleaseService.findAllPendingReleasesByProject(project)
        }

        pendingTasks.sort { it.dateCreated }
    }

    /**
     * Finds all pending deployments and releases for all projects.
     *
     * @return List of ApplicationDeployment and ApplicationRelease objects
     */
    List findAllPendingTasks() {
        def pendingTasks = []

        Project.list().each { project ->
            pendingTasks.addAll applicationDeploymentService.findAllPendingDeploymentsByProject(project)
            pendingTasks.addAll applicationReleaseService.findAllPendingReleasesByProject(project)
        }

        pendingTasks.sort { it.dateCreated }
    }

    /**
     * Finds all project administrator groups for the provided Project instance.
     *
     * @param project Project used for querying
     * @return List of UserGroup objects
     */
    List<UserGroup> findAllProjectAdministratorGroups(Project project) {
        aclService.findAllGroupsByDomainAndPermission(project, PROJECT_ADMINISTRATOR)
    }

    /**
     * Finds all project administrator users for the provided Project instance.
     *
     * @param project Project used for querying
     * @return List of User objects
     */
    List<User> findAllProjectAdministratorUsers(Project project) {
        aclService.findAllUsersByDomainAndPermission(project, PROJECT_ADMINISTRATOR)
    }

    boolean isProjectOwner(Project project) {
        if (!project) {
            return false
        }

        carmSecurityService.hasPermission(project, PROJECT_ADMINISTRATOR)
    }

    @Transactional
    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or hasPermission(filterObject, 'PROJECT_ADMINISTRATOR') )")
    void addAdministratorGroup(Project project, UserGroup userGroup) {
        aclService.addUserGroupPermission(project, userGroup, PROJECT_ADMINISTRATOR)
    }

    @Transactional
    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or hasPermission(filterObject, 'PROJECT_ADMINISTRATOR') )")
    void addAdministratorUser(Project project, User user) {
        aclService.addUserPermission(project, user, PROJECT_ADMINISTRATOR)
    }

    @Transactional
    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or hasPermission(filterObject, 'PROJECT_ADMINISTRATOR') )")
    void removeAdministratorGroup(Project project, UserGroup userGroup) {
        aclService.removeUserGroupPermission(project, userGroup, PROJECT_ADMINISTRATOR)
    }

    @Transactional
    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or hasPermission(filterObject, 'PROJECT_ADMINISTRATOR') )")
    void removeAdministratorUser(Project project, User user) {
        aclService.removeUserPermission(project, user, PROJECT_ADMINISTRATOR)
    }
}
