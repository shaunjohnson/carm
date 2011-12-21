<%@ page import="carm.System" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'system.label', default: 'System')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>
<body>
<div class="body">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <g:hasErrors bean="${systemInstance}">
        <div class="errors">
            <g:renderErrors bean="${systemInstance}" as="list"/>
        </div>
    </g:hasErrors>

    <g:form action="save">
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="name"><g:message code="system.name.label" default="Name"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: systemInstance, field: 'name', 'errors')}">
                        <g:textField name="name" maxlength="50" value="${systemInstance?.name}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="description"><g:message code="system.description.label" default="Description"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: systemInstance, field: 'description', 'errors')}">
                        <g:textArea name="description" cols="40" rows="5" value="${systemInstance?.description}"/>
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
