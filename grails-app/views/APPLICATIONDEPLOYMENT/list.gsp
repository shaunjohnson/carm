<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'applicationDeployment.label', default: 'Application Deployment')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${applicationDeploymentInstanceList}"
                 pageName="${message(code: 'default.list.label', args: [entityName])}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="list">
        <table>
            <thead>
            <tr>
                <g:sortableColumn property="deploymentState"
                                  title="${message(code: 'applicationDeployment.deploymentState.label', default: 'Deployment State')}"/>
                <th><g:message code="applicationDeployment.applicationRelease.label"
                               default="Application Release"/></th>
                <th><g:message code="applicationDeployment.sysEnvironment.label" default="Environment"/></th>
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
                        <g:link controller="systemDeplymentEnvironment" action="show"
                                id="${applicationDeploymentInstance?.sysEnvironment?.id}">
                            ${applicationDeploymentInstance?.sysEnvironment?.encodeAsHTML()}
                        </g:link>
                    </td>
                    <td>
                        <g:formatDate date="${applicationDeploymentInstance.requestedDeploymentDate}"/>
                    </td>
                    <td>
                        <g:formatDate date="${applicationDeploymentInstance.completedDeploymentDate}"/>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${applicationDeploymentInstanceTotal}"/>
    </div>
</div>
</body>
</html>
