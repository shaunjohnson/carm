import net.lmxm.carm.domains.ModuleType
import net.lmxm.carm.domains.Project
import net.lmxm.carm.domains.SourceControlServer
import net.lmxm.carm.enums.SourceControlServerType

class BootStrap {
    def init = { servletContext ->
        new SourceControlServer(name: "Subversion", type: SourceControlServerType.Subversion, 
            description: "Subversion server", url: "http://suafe.googlecode.com/svn/").save()
        
        new ModuleType(name: "ColdFusion", description: "ColdFushion Web Application").save()
        new ModuleType(name: "EJB", description: "Enterprise Java Bean Application").save()
        new ModuleType(name: "JSF Portlet", description: "JSF Portlet Application").save()
        new ModuleType(name: "JSF Web App", description: "JSF Web Application").save()
        new ModuleType(name: "MDB", description: "Message Driven Bean Application").save()
        new ModuleType(name: "Standalone JAR", description: "Standalone Java Application").save()
        
        new Project(name: "Suafe", description: "Subversion User Authorization File Editor")
    }

    def destroy = {
    }
}

