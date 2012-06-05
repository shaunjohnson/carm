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
            <g:message code="summary.label" default="Summary"/>
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
        <div class="row">
            <div class="span6">
                <g:render template="details" model="[userInstance: userInstance]"/>
            </div>

            <div class="span6">
                <g:render template="groups" model="[groups: groups]"/>
            </div>
        </div>
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
