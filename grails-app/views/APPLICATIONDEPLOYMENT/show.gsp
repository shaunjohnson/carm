<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'applicationDeployment.label', default: 'Application Deployment')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${applicationDeploymentInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <carmsec:isProjectOwner applicationDeployment="${applicationDeploymentInstance}">
        <div class="buttons">
            <g:if test="${nextEnvironment}">
                <carm:button controller="applicationDeployment" action="create"
                             id="${applicationDeploymentInstance.id}"
                             params="['deploymentEnvironment.id': nextEnvironment.id, 'applicationRelease.id': applicationDeploymentInstance.applicationRelease.id]">
                    <g:message code="default.button.promote.label" default="Promote to {0}"
                               args="[nextEnvironment.name]"/>
                </carm:button>
            </g:if>
            <carm:button controller="applicationDeployment" action="redeploy"
                         id="${applicationDeploymentInstance.id}">
                <g:message code="default.button.redeploy.label" default="Redeploy to {0}"
                           args="[applicationDeploymentInstance.deploymentEnvironment.name]"/>
            </carm:button>
        </div>
    </carmsec:isProjectOwner>

    <div id="applicationDeploymentTabs" class="tab-container">
        <ul class="tabs">
            <li>
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
                <a href="#deploymentsTab">
                    <g:message code="deployments.label" default="Deployments"/>
                </a>
            </li>
            <li>
                <a href="#activityTab">
                    <g:message code="activity.label" default="Activity"/>
                </a>
            </li>
        </ul>

        <div class="panel-container">
            <div id="summaryTab">
                <g:render template="applicationDeploymentSummary"
                          model="[applicationDeploymentInstance: applicationDeploymentInstance]"/>

                <div>&nbsp;</div>

                <g:render template="applicationDeploymentModules"
                          model="[applicationDeploymentInstance: applicationDeploymentInstance]"/>
            </div>

            <div id="detailsTab">
                <g:render template="applicationDeploymentDetails"
                          model="[applicationDeploymentInstance: applicationDeploymentInstance]"/>
            </div>

            <div id="deploymentsTab">
                <g:render template="applicationReleaseDeployments"
                          model="[applicationDeploymentInstance: applicationDeploymentInstance]"/>
            </div>

            <div id="activityTab">
                <g:render template="/common/activity"
                          model="[activityList: activityList, activityCount: activityCount, listActivityAction: 'listActivity', domainId: applicationDeploymentInstance.id]"/>
            </div>
        </div>
    </div>

    <script type='text/javascript'>
        jQuery(function () {
            jQuery("#applicationDeploymentTabs").easytabs({ animationSpeed:'fast' });
        });
    </script>
</div>
</body>
</html>
