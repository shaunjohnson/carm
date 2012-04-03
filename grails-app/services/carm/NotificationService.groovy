package carm

import java.text.MessageFormat

class NotificationService {

    static transactional = false

    def grailsApplication
    def mailService

    def sendEmail(String toEmailAddress, String subject, String message) {
        def type = grailsApplication.config.carm.mail.type

        println type
        
        if (type == 'shell') {
            sendEmailUsingShell(toEmailAddress, subject, message)
        }
        else if (type == 'mail') {
            sendEmailUsingMail(toEmailAddress, subject, message)
        }
        else {
            // Do nothing
        }
    }

    private sendEmailUsingMail(String toEmailAddress, String subject, String message) {
        mailService.sendMail {
            to toEmailAddress
            from grailsApplication.config.grails.mail.default.from
            subject subject
            body message
        }
    }

    private sendEmailUsingShell(String toEmailAddress, String subject, String message) {
        def shell = grailsApplication.config.carm.mail.shell.command
        def host = grailsApplication.config.grails.mail.host
        def port = grailsApplication.config.grails.mail.port
        def from = grailsApplication.config.grails.mail.default.from

        def command = MessageFormat.format(shell, [host, port, from, toEmailAddress, "[CARM] ${subject}", message].toArray())
        
        println command
        
        command.execute()
    }
}
