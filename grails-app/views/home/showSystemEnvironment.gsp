<%@ page import="carm.SystemEnvironment" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'systemEnvironment.label', default: 'SystemEnvironment')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>
<body>
<div class="body">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
            <tbody>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="systemEnvironment.name.label" default="Name"/></td>
                <td valign="top" class="value">${fieldValue(bean: systemEnvironmentInstance, field: "name")}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="systemEnvironment.description.label" default="Description"/></td>
                <td valign="top" class="value">${fieldValue(bean: systemEnvironmentInstance, field: "description")}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="systemEnvironment.system.label" default="System"/></td>
                <td valign="top" class="value"><g:link controller="home" action="showSystem" id="${systemEnvironmentInstance?.system?.id}">${systemEnvironmentInstance?.system?.encodeAsHTML()}</g:link></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="systemEnvironment.dateCreated.label" default="Date Created"/></td>
                <td valign="top" class="value"><g:formatDate date="${systemEnvironmentInstance?.dateCreated}"/></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="systemEnvironment.lastUpdated.label" default="Last Updated"/></td>
                <td valign="top" class="value"><g:formatDate date="${systemEnvironmentInstance?.lastUpdated}"/></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
