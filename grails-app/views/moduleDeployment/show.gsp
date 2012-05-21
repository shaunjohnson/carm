<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'moduleDeployment.label', default: 'Module Deployment')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${moduleDeploymentInstance}"/>

<g:render template="/common/messages"/>

<table id="moduleDeploymentDetails" class="details">
    <tbody>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="module.name.label" default="Name"/>
        </td>
        <td valign="top" class="value">
            <g:link controller="module" action="show" id="${moduleDeploymentInstance.moduleRelease.module.id}">
                ${moduleDeploymentInstance?.moduleRelease?.module?.name?.encodeAsHTML()}
            </g:link>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="applicationDeployment.applicationRelease.label" default="Application Release"/>
        </td>
        <td valign="top" class="value">
            <g:link action="show" id="${moduleDeploymentInstance.id}">
                ${moduleDeploymentInstance?.moduleRelease?.applicationRelease?.releaseNumber?.encodeAsHTML()}
            </g:link>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="moduleDeployment.deploymentState.label" default="Deployment State"/>
        </td>
        <td valign="top" class="value">
            <carm:formatModuleDeploymentState deploymentState="${moduleDeploymentInstance.deploymentState}"/>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="moduleDeployment.testState.label" default="Test State"/>
        </td>
        <td valign="top" class="value">
            ${fieldValue(bean: moduleDeploymentInstance, field: "testState")}
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="moduleDeployment.deploymentInstructions.label" default="Deployment Instructions"/>
        </td>
        <td valign="top" class="value">
            ${fieldValue(bean: moduleDeploymentInstance, field: "deploymentInstructions")}
        </td>
    </tr>

    <carm:formDividerRow/>

    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="moduleType.dateCreated.label" default="Date Created"/>
        </td>
        <td valign="top" class="value">
            <g:formatDate date="${moduleDeploymentInstance?.dateCreated}"/>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="moduleType.lastUpdated.label" default="Last Updated"/>
        </td>
        <td valign="top" class="value">
            <g:formatDate date="${moduleDeploymentInstance?.lastUpdated}"/>
        </td>
    </tr>
    </tbody>
</table>

</body>
</html>
