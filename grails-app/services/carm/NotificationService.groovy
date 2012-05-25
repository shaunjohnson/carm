package carm

import static carm.notification.NotificationEvent.*
import static carm.notification.NotificationRecipientType.*

import org.springframework.context.i18n.LocaleContextHolder
import java.text.MessageFormat
import org.springframework.context.MessageSource
import org.springframework.context.ApplicationContextAware
import org.springframework.context.ApplicationContext
import grails.util.Environment
import carm.deployment.ApplicationDeployment
import carm.notification.NotificationEvent
import carm.release.ApplicationRelease

import carm.notification.Notification
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

    def applicationDeploymentArchived(ApplicationDeployment applicationDeployment) {
        sendNotification(applicationDeployment, APPLICATION_DEPLOYMENT_ARCHIVED)
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

    def applicationReleaseArchived(ApplicationRelease applicationRelease) {
        sendNotification(applicationRelease, APPLICATION_RELEASE_ARCHIVED)
    }

    private List<String> getProjectAdministratorEmailAddresses(domain) {
        List<String> usernames = []

        if (domain instanceof ApplicationDeployment) {
            usernames = projectService.findAllProjectOwners(((ApplicationDeployment) domain).applicationRelease.application.project)
        }
        else if (domain instanceof ApplicationRelease) {
            usernames = projectService.findAllProjectOwners(((ApplicationRelease) domain).application.project)
        }

        carmSecurityService.findAllEmailByUsernameInList(usernames)
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
        def user = carmSecurityService.currentUser
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

    private List<String> getRecipients(Object domain, NotificationEvent notificationEvent) {
        List<String> recipients = []

        Notification.findAllByNotificationEvent(notificationEvent).each { Notification notification ->
            switch (notification.recipientType) {
                case CURRENT_USER:
                    recipients << carmSecurityService.currentUser.email
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

        recipients.flatten().unique()
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
            // Do nothing
        }
    }

    private sendEmailUsingMail(String fromEmailAddress, List<String> toEmailAddresses, String subjectText, String message) {
        toEmailAddresses.each { toEmailAddress ->
            mailService.sendMail {
                to toEmailAddress
                from fromEmailAddress
                subject subjectText
                html message
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
            sendEmail(carmSecurityService.currentUser.email,
                    getRecipients(domain, notificationEvent),
                    getSubjectText(domain, notificationEvent),
                    getMessageBody(domain, notificationEvent))
        }
        catch (Exception e) {
            log.error "Error occurred sending notification for ${notificationEvent}", e
        }
    }
}
