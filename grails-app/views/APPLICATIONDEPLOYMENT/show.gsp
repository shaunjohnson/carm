<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'applicationDeployment.label', default: 'Application Deployment')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${applicationDeploymentInstance}"/>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>

<carmsec:isProjectOwner applicationDeployment="${applicationDeploymentInstance}">
    <div style="margin-bottom: 1em;">
        <g:if test="${nextEnvironment}">
            <g:link class="btn" controller="applicationDeployment" action="create"
                    id="${applicationDeploymentInstance.id}"
                    params="['deploymentEnvironment.id': nextEnvironment.id, 'applicationRelease.id': applicationDeploymentInstance.applicationRelease.id]">
                <g:message code="default.button.promote.label" default="Promote to {0}"
                           args="[nextEnvironment.name]"/>
            </g:link>
        </g:if>
        <g:link class="btn" controller="applicationDeployment" action="redeploy"
                id="${applicationDeploymentInstance.id}">
            <g:message code="default.button.redeploy.label" default="Redeploy to {0}"
                       args="[applicationDeploymentInstance.deploymentEnvironment.name]"/>
        </g:link>
    </div>
</carmsec:isProjectOwner>

<ul id="applicationDeploymentTabs" class="nav nav-tabs">
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

<div class="tab-content">
    <div id="summaryTab" class="tab-pane active">
        <g:render template="applicationDeploymentSummary"
                  model="[applicationDeploymentInstance: applicationDeploymentInstance]"/>

        <div>&nbsp;</div>

        <g:render template="applicationDeploymentModules"
                  model="[applicationDeploymentInstance: applicationDeploymentInstance]"/>
    </div>

    <div id="detailsTab" class="tab-pane">
        <g:render template="applicationDeploymentDetails"
                  model="[applicationDeploymentInstance: applicationDeploymentInstance]"/>
    </div>

    <div id="deploymentsTab" class="tab-pane">
        <g:render template="applicationReleaseDeployments"
                  model="[applicationDeploymentInstance: applicationDeploymentInstance]"/>
    </div>

    <div id="activityTab" class="tab-pane">
        <g:render template="/common/activity"
                  model="[activityList: activityList, activityCount: activityCount, listActivityAction: 'listActivity', domainId: applicationDeploymentInstance.id]"/>
    </div>
</div>

<r:script>
    jQuery(function () {
        jQuery('#applicationDeploymentTabs a').click(function (e) {
            e.preventDefault();
            jQuery(this).tab('show');
        });
    });
</r:script>
</body>
</html>
