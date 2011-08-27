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

class BootStrap {
    def init = { servletContext ->
        // 
        // Configure Security
        //
        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

        def adminUser = new User(username: 'admin', enabled: true, password: 'admin')
        adminUser.save(flush: true)
        UserRole.create adminUser, adminRole, true

        def userUser = new User(username: 'user', enabled: true, password: 'user')
        userUser.save(flush: true)
        UserRole.create userUser, userRole, true

        assert User.count() == 2
        assert Role.count() == 2
        assert UserRole.count() == 2

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
        def subversion = new SourceControlServer(name: "Subversion", type: SourceControlServerType.Subversion,
                description: "Subversion server", url: "http://mybig.com/svn/").save()

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
                type: dbScriptsAppType, sourceControlServer: subversion, sourceControlPath: '/database',
                project: myBigProject, system: system).save()
        myBigProject.addToApplications(dataApplication)


        def earApplication = new Application(name: "My Big EAR", description: "My Big Project EAR",
                type: earAppType, sourceControlServer: subversion, sourceControlPath: '/ear',
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
                type: brokerAppType, sourceControlServer: subversion, sourceControlPath: '/broker',
                project: myBigProject, system: system).save()
        myBigProject.addToApplications(brokerApplication)


        def portalApplication = new Application(name: "My Portal App", description: "My Big Project portal application",
                type: portalAppType, sourceControlServer: subversion, sourceControlPath: '/portal',
                project: myBigProject, system: system).save()
        myBigProject.addToApplications(portalApplication)

        def portletModule = new Module(name: 'My Portlet', description: 'My Big Portlet application',
                type: portletModType, application: portalApplication, systemComponent: presentationLayer).save()
        portalApplication.addToModules(portletModule)

        myBigProject.save()
    }

    def destroy = {
    }
}

