<div class="sectionHeader">
    <div class="text">
        <g:message code="users.label" default="Users"/>
    </div>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <div class="section-action-icon new-action">
            <g:link controller="userGroupInstance" action="addUser" params="['userGroup.id': userGroupInstance?.id]">
                <g:message code="addUser.label" default="Add User"/>
            </g:link>
        </div>

        <div class="clearing"></div>
    </sec:ifAllGranted>
</div>

<g:if test="${userGroupInstance.users?.size()}">
    <ul>
        <g:each in="${userGroupInstance.users}" var="user">
            <li>
                <g:link controller="user" action="show" id="${user.id}">
                    ${user.fullName.encodeAsHTML()}
                </g:link>
            </li>
        </g:each>
    </ul>
</g:if>
<g:else>
    <carm:alertWarning message="${message(code: "userGroupDoesNotHaveAnyUsers.message")}"/>
</g:else>

