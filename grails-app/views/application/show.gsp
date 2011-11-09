<%@ page import="carm.Application" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'application.label', default: 'Application')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></span>
</div>
<div class="body">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
            <tbody>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.name.label" default="Name"/></td>
                <td valign="top" class="value">${fieldValue(bean: applicationInstance, field: "name")}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.description.label" default="Description"/></td>
                <td valign="top" class="value">${fieldValue(bean: applicationInstance, field: "description")}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.type.label" default="Type"/></td>
                <td valign="top" class="value"><g:link controller="applicationType" action="show" id="${applicationInstance?.type?.id}">${applicationInstance?.type?.encodeAsHTML()}</g:link></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.project.label" default="Project"/></td>
                <td valign="top" class="value"><g:link controller="project" action="show" id="${applicationInstance?.project?.id}">${applicationInstance?.project?.encodeAsHTML()}</g:link></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.sourceControlRepository.label" default="Source Control Repository"/></td>
                <td valign="top" class="value"><g:link controller="sourceControlRepository" action="show" id="${applicationInstance?.sourceControlRepository?.id}">${applicationInstance?.sourceControlRepository?.encodeAsHTML()}</g:link></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.sourceControlPath.label" default="Source Control Path"/></td>
                <td valign="top" class="value">${fieldValue(bean: applicationInstance, field: "sourceControlPath")}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.system.label" default="System"/></td>
                <td valign="top" class="value"><g:link controller="system" action="show" id="${applicationInstance?.system?.id}">${applicationInstance?.system?.encodeAsHTML()}</g:link></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.applicationRoles.label" default="Application Roles"/></td>
                <td valign="top" style="text-align: left;" class="value">
                    <ul>
                        <g:each in="${applicationInstance.applicationRoles}" var="a">
                            <li><g:link controller="applicationRole" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
                        </g:each>
                    </ul>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.dateCreated.label" default="Date Created"/></td>
                <td valign="top" class="value"><g:formatDate date="${applicationInstance?.dateCreated}"/></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.lastUpdated.label" default="Last Updated"/></td>
                <td valign="top" class="value"><g:formatDate date="${applicationInstance?.lastUpdated}"/></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.modules.label" default="Modules"/></td>
                <td valign="top" style="text-align: left;" class="value">
                    <ul>
                        <g:each in="${applicationInstance.modules.sort { it.name }}" var="m">
                            <li><g:link controller="module" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
                        </g:each>
                    </ul>
                    <g:link controller="module" action="create" params="['application.id': applicationInstance?.id]">Add Module</g:link>    
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${applicationInstance?.id}"/>
            <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}"/></span>
            <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
        </g:form>
    </div>
</div>
</body>
</html>
