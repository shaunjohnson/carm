import carm.Application
import carm.ApplicationType
import carm.Module
import carm.Project
import carm.System
import carm.SystemComponent
import carm.security.Role
import carm.SourceControlServer
import carm.security.User
import carm.security.UserRole
import carm.enums.SourceControlServerType
import carm.security.UserRole
import carm.security.User
import carm.SystemComponent
import carm.SystemEnvironment
import carm.ModuleType
import carm.SourceControlRepository
import carm.SourceControlUser
import carm.SourceControlRole
import carm.ApplicationRole
import carm.ApplicationRelease
import org.springframework.security.acls.domain.BasePermission
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class BootStrap {

    def aclUtilService

    def databaseAppType
    def enterpriseAppType
    def brokerAppType
    def portalAppType
    def standaloneAppType

    def barModuleType
    def configModType
    def ejbModType
    def mdbModType
    def portletModType

    def subversionServer
    def subversionRepository

    def bigSystem
    def dataLayer
    def integrationLayer
    def businessLayer
    def presentationLayer

    def sandboxEnv
    def integrationEnv
    def testEnv
    def stageEnv
    def trainEnv
    def productionEnv

    def init = { servletContext ->
        SpringSecurityUtils.reauthenticate('admin', 'admin')
        
        configureUserRoles()
        configureUsers()
        configureApplicationTypes()
        configureModuleTypes()
        configureSourceControlsServers()
        configureSystems()

        //
        // Configure Project with applications and modules
        //
        def myBigProject = new Project(name: 'Big Project', description: 'Big project').save()
        aclUtilService.addPermission(myBigProject, 'shaun', BasePermission.ADMINISTRATION)

        def dataApplication = new Application(name: 'Database Scripts', description: 'Big Project db scripts',
                type: databaseAppType, sourceControlRepository: subversionRepository, sourceControlPath: '/database',
                project: myBigProject, system: bigSystem).save()
        myBigProject.addToApplications(dataApplication)


        def earApplication = new Application(name: 'Business Services EAR', description: 'Big Project EAR application',
                type: enterpriseAppType, sourceControlRepository: subversionRepository, sourceControlPath: '/ear',
                project: myBigProject, system: bigSystem).save()
        myBigProject.addToApplications(earApplication)

        def bussEjbModule = new Module(name: 'Business Services EJB',
                description: 'Business Services EJB module',
                deployInstructions: 'Update existing application.\nRestart server.',
                type: ejbModType, application: earApplication, systemComponent: businessLayer).save()
        earApplication.addToModules(bussEjbModule)

        def transEjbModule = new Module(name: 'Transition Services EJB',
                description: 'Transition Services EJB module',
                deployInstructions: 'Update existing application.\nRestart server.',
                type: ejbModType, application: earApplication, systemComponent: businessLayer).save()
        earApplication.addToModules(transEjbModule)

        def bussMdbModule = new Module(name: 'Business Services MDB',
                description: 'Business Services MDB module',
                deployInstructions: 'Update existing application.\nRestart server.',
                type: mdbModType, application: earApplication, systemComponent: businessLayer).save()
        earApplication.addToModules(bussMdbModule)


        def brokerApplication = new Application(name: 'Business Services Broker',
                description: 'Big Project broker aplication',
                type: brokerAppType, sourceControlRepository: subversionRepository, sourceControlPath: '/broker',
                project: myBigProject, system: bigSystem).save()
        myBigProject.addToApplications(brokerApplication)

        def brokerModule = new Module(name: 'Broker BAR',
                description: 'Broker BAR module',
                deployInstructions: 'Update existing BAR file.',
                type: barModuleType, application: brokerApplication, systemComponent: integrationLayer).save()
        brokerApplication.addToModules(brokerModule)


        def portalApplication = new Application(name: 'Portal Application',
                description: 'My Big Project portal application',
                type: portalAppType, sourceControlRepository: subversionRepository, sourceControlPath: '/portal',
                project: myBigProject, system: bigSystem).save()
        myBigProject.addToApplications(portalApplication)

        def userPortletModule = new Module(name: 'User Portlet',
                description: 'Big Project User Portlet',
                deployInstructions: 'Update existing application.',
                type: portletModType, application: portalApplication, systemComponent: presentationLayer).save()
        portalApplication.addToModules(userPortletModule)

        def adminPortletModule = new Module(name: 'Admin Portlet',
                description: 'Big Project Administration Portlet',
                deployInstructions: 'Update existing application.',
                type: portletModType, application: portalApplication, systemComponent: presentationLayer).save()
        portalApplication.addToModules(adminPortletModule)

        myBigProject.save()

        //
        // Configure source control security
        //
        def developerScmRole = new SourceControlRole(name: 'developer', description: 'Developer role').save()
        def managerScmRole = new SourceControlRole(name: 'manager', description: 'Manager role').save()

        def shaunScmUser = new SourceControlUser(name: 'shaun', description: 'The user named Shaun',
                sourceControlServer: subversionServer,
                user: User.findByUsername('shaun')).save()
        new ApplicationRole(application: dataApplication, role: developerScmRole,
                sourceControlUser: shaunScmUser).save()
        new ApplicationRole(application: earApplication, role: developerScmRole,
                sourceControlUser: shaunScmUser).save()
        new ApplicationRole(application: brokerApplication, role: developerScmRole,
                sourceControlUser: shaunScmUser).save()
        new ApplicationRole(application: portalApplication, role: developerScmRole,
                sourceControlUser: shaunScmUser).save()

        def susanScmUser = new SourceControlUser(name: 'susan', description: 'The user named Susan',
                sourceControlServer: subversionServer,
                user: User.findByUsername('susan')).save()
        new ApplicationRole(application: dataApplication, role: developerScmRole,
                sourceControlUser: susanScmUser).save()
        new ApplicationRole(application: earApplication, role: developerScmRole,
                sourceControlUser: susanScmUser).save()
        new ApplicationRole(application: brokerApplication, role: developerScmRole,
                sourceControlUser: susanScmUser).save()
        new ApplicationRole(application: portalApplication, role: developerScmRole,
                sourceControlUser: susanScmUser).save()

        def scottScmUser = new SourceControlUser(name: 'scott', description: 'The user named Scott',
                sourceControlServer: subversionServer,
                user: User.findByUsername('scott')).save()
        new ApplicationRole(application: dataApplication, role: managerScmRole,
                sourceControlUser: scottScmUser).save()
        new ApplicationRole(application: earApplication, role: managerScmRole,
                sourceControlUser: scottScmUser).save()
        new ApplicationRole(application: brokerApplication, role: managerScmRole,
                sourceControlUser: scottScmUser).save()
        new ApplicationRole(application: portalApplication, role: managerScmRole,
                sourceControlUser: scottScmUser).save()
        //
        // Configure application releases
        //
        def brokerRelease = new ApplicationRelease(application: brokerApplication, releaseNumber: '1.0.5',
                description: 'Release broker application to fix message flow issue.').save()
        brokerApplication.addToReleases(brokerRelease)

        def portalRelease = new ApplicationRelease(application: portalApplication, releaseNumber: '2.1.0',
                description: 'Release portal application to update to latest jQuery version.').save()
        portalApplication.addToReleases(portalRelease)
    }

    def configureApplicationTypes() {
        databaseAppType = new ApplicationType(name: 'Database Application',
                description: 'Database application').save()
        enterpriseAppType = new ApplicationType(name: 'Enterprise Application',
                description: 'Enterprise application').save()
        brokerAppType = new ApplicationType(name: 'Broker Application',
                description: 'Broker application').save()
        portalAppType = new ApplicationType(name: 'Portal Application',
                description: 'Portal application').save()
        standaloneAppType = new ApplicationType(name: 'Standalone Application',
                description: 'Standalone application').save()
    }

    def configureModuleTypes() {
        barModuleType = new ModuleType(name: 'BAR', description: 'Broker archive').save()
        configModType = new ModuleType(name: 'Config', description: 'Configuration files').save()
        ejbModType = new ModuleType(name: 'EJB', description: 'Enterprise Java Bean').save()
        mdbModType = new ModuleType(name: 'MDB', description: 'Message Driven Bean').save()
        portletModType = new ModuleType(name: 'Portlet', description: 'Portlet').save()
    }

    def configureSourceControlsServers() {
        subversionServer = new SourceControlServer(name: 'Company SVN Server',
                type: SourceControlServerType.Subversion,
                description: 'Our company Subversion server.', url: 'http://svn.company.com').save()

        subversionRepository = new SourceControlRepository(name: 'Big System SVN Repository',
                description: 'Big System Subversion repository.', path: '/bigrepo',
                server: subversionServer).save()
    }

    def configureSystems() {
        bigSystem = new System(name: 'Big System', description: 'Big System').save()

        dataLayer = new SystemComponent(name: 'Oracle 10g Database Server',
                description: 'Oracle 10g database server that provides the data layer for Big System.',
                system: bigSystem)
        bigSystem.addToComponents(dataLayer)

        integrationLayer = new SystemComponent(name: 'WebSphere Broker 7.0',
                description: 'WebSphere Broker 7.0 is used for the Big System integration layer.',
                system: bigSystem)
        bigSystem.addToComponents(integrationLayer)

        businessLayer = new SystemComponent(name: 'WebSphere Application Server 7.0',
                description: 'The Big System business layer runs on WAS 7.0.',
                system: bigSystem)
        bigSystem.addToComponents(businessLayer)

        presentationLayer = new SystemComponent(name: 'WebSphere Portal Server 7.0',
                description: 'The Big System presentation layer is WebSphere Portal Server.',
                system: bigSystem)
        bigSystem.addToComponents(presentationLayer)

        sandboxEnv = new SystemEnvironment(name: 'Sandbox',
                description: 'Sandbox is where applications are initially tested.',
                system: bigSystem)
        bigSystem.addToEnvironments(sandboxEnv)

        integrationEnv = new SystemEnvironment(name: 'Integration',
                description: 'Integration is where applications are tested together.',
                system: bigSystem)
        bigSystem.addToEnvironments(integrationEnv)

        testEnv = new SystemEnvironment(name: 'Test',
                description: 'Application and user testing environment.',
                system: bigSystem)
        bigSystem.addToEnvironments(testEnv)

        stageEnv = new SystemEnvironment(name: 'Stage',
                description: 'Stage mimics production and is used for performance testing.',
                system: bigSystem)
        bigSystem.addToEnvironments(stageEnv)

        trainEnv = new SystemEnvironment(name: 'Train',
                description: 'Train is where users are trained to use the applications.',
                system: bigSystem)
        bigSystem.addToEnvironments(trainEnv)

        productionEnv = new SystemEnvironment(name: 'Production',
                description: 'Production is the end goal.',
                system: bigSystem)
        bigSystem.addToEnvironments(productionEnv)

        bigSystem.save()
    }

    def configureUserRoles() {
        new Role(authority: 'ROLE_ADMIN').save(flush: true)
        new Role(authority: 'ROLE_USER').save(flush: true)

        assert Role.count() == 2
    }

    def configureUsers() {
        def adminRole = Role.findByAuthority('ROLE_ADMIN')
        def userRole = Role.findByAuthority('ROLE_USER')

        //
        // Add users with ROLE_USER
        //
        def adminUser = new User(username: 'admin', enabled: true, password: 'admin')
        adminUser.save(flush: true)
        UserRole.create adminUser, adminRole, true

        def shaunUser = new User(username: 'shaun', enabled: true, password: 'shaun')
        shaunUser.save(flush: true)
        UserRole.create shaunUser, adminRole, true

        def scottUser = new User(username: 'scott', enabled: true, password: 'scott')
        scottUser.save(flush: true)
        UserRole.create scottUser, adminRole, true

        //
        // Add users with ROLE_ADMIN
        //
        def susanUser = new User(username: 'susan', enabled: true, password: 'susan')
        susanUser.save(flush: true)
        UserRole.create susanUser, userRole, true

        def userUser = new User(username: 'user', enabled: true, password: 'user')
        userUser.save(flush: true)
        UserRole.create userUser, userRole, true

        assert User.count() == 5
        assert UserRole.count() == 5
    }

    def destroy = {
    }
}

