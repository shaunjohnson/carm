<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'systemDeploymentEnvironment.label', default: 'SystemEnvironment Deployment Environment')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${systemDeploymentEnvironmentInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div id="systemTabs" class="tab-container">
        <ul class="tabs">
            <li>
                <a href="#applicationsTab">
                    <g:message code="applications.label" default="Applications"/>
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

        <div class="panel-container">
            <div id="applicationsTab">
                <g:render template="systemEnvironmentApplications"
                          model="['systemDeploymentEnvironmentInstance': systemDeploymentEnvironmentInstance, 'applicationsGrouped': applicationsGrouped, 'latestDeployments': latestDeployments]"/>
            </div>

            <div id="detailsTab">
                <g:render template="systemEnvironmentDetails" model="[systemDeploymentEnvironmentInstance: systemDeploymentEnvironmentInstance]"/>
            </div>

            <div id="activityTab">
                <g:render template="/common/activity"
                          model="[activityList: activityList, activityCount: activityCount, listActivityAction: 'listActivity', domainId: systemDeploymentEnvironmentInstance.id]"/>
            </div>
        </div>
    </div>

    <script type='text/javascript'>
        jQuery(function () {
            jQuery("#systemTabs").easytabs({ animationSpeed:'fast' });
        });
    </script>
</div>
</body>
</html>
