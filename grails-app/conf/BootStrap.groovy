import net.lmxm.carm.Application
import net.lmxm.carm.ApplicationType
import net.lmxm.carm.Module
import net.lmxm.carm.Project
import net.lmxm.carm.security.Role
import net.lmxm.carm.SourceControlServer
import net.lmxm.carm.security.User
import net.lmxm.carm.security.UserRole
import net.lmxm.carm.enums.SourceControlServerType
import net.lmxm.carm.security.UserRole
import net.lmxm.carm.security.User

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
        
        def coldFusionType = new ApplicationType(name: "ColdFusion", description: "ColdFushion Web Application").save()
        def ejbType = new ApplicationType(name: "EJB", description: "Enterprise Java Bean Application").save()
        def jsfPortletType = new ApplicationType(name: "JSF Portlet", description: "JSF Portlet Application").save()
        def jsfWebType = new ApplicationType(name: "JSF Web App", description: "JSF Web Application").save()
        def mdbType = new ApplicationType(name: "MDB", description: "Message Driven Bean Application").save()
        def standaloneType = new ApplicationType(name: "Standalone JAR", description: "Standalone Java Application").save()
        
        def suafeProject = new Project(name: "Suafe", description: "Subversion User Authorization File Editor")       
        suafeProject.addToApplications(new Application(name: "Core", description: "Suafe Core", type: standaloneType))
        suafeProject.save()
    }

    def destroy = {
    }
}

