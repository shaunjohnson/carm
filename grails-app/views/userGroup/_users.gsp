<div class="sectionHeader">
    <div class="text">
        <g:message code="users.label" default="Users"/>
    </div>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <div class="section-action-icon new-action">
            <g:link action="addUser" id="${userGroupInstance.id}">
                <g:message code="addUser.label" default="Add User"/>
            </g:link>
        </div>

        <div class="clearing"></div>
    </sec:ifAllGranted>
</div>

<g:if test="${userGroupInstance.users?.size()}">
    <ul id="users-block">
        <g:each in="${userGroupInstance.users.sort { it.fullName }}" var="user">
            <li id="user_${user.id}">
                <g:link controller="user" action="show" id="${user.id}">
                    ${user.fullName.encodeAsHTML()}
                </g:link>

                <sec:ifAllGranted roles="ROLE_ADMIN">
                    <a href="#" onclick="return removeUser(${user.id});"
                       title="${message(code: 'removeUser.label')}">
                        <img align="top" src='${fam.icon(name: 'delete')}' alt="Delete"/>
                    </a>
                </sec:ifAllGranted>
            </li>
        </g:each>
    </ul>

    <sec:ifAllGranted roles="ROLE_ADMIN">
        <r:script>
            function removeUser(userId) {
                jQuery.ajax({
                        cache: false,
                        url: '${createLink(controller: "userGroup", action: "ajaxRemoveUser", id: userGroupInstance.id)}',
                        data: { userId: userId }
                    });
                jQuery("#user_" + userId).remove();

                if (!jQuery("#users-block li").length) {
                    jQuery("#users-block").remove();
                    jQuery("#no-users-message").show();
                }

                return false;
            }
        </r:script>
    </sec:ifAllGranted>
</g:if>

<div id="no-users-message" style="display: ${userGroupInstance.users.size() ? 'none' : 'block'};">
    <carm:alertWarning message="${message(code: "userGroupDoesNotHaveAnyUsers.message")}"/>
</div>