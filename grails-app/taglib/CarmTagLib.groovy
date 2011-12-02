
import org.springframework.security.acls.model.NotFoundException

class CarmTagLib {
    def aclUtilService

    def formatSourceControlServer = { attrs, body ->
        def sourceControlRepository = attrs.sourceControlRepository
        def name = sourceControlRepository.server.name
        def url = "${sourceControlRepository.server.url}${sourceControlRepository.path}"

        out << "$name (<a href='$url' target='_blank'>$url</a>)"
    }

    def listUsersWithPermission = { attrs ->
        def domainObject = attrs.domainObject
        def permission = attrs.permission

        try {
            def acl = aclUtilService.readAcl(domainObject)
            def principals = []

            acl.entries.eachWithIndex { entry, i ->
                if (entry.permission.equals(permission)) {
                    principals.add entry.sid.principal
                }
            }

            if (principals.size()) {
                out << "<ul>"
                principals.each { out << "<li>$it</li>" }
                out << "</ul>"
            }
        }
        catch(NotFoundException e) {
            out << "No matching ACLs were found"
        }
    }
}
