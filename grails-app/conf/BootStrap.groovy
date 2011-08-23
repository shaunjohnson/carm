import net.lmxm.carm.domains.SourceControlServer

class BootStrap {
    def init = { servletContext ->
        new SourceControlServer(name: "Subversion", type: "SUBVERSION", description: "Subversion server").save()
    }

    def destroy = {
    }
}
