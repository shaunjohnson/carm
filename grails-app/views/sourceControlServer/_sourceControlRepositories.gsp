<h2 class="sectionHeader">
    <g:message code="sourceControlServer.repositories.label" default="Repositories"/>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <span class="actions">
            <g:link class="create" controller="sourceControlRepository" action="create"
                    params="['server.id': sourceControlServerInstance?.id]">
                <g:message code="addRepository.label" default="Add Repository"/>
            </g:link>
        </span>
    </sec:ifAllGranted>
</h2>

<g:if test="${sourceControlServerInstance.repositories.size()}">
    <ul>
        <g:each in="${sourceControlServerInstance.repositories.sort { it.name }}" var="repository">
            <li>
                <g:link controller="sourceControlRepository" action="show" id="${repository.id}">
                    ${repository?.encodeAsHTML()}
                </g:link>
            </li>
        </g:each>
    </ul>
</g:if>
<g:else>
    <p class="emphasis">
        <g:message code="sourceControlServerDoesNotHaveAnyRepositories.message"
                   default="This source control server does not have any repositories."/>
    </p>
</g:else>