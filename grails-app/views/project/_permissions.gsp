<div class="sectionHeader">
    <div class="text">
        <g:message code="projectAdministrators.label" default="Project Administrators"/>
    </div>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <div class="section-action-icon new-action">
            <g:link action="addAdministrator" id="${projectInstance.id}">
                <g:message code="addAdministrator.label" default="Add Administrator"/>
            </g:link>
        </div>

        <div class="clearing"></div>
    </sec:ifAllGranted>
</div>

<g:if test="${projectAdministratorGroups.size() || projectAdministratorUsers.size()}">
    <div class="row">
        <div class="span6">
            <h3><g:message code="groups.label"/></h3>
            <g:if test="${projectAdministratorGroups.size()}">
                <ul id="groups-block">
                    <g:each in="${projectAdministratorGroups}" var="userGroup">
                        <li id="group_${userGroup.id}">
                            <g:link controller="userGroup" action="show" id="${userGroup.id}">
                                ${userGroup.name.encodeAsHTML()}
                            </g:link>

                            <sec:ifAllGranted roles="ROLE_ADMIN">
                                <a href="#" onclick="return removeGroup(${userGroup.id});"
                                   title="${message(code: 'removeGroup.label')}">
                                    <img align="top" src='${fam.icon(name: 'delete')}' alt="Delete"/>
                                </a>
                            </sec:ifAllGranted>
                        </li>
                    </g:each>
                </ul>
            </g:if>

            <p id="no-groups-message" style="display: ${projectAdministratorGroups.size() ? 'none' : 'block'};">
                <em><g:message code="projectDoesNotHaveAnyAdministratorGroups.message"
                               default="This project does not have any administrator groups."/></em>
            </p>
        </div>

        <div class="span6">
            <h3><g:message code="users.label"/></h3>
            <g:if test="${projectAdministratorUsers.size()}">
                <ul id="users-block">
                    <g:each in="${projectAdministratorUsers}" var="user">
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
            </g:if>

            <p id="no-users-message" style="display: ${projectAdministratorUsers.size() ? 'none' : 'block'};">
                <em><g:message code="projectDoesNotHaveAnyAdministratorUsers.message"
                               default="This project does not have any administrator users."/></em>
            </p>
        </div>
    </div>

    <sec:ifAllGranted roles="ROLE_ADMIN">
        <r:script>
            function removeGroup(groupId) {
                jQuery.ajax({
                        cache: false,
                        url: '${createLink(controller: "project", action: "ajaxRemoveAdministratorGroup", id: projectInstance.id)}',
                        data: { userId: groupId }
                    });
                jQuery("#group_" + groupId).remove();

                if (!jQuery("#groups-block li").length) {
                    jQuery("#groups-block").remove();
                    jQuery("#no-groups-message").show();
                }

                return false;
            }

            function removeUser(userId) {
                jQuery.ajax({
                        cache: false,
                        url: '${createLink(controller: "project", action: "ajaxRemoveAdministratorUser", id: projectInstance.id)}',
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
<g:else>
    <carm:alertWarning message="${message(code: 'projectDoesNotHaveAnyAdministrators.message')}"/>
</g:else>
