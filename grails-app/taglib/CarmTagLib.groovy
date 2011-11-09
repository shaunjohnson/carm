class CarmTagLib {
    def formatSourceControlServer = { attrs, body ->
        def sourceControlRepository = attrs.sourceControlRepository
        def name = sourceControlRepository.server.name
        def url = "${sourceControlRepository.server.url}${sourceControlRepository.path}"

		out << "$name (<a href='$url' target='_blank'>$url</a>)"
	}
}
