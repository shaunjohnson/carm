<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName"
           value="${message(code: 'userGroup.label', default: 'User Group')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${userGroupInstance}"/>

<g:render template="/common/messages"/>

<ul id="userGroupTabs" class="nav nav-tabs">
    <li class="active">
        <a href="#summaryTab">
            <g:message code="summary.label" default="Summary"/>
            <g:if test="${!userGroupInstance.users.size()}">
                <span class="badge badge-warning">!</span>
            </g:if>
        </a>
    </li>
    <li>
        <a href="#detailsTab">
            <g:message code="details.label" default="Details"/>
        </a>
    </li>
</ul>

<div class="tab-content">
    <div id="summaryTab" class="tab-pane active">
        <div class="sectionHeader">
            <div class="text">
                <g:message code="users.label" default="Users"/>
            </div>
            <sec:ifAllGranted roles="ROLE_ADMIN">
                <div class="section-action-icon new-action">
                    <a href="#" onclick="return displayAddUserDialog(${userGroupInstance.id})">
                        <g:message code="addUser.label" default="Add User"/>
                    </a>
                </div>

                <div class="clearing"></div>
            </sec:ifAllGranted>
        </div>

        <div id="users-block">
            <g:render template="users" model="[userList: userGroupMemberList]"/>
        </div>

        <div id="no-users-message" style="display: ${userGroupInstance.users.size() ? 'none' : 'block'};">
            <carm:alertWarning message="${message(code: "userGroupDoesNotHaveAnyUsers.message")}"/>
        </div>
    </div>

    <div id="detailsTab" class="tab-pane">
        <table id="userGroupDetails" class="details">
            <tbody>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="userGroup.description.label" default="Description"/>
                </td>
                <td valign="top" class="value">
                    <div class="expander">
                        <carm:plainText value="${userGroupInstance?.description}"/>
                    </div>
                </td>
            </tr>

            <carm:formDividerRow/>

            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="userGroup.dateCreated.label" default="Date Created"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${userGroupInstance?.dateCreated}"/>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="userGroup.lastUpdated.label" default="Last Updated"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${userGroupInstance?.lastUpdated}"/>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<g:render template="/common/addUser" model="[callback: 'addUser', userList: userList]"/>

<r:script>
    jQuery(function () {
        jQuery('#userGroupTabs a').click(function (e) {
            e.preventDefault();
            jQuery(this).tab('show');
        });
    });

    function addUser(userId) {
        jQuery.ajax({
                cache: false,
                url: '${createLink(action: "ajaxAddUser", id: userGroupInstance.id)}',
                data: { userId: userId },
                dataType: 'html',
                success: function(data, textStatus, jqXHR) {
                    jQuery("#users-block").html(data).show();
                    jQuery("#no-users-message").hide();
                }
            });
    }

    function removeUser(userId) {
        jQuery.ajax({
                cache: false,
                url: '${createLink(controller: "userGroup", action: "ajaxRemoveUser", id: userGroupInstance.id)}',
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

</body>
</html>
