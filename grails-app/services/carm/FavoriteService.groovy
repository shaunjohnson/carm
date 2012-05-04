package carm

import carm.application.Application

class FavoriteService {

    static transactional = false

    def carmSecurityService

    /**
     * Find all Favorite objects by current user
     *
     * @return List of Favorite objects for the current user
     */
    List<Favorite> findAllByCurrentUser() {
        Favorite.executeQuery("from Favorite where user = :user order by application.name asc",
                [user: carmSecurityService.currentUser])
    }

    /**
     * Determines if the provided entity is on the current user's favorites list
     *
     * @param entity Entity to test
     * @return True of the entity is on the current users favorites list
     */
    boolean isFavoriteByCurrentuser(entity) {
        if (entity instanceof Application) {
            return Favorite.countByUserAndApplication(carmSecurityService.currentUser, entity) > 0
        }
        else {
            return false
        }
    }

    /**
     * Adds an Application to the current user's favorites.
     *
     * @param id Application ID
     */
    void addToFavorites(Serializable id) {
        Application application = Application.get(id)

        if (application) {
            new Favorite(user: carmSecurityService.currentUser, application: application).save()
            log.debug "Added $application to favories"
        }
    }

    /**
     * Removes an Application from the current user's favorites
     *
     * @param id Application ID
     */
    void removeFromFavorites(Serializable id) {
        Application application = Application.get(id)

        if (application) {
            Favorite favorite = Favorite.findByUserAndApplication(carmSecurityService.currentUser, application)

            if (favorite) {
                favorite.delete()
                log.debug "Removed $application from favories"
            }
        }
    }
}
