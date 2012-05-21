<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'systemEnvironment.label', default: 'SystemEnvironment')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${systemEnvironmentInstance}"/>

<g:render template="/common/messages"/>

<ul id="systemTabs" class="nav nav-tabs">
    <li class="active">
        <a href="#summaryTab">
            <g:message code="summary.label" default="Summary"/>
        </a>
    </li>
    <li>
        <a href="#detailsTab">
            <g:message code="details.label" default="Details"/>
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
                <g:render template="systemServers" model="['systemInstance': systemEnvironmentInstance]"/>
            </div>

            <div class="span6">
                <g:render template="systemDeploymentEnvironments"
                          model="['systemInstance': systemEnvironmentInstance]"/>
            </div>
        </div>

        <div class="row">
            <div class="span12">
                <g:render template="systemApplications"
                          model="['systemInstance': systemEnvironmentInstance, 'applicationsGrouped': applicationsGrouped, 'latestDeployments': latestDeployments]"/>
            </div>
        </div>
    </div>

    <div id="detailsTab" class="tab-pane">
        <g:render template="systemDetails" model="[systemInstance: systemEnvironmentInstance]"/>
    </div>

    <div id="activityTab" class="tab-pane">
        <g:render template="/common/activity"
                  model="[activityList: activityList, activityCount: activityCount, listActivityAction: 'listActivity', domainId: systemEnvironmentInstance.id]"/>
    </div>
</div>

<r:script>
    jQuery(function () {
        jQuery('#systemTabs a').click(function (e) {
            e.preventDefault();
            jQuery(this).tab('show');
        });
    });
</r:script>
</body>
</html>
