package carm.notification

import static carm.notification.NotificationEvent.*
import static carm.notification.NotificationRecipientType.*

import org.springframework.context.i18n.LocaleContextHolder
import java.text.MessageFormat
import org.springframework.context.MessageSource
import org.springframework.context.ApplicationContextAware
import org.springframework.context.ApplicationContext
import grails.util.Environment
import carm.deployment.ApplicationDeployment

import carm.release.ApplicationRelease

import grails.gsp.PageRenderer
import org.springframework.beans.factory.InitializingBean

class NotificationService implements ApplicationContextAware, InitializingBean {

    static transactional = false

    ApplicationContext applicationContext
    def carmSecurityService
    def grailsApplication
    PageRenderer groovyPageRenderer
    def linkGeneratorService
    def mailService
    private MessageSource messageSource
    private projectService
    def userService
    def watchService

    void afterPropertiesSet() {
        messageSource = applicationContext.getBean("messageSource")
        projectService = applicationContext.getBean("projectService")
    }

    def applicationDeploymentSubmitted(ApplicationDeployment applicationDeployment) {
        sendNotification(applicationDeployment, APPLICATION_DEPLOYMENT_SUBMITTED)
    }

    def applicationDeploymentAssigned(ApplicationDeployment applicationDeployment) {
        sendNotification(applicationDeployment, APPLICATION_DEPLOYMENT_ASSIGNED)
    }

    def applicationDeploymentRejected(ApplicationDeployment applicationDeployment) {
        sendNotification(applicationDeployment, APPLICATION_DEPLOYMENT_REJECTED)
    }

    def applicationDeploymentDeployed(ApplicationDeployment applicationDeployment) {
        sendNotification(applicationDeployment, APPLICATION_DEPLOYMENT_DEPLOYED)
    }

    def applicationDeploymentCompleted(ApplicationDeployment applicationDeployment) {
        sendNotification(applicationDeployment, APPLICATION_DEPLOYMENT_COMPLETED)
    }

    def applicationReleaseSubmitted(ApplicationRelease applicationRelease) {
        sendNotification(applicationRelease, APPLICATION_RELEASE_SUBMITTED)
    }

    def applicationReleaseAssigned(ApplicationRelease applicationRelease) {
        sendNotification(applicationRelease, APPLICATION_RELEASE_ASSIGNED)
    }

    def applicationReleaseRejected(ApplicationRelease applicationRelease) {
        sendNotification(applicationRelease, APPLICATION_RELEASE_REJECTED)
    }

    def applicationReleaseReleased(ApplicationRelease applicationRelease) {
        sendNotification(applicationRelease, APPLICATION_RELEASE_RELEASED)
    }

    def applicationReleaseCompleted(ApplicationRelease applicationRelease) {
        sendNotification(applicationRelease, APPLICATION_RELEASE_COMPLETED)
    }

    private List<String> getProjectAdministratorEmailAddresses(domain) {
        List<String> usernames = []

        if (domain instanceof ApplicationDeployment) {
            usernames = projectService.findAllProjectAdministratorUsers(((ApplicationDeployment) domain).applicationRelease.application.project)
        }
        else if (domain instanceof ApplicationRelease) {
            usernames = projectService.findAllProjectAdministratorUsers(((ApplicationRelease) domain).application.project)
        }

        userService.collectAllEmailByUsernameInList(usernames)
    }

    private List<String> getApplicationWatcherEmailAddresses(domain) {
        if (domain instanceof ApplicationDeployment) {
            watchService.findAllEmailByApplication(((ApplicationDeployment) domain).applicationRelease.application)
        }
        else if (domain instanceof ApplicationRelease) {
            watchService.findAllEmailByApplication(((ApplicationRelease) domain).application)
        }
        else {
            []
        }
    }

    private String getMessageBody(Object domain, NotificationEvent notificationEvent) {
        def user = userService.currentUser
        def model = [:]

        model['currentUserFullName'] = user.fullName
        model['currentUserLink'] = linkGeneratorService.createLink(controller: 'user', action: 'show', id: user.id)

        if (domain instanceof ApplicationDeployment) {
            ApplicationDeployment applicationDeployment = (ApplicationDeployment) domain

            model['applicationName'] = applicationDeployment.applicationRelease.application.name
            model['applicationLink'] = linkGeneratorService.createLink(controller: 'application', action: 'show',
                    id: applicationDeployment.applicationRelease.application.id)

            model['applicationReleaseNumber'] = applicationDeployment.applicationRelease.releaseNumber
            model['applicationReleaseLink'] = linkGeneratorService.createLink(controller: 'applicationRelease',
                    action: 'show', id: applicationDeployment.applicationRelease.id)
            model['applicationReleaseChangeLog'] = applicationDeployment.applicationRelease.changeLog

            model['systemDeploymentEnvironmentName'] = applicationDeployment.deploymentEnvironment.name
            model['systemDeploymentEnvironmentLink'] = linkGeneratorService.createLink(controller: 'systemDeploymentEnvironment',
                    action: 'show', id: applicationDeployment.deploymentEnvironment.id)

            model['applicationDeploymentLink'] = linkGeneratorService.createLink(controller: 'applicationDeployment',
                    action: 'show', id: applicationDeployment.id)
        }
        else if (domain instanceof ApplicationRelease) {
            ApplicationRelease applicationRelease = (ApplicationRelease) domain

            model['applicationName'] = applicationRelease.application.name
            model['applicationLink'] = linkGeneratorService.createLink(controller: 'application', action: 'show',
                    id: applicationRelease.application.id)

            model['applicationReleaseNumber'] = applicationRelease.releaseNumber
            model['applicationReleaseLink'] = linkGeneratorService.createLink(controller: 'applicationRelease',
                    action: 'show', id: applicationRelease.id)
            model['applicationReleaseChangeLog'] = applicationRelease.changeLog
        }

        groovyPageRenderer.render(view: '/email/' + notificationEvent.name(), model: model)
    }

    private List<String> getProjectWatcherEmailAddresses(domain) {
        if (domain instanceof ApplicationDeployment) {
            watchService.findAllEmailByProject(((ApplicationDeployment) domain).applicationRelease.application.project)
        }
        else if (domain instanceof ApplicationRelease) {
            watchService.findAllEmailByProject(((ApplicationRelease) domain).application.project)
        }
        else {
            []
        }
    }

    private List<Notification> getNotifications(Object domain, NotificationEvent notificationEvent) {
        Notification.findAllByNotificationEvent(notificationEvent)

        NotificationScheme notificationScheme =  null

        if (domain instanceof ApplicationDeployment) {
            notificationScheme = ((ApplicationDeployment) domain).applicationRelease.application.notificationScheme
        }
        else if (domain instanceof ApplicationRelease) {
            notificationScheme = ((ApplicationRelease) domain).application.notificationScheme
        }

        Notification.findAllByNotificationSchemeAndNotificationEvent(notificationScheme, notificationEvent)
    }

    private List<String> getRecipients(Object domain, NotificationEvent notificationEvent) {
        List<String> recipients = []

        boolean removeCurrentUser = true

        getNotifications(domain, notificationEvent).each { Notification notification ->
            switch (notification.recipientType) {
                case CURRENT_USER:
                    removeCurrentUser = false
                    recipients << userService.currentUser.email
                    break;

                case PROJECT_ADMINISTRATORS:
                    recipients << getProjectAdministratorEmailAddresses(domain)
                    break;

                case GROUP:
                    // TODO To be implemented with user groups are implemented
                    break;

                case USER:
                    recipients << notification.user.email
                    break;

                case EMAIL_ADDRESS:
                    recipients << notification.emailAddress
                    break;

                case APPLICATION_WATCHERS:
                    recipients << getApplicationWatcherEmailAddresses(domain)
                    break;

                case PROJECT_WATCHERS:
                    recipients << getProjectWatcherEmailAddresses(domain)
                    break;
            }
        }

        recipients = recipients.flatten().unique()

        if (removeCurrentUser) {
            recipients.remove(userService.currentUser.email)
        }

        recipients
    }

    private String getSubjectText(Object domain, NotificationEvent notificationEvent) {
        def args = []

        if (domain instanceof ApplicationDeployment) {
            ApplicationDeployment applicationDeployment = (ApplicationDeployment) domain

            args << applicationDeployment.applicationRelease.application.name
            args << applicationDeployment.applicationRelease.releaseNumber
            args << applicationDeployment.deploymentEnvironment.name
        }
        else if (domain instanceof ApplicationRelease) {
            ApplicationRelease applicationRelease = (ApplicationRelease) domain

            args << applicationRelease.application.name
            args << applicationRelease.releaseNumber
        }

        messageSource.getMessage("notification.${notificationEvent}.subject", args.toArray(), LocaleContextHolder.locale)
    }

    private sendEmail(String fromEmailAddress, List<String> toEmailAddresses, String subjectText, String messageBody) {
        def currentEnv = (Environment.current == Environment.PRODUCTION ? "" : " - ${Environment.current}")
        String subject = "[CARM${currentEnv}] " + subjectText

        def type = grailsApplication.config.carm.mail.type
        if (type == 'shell') {
            sendEmailUsingShell(fromEmailAddress, toEmailAddresses, subject, messageBody)
        }
        else if (type == 'mail') {
            sendEmailUsingMail(fromEmailAddress, toEmailAddresses, subject, messageBody)
        }
        else {
            log.warn "Mail type configured to be $type, which is not supported. Mail will not be sent."
        }
    }

    private sendEmailUsingMail(String fromEmailAddress, List<String> toEmailAddresses, String subjectText, String message) {
        toEmailAddresses.each { toEmailAddress ->
            try {
                mailService.sendMail {
                    to toEmailAddress
                    from fromEmailAddress
                    subject subjectText
                    html message
                }
            }
            catch (Exception e) {
                log.error "Error sending mail to ${toEmailAddresses}", e
            }
        }
    }

    private sendEmailUsingShell(String fromEmailAddress, List<String> toEmailAddresses, String subject, String message) {
        def shell = grailsApplication.config.carm.mail.shell.command
        def host = grailsApplication.config.grails.mail.host
        def port = grailsApplication.config.grails.mail.port

        toEmailAddresses.each { toEmailAddress ->
            def command = MessageFormat.format(shell, [host, port, fromEmailAddress, toEmailAddress, subject, message].toArray())
            command.execute()
        }
    }

    private sendNotification(Object domain, NotificationEvent notificationEvent) {
        try {
            sendEmail(userService.currentUser.email,
                    getRecipients(domain, notificationEvent),
                    getSubjectText(domain, notificationEvent),
                    getMessageBody(domain, notificationEvent))
        }
        catch (Exception e) {
            log.error "Error occurred sending notification for ${notificationEvent}", e
        }
    }

    /**
     * Find all notifications for a specific scheme, grouped by event type.
     *
     * @param notificationSchemeInstance Scheme to filter notifications
     * @return Map of notifications grouped/keyed by notification event type
     */
    def findAllNotificationsBySchemeGroupedByEvent(notificationSchemeInstance) {
        Notification.findAllByNotificationScheme(notificationSchemeInstance).groupBy { it.notificationEvent }.sort { e1, e2 -> e1.key <=> e2.key }
    }
}
