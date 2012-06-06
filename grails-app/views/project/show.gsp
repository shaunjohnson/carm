<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${projectInstance}"/>

<g:render template="/common/messages"/>

<ul id="projectTabs" class="nav nav-tabs">
    <li class="active">
        <a href="#summaryTab">
            <g:message code="summary.label" default="Summary"/>
            <g:if test="${applicationsGrouped.size() == 0}">
                <span class="badge badge-warning">!</span>
            </g:if>
        </a>
    </li>
    <li>
        <a href="#detailsTab">
            <g:message code="details.label" default="Details"/>
        </a>
    </li>
    <li>
        <a href="#permissionsTab">
            <g:message code="permissions.label" default="Permissions"/>
            <g:if test="${projectAdministratorGroups.size() == 0 && projectAdministratorUsers.size() == 0}">
                <span class="badge badge-warning">!</span>
            </g:if>
        </a>
    </li>
</ul>

<div class="tab-content">
    <div id="summaryTab" class="tab-pane active">
        <div>
            <g:render template="pendingTasks" model="[pendingTasks: pendingTasks]"/>
        </div>

        <div class="row">
            <div class="span6">
                <g:render template="projectApplications" model="[projectInstance: projectInstance]"/>
            </div>

            <div class="span6">
                <g:render template="/common/activity"
                          model="[activityList: activityList, activityCount: activityCount, listActivityAction: 'listActivity', domainId: projectInstance.id]"/>
            </div>
        </div>
    </div>

    <div id="detailsTab" class="tab-pane">
        <g:render template="projectDetails" model="[projectInstance: projectInstance]"/>
    </div>

    <div id="permissionsTab" class="tab-pane">
        <div class="sectionHeader">
            <div class="text">
                <g:message code="projectAdministrators.label" default="Project Administrators"/>
            </div>
            <sec:ifAllGranted roles="ROLE_ADMIN">
                <div class="section-action-icon new-action">
                    <a href="#" onclick="return displayAddUserDialog(${projectInstance.id})">
                        <g:message code="addUser.label" default="Add User"/>
                    </a>
                </div>

                <div class="section-action-icon new-action">
                    <a href="#" onclick="return displayAddUserGroupDialog(${projectInstance.id})">
                        <g:message code="addGroup.label" default="Add Group"/>
                    </a>
                </div>

                <div class="clearing"></div>
            </sec:ifAllGranted>
        </div>

        <div class="row">
            <div class="span6">
                <h3><g:message code="groups.label"/></h3>

                <div id="groups-block">
                    <g:render template="projectAdministratorGroups"
                              model="[userGroupList: projectAdministratorGroups]"/>
                </div>

                <p id="no-groups-message" style="display: ${projectAdministratorGroups.size() ? 'none' : 'block'};">
                    <em><g:message code="projectDoesNotHaveAnyAdministratorGroups.message"
                                   default="This project does not have any administrator groups."/></em>
                </p>
            </div>

            <div class="span6">
                <h3><g:message code="users.label"/></h3>

                <div id="users-block">
                    <g:render template="projectAdministratorUsers" model="[userList: projectAdministratorUsers]"/>
                </div>

                <p id="no-users-message" style="display: ${projectAdministratorUsers.size() ? 'none' : 'block'};">
                    <em><g:message code="projectDoesNotHaveAnyAdministratorUsers.message"
                                   default="This project does not have any administrator users."/></em>
                </p>
            </div>
        </div>
    </div>
</div>

<r:script>
    jQuery(function () {
        jQuery('#projectTabs a').click(function (e) {
            e.preventDefault();
            jQuery(this).tab('show');
        });
    });

    function addAdministratorGroup(groupId) {
        jQuery.ajax({
                cache: false,
                url: '${createLink(action: "ajaxAddAdministratorGroup", id: projectInstance.id)}',
                data: { groupId: groupId },
                dataType: 'html',
                success: function(data, textStatus, jqXHR) {
                    jQuery("#groups-block").html(data).show();
                    jQuery("#no-groups-message").hide();
                }
            });
    }

    function addAdministratorUser(userId) {
        jQuery.ajax({
                cache: false,
                url: '${createLink(action: "ajaxAddAdministratorUser", id: projectInstance.id)}',
                data: { userId: userId },
                dataType: 'html',
                success: function(data, textStatus, jqXHR) {
                    jQuery("#users-block").html(data).show();
                    jQuery("#no-users-message").hide();
                }
            });
    }

    function removeGroup(groupId) {
        jQuery.ajax({
                cache: false,
                url: '${createLink(controller: "project", action: "ajaxRemoveAdministratorGroup", id: projectInstance.id)}',
                data: { groupId: groupId }
            });
        jQuery("#group_" + groupId).remove();

        if (!jQuery("#groups-block li").length) {
            jQuery("#groups-block").hide();
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
            jQuery("#users-block").hide();
            jQuery("#no-users-message").show();
        }

        return false;
    }
</r:script>

<g:render template="/common/addUserGroup"
          model="[callback: 'addAdministratorGroup', userGroupList: userGroupList]"/>

<g:render template="/common/addUser"
          model="[callback: 'addAdministratorUser', userList: userList]"/>

</body>
</html>
