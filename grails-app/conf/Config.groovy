// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.views.javascript.library = "jquery"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// whether to install the java.util.logging bridge for sl4j. Disable for AppEngine!
grails.logging.jul.usebridge = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

grails.gorm.default.mapping = {
    "user-type" type: org.jadira.usertype.dateandtime.joda.PersistentDateTime, class: org.joda.time.DateTime
    "user-type" type: org.jadira.usertype.dateandtime.joda.PersistentLocalDate, class: org.joda.time.LocalDate
}

jodatime {
    periodpicker.default.fields = "years,months,days,hours,minutes"
    format.org.joda.time.DateTime = "MM/dd/yyyy h:mm a"
    format.org.joda.time.LocalDate = "MM/dd/yyyy"
    format.org.joda.time.LocalTime = "h:mm a"
}

// set per-environment serverURL stem for creating absolute links
environments {
    production {
        // grails.serverURL = "http://www.changeme.com"
        grails.logging.jul.usebridge = false
    }
    development {
        // grails.serverURL = "http://localhost:8080/${appName}"
        grails.logging.jul.usebridge = true
    }
    test {
        // grails.serverURL = "http://localhost:8080/${appName}"
        grails.logging.jul.usebridge = true
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    
    appenders {
        console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    }

    debug  'grails.app'

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

    warn   'org.mortbay.log'
}

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'carm.security.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'carm.security.UserRole'
grails.plugins.springsecurity.authority.className = 'carm.security.Role'
//grails.plugins.springsecurity.rejectIfNoRule = true

grails.plugins.springsecurity.controllerAnnotations.staticRules = [
    '/':                              ['permitAll'],
    '/index.gsp':                     ['permitAll'],
    '/js/**':                         ['permitAll'],
    '/css/**':                        ['permitAll'],
    '/images/**':                     ['permitAll'],
    '/login/**':                      ['permitAll'],
    '/logout/**':                     ['permitAll'],
    '/plugins/**':                    ['permitAll'],
    '/j_spring_security_switch_user': ['ROLE_SUPERVISOR'],

    '/**':                            ['permitAll'],

    '/home/**':                       ['permitAll'],

    '/applicationRelease/**':         ['permitAll'],
    '/applicationRelease/index':      ['permitAll'],
    '/applicationRelease/list':       ['permitAll'],
    '/applicationRelease/show':       ['permitAll'],

    '/user/**':                       ['permitAll'],
    '/role/**':                       ['permitAll'],
    '/application/**':                ['permitAll'],
    '/applicationType/**':            ['permitAll'],
    '/module/**':                     ['permitAll'],
    '/moduleType/**':                 ['permitAll'],
    '/project/**':                    ['permitAll'],
    '/sourceControlRepository/**':    ['permitAll'],
    '/sourceControlRole/**':          ['permitAll'],
    '/sourceControlServer/**':        ['permitAll'],
    '/sourceControlUser/**':          ['permitAll'],
    '/system/**':                     ['permitAll'],
    '/systemServer/**':            ['permitAll'],
    '/systemDeploymentEnvironment/**':          ['permitAll']
]

// Grant ROLE_ADMIN the ROLE_USER role too
grails.plugins.springsecurity.roleHierarchy = '''
   ROLE_ADMIN > ROLE_USER
'''

grails.plugins.springsecurity.secureChannel.definition = [
//   '/login/**':         'REQUIRES_SECURE_CHANNEL'
]

// CARM UI configurable options
ui {
    activityTrace {
        maxRecords = 10
        listMax = 25
    }
    application {
        listMax = 25
    }
    applicationDeployment {
        listMax = 25
    }
    applicationRelease {
        listMax = 25
        maxRecords = 5
    }
    applicationReleaseHistory {
        listMax = 25
    }
    applicationReleaseTestState {
        listMax = 25
    }
    applicationType {
        listMax = 25
    }
    module {
        listMax = 25
    }
    moduleDeployment {
        listMax = 25
    }
    moduleDeploymentTestState {
        listMax = 25
    }
    moduleType {
        listMax = 25
    }
    project {
        listMax = 25
    }
    projectCategory {
        listMax = 25
    }
    richTextEditor {
        type = "medium" // One of 'simple', 'medium', 'advanced', 'full'
        height = 400    // Height of editor in pixels
        width = 600     // Width of editor in pixels
    }
    sourceControlRepository {
        listMax = 25
    }
    sourceControlRole {
        listMax = 25
    }
    sourceControlServer {
        listMax = 25
    }
    sourceControluser {
        listMax = 25
    }
    systemDeploymentEnvironment {
        listMax = 25
    }
    systemEnvironment {
        listMax = 25
    }
    systemServer {
        listMax = 25
    }
    textarea{
        cols = 60
        rows = 8
    }
    user {
        listMax = 25
    }
}