<%@ page import="carm.Module; carm.SystemComponent" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'systemComponent.label', default: 'SystemComponent')}"/>
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
                <td valign="top" class="name"><g:message code="systemComponent.name.label" default="Name"/></td>
                <td valign="top" class="value">${fieldValue(bean: systemComponentInstance, field: "name")}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="systemComponent.description.label" default="Description"/></td>
                <td valign="top" class="value">${fieldValue(bean: systemComponentInstance, field: "description")}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="systemComponent.system.label" default="System"/></td>
                <td valign="top" class="value"><g:link controller="home" action="showSystem" id="${systemComponentInstance?.system?.id}">${systemComponentInstance?.system?.encodeAsHTML()}</g:link></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="systemComponent.dateCreated.label" default="Date Created"/></td>
                <td valign="top" class="value"><g:formatDate date="${systemComponentInstance?.dateCreated}"/></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="systemComponent.lastUpdated.label" default="Last Updated"/></td>
                <td valign="top" class="value"><g:formatDate date="${systemComponentInstance?.lastUpdated}"/></td>
            </tr>
            </tbody>
        </table>
    </div>

    <h2>Modules</h2>
    <div class="dialog">
        <ul>
            <g:each in="${Module.findAllBySystemComponent(systemComponentInstance).sort { it.name } }" var="module">
                <li>
                    <g:link controller="home" action="showModule"
                            id="${module.id}">${module?.encodeAsHTML()}</g:link>
                    [<g:link controller="home" action="showApplication"
                            id="${module.application.id}">${module?.application?.encodeAsHTML()}</g:link>]
                </li>
            </g:each>
        </ul>
    </div>
</div>
</body>
</html>
