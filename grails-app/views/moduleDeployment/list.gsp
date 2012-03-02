<%@ page import="carm.module.ModuleDeployment" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'moduleDeployment.label', default: 'Module Deployment')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${moduleDeploymentInstanceList}"
                 pageName="${message(code: 'default.list.label', args: [entityName])}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="list">
        <table>
            <thead>
            <tr>
                <g:sortableColumn property="name" title="${message(code: 'module.name.label', default: 'Name')}"/>
                <g:sortableColumn property="name" title="${message(code: 'applicationRelease.releaseNumber.label', default: 'Release Number')}"/>
                <g:sortableColumn property="deploymentState"
                                  title="${message(code: 'moduleDeployment.deploymentState.label', default: 'Deployment State')}"/>
                <g:sortableColumn property="testState"
                                  title="${message(code: 'moduleDeployment.testState.label', default: 'Test State')}"/>
            </tr>
            </thead>
            <tbody>
            <g:each in="${moduleDeploymentInstanceList}" status="i" var="moduleDeploymentInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <td>
                        <g:link action="show" id="${moduleDeploymentInstance.id}">
                            ${moduleDeploymentInstance?.moduleRelease?.module?.name?.encodeAsHTML()}
                        </g:link>
                    </td>
                    <td>
                        <g:link controller="applicationRelease" action="show" id="${moduleDeploymentInstance?.moduleRelease?.applicationRelease?.id}">
                            ${moduleDeploymentInstance?.moduleRelease?.applicationRelease?.releaseNumber?.encodeAsHTML()}
                        </g:link>
                    </td>
                    <td>
                        ${fieldValue(bean: moduleDeploymentInstance, field: "deploymentState")}
                    </td>
                    <td>
                        ${fieldValue(bean: moduleDeploymentInstance, field: "testState")}
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${moduleDeploymentInstanceTotal}"/>
    </div>
</div>
</body>
</html>
