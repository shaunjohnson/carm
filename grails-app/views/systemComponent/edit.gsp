<%@ page import="carm.SystemComponent" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'systemComponent.label', default: 'SystemComponent')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>
<body>
<div class="body">
    <g:header domain="${systemComponentInstance}" />

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${systemComponentInstance}">
        <div class="errors">
            <g:renderErrors bean="${systemComponentInstance}" as="list"/>
        </div>
    </g:hasErrors>

    <g:form action="update" method="post">
        <g:hiddenField name="id" value="${systemComponentInstance?.id}"/>
        <g:hiddenField name="version" value="${systemComponentInstance?.version}"/>
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="system"><g:message code="systemComponent.system.label" default="System"/></label>
                    </td>
                    <td valign="top" class="value">
                        ${systemComponentInstance?.system?.encodeAsHTML()}
                        <g:hiddenField name="system.id" value="${systemComponentInstance?.system?.id}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="name"><g:message code="systemComponent.name.label" default="Name"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: systemComponentInstance, field: 'name', 'errors')}">
                        <g:textField name="name" maxlength="50" value="${systemComponentInstance?.name}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="description"><g:message code="systemComponent.description.label" default="Description"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: systemComponentInstance, field: 'description', 'errors')}">
                        <g:textArea name="description" cols="40" rows="5" value="${systemComponentInstance?.description}"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button">
                <g:link class="show" action="show" id="${systemComponentInstance.id}">
                    <g:message code="default.button.cancel.label" default="Cancel"/>
                </g:link>
            </span>
            <span class="button">
                <g:submitButton name="save" class="save" value="${message(code: 'default.button.update.label', default: 'Update')}"/>
            </span>
        </div>
    </g:form>
</div>
</body>
</html>
