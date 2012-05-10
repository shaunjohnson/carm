package carm

import carm.application.Application
import carm.security.User
import carm.project.Project

class FavoriteService {

    static transactional = false

    def carmSecurityService
    def springSecurityService

    /**
     * Adds an Application to the current user's favorites.
     *
     * @param id Application ID
     */
    void addApplicationToCurrentUserFavorites(Serializable id) {
        Application application = Application.get(id)

        if (application) {
            new Favorite(user: carmSecurityService.currentUser, application: application).save()
            log.debug "Added $application to favories"
        }
    }

    /**
     * Adds an Application to the current user's favorites.
     *
     * @param id Application ID
     */
    void addProjectToCurrentUserFavorites(Serializable id) {
        Project project = Project.get(id)

        if (project) {
            new Favorite(user: carmSecurityService.currentUser, project: project).save()
            log.debug "Added $project to favories"
        }
    }

    /**
     * Delete all favorites for the provided application ID
     *
     * @param applicationId Application ID for favorites to delete
     */
    void deleteAllForApplicationId(Serializable applicationId) {
        Favorite.executeUpdate("delete from Favorite where application.id = :applicationId", [applicationId: applicationId])
    }

    /**
     * Delete all favorites for the provided project ID
     *
     * @param projectId Project ID for favorites to delete
     */
    void deleteAllForProjectId(Serializable projectId) {
        Favorite.executeUpdate("delete from Favorite where project.id = :projectId", [projectId: projectId])
    }

    /**
     * Delete all favorites for the current user
     */
    void deleteAllFromCurrentUser() {
        Favorite.executeUpdate("delete from Favorite where user = :user", [user: carmSecurityService.currentUser])
    }

    /**
     * Delete a favorite record by ID for the current user.
     *
     * @param id ID of Favorite record to delete
     */
    void deleteFromCurrentUserById(Serializable id) {
        Favorite favorite = Favorite.get(id)

        if (favorite && favorite.user == carmSecurityService.currentUser) {
            favorite.delete()
        }
    }

    /**
     * Find all Favorite objects by current user
     *
     * @return List of Favorite objects for the current user
     */
    List<Favorite> findAllByCurrentUser() {
        springSecurityService.isLoggedIn() ? findAllByUser(carmSecurityService.currentUser) : []
    }

    /**
     * Find all Favorite objects by user
     *
     * @return List of Favorite objects for the user
     */
    List<Favorite> findAllByUser(User user) {
        Favorite.executeQuery("from Favorite where user = :user", [user: user]).sort { it.project <=> it.application }
    }

    /**
     * Determines if the provided Application is on the current user's favorites list
     *
     * @param application Application to test
     * @return True of the application is on the current users favorites list
     */
    boolean isApplicationFavoriteByCurrentUser(Application application) {
        Favorite.countByUserAndApplication(carmSecurityService.currentUser, application) > 0
    }

    /**
     * Determines if the provided Project is on the current user's favorites list
     *
     * @param project Project to test
     * @return True of the project is on the current users favorites list
     */
    boolean isProjectFavoriteByCurrentUser(Project project) {
        Favorite.countByUserAndProject(carmSecurityService.currentUser, project) > 0
    }

    /**
     * Removes an Application from the current user's favorites
     *
     * @param id Application ID
     */
    void removeApplicationFromCurrentUserFavorites(Serializable id) {
        Application application = Application.get(id)

        if (application) {
            Favorite favorite = Favorite.findByUserAndApplication(carmSecurityService.currentUser, application)

            if (favorite) {
                favorite.delete()
                log.debug "Removed $application from favorites"
            }
        }
    }

    /**
     * Removes an Project from the current user's favorites
     *
     * @param id Project ID
     */
    void removeProjectFromCurrentUserFavorites(Serializable id) {
        Project project = Project.get(id)

        if (project) {
            Favorite favorite = Favorite.findByUserAndProject(carmSecurityService.currentUser, project)

            if (favorite) {
                favorite.delete()
                log.debug "Removed $project from favorites"
            }
        }
    }
}
