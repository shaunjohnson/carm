package carm

import org.springframework.context.i18n.LocaleContextHolder
import java.text.MessageFormat
import org.springframework.context.MessageSource
import org.springframework.context.ApplicationContextAware
import org.springframework.context.ApplicationContext

class NotificationService implements ApplicationContextAware {

    static transactional = false

    ApplicationContext applicationContext
    def grailsApplication
    def mailService

    def sendEmail(String fromEmailAddress, List<String> toEmailAddresses, String subjectCode, String messageCode, List<Object> args) {
        MessageSource messageSource = applicationContext.getBean("messageSource")
        String subject = "[CARM] " + messageSource.getMessage(subjectCode, args.toArray(), LocaleContextHolder.locale)
        String message = messageSource.getMessage(messageCode, args.toArray(), LocaleContextHolder.locale)

        def type = grailsApplication.config.carm.mail.type
        if (type == 'shell') {
            sendEmailUsingShell(fromEmailAddress, toEmailAddresses, subject, message)
        }
        else if (type == 'mail') {
            sendEmailUsingMail(fromEmailAddress, toEmailAddresses, subject, message)
        }
        else {
            // Do nothing
        }
    }

    private sendEmailUsingMail(String fromEmailAddress, List<String> toEmailAddresses, String subject, String message) {
        mailService.sendMail {
            to toEmailAddresses.join(", ")
            from fromEmailAddress
            subject subject
            body message
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
}
