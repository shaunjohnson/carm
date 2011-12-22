<%@ page import="carm.Module" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'module.label', default: 'Module')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>
<body>
<div class="body">
    <g:header domain="${moduleInstance}" pageName="${message(code: 'default.edit.label', args: [entityName])}" />

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <g:hasErrors bean="${moduleInstance}">
        <div class="errors">
            <g:renderErrors bean="${moduleInstance}" as="list"/>
        </div>
    </g:hasErrors>

    <g:form method="post">
        <g:hiddenField name="id" value="${moduleInstance?.id}"/>
        <g:hiddenField name="version" value="${moduleInstance?.version}"/>
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="application.id"><g:message code="module.application.label" default="Application"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: moduleInstance, field: 'application', 'errors')}">
                        ${moduleInstance?.application?.encodeAsHTML()}
                        <g:hiddenField name="application.id" value="${moduleInstance?.application?.id}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="name"><g:message code="module.name.label" default="Name"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: moduleInstance, field: 'name', 'errors')}">
                        <g:textField name="name" maxlength="50" value="${moduleInstance?.name}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="description"><g:message code="module.description.label" default="Description"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: moduleInstance, field: 'description', 'errors')}">
                        <g:textArea name="description" cols="40" rows="5" value="${moduleInstance?.description}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="type"><g:message code="module.type.label" default="Type"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: moduleInstance, field: 'type', 'errors')}">
                        <g:select name="type.id" from="${carm.ModuleType.list()}" optionKey="id" value="${moduleInstance?.type?.id}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="systemComponent.id"><g:message code="module.systemComponent.label" default="System Component"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: moduleInstance, field: 'systemComponent', 'errors')}">
                        <g:select name="systemComponent.id" from="${carm.SystemComponent.list()}" optionKey="id" value="${moduleInstance?.systemComponent?.id}" noSelection="['null': '']"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="deployInstructions"><g:message code="module.deployInstructions.label" default="Deploy Instructions"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: moduleInstance, field: 'deployInstructions', 'errors')}">
                        <g:textArea name="deployInstructions" cols="40" rows="5" value="${moduleInstance?.deployInstructions}"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><g:link class="show" action="show" id="${moduleInstance.id}"><g:message code="default.button.cancel.label" default="Cancel"/></g:link></span>
            <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}"/></span>
            <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
