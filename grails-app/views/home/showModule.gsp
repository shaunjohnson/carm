<%@ page import="carm.Module" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'module.label', default: 'Module')}"/>
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
                <td valign="top" class="name"><g:message code="module.name.label" default="Name"/></td>
                <td valign="top" class="value">${fieldValue(bean: moduleInstance, field: "name")}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="module.description.label" default="Description"/></td>
                <td valign="top" class="value">${fieldValue(bean: moduleInstance, field: "description")}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="module.type.label" default="Type"/></td>
                <td valign="top" class="value">${moduleInstance?.type?.encodeAsHTML()}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="module.application.label" default="Application"/></td>
                <td valign="top" class="value">
                    <g:link controller="home" action="showApplication"
                            id="${moduleInstance?.application?.id}">${moduleInstance?.application?.encodeAsHTML()}</g:link>
                    [<g:link controller="home" action="showProject"
                            id="${moduleInstance?.application?.project?.id}">${moduleInstance?.application?.project?.encodeAsHTML()}</g:link>]
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="module.systemComponent.label" default="System Component"/></td>
                <td valign="top" class="value">
                    <g:link controller="home" action="showSystemComponent"
                            id="${moduleInstance?.systemComponent?.id}">${moduleInstance?.systemComponent?.encodeAsHTML()}</g:link>
                    [<g:link controller="home" action="showSystemComponent"
                            id="${moduleInstance?.systemComponent?.system?.id}">${moduleInstance?.systemComponent?.system?.encodeAsHTML()}</g:link>]
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="module.dateCreated.label" default="Date Created"/></td>
                <td valign="top" class="value"><g:formatDate date="${moduleInstance?.dateCreated}"/></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="module.lastUpdated.label" default="Last Updated"/></td>
                <td valign="top" class="value"><g:formatDate date="${moduleInstance?.lastUpdated}"/></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
