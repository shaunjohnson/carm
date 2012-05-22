<div class="sectionHeader">
    <div class="text">
        <g:message code="sourceControlServer.users.label" default="Users"/>
    </div>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <div class="section-action-icon new-user-action">
            <g:link class="create" controller="sourceControlUser" action="create"
                    params="['server.id': sourceControlServerInstance?.id]">
                <g:message code="addUser.label" default="Add User"/>
            </g:link>
        </div>

        <div class="clearing"></div>
    </sec:ifAllGranted>
</div>

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
    <carm:alertWarning message="${message(code: "sourceControlServerDoesNotHaveAnyUsers.message")}"/>
</g:else>