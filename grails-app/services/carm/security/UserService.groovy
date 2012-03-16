package carm.security

class UserService {

    static transactional = false

    def grailsApplication

    /**
     * Returns a count of all User objects.
     *
     * @return Total number of User objects.
     */
    int count() {
        User.count()
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
     * Gets a list of all User objects.
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
}
