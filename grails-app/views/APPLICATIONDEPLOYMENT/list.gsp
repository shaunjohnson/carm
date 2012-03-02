<%@ page import="carm.deployment.ApplicationDeployment" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'applicationDeployment.label', default: 'Application Deployment')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${applicationDeploymentInstanceList}" pageName="${message(code: 'default.list.label', args: [entityName])}"/>

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
                            ${fieldValue(bean: applicationDeploymentInstance, field: "deploymentState")}
                        </g:link>
                    </td>
                    <td>
                        ${fieldValue(bean: applicationDeploymentInstance, field: "applicationRelease")}
                    </td>
                    <td>
                        ${fieldValue(bean: applicationDeploymentInstance, field: "sysEnvironment")}
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
