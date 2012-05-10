package carm

import carm.application.Application
import carm.project.Project

class WatchService {

    static transactional = false

    def carmSecurityService

    /**
     * Adds an Application to the current user's watches.
     *
     * @param id Application ID
     */
    void addApplicationToCurrentUserWatches(Serializable id) {
        Application application = Application.get(id)

        if (application) {
            new Watch(user: carmSecurityService.currentUser, application: application).save()
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
            new Watch(user: carmSecurityService.currentUser, project: project).save()
            log.debug "Added $project to watches"
        }
    }

    /**
     * Removes an Application from the current user's watches
     *
     * @param id Application ID
     */
    void removeApplicationFromCurrentUserWatches(Serializable id) {
        Application application = Application.get(id)

        if (application) {
            Watch watch = Watch.findByUserAndApplication(carmSecurityService.currentUser, application)

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
            Watch watch = Watch.findByUserAndProject(carmSecurityService.currentUser, project)

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
        Watch.countByUserAndApplication(carmSecurityService.currentUser, application) > 0
    }

    /**
     * Determines if the provided Project is on the current user's watch list
     *
     * @param application Project to test
     * @return True of the Project is on the current users watch list
     */
    boolean isProjectWatchedByCurrentUser(Project project) {
        Watch.countByUserAndProject(carmSecurityService.currentUser, project) > 0
    }
}
