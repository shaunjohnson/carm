<h2 class="sectionHeader">
    <g:message code="sourceControlServer.users.label" default="Users"/>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <span class="actions">
            <g:link class="create" controller="sourceControlUser" action="create"
                    params="['server.id': sourceControlServerInstance?.id]">
                <g:message code="addUser.label" default="Add User"/>
            </g:link>
        </span>
    </sec:ifAllGranted>
</h2>

<g:if test="${sourceControlServerInstance?.users?.size()}">
    <ul>
        <g:each in="${sourceControlServerInstance.users.sort { it.name }}" var="user">
            <li>
                <g:link controller="sourceControlUser" action="show" id="${user.id}">
                    ${user?.name?.encodeAsHTML()}
                </g:link>
            </li>
        </g:each>
    </ul>
</g:if>
<g:else>
    <p class="emphasis">
        <g:message code="sourceControlServerDoesNotHaveAnyUsers.message"
                   default="This source control server does not have any users."/>
    </p>
</g:else>