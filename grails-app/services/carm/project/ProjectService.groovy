package carm.project

import org.springframework.security.access.prepost.PostFilter
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.acls.domain.BasePermission
import org.springframework.transaction.annotation.Transactional

class ProjectService {
    static transactional = false

    def activityTraceService
    def applicationDeploymentService
    def applicationReleaseService
    def carmSecurityService
    def grailsApplication

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

        if (!params.projectOwners) {
            log.error "$prefix At least one project manager must be selected"
            throw new RuntimeException("At least one project manager must be selected")
        }

        Project project = new Project(params)
        project.save()

        // Grant the list of project owners administration permission
        if (!project.hasErrors()) {
            carmSecurityService.createPermissions(project, params.projectOwners, BasePermission.ADMINISTRATION)
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
    @PreAuthorize("hasPermission(#project, delete) or hasPermission(#project, admin)")
    void delete(Project project) {
        def prefix = "delete() :"

        log.debug "$prefix entered, project=$project"

        Project.withTransaction {
            project.delete()
            carmSecurityService.deleteAllPermissions(project)
        }

        log.debug "$prefix leaving"
    }

    /**
     * Gets the Project object with the provided ID.
     *
     * @param id ID of Project object
     * @return Matching Project object
     */
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
    @PreAuthorize("hasPermission(#project, write) or hasPermission(#project, admin)")
    void update(Project project, Map params) {
        def prefix = "update() :"

        log.debug "$prefix entered"

        if (!params.projectOwners) {
            log.error "$prefix At least one project owner must be selected"
            throw new RuntimeException("At least one project owner must be selected")
        }

        project.properties = params

        // Grant the list of project owners administration permission
        if (!project.hasErrors()) {
            carmSecurityService.updatePermissions(project, params.projectOwners, BasePermission.ADMINISTRATION)
        }

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
    @PostFilter("hasPermission(filterObject, admin)")
    List<Project> getAllProjectsWhereOwner(Map params) {
        Project.list params
    }

    /**
     * Lists out the most active projects.
     *
     * @param params Query parameters
     * @return List of Project objects
     */
    List<Project> getMostActiveProjects(Map params) {
        def queryParams = [
                max: grailsApplication.config.ui.project.listMax,
                offset: params?.offset,
                sort: params?.sort,
                order: params?.order
        ]
        
        def activeIds = activityTraceService.getMostActiveProjectIds([ max: queryParams.max ])

        Project.findAllByIdInList(activeIds, queryParams)
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
     * Finds all project owners for the provided Project instance.
     *
     * @param project Project used for querying
     * @return List of project owner usernames
     */
    List<String> findAllProjectOwners(Project project) {
        carmSecurityService.findAllPrincipalsByDomainAndPermission(project, BasePermission.ADMINISTRATION)
    }

    boolean isProjectOwner(Project project) {
        if (!project) {
            return false
        }

        carmSecurityService.hasPermission(project, BasePermission.ADMINISTRATION)
    }
}
