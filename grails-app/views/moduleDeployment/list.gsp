<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'moduleDeployment.label', default: 'Module Deployment')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${moduleDeploymentInstanceList}"
             pageName="${message(code: 'default.list.label', args: [entityName])}"/>

<g:render template="/common/messages"/>

<table class="table table-striped">
    <thead>
    <tr>
        <g:sortableColumn property="name" title="${message(code: 'module.name.label', default: 'Name')}"/>
        <g:sortableColumn property="name"
                          title="${message(code: 'applicationRelease.releaseNumber.label', default: 'Release Number')}"/>
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
                <g:link controller="applicationRelease" action="show"
                        id="${moduleDeploymentInstance?.moduleRelease?.applicationRelease?.id}">
                    ${moduleDeploymentInstance?.moduleRelease?.applicationRelease?.releaseNumber?.encodeAsHTML()}
                </g:link>
            </td>
            <td>
                <carm:formatModuleDeploymentState
                        deploymentState="${moduleDeploymentInstance.deploymentState}"/>
            </td>
            <td>
                ${fieldValue(bean: moduleDeploymentInstance, field: "testState")}
            </td>
        </tr>
    </g:each>
    </tbody>
</table>

<carm:paginate total="${moduleDeploymentInstanceTotal}"
               max="${grailsApplication.config.ui.moduleDeployment.listMax}"/>

</body>
</html>
