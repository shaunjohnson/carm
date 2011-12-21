<%@ page import="carm.Application" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'application.label', default: 'Application')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>
<body>
<div class="body">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <g:hasErrors bean="${applicationInstance}">
        <div class="errors">
            <g:renderErrors bean="${applicationInstance}" as="list"/>
        </div>
    </g:hasErrors>

    <g:form action="save">
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
                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><g:link class="list" action="list"><g:message code="default.button.cancel.label" default="Cancel"/></g:link></span>
            <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
