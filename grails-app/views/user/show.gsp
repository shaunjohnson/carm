<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${userInstance}"/>

<g:render template="/common/messages"/>

<ul id="userTabs" class="nav nav-tabs">
    <li class="active">
        <a href="#summaryTab">
            <g:message code="details.label" default="Details"/>
        </a>
    </li>
    <li>
        <a href="#favoritesAndWatchesTab">
            <g:message code="favoritesAndWatches.label" default="Favorites and Watches"/>
        </a>
    </li>
    <li>
        <a href="#activityTab">
            <g:message code="activity.label" default="Activity"/>
        </a>
    </li>
</ul>

<div class="tab-content">
    <div id="summaryTab" class="tab-pane active">
        <table class="details">
            <tbody>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="user.fullName.label" default="Full Name"/>
                </td>
                <td valign="top" class="value">
                    ${userInstance.fullName.encodeAsHTML()}
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="user.username.label" default="Username"/>
                </td>
                <td valign="top" class="value">
                    ${userInstance.username.encodeAsHTML()}
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="user.email.label" default="Email"/>
                </td>
                <td valign="top" class="value">
                    <a href="mailto:${userInstance.email}">
                        ${userInstance.email.encodeAsHTML()}
                    </a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <div id="favoritesAndWatchesTab" class="tab-pane">
        <div class="row">
            <div class="span6">
                <g:render template="favorites"
                          model="[favorites: favorites, isCurrentUser: isCurrentUser, userInstance: userInstance]"/>
            </div>

            <div class="span6">
                <g:render template="watches"
                          model="[watches: watches, isCurrentUser: isCurrentUser, userInstance: userInstance]"/>
            </div>
        </div>
    </div>

    <div id="activityTab" class="tab-pane">
        <g:render template="/common/activity"
                  model="[activityList: activityList, activityCount: activityCount, listActivityAction: 'listActivity', domainId: userInstance.id]"/>
    </div>
</div>

<r:script>
    jQuery(function () {
        jQuery('#userTabs a').click(function (e) {
            e.preventDefault();
            jQuery(this).tab('show');
        });
    });
</r:script>
</body>
</html>
