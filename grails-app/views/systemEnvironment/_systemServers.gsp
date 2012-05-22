<div class="sectionHeader">
    <div class="text">
        <g:message code="systemEnvironment.servers.label" default="Servers"/>
    </div>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <div class="section-action-icon new-system-server-action">
            <g:link controller="systemServer" action="create"
                    params="['sysEnvironment.id': systemEnvironmentInstance?.id]">
                <g:message code="addServer.label" default="Add Server"/>
            </g:link>
        </div>

        <div class="clearing"></div>
    </sec:ifAllGranted>
</div>

<g:if test="${systemEnvironmentInstance.servers.size()}">
    <ul>
        <g:each in="${systemEnvironmentInstance.servers.sort { it.name }}" var="server">
            <li>
                <g:link controller="systemServer" action="show" id="${server.id}">
                    ${server?.encodeAsHTML()}
                </g:link>
            </li>
        </g:each>
    </ul>
</g:if>
<g:else>
    <carm:alertWarning message="${message(code: "systemEnvironmentDoesNotHaveAnyServers.message")}"/>
</g:else>