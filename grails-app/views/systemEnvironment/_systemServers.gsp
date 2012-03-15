<div class="sectionHeader">
    <div class="text">
        <g:message code="systemEnvironment.servers.label" default="Servers"/>
    </div>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <div class="actions">
            <g:link class="create" controller="systemServer" action="create"
                    params="['sysEnvironment.id': systemInstance?.id]">
                <g:message code="addServer.label" default="Add Server"/>
            </g:link>
        </div>
    </sec:ifAllGranted>
</div>

<g:if test="${systemInstance.servers.size()}">
    <ul>
        <g:each in="${systemInstance.servers.sort { it.name }}" var="server">
            <li>
                <g:link controller="systemServer" action="show" id="${server.id}">
                    ${server?.encodeAsHTML()}
                </g:link>
            </li>
        </g:each>
    </ul>
</g:if>
<g:else>
    <p class="emphasis">
        <g:message code="systemEnvironmentDoesNotHaveAnyServers.message"
                   default="This environment does not have any servers."/>
    </p>
</g:else>