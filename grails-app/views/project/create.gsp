<%@ page import="carm.security.User; carm.Project" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>
<body>
<div class="body">
    <g:header domain="${projectInstance}" pageName="${message(code: 'default.create.label', args: [entityName])}" />

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${projectInstance}">
        <div class="errors">
            <g:renderErrors bean="${projectInstance}" as="list"/>
        </div>
    </g:hasErrors>
    
    <g:form action="save">
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="name"><g:message code="project.name.label" default="Name"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: projectInstance, field: 'name', 'errors')}">
                        <g:textField name="name" maxlength="50" value="${projectInstance?.name}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="description"><g:message code="project.description.label" default="Description"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: projectInstance, field: 'description', 'errors')}">
                        <g:textArea name="description" cols="40" rows="5" value="${projectInstance?.description}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="description"><g:message code="project.projectManagers.label" default="Project Managers"/></label>
                    </td>
                    <td valign="top" class="value">
                        <g:select name="projectManagers" from="${projectManagerList}" optionKey="username" value="${projectManagers}" multiple="true"/>
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
