<%@ page import="carm.Application" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'application.label', default: 'Application')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></span>
</div>
<div class="body">
    <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${applicationInstance}">
        <div class="errors">
            <g:renderErrors bean="${applicationInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form method="post">
        <g:hiddenField name="id" value="${applicationInstance?.id}"/>
        <g:hiddenField name="version" value="${applicationInstance?.version}"/>
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="name"><g:message code="application.name.label" default="Name"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: applicationInstance, field: 'name', 'errors')}">
                        <g:textField name="name" maxlength="50" value="${applicationInstance?.name}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="description"><g:message code="application.description.label" default="Description"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: applicationInstance, field: 'description', 'errors')}">
                        <g:textArea name="description" cols="40" rows="5" value="${applicationInstance?.description}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="type"><g:message code="application.type.label" default="Type"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: applicationInstance, field: 'type', 'errors')}">
                        <g:select name="type.id" from="${carm.ApplicationType.list()}" optionKey="id" value="${applicationInstance?.type?.id}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="project"><g:message code="application.project.label" default="Project"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: applicationInstance, field: 'project', 'errors')}">
                        <g:select name="project.id" from="${carm.Project.list()}" optionKey="id" value="${applicationInstance?.project?.id}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="sourceControlRepository"><g:message code="application.sourceControlRepository.label" default="Source Control Repository"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: applicationInstance, field: 'sourceControlRepository', 'errors')}">
                        <g:select name="sourceControlRepository.id" from="${carm.SourceControlRepository.list()}" optionKey="id" value="${applicationInstance?.sourceControlRepository?.id}" noSelection="['null': '']"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="sourceControlPath"><g:message code="application.sourceControlPath.label" default="Source Control Path"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: applicationInstance, field: 'sourceControlPath', 'errors')}">
                        <g:textField name="sourceControlPath" maxlength="200" value="${applicationInstance?.sourceControlPath}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="system"><g:message code="application.system.label" default="System"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: applicationInstance, field: 'system', 'errors')}">
                        <g:select name="system.id" from="${carm.System.list()}" optionKey="id" value="${applicationInstance?.system?.id}" noSelection="['null': '']"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="applicationRoles"><g:message code="application.applicationRoles.label" default="Application Roles"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: applicationInstance, field: 'applicationRoles', 'errors')}">
                        <ul>
                            <g:each in="${applicationInstance?.applicationRoles?}" var="a">
                                <li><g:link controller="applicationRole" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
                            </g:each>
                        </ul>
                        <g:link controller="applicationRole" action="create" params="['application.id': applicationInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'applicationRole.label', default: 'ApplicationRole')])}</g:link>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="modules"><g:message code="application.modules.label" default="Modules"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: applicationInstance, field: 'modules', 'errors')}">
                        <ul>
                            <g:each in="${applicationInstance?.modules?}" var="m">
                                <li><g:link controller="module" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
                            </g:each>
                        </ul>
                        <g:link controller="module" action="create" params="['application.id': applicationInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'module.label', default: 'Module')])}</g:link>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}"/></span>
            <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
