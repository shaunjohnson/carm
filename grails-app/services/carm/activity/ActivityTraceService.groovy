package carm.activity

import org.springframework.context.ApplicationContextAware
import org.springframework.context.ApplicationContext
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import static carm.activity.ActivityAction.*
import org.joda.time.DateTime
import carm.project.Project
import carm.module.Module
import carm.application.Application
import carm.release.ApplicationRelease
import carm.system.System

class ActivityTraceService implements ApplicationContextAware {

    static transactional = false

    def carmSecurityService
    def springSecurityService

    MessageSource messageSource

    private static final String APPLICATION_TYPE = Application.class.name
    private static final String APPLICATION_RELEASE_TYPE = ApplicationRelease.class.name
    private static final String MODULE_TYPE = Module.class.name
    private static final String PROJECT_TYPE = Project.class.name
    private static final String SYSTEM_TYPE = System.class.name

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
                max: params.max ?: 10,
                sort: "dateOccurred",
                order: "desc"
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
     * Counts the total number of activity events for a System.
     *
     * @param system System used for query
     * @return Number of ActivityTrace objects
     */
    int countActivityBySystem(System system) {
        ActivityTrace.countByOid(generateOid(SYSTEM_TYPE, system.id))
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
     * Lists latest activity events for a System in reverse chronological order.
     *
     * @param system System used for query
     * @param params Query parameters
     * @return List of ActivityTrace objects
     */
    List<ActivityTrace> listActivityBySystem(System system, Map params) {
        ActivityTrace.findAllByOid(generateOid(SYSTEM_TYPE, system.id), buildQueryParams(params))
    }


    /**
     * Application object was created.
     *
     * @param application Application that was created
     */
    void applicationCreated(Application application) {
        String projectOid = generateOid(PROJECT_TYPE, application.project.id)
        insertActivityTrace(projectOid, APPLICATION_TYPE, CREATED, application.id, application.name)

        String applicationOid = generateOid(APPLICATION_TYPE, application.id)
        insertActivityTrace(applicationOid, APPLICATION_TYPE, CREATED, application.id, application.name)
    }

    /**
     * Application object was deleted.
     *
     * @param application Application that was deleted
     */
    void applicationDeleted(Application application) {
        String oid = generateOid(PROJECT_TYPE, application.project.id)
        insertActivityTrace(oid, APPLICATION_TYPE, DELETED, application.id, application.name)
    }

    /**
     * Application object was updated.
     *
     * @param application Application that was updated
     */
    void applicationUpdated(Application application) {
        String oid = generateOid(APPLICATION_TYPE, application.id)
        insertActivityTrace(oid, APPLICATION_TYPE, UPDATED, application.id, application.name)
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
        String oid = generateOid(APPLICATION_TYPE, module.id)
        insertActivityTrace(oid, MODULE_TYPE, DELETED, module.id, module.name)
    }

    /**
     * Module object was updated.
     *
     * @param module Module that was updated
     */
    void moduleUpdated(Module module) {
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
        String oid = generateOid(ROOT_TYPE, ROOT_ID)
        insertActivityTrace(oid, PROJECT_TYPE, DELETED, project.id, project.name)
    }

    /**
     * Project object was updated.
     *
     * @param project Project that was updated
     */
    void projectUpdated(Project project) {
        String oid = generateOid(PROJECT_TYPE, project.id)
        insertActivityTrace(oid, PROJECT_TYPE, UPDATED, project.id, project.name)
    }

    /**
     * System object was created.
     *
     * @param system System that was created
     */
    void systemCreated(System system) {
        String rootOid = generateOid(ROOT_TYPE, ROOT_ID)
        insertActivityTrace(rootOid, SYSTEM_TYPE, CREATED, system.id, system.name)

        String projectOid = generateOid(SYSTEM_TYPE, system.id)
        insertActivityTrace(projectOid, SYSTEM_TYPE, CREATED, system.id, system.name)
    }

    /**
     * System object was deleted.
     *
     * @param system System that was deleted
     */
    void systemDeleted(System system) {
        String rootOid = generateOid(ROOT_TYPE, ROOT_ID)
        insertActivityTrace(rootOid, SYSTEM_TYPE, DELETED, system.id, system.name)

        String oid = generateOid(ROOT_TYPE, ROOT_ID)
        insertActivityTrace(oid, SYSTEM_TYPE, DELETED, system.id, system.name)
    }

    /**
     * System object was updated.
     *
     * @param system System that was updated
     */
    void systemUpdated(System system) {
        String rootOid = generateOid(ROOT_TYPE, ROOT_ID)
        insertActivityTrace(rootOid, SYSTEM_TYPE, UPDATED, system.id, system.name)

        String oid = generateOid(SYSTEM_TYPE, system.id)
        insertActivityTrace(oid, SYSTEM_TYPE, UPDATED, system.id, system.name)
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
            if (trace.save()) {
                // println "Successful save"
            }
            else {
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

    /**
     * Generates a summary string using the provided key and action.
     *
     * @param type type
     * @param args
     * @return
     */
    private String generateSummary(domain, action, args) {
        return messageSource.getMessage("activityTrace.${domain}.${action}", args?.toArray(), LocaleContextHolder.getLocale())
    }

    /**
     * Sets the current applicationContext.
     *
     * @param applicationContext current ApplicationContext
     */
    @Override
    void setApplicationContext(ApplicationContext applicationContext) {
        messageSource = applicationContext.getBean("messageSource")
    }
}
