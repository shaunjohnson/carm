package carm.security

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.transaction.annotation.Transactional

class UserService {

    static transactional = false

    def aclService
    def favoriteService
    def grailsApplication
    def springSecurityService
    def userGroupService
    def watchService

    /**
     * Finds all user email addresses by username in list
     *
     * @param usernames Username list used for filtering
     * @return List of email addresses
     */
    List<String> collectAllEmailByUsernameInList(List<String> usernames) {
        User.findAllByUsernameInList(usernames)*.email
    }

    /**
     * Returns a count of all User objects.
     *
     * @return Total number of User objects.
     */
    int count() {
        User.count()
    }

    /**
     * Gets the current User.
     *
     * @return Current User object.
     */
    User getCurrentUser() {
        (User) springSecurityService.currentUser
    }

    /**
     * Gets the current User's username
     *
     * @return Username
     */
    String getCurrentUsername() {
        currentUser.username
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
            aclService.deleteAllAclsByUser(user)
            favoriteService.deleteAllFromUser(user)
            watchService.deleteAllFromUser(user)
            userGroupService.removeUserFromAllGroups(user)
            UserRole.executeUpdate("delete UserRole where user = :user", [user: user])
            user.delete()
        }

        log.debug "$prefix leaving"
    }

    /**
     * Finds a user by username.
     *
     * @param username Username for user to find
     * @return Matching User object
     */
    User findByUsername(String username) {
        User.findByUsername(username)
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
