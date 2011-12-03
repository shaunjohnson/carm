<%@ page import="carm.Application; carm.System" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'system.label', default: 'System')}"/>
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
                <td valign="top" class="name"><g:message code="system.name.label" default="Name"/></td>
                <td valign="top" class="value">${fieldValue(bean: systemInstance, field: "name")}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="system.description.label" default="Description"/></td>
                <td valign="top" class="value">${fieldValue(bean: systemInstance, field: "description")}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="system.components.label" default="Components"/></td>
                <td valign="top" style="text-align: left;" class="value">
                    <ul>
                        <g:each in="${systemInstance.components.sort { it.name }}" var="c">
                            <li><g:link controller="home" action="showSystemComponent" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
                        </g:each>
                    </ul>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="system.environments.label" default="Environments"/></td>
                <td valign="top" style="text-align: left;" class="value">
                    <ul>
                        <g:each in="${systemInstance.environments.sort { it.name }}" var="e">
                            <li><g:link controller="home" action="showSystemEnvironment" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
                        </g:each>
                    </ul>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="system.dateCreated.label" default="Date Created"/></td>
                <td valign="top" class="value"><g:formatDate date="${systemInstance?.dateCreated}"/></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="system.lastUpdated.label" default="Last Updated"/></td>
                <td valign="top" class="value"><g:formatDate date="${systemInstance?.lastUpdated}"/></td>
            </tr>
            </tbody>
        </table>
    </div>

    <h2>Applications</h2>
    <div class="dialog">
        <ul>
            <g:each in="${Application.findAllBySystem(systemInstance).sort { it.name } }" var="application">
                <li><g:link controller="home" action="showApplication" id="${application.id}">${application?.encodeAsHTML()}</g:link></li>
            </g:each>
        </ul>
    </div>
</div>
</body>
</html>
