grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.war.file = "target/${appName}.war"
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        inherits true // Whether to inherit repository definitions from plugins
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()

        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        //mavenLocal()
        //mavenCentral()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        // runtime 'mysql:mysql-connector-java:5.1.13'

        compile "org.jadira.usertype:usertype.jodatime:1.9"
        compile "org.grails.plugins:webxml:1.4.1"
        compile "org.semver:api:0.9.12"
        runtime "org.springframework.security:spring-security-config:3.0.7.RELEASE"

        compile "org.grails.plugins:ckeditor:3.6.3.0"
        compile "org.grails.plugins:database-migration:1.3.2"
        compile "org.grails.plugins:famfamfam:1.0.1"
        compile "org.grails.plugins:google-chart:0.5.2"
        compile "org.grails.plugins:joda-time:1.4"
        compile "org.grails.plugins:jquery:1.8.3"
        compile "org.grails.plugins:jquery-ui:1.8.24"
        compile "org.grails.plugins:mail:1.0.1"
        compile "org.grails.plugins:resources:1.2.RC2"
        compile "org.grails.plugins:twitter-bootstrap:2.3.0"
        compile "org.grails.plugins:webxml:1.4.1"

        test "org.grails.plugins:build-test-data:2.0.4"
        test "org.grails.plugins:code-coverage:1.2.6"
        test "org.grails.plugins:codenarc:0.18.1"
        test "org.grails.plugins:fixtures:1.2"
    }
}
