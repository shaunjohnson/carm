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

class ActivityTraceService implements ApplicationContextAware {

    static transactional = false

    def carmSecurityService
    def springSecurityService

    MessageSource messageSource

    private static final String APPLICATION_TYPE = Application.class.name
    private static final String APPLICATION_RELEASE_TYPE = ApplicationRelease.class.name
    private static final String MODULE_TYPE = Module.class.name
    private static final String PROJECT_TYPE = Project.class.name

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
    def countActivityByApplication(Application application) {
        String oid = generateOid(APPLICATION_TYPE, application.id)
        ActivityTrace.countByOid(oid)
    }

    /**
     * Counts the total number of activity events for a Module.
     *
     * @param module Module used for query
     * @return Number of ActivityTrace objects
     */
    def countActivityByModule(Module module) {
        String oid = generateOid(MODULE_TYPE, module.id)
        ActivityTrace.countByOid(oid)
    }

    /**
     * Counts the total number of activity events for a Project.
     *
     * @param project Project used for query
     * @return Number of ActivityTrace objects
     */
    def countActivityByProject(Project project) {
        String oid = generateOid(PROJECT_TYPE, project.id)
        ActivityTrace.countByOid(oid)
    }

    /**
     * Counts the total number of activity events for the CARM application.
     *
     * @param project Project used for query
     * @return Number of ActivityTrace objects
     */
    def countActivityByRoot() {
        String oid = generateOid(ROOT_TYPE, ROOT_ID)
        ActivityTrace.countByOid(oid)
    }


    /**
     * Lists latest activity events for an Application in reverse chronological order.
     *
     * @param application Application used for query
     * @param params Query parameters
     * @return List of ActivityTrace objects
     */
    List<ActivityTrace> listActivityByApplication(Application application, Map params) {
        String oid = generateOid(APPLICATION_TYPE, application.id)
        ActivityTrace.findAllByOid(oid, buildQueryParams(params))
    }

    /**
     * Lists latest activity events for a Module in reverse chronological order.
     *
     * @param module Module used for query
     * @param params Query parameters
     * @return List of ActivityTrace objects
     */
    List<ActivityTrace> listActivityByModule(Module module, Map params) {
        String oid = generateOid(MODULE_TYPE, module.id)
        ActivityTrace.findAllByOid(oid, buildQueryParams(params))
    }

    /**
     * Lists latest activity events for a Project in reverse chronological order.
     *
     * @param project Project used for query
     * @param params Query parameters
     * @return List of ActivityTrace objects
     */
    List<ActivityTrace> listActivityByProject(Project project, Map params) {
        String oid = generateOid(PROJECT_TYPE, project.id)
        ActivityTrace.findAllByOid(oid, buildQueryParams(params))
    }

    /**
     * Lists latest activity events for the CARM application in reverse chronological order.
     *
     * @param params Query parameters
     * @return List of ActivityTrace objects
     */
    List<ActivityTrace> listActivityByRoot(Map params) {
        String oid = generateOid(ROOT_TYPE, ROOT_ID)
        ActivityTrace.findAllByOid(oid, buildQueryParams(params))
    }


    /**
     * Application object was created.
     *
     * @param application Application that was created
     * @return
     */
    def applicationCreated(Application application) {
        String projectOid = generateOid(PROJECT_TYPE, application.project.id)
        insertActivityTrace(projectOid, APPLICATION_TYPE, CREATED, application.id, application.name)

        String applicationOid = generateOid(APPLICATION_TYPE, application.id)
        insertActivityTrace(applicationOid, APPLICATION_TYPE, CREATED, application.id, application.name)
    }

    /**
     * Application object was deleted.
     *
     * @param application Application that was deleted
     * @return
     */
    def applicationDeleted(Application application) {
        String oid = generateOid(PROJECT_TYPE, application.project.id)
        insertActivityTrace(oid, APPLICATION_TYPE, DELETED, application.id, application.name)
    }

    /**
     * Application object was updated.
     *
     * @param application Application that was updated
     * @return
     */
    def applicationUpdated(Application application) {
        String oid = generateOid(APPLICATION_TYPE, application.id)
        insertActivityTrace(oid, APPLICATION_TYPE, UPDATED, application.id, application.name)
    }

    /**
     * ApplicationRelease object was completed.
     *
     * @param applicationRelease ApplicationRelease that was completed
     * @return
     */
    def applicationReleaseCompleted(ApplicationRelease applicationRelease) {
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
     * @return
     */
    def applicationReleaseCreated(ApplicationRelease applicationRelease) {
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
     * @return
     */
    def applicationReleaseDeleted(ApplicationRelease applicationRelease) {
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
     * @return
     */
    def applicationReleaseSubmitted(ApplicationRelease applicationRelease) {
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
     * @return
     */
    def applicationReleaseUpdated(ApplicationRelease applicationRelease) {
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
     * @return
     */
    def moduleCreated(Module module) {
        String applicationOid = generateOid(APPLICATION_TYPE, module.application.id)
        insertActivityTrace(applicationOid, MODULE_TYPE, CREATED, module.id, module.name)

        String moduleOid = generateOid(MODULE_TYPE, module.id)
        insertActivityTrace(moduleOid, MODULE_TYPE, CREATED, module.id, module.name)
    }

    /**
     * Module object was deleted.
     *
     * @param module Module that was deleted
     * @return
     */
    def moduleDeleted(Module module) {
        String oid = generateOid(APPLICATION_TYPE, module.id)
        insertActivityTrace(oid, MODULE_TYPE, DELETED, module.id, module.name)
    }

    /**
     * Module object was updated.
     *
     * @param module Module that was updated
     * @return
     */
    def moduleUpdated(Module module) {
        String oid = generateOid(MODULE_TYPE, module.id)
        insertActivityTrace(oid, MODULE_TYPE, UPDATED, module.id, module.name)
    }

    /**
     * Project object was created.
     *
     * @param project Project that was created
     * @return
     */
    def projectCreated(Project project) {
        String rootOid = generateOid(ROOT_TYPE, ROOT_ID)
        insertActivityTrace(rootOid, PROJECT_TYPE, CREATED, project.id, project.name)

        String projectOid = generateOid(PROJECT_TYPE, project.id)
        insertActivityTrace(projectOid, PROJECT_TYPE, CREATED, project.id, project.name)
    }

    /**
     * Project object was deleted.
     *
     * @param project Project that was deleted
     * @return
     */
    def projectDeleted(Project project) {
        String oid = generateOid(ROOT_TYPE, ROOT_ID)
        insertActivityTrace(oid, PROJECT_TYPE, DELETED, project.id, project.name)
    }

    /**
     * Project object was updated.
     *
     * @param project Project that was updated
     * @return
     */
    def projectUpdated(Project project) {
        String oid = generateOid(PROJECT_TYPE, project.id)
        insertActivityTrace(oid, PROJECT_TYPE, UPDATED, project.id, project.name)
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
