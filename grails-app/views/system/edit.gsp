<%@ page import="carm.System" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'system.label', default: 'System')}"/>
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
    <g:hasErrors bean="${systemInstance}">
        <div class="errors">
            <g:renderErrors bean="${systemInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form method="post">
        <g:hiddenField name="id" value="${systemInstance?.id}"/>
        <g:hiddenField name="version" value="${systemInstance?.version}"/>
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
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="components"><g:message code="system.components.label" default="Components"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: systemInstance, field: 'components', 'errors')}">
                        <ul>
                            <g:each in="${systemInstance?.components?}" var="c">
                                <li><g:link controller="systemComponent" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
                            </g:each>
                        </ul>
                        <g:link controller="systemComponent" action="create" params="['system.id': systemInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'systemComponent.label', default: 'SystemComponent')])}</g:link>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="environments"><g:message code="system.environments.label" default="Environments"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: systemInstance, field: 'environments', 'errors')}">
                        <ul>
                            <g:each in="${systemInstance?.environments?}" var="e">
                                <li><g:link controller="systemEnvironment" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
                            </g:each>
                        </ul>
                        <g:link controller="systemEnvironment" action="create" params="['system.id': systemInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'systemEnvironment.label', default: 'SystemEnvironment')])}</g:link>
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
