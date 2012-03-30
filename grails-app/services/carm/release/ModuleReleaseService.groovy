package carm.release

class ModuleReleaseService {

    static transactional = false

    def applicationReleaseService
    def moduleService

    /**
     * Determines if the module release is deployable.
     *
     * @param moduleRelease Application release object to test
     * @return True if the module release can be deployed
     */
    boolean isDeployable(ModuleRelease moduleRelease) {
        def moduleIsDeployable = moduleService.isDeployable(moduleRelease.module)
        def releaseIsDeployable = applicationReleaseService.isDeployable(moduleRelease.applicationRelease)

        return moduleIsDeployable && releaseIsDeployable
    }
}
