<%@ page import="carm.Project" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}"/>
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
                <td valign="top" class="name"><g:message code="project.name.label" default="Name"/></td>
                <td valign="top" class="value">${fieldValue(bean: projectInstance, field: "name")}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="project.description.label" default="Description"/></td>
                <td valign="top" class="value">${fieldValue(bean: projectInstance, field: "description")}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="project.applications.label" default="Applications"/></td>
                <td valign="top" style="text-align: left;" class="value">
                    <ul>
                        <g:each in="${projectInstance.applications.sort { it.name }}" var="a">
                            <li><g:link controller="home" action="showApplication" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
                        </g:each>
                    </ul>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="project.dateCreated.label" default="Date Created"/></td>
                <td valign="top" class="value"><g:formatDate date="${projectInstance?.dateCreated}"/></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="project.lastUpdated.label" default="Last Updated"/></td>
                <td valign="top" class="value"><g:formatDate date="${projectInstance?.lastUpdated}"/></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
