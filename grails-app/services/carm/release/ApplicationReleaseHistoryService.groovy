package carm.release

/**
 * ApplicationReleaseHistory service methods.
 */
class ApplicationReleaseHistoryService {

    static transactional = false

    def grailsApplication

    /**
     * Gets a count of all ApplicationReleaseHistory records.
     *
     * @return Total number of all ApplicationReleaseHistory records
     */
    int count() {
        ApplicationReleaseHistory.count()
    }

    /**
     * Gets the ApplicationReleaseHistory object with the provided ID.
     *
     * @param id ID of ApplicationReleaseHistory object to load
     * @return ApplicationReleaseHistory with the provided ID
     */
    ApplicationReleaseHistory get(Serializable id) {
        ApplicationReleaseHistory.get(id)
    }

    /**
     * Gets all ApplicationReleaseHistory records.
     *
     * @param params Query parameters
     * @return List of ApplicationReleaseHistory objects
     */
    List<ApplicationReleaseHistory> list(Map params) {
        ApplicationReleaseHistory.list([
                max: grailsApplication.config.ui.applicationReleaseHistory.listMax,
                offset: params?.offset,
                sort: params?.sort,
                order: params?.order
        ])
    }
}
