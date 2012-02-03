package carm

import org.springframework.context.ApplicationContextAware
import org.springframework.context.ApplicationContext
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import carm.enums.ActivityAction

class ActivityTraceService implements ApplicationContextAware {

    static transactional = false

    def springSecurityService

    MessageSource messageSource

    private static final String APPLICAION_TYPE = Application.class.name
    private static final String PROJECT_TYPE = Project.class.name

    /**
     * Lists latest activity events for an application in reverse chronological order.
     *
     * @param application Application used for query
     * @return List of ActivityTrace objects
     */
    List<ActivityTrace> listApplicationActivity(Application application) {
        def oid = generateOid(APPLICAION_TYPE, application.id)

        ActivityTrace.findAllByOid(oid, [max: 10, sort: "dateOccurred", order: "desc"])
    }

    /**
     * Lists latest activity events for a project in reverse chronological order.
     *
     * @param project Project used for query
     * @return List of ActivityTrace objects
     */
    List<ActivityTrace> listProjectActivity(Project project) {
        def oid = generateOid(PROJECT_TYPE, project.id)

        ActivityTrace.findAllByOid(oid, [max: 10, sort: "dateOccurred", order: "desc"])
    }

    /**
     * Application object was created.
     *
     * @param application Application that was created
     * @return
     */
    def applicationCreated(Application application) {
        def projectOid = generateOid(PROJECT_TYPE, application.project.id)
        insertActivityTrace(projectOid, ActivityAction.CREATED, application.id, application.name, APPLICAION_TYPE)

        def applicationOid = generateOid(APPLICAION_TYPE, application.id)
        insertActivityTrace(applicationOid, ActivityAction.CREATED, application.id, application.name, APPLICAION_TYPE)
    }

    /**
     * Application object was deleted.
     *
     * @param application Application that was deleted
     * @return
     */
    def applicationDeleted(Application application) {
        def oid = generateOid(PROJECT_TYPE, application.id)

        insertActivityTrace(oid, ActivityAction.DELETED, application.id, application.name, APPLICAION_TYPE)
    }

    /**
     * Application object was updated.
     *
     * @param application Application that was updated
     * @return
     */
    def applicationUpdated(Application application) {
        def oid = generateOid(APPLICAION_TYPE, application.id)

        insertActivityTrace(oid, ActivityAction.UPDATED, application.id, application.name, APPLICAION_TYPE)
    }

    /**
     * Inserts a new ActivityTrace record
     *
     * @param oid OID value
     * @param summary Summary of the event
     * @return
     */
    private insertActivityTrace(String oid, ActivityAction action, Long objectId, String objectName, String objectType) {
        ActivityTrace.withNewSession {
            def trace = new ActivityTrace(oid: oid, action:  action, objectId: objectId, objectName: objectName,
                    objectType: objectType, dateOccurred: new Date(), username: getUsername())
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
     * Gets the current username (principle).
     *
     * @return Current username
     */
    private String getUsername() {
        springSecurityService.getPrincipal()
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
