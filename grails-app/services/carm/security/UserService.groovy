package carm.security

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.transaction.annotation.Transactional

class UserService {

    static transactional = false

    def carmSecurityService
    def favoriteService
    def grailsApplication
    def userGroupService
    def watchService

    /**
     * Returns a count of all User objects.
     *
     * @return Total number of User objects.
     */
    int count() {
        User.count()
    }

    /**
     * Deletes the provided User object.
     *
     * @param user User object to delete
     */
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(User user) {
        def prefix = "delete() :"

        log.debug "$prefix entered, user=$user"

        User.withTransaction { status ->
            carmSecurityService.deleteAllAclsByUser(user)
            favoriteService.deleteAllFromUser(user)
            watchService.deleteAllFromUser(user)
            userGroupService.removeUserFromAllGroups(user)
            UserRole.executeUpdate("delete UserRole where user = :user", [user: user])
            user.delete()
        }

        log.debug "$prefix leaving"
    }

    /**
     * Gets the User object with the provided ID.
     *
     * @param id ID of User object
     * @return Matching User object
     */
    User get(Serializable id) {
        User.get(id)
    }

    /**
     * Gets the User object with the provided username.
     *
     * @param username Username of User object
     * @return Matching User object
     */
    User getByUsername(String username) {
        User.findByUsername(username)
    }

    /**
     * Gets a list of all User objects using provided params.
     *
     * @param params Query parameters
     * @return List of User objects
     */
    List<User> list(Map params) {
        User.list([
                max: grailsApplication.config.ui.user.listMax,
                offset: params?.offset,
                sort: params?.sort,
                order: params?.order
        ])
    }

    /**
     * Gets a list of all User objects.
     *
     * @param params Query parameters
     * @return List of User objects
     */
    List<User> listAll() {
        User.list()
    }
}
