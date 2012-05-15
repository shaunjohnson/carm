<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName"
           value="${message(code: 'moduleDeploymentTestState.label', default: 'Module Deployment Test State')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${moduleDeploymentTestStateInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div id="moduleDeploymentTestStateDetails" class="dialog">
        <table class="details">
            <tbody>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="moduleDeploymentTestState.description.label" default="Description"/>
                </td>
                <td valign="top" class="value">
                    <div class="expander">
                        <carm:plainText value="${moduleDeploymentTestStateInstance?.description}"/>
                    </div>
                </td>
            </tr>

            <carm:formDividerRow/>

            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="moduleDeploymentTestState.dateCreated.label" default="Date Created"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${moduleDeploymentTestStateInstance?.dateCreated}"/>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="moduleDeploymentTestState.lastUpdated.label" default="Last Updated"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${moduleDeploymentTestStateInstance?.lastUpdated}"/>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
