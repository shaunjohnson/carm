import org.springframework.security.acls.model.NotFoundException

class CarmTagLib {
    def aclUtilService

    /**
     * Formats a SourceControlRepository as a link.
     *
     * attrs.sourceControlRepository - SourceControlRespository instance
     */
    def formatSourceControlRepository = { attrs, body ->
        def sourceControlRepository = attrs.sourceControlRepository

        def name = sourceControlRepository.server.name
        def url = "${sourceControlRepository.server.url}${sourceControlRepository.path}"

        out << "$name (<a href='$url' target='_blank'>$url</a>)"
    }

    /**
     * Lists all users with specified permission for the provided domain object. Outputs a static message if there are
     * no users granted the permission to the provided domain object.
     *
     * attrs.domainObject - Domain object to lookup
     * attrs.permission - Permission used to locate permitted users
     */
    def listUsersWithPermission = { attrs ->
        def domainObject = attrs.domainObject
        def permission = attrs.permission

        def principals = []

        try {
            def acl = aclUtilService.readAcl(domainObject)

            acl.entries.eachWithIndex { entry, i ->
                if (entry.permission.equals(permission)) {
                    principals.add(entry.sid.principal)
                }
            }
        }
        catch (NotFoundException e) {
            log.debug "listUsersWithPermission() : NotFoundException caught looking up users for domain object"
        }

        if (principals.size()) {
            out << "<ul>"
            principals.each { out << "<li>$it</li>" }
            out << "</ul>"
        }
        else {
            out << "No matching ACLs were found"
        }
    }
}
