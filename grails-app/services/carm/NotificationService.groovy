package carm

import java.text.MessageFormat

class NotificationService {

    static transactional = false

    def grailsApplication
    def mailService

    def sendEmail(String fromEmailAddress, String toEmailAddress, String subject, String message) {
        def type = grailsApplication.config.carm.mail.type
        def emailSubject = "[CARM] ${subject}"

        if (type == 'shell') {
            sendEmailUsingShell(fromEmailAddress, toEmailAddress, emailSubject, message)
        }
        else if (type == 'mail') {
            sendEmailUsingMail(fromEmailAddress, toEmailAddress, emailSubject, message)
        }
        else {
            // Do nothing
        }
    }

    private sendEmailUsingMail(String fromEmailAddress, String toEmailAddress, String subject, String message) {
        mailService.sendMail {
            to toEmailAddress
            from fromEmailAddress
            subject subject
            body message
        }
    }

    private sendEmailUsingShell(String fromEmailAddress, String toEmailAddress, String subject, String message) {
        def shell = grailsApplication.config.carm.mail.shell.command
        def host = grailsApplication.config.grails.mail.host
        def port = grailsApplication.config.grails.mail.port

        def command = MessageFormat.format(shell, [host, port, fromEmailAddress, toEmailAddress, subject, message].toArray())

        command.execute()
    }
}
