<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName"
           value="${message(code: 'systemDeploymentEnvironment.label', default: 'SystemEnvironment Deployment Environment')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${systemDeploymentEnvironmentInstance}"/>

<g:render template="/common/messages"/>

<ul id="environmentTabs" class="nav nav-tabs">
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
        <g:render template="systemEnvironmentApplications"
                  model="['systemDeploymentEnvironmentInstance': systemDeploymentEnvironmentInstance,
                          'applicationsGrouped': applicationsGrouped,
                          'latestDeployments': latestDeployments]"/>
    </div>

    <div id="detailsTab" class="tab-pane">
        <g:render template="systemEnvironmentDetails"
                  model="[systemDeploymentEnvironmentInstance: systemDeploymentEnvironmentInstance]"/>
    </div>

    <div id="activityTab" class="tab-pane">
        <g:render template="/common/activity"
                  model="[activityList: activityList, activityCount: activityCount, listActivityAction: 'listActivity',
                          domainId: systemDeploymentEnvironmentInstance.id]"/>
    </div>
</div>

<r:script>
    jQuery(function () {
        jQuery('#environmentTabs a').click(function (e) {
            e.preventDefault();
            jQuery(this).tab('show');
        });
    });
</r:script>
</body>
</html>
