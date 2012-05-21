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
    <ul class="offset1">
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
    <p>
        <em><g:message code="sourceControlServerDoesNotHaveAnyUsers.message"
                       default="This source control server does not have any users."/></em>
    </p>
</g:else>