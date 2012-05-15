<div class="sectionHeader">
    <div class="text">
        <g:message code="sourceControlServer.repositories.label" default="Repositories"/>
    </div>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <div class="header-action add-source-contro-repo-action">
            <g:link class="create" controller="sourceControlRepository" action="create"
                    params="['server.id': sourceControlServerInstance?.id]">
                <g:message code="addRepository.label" default="Add Repository"/>
            </g:link>
        </div>

        <div class="clearing"></div>
    </sec:ifAllGranted>
</div>

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