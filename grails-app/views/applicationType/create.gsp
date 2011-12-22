<%@ page import="carm.ApplicationType" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'applicationType.label', default: 'ApplicationType')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>
<body>
<div class="body">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>

    <div class="nav">
        <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></span>
    </div>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${applicationTypeInstance}">
        <div class="errors">
            <g:renderErrors bean="${applicationTypeInstance}" as="list"/>
        </div>
    </g:hasErrors>

    <g:form action="save">
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="name"><g:message code="applicationType.name.label" default="Name"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: applicationTypeInstance, field: 'name', 'errors')}">
                        <g:textField name="name" maxlength="50" value="${applicationTypeInstance?.name}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="description"><g:message code="applicationType.description.label" default="Description"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: applicationTypeInstance, field: 'description', 'errors')}">
                        <g:textArea name="description" cols="40" rows="5" value="${applicationTypeInstance?.description}"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
