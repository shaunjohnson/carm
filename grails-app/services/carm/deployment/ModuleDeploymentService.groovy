package carm.deployment

class ModuleDeploymentService {

    static transactional = false

    /**
     * Returns a count of all ModuleDeployment objects.
     *
     * @return Total number of ModuleDeployment objects.
     */
    int count() {
        ModuleDeployment.count()
    }

    /**
     * Gets the ModuleDeployment object with the provided ID.
     *
     * @param id ID of ModuleDeployment object
     * @return Matching ModuleDeployment object
     */
    ModuleDeployment get(Serializable id) {
        ModuleDeployment.get(id)
    }

    /**
     * Gets a list of all ModuleDeployment objects.
     *
     * @param params Query parameters
     * @return List of ModuleDeployment objects
     */
    List<ModuleDeployment> list(Map params) {
        ModuleDeployment.list(params)
    }
}
