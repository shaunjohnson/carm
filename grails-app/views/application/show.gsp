<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'application.label', default: 'Application')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${applicationInstance}"/>

<g:render template="/common/messages"/>

<ul id="applicationTabs" class="nav nav-tabs">
    <li class="active">
        <a href="#summaryTab" data-toggle="tab">
            <g:message code="summary.label" default="Summary"/>
        </a>
    </li>
    <li>
        <a href="#detailsTab" data-toggle="tab">
            <g:message code="details.label" default="Details"/>
        </a>
    </li>
    <li>
        <a href="#modulesTab" data-toggle="tab">
            <g:message code="modules.label" default="Modules"/>
            <g:if test="${applicationInstance.modules.size()}">
                <span class="badge">${applicationInstance.modules.size()}</span>
            </g:if>
            <g:else>
                <span class="badge badge-warning">!</span>
            </g:else>
        </a>
    </li>
    <li>
        <a href="#permissionsTab" data-toggle="tab">
            <g:message code="permissions.label" default="Permissions"/>
        </a>
    </li>
    <li>
        <a href="#activityTab" data-toggle="tab">
            <g:message code="activity.label" default="Activity"/>
        </a>
    </li>
</ul>

<div class="tab-content">
    <div id="summaryTab" class="tab-pane active">
        <div class="row">
            <div class="span12">
                <g:render template="pendingTasks" model="[pendingTasks: pendingTasks]"/>
            </div>
        </div>

        <div class="row">
            <div class="span6">
                <g:render template="applicationEnvironments" model="[applicationInstance: applicationInstance]"/>
            </div>

            <div class="span6">
                <g:render template="applicationReleases" model="[applicationInstance: applicationInstance]"/>
            </div>
        </div>
    </div>

    <div id="detailsTab" class="tab-pane">
        <g:render template="applicationDetails" model="[applicationInstance: applicationInstance]"/>
    </div>

    <div id="modulesTab" class="tab-pane">
        <g:render template="applicationModules" model="[applicationInstance: applicationInstance]"/>
    </div>

    <div id="permissionsTab" class="tab-pane">
        <div class="row">
            <div class="span6">

                <div class="sectionHeader">
                    <div class="text">
                        <g:message code="teamLeaders.label" default="Team Leaders"/>
                    </div>
                    <carmsec:isProjectOwner application="${applicationInstance}">
                        <div class="section-action-icon new-action">
                            <a href="#" onclick="return displayAddUserDialog(addTeamLeaderUser)">
                                <g:message code="addUser.label" default="Add User"/>
                            </a>
                        </div>

                        <div class="section-action-icon new-action">
                            <a href="#"
                               onclick="return displayAddUserGroupDialog(addTeamLeaderGroup)">
                                <g:message code="addGroup.label" default="Add Group"/>
                            </a>
                        </div>

                        <div class="clearing"></div>
                    </carmsec:isProjectOwner>
                </div>

                <div class="section-content">
                    <div style="margin-bottom: 2em;">
                        <h4><g:message code="groups.label"/></h4>

                        <div id="team-leader-groups-block">
                            <g:render template="teamLeaderGroups"
                                      model="[userGroupList: teamLeaderGroups, applicationInstance: applicationInstance]"/>
                        </div>

                        <p id="no-team-leader-groups-message"
                           style="display: ${teamLeaderGroups.size() ? 'none' : 'block'};">
                            <em><g:message code="applicationDoesNotHaveAnyTeamLeaderGroups.message"
                                           default="This application does not have any team leader groups."/></em>
                        </p>
                    </div>

                    <div>
                        <h4><g:message code="users.label"/></h4>

                        <div id="team-leader-users-block">
                            <g:render template="teamLeaderUsers"
                                      model="[userList: teamLeaderUsers, applicationInstance: applicationInstance]"/>
                        </div>

                        <p id="no-team-leader-users-message"
                           style="display: ${teamLeaderUsers.size() ? 'none' : 'block'};">
                            <em><g:message code="applicationDoesNotHaveAnyTeamLeaderUsers.message"
                                           default="This application does not have any team leader users."/></em>
                        </p>
                    </div>
                </div>

            </div>

            <div class="span6">

                <div class="sectionHeader">
                    <div class="text">
                        <g:message code="applicationDevelopers.label" default="Developers"/>
                    </div>
                    <carmsec:isProjectOwner application="${applicationInstance}">
                        <div class="section-action-icon new-action">
                            <a href="#" onclick="return displayAddUserDialog(addApplicationDeveloperUser)">
                                <g:message code="addUser.label" default="Add User"/>
                            </a>
                        </div>

                        <div class="section-action-icon new-action">
                            <a href="#"
                               onclick="return displayAddUserGroupDialog(addApplicationDeveloperGroup)">
                                <g:message code="addGroup.label" default="Add Group"/>
                            </a>
                        </div>

                        <div class="clearing"></div>
                    </carmsec:isProjectOwner>
                </div>

                <div class="section-content">
                    <div style="margin-bottom: 2em;">
                        <h4><g:message code="groups.label"/></h4>

                        <div id="app-developer-groups-block">
                            <g:render template="applicationDeveloperGroups"
                                      model="[userGroupList: applicationDeveloperGroups, applicationInstance: applicationInstance]"/>
                        </div>

                        <p id="no-app-developer-groups-message"
                           style="display: ${applicationDeveloperGroups.size() ? 'none' : 'block'};">
                            <em><g:message code="applicationDoesNotHaveAnyApplicationDeveloperGroups.message"
                                           default="This application does not have any application developer groups."/></em>
                        </p>
                    </div>

                    <div>
                        <h4><g:message code="users.label"/></h4>

                        <div id="app-developer-users-block">
                            <g:render template="applicationDeveloperUsers"
                                      model="[userList: applicationDeveloperUsers, applicationInstance: applicationInstance]"/>
                        </div>

                        <p id="no-app-developer-users-message"
                           style="display: ${applicationDeveloperUsers.size() ? 'none' : 'block'};">
                            <em><g:message code="applicationDoesNotHaveAnyApplicationDeveloperUsers.message"
                                           default="This application does not have any application developer users."/></em>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="activityTab" class="tab-pane">
        <g:render template="/common/activity"
                  model="[activityList: activityList, activityCount: activityCount, listActivityAction: 'listActivity', domainId: applicationInstance.id]"/>
    </div>
</div>

<g:render template="/common/addUserGroup" model="[userGroupList: userGroupList]"/>
<g:render template="/common/addUser" model="[userList: userList]"/>

<r:script>
    jQuery(function () {
        jQuery('#applicationTabs a').click(function (e) {
            e.preventDefault();
            jQuery(this).tab('show');
        });
    });

    function addApplicationDeveloperGroup(groupId) {
        jQuery.ajax({
                cache: false,
                url: '${createLink(action: "ajaxAddApplicationDeveloperGroup", id: applicationInstance.id)}',
                data: { groupId: groupId },
                dataType: 'html',
                success: function(data, textStatus, jqXHR) {
                    jQuery("#app-developer-groups-block").html(data).show();
                    jQuery("#no-app-developer-groups-message").hide();
                }
            });
    }

    function addApplicationDeveloperUser(userId) {
        jQuery.ajax({
                cache: false,
                url: '${createLink(action: "ajaxAddApplicationDeveloperUser", id: applicationInstance.id)}',
                data: { userId: userId },
                dataType: 'html',
                success: function(data, textStatus, jqXHR) {
                    jQuery("#app-developer-users-block").html(data).show();
                    jQuery("#no-app-developer-users-message").hide();
                }
            });
    }

    function addTeamLeaderGroup(groupId) {
        jQuery.ajax({
                cache: false,
                url: '${createLink(action: "ajaxAddTeamLeaderGroup", id: applicationInstance.id)}',
                data: { groupId: groupId },
                dataType: 'html',
                success: function(data, textStatus, jqXHR) {
                    jQuery("#team-leader-groups-block").html(data).show();
                    jQuery("#no-team-leader-groups-message").hide();
                }
            });
    }

    function addTeamLeaderUser(userId) {
        jQuery.ajax({
                cache: false,
                url: '${createLink(action: "ajaxAddTeamLeaderUser", id: applicationInstance.id)}',
                data: { userId: userId },
                dataType: 'html',
                success: function(data, textStatus, jqXHR) {
                    jQuery("#team-leader-users-block").html(data).show();
                    jQuery("#no-team-leader-users-message").hide();
                }
            });
    }

    function removeApplicationDeveloperGroup(groupId) {
        jQuery.ajax({
                cache: false,
                url: '${createLink(action: "ajaxRemoveApplicationDeveloperGroup", id: applicationInstance.id)}',
                data: { groupId: groupId }
            });
        jQuery("#app-developer-group_" + groupId).remove();

        if (!jQuery("#app-developer-groups-block li").length) {
            jQuery("#app-developer-groups-block").hide();
            jQuery("#no-app-developer-groups-message").show();
        }

        return false;
    }

    function removeApplicationDeveloperUser(userId) {
        jQuery.ajax({
                cache: false,
                url: '${createLink(action: "ajaxRemoveApplicationDeveloperUser", id: applicationInstance.id)}',
                data: { userId: userId }
            });
        jQuery("#app-developer-user_" + userId).remove();

        if (!jQuery("#app-developer-users-block li").length) {
            jQuery("#app-developer-users-block").hide();
            jQuery("#no-app-developer-users-message").show();
        }

        return false;
    }

    function removeTeamLeaderGroup(groupId) {
        jQuery.ajax({
                cache: false,
                url: '${createLink(action: "ajaxRemoveTeamLeaderGroup", id: applicationInstance.id)}',
                data: { groupId: groupId }
            });
        jQuery("#team-leader-group_" + groupId).remove();

        if (!jQuery("#team-leader-groups-block li").length) {
            jQuery("#team-leader-groups-block").hide();
            jQuery("#no-team-leader-groups-message").show();
        }

        return false;
    }

    function removeTeamLeaderUser(userId) {
        jQuery.ajax({
                cache: false,
                url: '${createLink(action: "ajaxRemoveTeamLeaderUser", id: applicationInstance.id)}',
                data: { userId: userId }
            });
        jQuery("#team-leader-user_" + userId).remove();

        if (!jQuery("#team-leader-users-block li").length) {
            jQuery("#team-leader-users-block").hide();
            jQuery("#no-team-leader-users-message").show();
        }

        return false;
    }
</r:script>

</body>
</html>