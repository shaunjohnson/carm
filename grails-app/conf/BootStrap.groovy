import net.lmxm.carm.Application
import net.lmxm.carm.ApplicationType
import net.lmxm.carm.Module
import net.lmxm.carm.Project
import net.lmxm.carm.System
import net.lmxm.carm.SystemComponent
import net.lmxm.carm.security.Role
import net.lmxm.carm.SourceControlServer
import net.lmxm.carm.security.User
import net.lmxm.carm.security.UserRole
import net.lmxm.carm.enums.SourceControlServerType
import net.lmxm.carm.security.UserRole
import net.lmxm.carm.security.User
import net.lmxm.carm.SystemComponent
import net.lmxm.carm.SystemEnvironment
import net.lmxm.carm.ModuleType
import net.lmxm.carm.SourceControlRepository
import net.lmxm.carm.SourceControlUser
import net.lmxm.carm.SourceControlRole
import net.lmxm.carm.ApplicationRole
import net.lmxm.carm.ApplicationRelease

class BootStrap {
    def init = { servletContext ->
        //
        // Configure static values
        //
        configureUserRoles()

        //
        // Configure test values
        //
        configureUsers()

        //
        // Configure Systems
        //
        def system = new System(name: 'My Big System', description: 'My Big System').save()
        def dataLayer = new SystemComponent(name: 'Data Layer', description: 'Big system data layer',
                system: system).save()
        system.addToComponents(dataLayer);

        def integrationLayer = new SystemComponent(name: 'Integraion Layer', description: 'Big system integration layer',
                system: system).save()
        system.addToComponents(integrationLayer)

        def businessLayer = new SystemComponent(name: 'Business Layer', description: 'Big system business layer',
                system: system).save()
        system.addToComponents(businessLayer)

        def presentationLayer = new SystemComponent(name: 'Presentation Layer', description: 'Big system presentation layer',
                system: system).save()
        system.addToComponents(presentationLayer)

        def sandboxEnv = new SystemEnvironment(name: "Sandbox", system: system).save()
        system.addToEnvironments(sandboxEnv)

        def integrationEnv = new SystemEnvironment(name: "Integration", system: system).save()
        system.addToEnvironments(integrationEnv)

        def testEnv = new SystemEnvironment(name: "Test", system: system).save()
        system.addToEnvironments(testEnv)

        def stageEnv = new SystemEnvironment(name: "Stage", system: system).save()
        system.addToEnvironments(stageEnv)

        def trainEnv = new SystemEnvironment(name: "Train", system: system).save()
        system.addToEnvironments(trainEnv)

        def productionEnv = new SystemEnvironment(name: "Production", system: system).save()
        system.addToEnvironments(productionEnv)

        system.save()

        //
        // Configure Source Control Servers
        //
        def subversionServer = new SourceControlServer(name: "My Big SVN Server",
                type: SourceControlServerType.Subversion,
                description: "Subversion server", url: "http://svn.mybig.com").save()

        def subversionRepository = new SourceControlRepository(name: "My Big SVN Repository",
                description: "Subversion repository", path: "/repository",
                server: subversionServer).save()

        //
        // Configure Application Types
        //
        def dbScriptsAppType = new ApplicationType(name: "DB Scripts", description: "Database scripts").save()
        def earAppType = new ApplicationType(name: "EAR App", description: "Enterprise Application").save()
        def brokerAppType = new ApplicationType(name: "Broker App", description: "Broker Application").save()
        def portalAppType = new ApplicationType(name: "Portal App", description: "Portal Application").save()

        //
        // Configure Module Types
        //
        def ejbModType = new ModuleType(name: 'EJB', description: 'Enterprise Java Bean').save()
        def mdbModType = new ModuleType(name: 'MDB', description: 'Message Driven Bean').save()
        def portletModType = new ModuleType(name: 'Portlet', description: 'Portlet').save()

        //
        // Configure Project with applications and modules
        //
        def myBigProject = new Project(name: 'My Big Project', description: 'My big project').save()

        def dataApplication = new Application(name: "Database Scripts", description: "My Big Project db scripts",
                type: dbScriptsAppType, sourceControlRepository: subversionRepository, sourceControlPath: '/database',
                project: myBigProject, system: system).save()
        myBigProject.addToApplications(dataApplication)


        def earApplication = new Application(name: "My Big EAR", description: "My Big Project EAR",
                type: earAppType, sourceControlRepository: subversionRepository, sourceControlPath: '/ear',
                project: myBigProject, system: system).save()
        myBigProject.addToApplications(earApplication)

        def bussEjbModule = new Module(name: 'Business Services EJB', description: 'Business Services EJB',
                type: ejbModType, application: earApplication, systemComponent: businessLayer).save()
        earApplication.addToModules(bussEjbModule)

        def transEjbModule = new Module(name: 'Transition EJB', description: 'Transition Services EJB',
                type: ejbModType, application: earApplication, systemComponent: businessLayer).save()
        earApplication.addToModules(transEjbModule)

        def bussMdbModule = new Module(name: 'Business Services MDB', description: 'Business Services MDB',
                type: mdbModType, application: earApplication, systemComponent: businessLayer).save()
        earApplication.addToModules(bussMdbModule)


        def brokerApplication = new Application(name: "My Big Broker", description: "My Big Project broker",
                type: brokerAppType, sourceControlRepository: subversionRepository, sourceControlPath: '/broker',
                project: myBigProject, system: system).save()
        myBigProject.addToApplications(brokerApplication)


        def portalApplication = new Application(name: "My Portal App", description: "My Big Project portal application",
                type: portalAppType, sourceControlRepository: subversionRepository, sourceControlPath: '/portal',
                project: myBigProject, system: system).save()
        myBigProject.addToApplications(portalApplication)

        def portletModule = new Module(name: 'My Portlet', description: 'My Big Portlet application',
                type: portletModType, application: portalApplication, systemComponent: presentationLayer).save()
        portalApplication.addToModules(portletModule)

        myBigProject.save()

        //
        // Configure source control security
        //
        def shaunScmUser = new SourceControlUser(name: 'shaun', description: 'Shaun user',
                sourceControlServer: subversionServer,
                user: User.findByUsername('shaun')).save()

        def developerScmRole = new SourceControlRole(name: 'developer', description: 'Developer role').save()

        new ApplicationRole(application: dataApplication, role: developerScmRole,
                sourceControlUser: shaunScmUser).save()
        new ApplicationRole(application: earApplication, role: developerScmRole,
                sourceControlUser: shaunScmUser).save()
        new ApplicationRole(application: brokerApplication, role: developerScmRole,
                sourceControlUser: shaunScmUser).save()
        new ApplicationRole(application: portalApplication, role: developerScmRole,
                sourceControlUser: shaunScmUser).save()

        //
        // Configure application releases
        //
        def brokerRelease = new ApplicationRelease(application: brokerApplication, releaseNumber: "1.0.5",
                description: "Release broker application").save()
        brokerApplication.addToReleases(brokerRelease)

        def portalRelease = new ApplicationRelease(application: portalApplication, releaseNumber: "2.1.0",
                description: "Release portal application").save()
        portalApplication.addToReleases(portalRelease)
    }

    def configureUserRoles = {
        new Role(authority: 'ROLE_ADMIN').save(flush: true)
        new Role(authority: 'ROLE_USER').save(flush: true)

        assert Role.count() == 2
    }

    def configureUsers = {
        def adminRole = Role.findByAuthority('ROLE_ADMIN')
        def userRole = Role.findByAuthority('ROLE_USER')

        def adminUser = new User(username: 'admin', enabled: true, password: 'admin')
        adminUser.save(flush: true)
        UserRole.create adminUser, adminRole, true
        UserRole.create adminUser, userRole, true

        def userUser = new User(username: 'user', enabled: true, password: 'user')
        userUser.save(flush: true)
        UserRole.create userUser, userRole, true

        def shaunUser = new User(username: 'shaun', enabled: true, password: 'shaun')
        shaunUser.save(flush: true)
        UserRole.create shaunUser, adminRole, true


        assert User.count() == 3
        assert UserRole.count() == 4
    }

    def destroy = {
    }
}

