package carm.activity

import static carm.activity.ActivityAction.*

import org.joda.time.DateTime
import carm.project.Project
import carm.module.Module
import carm.application.Application
import carm.release.ApplicationRelease

import carm.system.SystemServer
import carm.system.SystemEnvironment
import carm.system.SystemDeploymentEnvironment
import carm.deployment.ApplicationDeployment

class ActivityTraceService {

    static transactional = false

    def carmSecurityService
    def grailsApplication
    def springSecurityService

    public static final String APPLICATION_DEPLOYMENT_TYPE = ApplicationDeployment.class.name
    public static final String APPLICATION_TYPE = Application.class.name
    public static final String APPLICATION_RELEASE_TYPE = ApplicationRelease.class.name
    public static final String MODULE_TYPE = Module.class.name
    public static final String PROJECT_TYPE = Project.class.name
    public static final String SYSTEM_DEPLOYMENT_ENVIRONMENT_TYPE = SystemDeploymentEnvironment.class.name
    public static final String SYSTEM_ENVIRONMENT_TYPE = SystemEnvironment.class.name
    public static final String SYSTEM_SERVER_TYPE = SystemServer.class.name

    private static final Long ROOT_ID = 0L
    private static final String ROOT_TYPE = "Root"

    /**
     * Builds a query parameter list based on the values provided. If a value is not provided then a sensible default
     * is selected.
     *
     * @param params Map of parameters that may be set
     * @return Query parameter map
     */
    private Map buildQueryParams(Map params) {
        [
                max: params?.max ?: grailsApplication.config.ui.activityTrace.listMax,
                offset: params?.offset,
                sort: params?.sort ?: "dateOccurred",
                order: params?.order ?: "desc"
        ]
    }

    /**
     * Counts the total number of activity events for a Application.
     *
     * @param application Application used for query
     * @return Number of ActivityTrace objects
     */
    int countActivityByApplication(Application application) {
        ActivityTrace.countByOid(generateOid(APPLICATION_TYPE, application.id))
    }

    /**
     * Counts the total number of activity events for a ApplicationDeployment.
     *
     * @param applicationDeployment ApplicationDeployment used for query
     * @return Number of ActivityTrace objects
     */
    int countActivityByApplicationDeployment(ApplicationDeployment applicationDeployment) {
        ActivityTrace.countByOid(generateOid(APPLICATION_DEPLOYMENT_TYPE, applicationDeployment.id))
    }

    /**
     * Counts the total number of activity events for a ApplicationRelease.
     *
     * @param applicationRelease ApplicationRelease used for query
     * @return Number of ActivityTrace objects
     */
    int countActivityByApplicationRelease(ApplicationRelease applicationRelease) {
        ActivityTrace.countByOid(generateOid(APPLICATION_RELEASE_TYPE, applicationRelease.id))
    }

    /**
     * Counts the total number of activity events for a Module.
     *
     * @param module Module used for query
     * @return Number of ActivityTrace objects
     */
    int countActivityByModule(Module module) {
        ActivityTrace.countByOid(generateOid(MODULE_TYPE, module.id))
    }

    /**
     * Counts the total number of activity events for a Project.
     *
     * @param project Project used for query
     * @return Number of ActivityTrace objects
     */
    int countActivityByProject(Project project) {
        ActivityTrace.countByOid(generateOid(PROJECT_TYPE, project.id))
    }

    /**
     * Counts the total number of activity events for a SystemDeploymentEnvironment.
     *
     * @param systemDeploymentEnvironment SystemDeploymentEnvironment used for query
     * @return Number of ActivityTrace objects
     */
    int countActivityBySystemDeploymentEnvironment(SystemDeploymentEnvironment systemDeploymentEnvironment) {
        ActivityTrace.countByOid(generateOid(SYSTEM_DEPLOYMENT_ENVIRONMENT_TYPE, systemDeploymentEnvironment.id))
    }

    /**
     * Counts the total number of activity events for a SystemEnvironment.
     *
     * @param systemEnvironment SystemEnvironment used for query
     * @return Number of ActivityTrace objects
     */
    int countActivityBySystemEnvironment(SystemEnvironment systemEnvironment) {
        ActivityTrace.countByOid(generateOid(SYSTEM_ENVIRONMENT_TYPE, systemEnvironment.id))
    }

    /**
     * Counts the total number of activity events for a SystemServer.
     *
     * @param systemServer SystemServer used for query
     * @return Number of ActivityTrace objects
     */
    int countActivityBySystemServer(SystemServer systemServer) {
        ActivityTrace.countByOid(generateOid(SYSTEM_SERVER_TYPE, systemServer.id))
    }

    /**
     * Counts the total number of activity events for the CARM application.
     *
     * @param project Project used for query
     * @return Number of ActivityTrace objects
     */
    int countActivityByRoot() {
        ActivityTrace.countByOid(generateOid(ROOT_TYPE, ROOT_ID))
    }

    /**
     * Lists latest activity events for an Application in reverse chronological order.
     *
     * @param application Application used for query
     * @param params Query parameters
     * @return List of ActivityTrace objects
     */
    List<ActivityTrace> listActivityByApplication(Application application, Map params) {
        ActivityTrace.findAllByOid(generateOid(APPLICATION_TYPE, application.id), buildQueryParams(params))
    }

    /**
     * Lists latest activity events for an ApplicationDeployment in reverse chronological order.
     *
     * @param applicationDeployment ApplicationDeployment used for query
     * @param params Query parameters
     * @return List of ActivityTrace objects
     */
    List<ActivityTrace> listActivityByApplicationDeployment(ApplicationDeployment applicationDeployment, Map params) {
        ActivityTrace.findAllByOid(generateOid(APPLICATION_DEPLOYMENT_TYPE, applicationDeployment.id), buildQueryParams(params))
    }

    /**
     * Lists latest activity events for an ApplicationRelease in reverse chronological order.
     *
     * @param applicationRelease ApplicationRelease used for query
     * @param params Query parameters
     * @return List of ActivityTrace objects
     */
    List<ActivityTrace> listActivityByApplicationRelease(ApplicationRelease applicationRelease, Map params) {
        ActivityTrace.findAllByOid(generateOid(APPLICATION_RELEASE_TYPE, applicationRelease.id), buildQueryParams(params))
    }

    /**
     * Lists latest activity events for a Module in reverse chronological order.
     *
     * @param module Module used for query
     * @param params Query parameters
     * @return List of ActivityTrace objects
     */
    List<ActivityTrace> listActivityByModule(Module module, Map params) {
        ActivityTrace.findAllByOid(generateOid(MODULE_TYPE, module.id), buildQueryParams(params))
    }

    /**
     * Lists latest activity events for a Project in reverse chronological order.
     *
     * @param project Project used for query
     * @param params Query parameters
     * @return List of ActivityTrace objects
     */
    List<ActivityTrace> listActivityByProject(Project project, Map params) {
        ActivityTrace.findAllByOid(generateOid(PROJECT_TYPE, project.id), buildQueryParams(params))
    }

    /**
     * Lists latest activity events for the CARM application in reverse chronological order.
     *
     * @param params Query parameters
     * @return List of ActivityTrace objects
     */
    List<ActivityTrace> listActivityByRoot(Map params) {
        ActivityTrace.findAllByOid(generateOid(ROOT_TYPE, ROOT_ID), buildQueryParams(params))
    }

    /**
     * Lists latest activity events for a SystemDeploymentEnvironment in reverse chronological order.
     *
     * @param systemDeploymentEnvironment SystemDeploymentEnvironment used for query
     * @param params Query parameters
     * @return List of ActivityTrace objects
     */
    List<ActivityTrace> listActivityBySystemDeploymentEnvironment(SystemDeploymentEnvironment systemDeploymentEnvironment, Map params) {
        ActivityTrace.findAllByOid(generateOid(SYSTEM_DEPLOYMENT_ENVIRONMENT_TYPE, systemDeploymentEnvironment.id), buildQueryParams(params))
    }

    /**
     * Lists latest activity events for a SystemEnvironment in reverse chronological order.
     *
     * @param systemEnvironment SystemEnvironment used for query
     * @param params Query parameters
     * @return List of ActivityTrace objects
     */
    List<ActivityTrace> listActivityBySystemEnvironment(SystemEnvironment systemEnvironment, Map params) {
        ActivityTrace.findAllByOid(generateOid(SYSTEM_ENVIRONMENT_TYPE, systemEnvironment.id), buildQueryParams(params))
    }

    /**
     * Lists latest activity events for a SystemServer in reverse chronological order.
     *
     * @param systemServer SystemServer used for query
     * @param params Query parameters
     * @return List of ActivityTrace objects
     */
    List<ActivityTrace> listActivityBySystemServer(SystemServer systemServer, Map params) {
        ActivityTrace.findAllByOid(generateOid(SYSTEM_SERVER_TYPE, systemServer.id), buildQueryParams(params))
    }

    /**
     * Application object was created.
     *
     * @param application Application that was created
     */
    void applicationCreated(Application application) {
        String projectOid = generateOid(PROJECT_TYPE, application.project.id)
        insertActivityTrace(projectOid, APPLICATION_TYPE, CREATED, application.id, application.name)

        String oid = generateOid(APPLICATION_TYPE, application.id)
        insertActivityTrace(oid, APPLICATION_TYPE, CREATED, application.id, application.name)
    }

    /**
     * Application object was deleted.
     *
     * @param application Application that was deleted
     */
    void applicationDeleted(Application application) {
        String projectOid = generateOid(PROJECT_TYPE, application.project.id)
        insertActivityTrace(projectOid, APPLICATION_TYPE, DELETED, application.id, application.name)
    }

    /**
     * Application object was updated.
     *
     * @param application Application that was updated
     */
    void applicationUpdated(Application application) {
        String projectOid = generateOid(PROJECT_TYPE, application.project.id)
        insertActivityTrace(projectOid, APPLICATION_TYPE, UPDATED, application.id, application.name)

        String oid = generateOid(APPLICATION_TYPE, application.id)
        insertActivityTrace(oid, APPLICATION_TYPE, UPDATED, application.id, application.name)
    }

    /**
     * ApplicationDeployment object was completed.
     *
     * @param applicationDeployment ApplicationDeployment that was completed
     */
    void applicationDeploymentCompleted(ApplicationDeployment applicationDeployment) {
        ApplicationRelease applicationRelease = applicationDeployment.applicationRelease
        Application application = applicationRelease.application
        Project project = application.project
        SystemDeploymentEnvironment deploymentEnvironment = applicationDeployment.deploymentEnvironment
        SystemEnvironment systemEnvironment = deploymentEnvironment.sysEnvironment
        def servers = (application.modules*.systemServers).flatten()

        String objectName = "${application.name}:${applicationRelease.releaseNumber} to ${applicationDeployment.deploymentEnvironment.name}"

        String applicationDeploymentOid = generateOid(APPLICATION_DEPLOYMENT_TYPE, applicationDeployment.id)
        insertActivityTrace(applicationDeploymentOid, APPLICATION_DEPLOYMENT_TYPE, COMPLETED, applicationDeployment.id, objectName)

        String applicationReleaseOid = generateOid(APPLICATION_RELEASE_TYPE, applicationRelease.id)
        insertActivityTrace(applicationReleaseOid, APPLICATION_DEPLOYMENT_TYPE, COMPLETED, applicationDeployment.id, objectName)

        String applicationOid = generateOid(APPLICATION_TYPE, application.id)
        insertActivityTrace(applicationOid, APPLICATION_DEPLOYMENT_TYPE, COMPLETED, applicationDeployment.id, objectName)

        String projectOid = generateOid(PROJECT_TYPE, project.id)
        insertActivityTrace(projectOid, APPLICATION_DEPLOYMENT_TYPE, COMPLETED, applicationDeployment.id, objectName)

        String systemDeploymentEnvironmentOid = generateOid(SYSTEM_DEPLOYMENT_ENVIRONMENT_TYPE, deploymentEnvironment.id)
        insertActivityTrace(systemDeploymentEnvironmentOid, APPLICATION_DEPLOYMENT_TYPE, COMPLETED, applicationDeployment.id, objectName)

        String systemEnvironmentOid = generateOid(SYSTEM_ENVIRONMENT_TYPE, systemEnvironment.id)
        insertActivityTrace(systemEnvironmentOid, APPLICATION_DEPLOYMENT_TYPE, COMPLETED, applicationDeployment.id, objectName)

        servers.each { SystemServer server ->
            String systemServerOid = generateOid(SYSTEM_SERVER_TYPE, server.id)
            insertActivityTrace(systemServerOid, APPLICATION_DEPLOYMENT_TYPE, COMPLETED, applicationDeployment.id, objectName)
        }
    }

    /**
     * ApplicationDeployment object was created.
     *
     * @param applicationDeployment ApplicationDeployment that was created
     */
    void applicationDeploymentCreated(ApplicationDeployment applicationDeployment) {
        ApplicationRelease applicationRelease = applicationDeployment.applicationRelease
        Application application = applicationRelease.application
        Project project = application.project

        String objectName = "${application.name}:${applicationRelease.releaseNumber} to ${applicationDeployment.deploymentEnvironment.name}"

        String applicationDeploymentOid = generateOid(APPLICATION_DEPLOYMENT_TYPE, applicationDeployment.id)
        insertActivityTrace(applicationDeploymentOid, APPLICATION_DEPLOYMENT_TYPE, CREATED, applicationDeployment.id, objectName)

        String applicationReleaseOid = generateOid(APPLICATION_RELEASE_TYPE, applicationRelease.id)
        insertActivityTrace(applicationReleaseOid, APPLICATION_DEPLOYMENT_TYPE, CREATED, applicationDeployment.id, objectName)

        String applicationOid = generateOid(APPLICATION_TYPE, application.id)
        insertActivityTrace(applicationOid, APPLICATION_DEPLOYMENT_TYPE, CREATED, applicationDeployment.id, objectName)

        String projectOid = generateOid(PROJECT_TYPE, project.id)
        insertActivityTrace(projectOid, APPLICATION_DEPLOYMENT_TYPE, CREATED, applicationDeployment.id, objectName)
    }

    /**
     * ApplicationDeployment object was deleted.
     *
     * @param applicationDeployment ApplicationDeployment that was deleted
     */
    void applicationDeploymentDeleted(ApplicationDeployment applicationDeployment) {
        ApplicationRelease applicationRelease = applicationDeployment.applicationRelease
        Application application = applicationRelease.application
        Project project = application.project

        String objectName = "${application.name}:${applicationRelease.releaseNumber} to ${applicationDeployment.deploymentEnvironment.name}"

        String applicationDeploymentOid = generateOid(APPLICATION_DEPLOYMENT_TYPE, applicationDeployment.id)
        insertActivityTrace(applicationDeploymentOid, APPLICATION_DEPLOYMENT_TYPE, DELETED, applicationDeployment.id, objectName)

        String applicationReleaseOid = generateOid(APPLICATION_RELEASE_TYPE, applicationRelease.id)
        insertActivityTrace(applicationReleaseOid, APPLICATION_DEPLOYMENT_TYPE, DELETED, applicationDeployment.id, objectName)

        String applicationOid = generateOid(APPLICATION_TYPE, application.id)
        insertActivityTrace(applicationOid, APPLICATION_DEPLOYMENT_TYPE, DELETED, applicationDeployment.id, objectName)

        String projectOid = generateOid(PROJECT_TYPE, project.id)
        insertActivityTrace(projectOid, APPLICATION_DEPLOYMENT_TYPE, DELETED, applicationDeployment.id, objectName)
    }

    /**
     * ApplicationDeployment object was submitted.
     *
     * @param applicationDeployment ApplicationDeployment that was submitted
     */
    void applicationDeploymentSubmitted(ApplicationDeployment applicationDeployment) {
        ApplicationRelease applicationRelease = applicationDeployment.applicationRelease
        Application application = applicationRelease.application
        Project project = application.project
        SystemDeploymentEnvironment deploymentEnvironment = applicationDeployment.deploymentEnvironment
        SystemEnvironment systemEnvironment = deploymentEnvironment.sysEnvironment
        def servers = (application.modules*.systemServers).flatten()

        String objectName = "${application.name}:${applicationRelease.releaseNumber} to ${applicationDeployment.deploymentEnvironment.name}"

        String applicationDeploymentOid = generateOid(APPLICATION_DEPLOYMENT_TYPE, applicationDeployment.id)
        insertActivityTrace(applicationDeploymentOid, APPLICATION_DEPLOYMENT_TYPE, SUBMITTED, applicationDeployment.id, objectName)

        String applicationReleaseOid = generateOid(APPLICATION_RELEASE_TYPE, applicationRelease.id)
        insertActivityTrace(applicationReleaseOid, APPLICATION_DEPLOYMENT_TYPE, SUBMITTED, applicationDeployment.id, objectName)

        String applicationOid = generateOid(APPLICATION_TYPE, application.id)
        insertActivityTrace(applicationOid, APPLICATION_DEPLOYMENT_TYPE, SUBMITTED, applicationDeployment.id, objectName)

        String projectOid = generateOid(PROJECT_TYPE, project.id)
        insertActivityTrace(projectOid, APPLICATION_DEPLOYMENT_TYPE, SUBMITTED, applicationDeployment.id, objectName)

        String systemDeploymentEnvironmentOid = generateOid(SYSTEM_DEPLOYMENT_ENVIRONMENT_TYPE, deploymentEnvironment.id)
        insertActivityTrace(systemDeploymentEnvironmentOid, APPLICATION_DEPLOYMENT_TYPE, SUBMITTED, applicationDeployment.id, objectName)

        String systemEnvironmentOid = generateOid(SYSTEM_ENVIRONMENT_TYPE, systemEnvironment.id)
        insertActivityTrace(systemEnvironmentOid, APPLICATION_DEPLOYMENT_TYPE, SUBMITTED, applicationDeployment.id, objectName)

        servers.each { SystemServer server ->
            String systemServerOid = generateOid(SYSTEM_SERVER_TYPE, server.id)
            insertActivityTrace(systemServerOid, APPLICATION_DEPLOYMENT_TYPE, SUBMITTED, applicationDeployment.id, objectName)
        }
    }

    /**
     * ApplicationDeployment object was updated.
     *
     * @param applicationDeployment ApplicationDeployment that was updated
     */
    void applicationDeploymentUpdated(ApplicationDeployment applicationDeployment) {
        ApplicationRelease applicationRelease = applicationDeployment.applicationRelease
        Application application = applicationRelease.application
        Project project = application.project

        String objectName = "${application.name}:${applicationRelease.releaseNumber} to ${applicationDeployment.deploymentEnvironment.name}"

        String applicationDeploymentOid = generateOid(APPLICATION_DEPLOYMENT_TYPE, applicationDeployment.id)
        insertActivityTrace(applicationDeploymentOid, APPLICATION_DEPLOYMENT_TYPE, UPDATED, applicationDeployment.id, objectName)

        String applicationReleaseOid = generateOid(APPLICATION_RELEASE_TYPE, applicationRelease.id)
        insertActivityTrace(applicationReleaseOid, APPLICATION_DEPLOYMENT_TYPE, UPDATED, applicationDeployment.id, objectName)

        String applicationOid = generateOid(APPLICATION_TYPE, application.id)
        insertActivityTrace(applicationOid, APPLICATION_DEPLOYMENT_TYPE, UPDATED, applicationDeployment.id, objectName)

        String projectOid = generateOid(PROJECT_TYPE, project.id)
        insertActivityTrace(projectOid, APPLICATION_DEPLOYMENT_TYPE, UPDATED, applicationDeployment.id, objectName)
    }

    /**
     * ApplicationRelease object was completed.
     *
     * @param applicationRelease ApplicationRelease that was completed
     */
    void applicationReleaseCompleted(ApplicationRelease applicationRelease) {
        Application application = applicationRelease.application
        String objectName = "${application.name}:${applicationRelease.releaseNumber}"

        String projectOid = generateOid(PROJECT_TYPE, application.project.id)
        insertActivityTrace(projectOid, APPLICATION_RELEASE_TYPE, COMPLETED, applicationRelease.id, objectName)

        String applicationOid = generateOid(APPLICATION_TYPE, application.id)
        insertActivityTrace(applicationOid, APPLICATION_RELEASE_TYPE, COMPLETED, applicationRelease.id, objectName)
    }

    /**
     * ApplicationRelease object was created.
     *
     * @param applicationRelease ApplicationRelease that was created
     */
    void applicationReleaseCreated(ApplicationRelease applicationRelease) {
        Application application = applicationRelease.application
        String objectName = "${application.name}:${applicationRelease.releaseNumber}"

        String projectOid = generateOid(PROJECT_TYPE, application.project.id)
        insertActivityTrace(projectOid, APPLICATION_RELEASE_TYPE, CREATED, applicationRelease.id, objectName)

        String applicationOid = generateOid(APPLICATION_TYPE, application.id)
        insertActivityTrace(applicationOid, APPLICATION_RELEASE_TYPE, CREATED, applicationRelease.id, objectName)
    }

    /**
     * ApplicationRelease object was deleted.
     *
     * @param applicationRelease ApplicationRelease that was deleted
     */
    void applicationReleaseDeleted(ApplicationRelease applicationRelease) {
        Application application = applicationRelease.application
        String objectName = "${application.name}:${applicationRelease.releaseNumber}"

        String projectOid = generateOid(PROJECT_TYPE, application.project.id)
        insertActivityTrace(projectOid, APPLICATION_RELEASE_TYPE, DELETED, applicationRelease.id, objectName)

        String applicationOid = generateOid(APPLICATION_TYPE, application.id)
        insertActivityTrace(applicationOid, APPLICATION_RELEASE_TYPE, DELETED, applicationRelease.id, objectName)
    }

    /**
     * ApplicationRelease object was submitted.
     *
     * @param applicationRelease ApplicationRelease that was submitted
     */
    void applicationReleaseSubmitted(ApplicationRelease applicationRelease) {
        Application application = applicationRelease.application
        String objectName = "${application.name}:${applicationRelease.releaseNumber}"

        String projectOid = generateOid(PROJECT_TYPE, application.project.id)
        insertActivityTrace(projectOid, APPLICATION_RELEASE_TYPE, SUBMITTED, applicationRelease.id, objectName)

        String applicationOid = generateOid(APPLICATION_TYPE, application.id)
        insertActivityTrace(applicationOid, APPLICATION_RELEASE_TYPE, SUBMITTED, applicationRelease.id, objectName)
    }

    /**
     * ApplicationRelease object was updated.
     *
     * @param applicationRelease ApplicationRelease that was updated
     */
    void applicationReleaseUpdated(ApplicationRelease applicationRelease) {
        Application application = applicationRelease.application
        String objectName = "${application.name}:${applicationRelease.releaseNumber}"

        String projectOid = generateOid(PROJECT_TYPE, application.project.id)
        insertActivityTrace(projectOid, APPLICATION_RELEASE_TYPE, UPDATED, applicationRelease.id, objectName)

        String applicationOid = generateOid(APPLICATION_TYPE, application.id)
        insertActivityTrace(applicationOid, APPLICATION_RELEASE_TYPE, UPDATED, applicationRelease.id, objectName)
    }

    /**
     * Module object was created.
     *
     * @param module Module that was created
     */
    void moduleCreated(Module module) {
        String applicationOid = generateOid(APPLICATION_TYPE, module.application.id)
        insertActivityTrace(applicationOid, MODULE_TYPE, CREATED, module.id, module.name)

        String moduleOid = generateOid(MODULE_TYPE, module.id)
        insertActivityTrace(moduleOid, MODULE_TYPE, CREATED, module.id, module.name)
    }

    /**
     * Module object was deleted.
     *
     * @param module Module that was deleted
     */
    void moduleDeleted(Module module) {
        String applicationOid = generateOid(APPLICATION_TYPE, module.id)
        insertActivityTrace(applicationOid, MODULE_TYPE, DELETED, module.id, module.name)
    }

    /**
     * Module object was updated.
     *
     * @param module Module that was updated
     */
    void moduleUpdated(Module module) {
        String applicationOid = generateOid(APPLICATION_TYPE, module.application.id)
        insertActivityTrace(applicationOid, MODULE_TYPE, UPDATED, module.id, module.name)

        String oid = generateOid(MODULE_TYPE, module.id)
        insertActivityTrace(oid, MODULE_TYPE, UPDATED, module.id, module.name)
    }

    /**
     * Project object was created.
     *
     * @param project Project that was created
     */
    void projectCreated(Project project) {
        String rootOid = generateOid(ROOT_TYPE, ROOT_ID)
        insertActivityTrace(rootOid, PROJECT_TYPE, CREATED, project.id, project.name)

        String projectOid = generateOid(PROJECT_TYPE, project.id)
        insertActivityTrace(projectOid, PROJECT_TYPE, CREATED, project.id, project.name)
    }

    /**
     * Project object was deleted.
     *
     * @param project Project that was deleted
     */
    void projectDeleted(Project project) {
        String rootOid = generateOid(ROOT_TYPE, ROOT_ID)
        insertActivityTrace(rootOid, PROJECT_TYPE, DELETED, project.id, project.name)
    }

    /**
     * Project object was updated.
     *
     * @param project Project that was updated
     */
    void projectUpdated(Project project) {
        String rootOid = generateOid(ROOT_TYPE, ROOT_ID)
        insertActivityTrace(rootOid, PROJECT_TYPE, UPDATED, project.id, project.name)

        String oid = generateOid(PROJECT_TYPE, project.id)
        insertActivityTrace(oid, PROJECT_TYPE, UPDATED, project.id, project.name)
    }

    /**
     * SystemDeploymentEnvironment object was created.
     *
     * @param systemDeploymentEnvironment SystemDeploymentEnvironment that was created
     */
    void systemDeploymentEnvironmentCreated(SystemDeploymentEnvironment systemDeploymentEnvironment) {
        String systemEnvironmentOid = generateOid(SYSTEM_ENVIRONMENT_TYPE, systemDeploymentEnvironment.sysEnvironment.id)
        insertActivityTrace(systemEnvironmentOid, SYSTEM_DEPLOYMENT_ENVIRONMENT_TYPE, CREATED, systemDeploymentEnvironment.id, systemDeploymentEnvironment.name)

        String oid = generateOid(SYSTEM_DEPLOYMENT_ENVIRONMENT_TYPE, systemDeploymentEnvironment.id)
        insertActivityTrace(oid, SYSTEM_DEPLOYMENT_ENVIRONMENT_TYPE, CREATED, systemDeploymentEnvironment.id, systemDeploymentEnvironment.name)
    }

    /**
     * SystemDeploymentEnvironment object was deleted.
     *
     * @param systemDeploymentEnvironment SystemDeploymentEnvironment that was deleted
     */
    void systemDeploymentEnvironmentDeleted(SystemDeploymentEnvironment systemDeploymentEnvironment) {
        String systemEnvironmentOid = generateOid(SYSTEM_ENVIRONMENT_TYPE, systemDeploymentEnvironment.sysEnvironment.id)
        insertActivityTrace(systemEnvironmentOid, SYSTEM_DEPLOYMENT_ENVIRONMENT_TYPE, DELETED, systemDeploymentEnvironment.id, systemDeploymentEnvironment.name)
    }

    /**
     * SystemDeploymentEnvironment object was updated.
     *
     * @param systemDeploymentEnvironment SystemDeploymentEnvironment that was updated
     */
    void systemDeploymentEnvironmentUpdated(SystemDeploymentEnvironment systemDeploymentEnvironment) {
        String systemEnvironmentOid = generateOid(SYSTEM_ENVIRONMENT_TYPE, systemDeploymentEnvironment.sysEnvironment.id)
        insertActivityTrace(systemEnvironmentOid, SYSTEM_DEPLOYMENT_ENVIRONMENT_TYPE, UPDATED, systemDeploymentEnvironment.id, systemDeploymentEnvironment.name)

        String oid = generateOid(SYSTEM_DEPLOYMENT_ENVIRONMENT_TYPE, systemDeploymentEnvironment.id)
        insertActivityTrace(oid, SYSTEM_DEPLOYMENT_ENVIRONMENT_TYPE, UPDATED, systemDeploymentEnvironment.id, systemDeploymentEnvironment.name)
    }

    /**
     * SystemEnvironment object was created.
     *
     * @param systemEnvironment SystemEnvironment that was created
     */
    void systemEnvironmentCreated(SystemEnvironment systemEnvironment) {
        String rootOid = generateOid(ROOT_TYPE, ROOT_ID)
        insertActivityTrace(rootOid, SYSTEM_ENVIRONMENT_TYPE, CREATED, systemEnvironment.id, systemEnvironment.name)

        String projectOid = generateOid(SYSTEM_ENVIRONMENT_TYPE, systemEnvironment.id)
        insertActivityTrace(projectOid, SYSTEM_ENVIRONMENT_TYPE, CREATED, systemEnvironment.id, systemEnvironment.name)
    }

    /**
     * SystemEnvironment object was deleted.
     *
     * @param systemEnvironment SystemEnvironment that was deleted
     */
    void systemEnvironmentDeleted(SystemEnvironment systemEnvironment) {
        String rootOid = generateOid(ROOT_TYPE, ROOT_ID)
        insertActivityTrace(rootOid, SYSTEM_ENVIRONMENT_TYPE, DELETED, systemEnvironment.id, systemEnvironment.name)
    }

    /**
     * SystemEnvironment object was updated.
     *
     * @param systemEnvironment SystemEnvironment that was updated
     */
    void systemEnvironmentUpdated(SystemEnvironment systemEnvironment) {
        String rootOid = generateOid(ROOT_TYPE, ROOT_ID)
        insertActivityTrace(rootOid, SYSTEM_ENVIRONMENT_TYPE, UPDATED, systemEnvironment.id, systemEnvironment.name)

        String oid = generateOid(SYSTEM_ENVIRONMENT_TYPE, systemEnvironment.id)
        insertActivityTrace(oid, SYSTEM_ENVIRONMENT_TYPE, UPDATED, systemEnvironment.id, systemEnvironment.name)
    }

    /**
     * SystemServer object was created.
     *
     * @param systemServer SystemServer that was created
     */
    void systemServerCreated(SystemServer systemServer) {
        String systemOid = generateOid(SYSTEM_ENVIRONMENT_TYPE, systemServer.sysEnvironment.id)
        insertActivityTrace(systemOid, SYSTEM_SERVER_TYPE, CREATED, systemServer.id, systemServer.name)

        String oid = generateOid(SYSTEM_SERVER_TYPE, systemServer.id)
        insertActivityTrace(oid, SYSTEM_SERVER_TYPE, CREATED, systemServer.id, systemServer.name)
    }

    /**
     * SystemServer object was deleted.
     *
     * @param systemServer SystemServer that was deleted
     */
    void systemServerDeleted(SystemServer systemServer) {
        String systemOid = generateOid(SYSTEM_ENVIRONMENT_TYPE, systemServer.sysEnvironment.id)
        insertActivityTrace(systemOid, SYSTEM_SERVER_TYPE, DELETED, systemServer.id, systemServer.name)
    }

    /**
     * SystemServer object was updated.
     *
     * @param systemServer SystemServer that was updated
     */
    void systemServerUpdated(SystemServer systemServer) {
        String systemOid = generateOid(SYSTEM_ENVIRONMENT_TYPE, systemServer.sysEnvironment.id)
        insertActivityTrace(systemOid, SYSTEM_SERVER_TYPE, UPDATED, systemServer.id, systemServer.name)

        String oid = generateOid(SYSTEM_SERVER_TYPE, systemServer.id)
        insertActivityTrace(oid, SYSTEM_SERVER_TYPE, UPDATED, systemServer.id, systemServer.name)
    }

    private getMostActiveByOidType(String oidType, Map params) {
        def activityTraces = ActivityTrace.executeQuery(
                """select
                    oid
                from
                    ActivityTrace
                where
                    oid like :oidLike
                group by
                    oid
                order by
                    count(oid) desc""",
                [oidLike: oidType + ":%"], [max: buildQueryParams(params).max])
    }

    public List<Serializable> getMostActiveApplicationIds(Map params) {
        getMostActiveByOidType(APPLICATION_TYPE, params).collect { String oid -> oid.split(":")[1] as Long }
    }

    public List<Serializable> getMostActiveSystemEnvironmentIds(Map params) {
        getMostActiveByOidType(SYSTEM_ENVIRONMENT_TYPE, params).collect { String oid -> oid.split(":")[1] as Long }
    }

    /**
     * Inserts a new ActivityTrace record
     *
     * @param oid OID value
     * @param summary Summary of the event
     * @return
     */
    private insertActivityTrace(String oid, String objectType, ActivityAction action, Long objectId, String objectName) {
        ActivityTrace.withNewSession {
            ActivityTrace trace = new ActivityTrace(oid: oid, action: action, objectId: objectId, objectName: objectName,
                    objectType: objectType, dateOccurred: new DateTime(), username: carmSecurityService.currentUsername)

            if (!trace.save()) {
                println "Error on save" + trace.errors
            }
        }
    }

    /**
     * Generates an OID for the specified type and ID value.
     *
     * @param domain Domain name
     * @param id ID of object
     * @return Generated OID
     */
    private String generateOid(String domain, Long id) {
        return "${domain}:${id}"
    }
}
