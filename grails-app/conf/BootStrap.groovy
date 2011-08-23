import net.lmxm.carm.domains.Module
import net.lmxm.carm.domains.ModuleType
import net.lmxm.carm.domains.Project
import net.lmxm.carm.domains.Role
import net.lmxm.carm.domains.SourceControlServer
import net.lmxm.carm.domains.User
import net.lmxm.carm.domains.UserRole
import net.lmxm.carm.enums.SourceControlServerType

class BootStrap {
    def init = { servletContext ->
        // 
        // Configure Security
        //
        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

        def testUser = new User(username: 'me', enabled: true, password: 'password')
        testUser.save(flush: true)

        UserRole.create testUser, adminRole, true

        assert User.count() == 1
        assert Role.count() == 2
        assert UserRole.count() == 1
        
        //
        // Configure Domains
        //
        def subversion = new SourceControlServer(name: "Subversion", type: SourceControlServerType.Subversion, 
            description: "Subversion server", url: "http://suafe.googlecode.com/svn/").save()
        
        def coldFusionType = new ModuleType(name: "ColdFusion", description: "ColdFushion Web Application").save()
        def ejbType = new ModuleType(name: "EJB", description: "Enterprise Java Bean Application").save()
        def jsfPortletType = new ModuleType(name: "JSF Portlet", description: "JSF Portlet Application").save()
        def jsfWebType = new ModuleType(name: "JSF Web App", description: "JSF Web Application").save()
        def mdbType = new ModuleType(name: "MDB", description: "Message Driven Bean Application").save()
        def standaloneType = new ModuleType(name: "Standalone JAR", description: "Standalone Java Application").save()
        
        def suafeProject = new Project(name: "Suafe", description: "Subversion User Authorization File Editor")       
        suafeProject.addToModules(new Module(name: "Core", description: "Suafe Core", type: standaloneType))
        suafeProject.save()
    }

    def destroy = {
    }
}

