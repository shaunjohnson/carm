import net.lmxm.carm.Module
import net.lmxm.carm.ModuleType
import net.lmxm.carm.Project
import net.lmxm.carm.Role
import net.lmxm.carm.SourceControlServer
import net.lmxm.carm.User
import net.lmxm.carm.UserRole
import net.lmxm.carm.enums.SourceControlServerType

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

