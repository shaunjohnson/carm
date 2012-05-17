<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'applicationDeployment.label', default: 'Application Deployment')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${systemInstance}" pageName="${message(code: 'default.list.label', args: [entityName])}"/>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>

<div>
    <g:link class="btn" action="completedDeployments" id="${params.id}">
        <g:message code="completedDeployments.label" default="Completed Deployments"/>
    </g:link>
</div>

<g:if test="${applicationDeploymentsGrouped?.size()}">
    <g:render template="deploymentsBlock"
              model="[applicationDeploymentsGrouped: applicationDeploymentsGrouped]"/>
</g:if>
<g:else>
    <p>
        <em><g:message code="noUpcomingDeploymentsForEnvironment.message"
                       default="There are no upcoming deployments for this environment."/></em>
    </p>
</g:else>
</body>
</html>
