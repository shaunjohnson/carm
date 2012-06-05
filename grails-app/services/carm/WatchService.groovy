package carm

import carm.application.Application
import carm.project.Project
import carm.security.User

class WatchService {

    static transactional = false

    def userService

    /**
     * Adds an Application to the current user's watches.
     *
     * @param id Application ID
     */
    void addApplicationToCurrentUserWatches(Serializable id) {
        Application application = Application.get(id)

        if (application) {
            new Watch(user: userService.currentUser, application: application).save()
            log.debug "Added $application to watches"
        }
    }

    /**
     * Adds a Project to the current user's watches.
     *
     * @param id Project ID
     */
    void addProjectToCurrentUserWatches(Serializable id) {
        Project project = Project.get(id)

        if (project) {
            new Watch(user: userService.currentUser, project: project).save()
            log.debug "Added $project to watches"
        }
    }

    /**
     * Delete all watches for the provided application ID
     *
     * @param applicationId Application ID for watches to delete
     */
    void deleteAllForApplicationId(Serializable applicationId) {
        Watch.executeUpdate("delete from Watch where application.id = :applicationId", [applicationId: applicationId])
    }

    /**
     * Delete all watches for the provided project ID
     *
     * @param projectId Project ID for watches to delete
     */
    void deleteAllForProjectId(Serializable projectId) {
        Watch.executeUpdate("delete from Watch where project.id = :projectId", [projectId: projectId])
    }

    /**
     * Delete all watches for the current user
     */
    void deleteAllFromCurrentUser() {
        deleteAllFromUser(userService.currentUser)
    }

    /**
     * Delete all watches for the provided user
     *
     * @param user User to delete watches for
     */
    void deleteAllFromUser(User user) {
        Watch.executeUpdate("delete from Watch where user = :user", [user: user])
    }


    /**
     * Delete a Watch record by ID for the current user.
     *
     * @param id ID of Watch record to delete
     */
    void deleteFromCurrentUserById(Serializable id) {
        Watch watch = Watch.get(id)

        if (watch && watch.user == userService.currentUser) {
            watch.delete()
        }
    }

    /**
     * Find all Watch objects by user
     *
     * @return List of Watch objects for the user
     */
    List<Watch> findAllByUser(User user) {
        Watch.executeQuery("from Watch where user = :user", [user: user]).sort { it.project <=> it.application }
    }

    /**
     * Finds all user email addresses by application.
     *
     * @param application Application used to filter watches
     * @return List of email addresses
     */
    List<String> findAllEmailByApplication(Application application) {
        Watch.findAllByApplication(application)*.user.email
    }

    /**
     * Finds all user email addresses by application.
     *
     * @param application Application used to filter watches
     * @return List of email addresses
     */
    List<String> findAllEmailByProject(Project project) {
        Watch.findAllByProject(project)*.user.email
    }

    /**
     * Removes an Application from the current user's watches
     *
     * @param id Application ID
     */
    void removeApplicationFromCurrentUserWatches(Serializable id) {
        Application application = Application.get(id)

        if (application) {
            Watch watch = Watch.findByUserAndApplication(userService.currentUser, application)

            if (watch) {
                watch.delete()
                log.debug "Removed $application from watches"
            }
        }
    }

    /**
     * Removes an Project from the current user's watches
     *
     * @param id Project ID
     */
    void removeProjectFromCurrentUserWatches(Serializable id) {
        Project project = Project.get(id)

        if (project) {
            Watch watch = Watch.findByUserAndProject(userService.currentUser, project)

            if (watch) {
                watch.delete()
                log.debug "Removed $project from watches"
            }
        }
    }

    /**
     * Determines if the provided Application is on the current user's watch list
     *
     * @param application Application to test
     * @return True of the Application is on the current users watch list
     */
    boolean isApplicationWatchedByCurrentUser(Application application) {
        Watch.countByUserAndApplication(userService.currentUser, application) > 0
    }

    /**
     * Determines if the provided Project is on the current user's watch list
     *
     * @param application Project to test
     * @return True of the Project is on the current users watch list
     */
    boolean isProjectWatchedByCurrentUser(Project project) {
        Watch.countByUserAndProject(userService.currentUser, project) > 0
    }
}
