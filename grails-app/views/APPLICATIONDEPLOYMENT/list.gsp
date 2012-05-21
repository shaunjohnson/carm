<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'applicationDeployment.label', default: 'Application Deployment')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${applicationDeploymentInstanceList}"
             pageName="${message(code: 'default.list.label', args: [entityName])}"/>

<g:render template="/common/messages"/>

<table class="table table-striped">
    <thead>
    <tr>
        <g:sortableColumn property="deploymentState"
                          title="${message(code: 'applicationDeployment.deploymentState.label', default: 'Deployment State')}"/>
        <th><g:message code="applicationDeployment.applicationRelease.label"
                       default="Application Release"/></th>
        <th><g:message code="applicationDeployment.deploymentEnvironment.label" default="Environment"/></th>
        <g:sortableColumn property="requestedDeploymentDate"
                          title="${message(code: 'applicationDeployment.requestedDeploymentDate.label', default: 'Requested Deployment Date')}"/>
        <g:sortableColumn property="completedDeploymentDate"
                          title="${message(code: 'applicationDeployment.completedDeploymentDate.label', default: 'Completed Deployment Date')}"/>
    </tr>
    </thead>
    <tbody>
    <g:each in="${applicationDeploymentInstanceList}" status="i" var="applicationDeploymentInstance">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
            <td>
                <g:link action="show" id="${applicationDeploymentInstance.id}">
                    <carm:formatApplicationDeploymentState
                            deploymentState="${applicationDeploymentInstance.deploymentState}"/>
                </g:link>
            </td>
            <td>
                <g:link controller="applicationRelease" action="show"
                        id="${applicationDeploymentInstance?.applicationRelease?.id}">
                    ${message(code: 'pageHeader.applicationRelease.label', args: [
                            applicationDeploymentInstance?.applicationRelease?.application?.name,
                            applicationDeploymentInstance?.applicationRelease?.releaseNumber])?.encodeAsHTML()}
                </g:link>
            </td>
            <td>
                <g:link controller="systemDeploymentEnvironment" action="show"
                        id="${applicationDeploymentInstance?.deploymentEnvironment?.id}">
                    ${applicationDeploymentInstance?.deploymentEnvironment?.encodeAsHTML()}
                </g:link>
            </td>
            <td>
                <carm:formatDateOnly date="${applicationDeploymentInstance.requestedDeploymentDate}"/>
            </td>
            <td>
                <carm:formatDateOnly date="${applicationDeploymentInstance.completedDeploymentDate}"/>
            </td>
        </tr>
    </g:each>
    </tbody>
</table>

<carm:paginate total="${applicationDeploymentInstanceTotal}"
               max="${grailsApplication.config.ui.applicationDeployment.listMax}"/>

</body>
</html>
