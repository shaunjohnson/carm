<%@ page import="carm.SourceControlUser" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'sourceControlUser.label', default: 'SourceControlUser')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>
<body>
<div class="body">
    <g:header domain="${sourceControlUserInstance}" />

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${sourceControlUserInstance}">
        <div class="errors">
            <g:renderErrors bean="${sourceControlUserInstance}" as="list"/>
        </div>
    </g:hasErrors>

    <g:form method="post">
        <g:hiddenField name="id" value="${sourceControlUserInstance?.id}"/>
        <g:hiddenField name="version" value="${sourceControlUserInstance?.version}"/>
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="name"><g:message code="sourceControlUser.name.label" default="Name"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: sourceControlUserInstance, field: 'name', 'errors')}">
                        <g:textField name="name" maxlength="50" value="${sourceControlUserInstance?.name}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="description"><g:message code="sourceControlUser.description.label" default="Description"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: sourceControlUserInstance, field: 'description', 'errors')}">
                        <g:textArea name="description" cols="40" rows="5" value="${sourceControlUserInstance?.description}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="sourceControlServer"><g:message code="sourceControlUser.sourceControlServer.label" default="Source Control Server"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: sourceControlUserInstance, field: 'sourceControlServer', 'errors')}">
                        <g:select name="sourceControlServer.id" from="${carm.SourceControlServer.list()}" optionKey="id" value="${sourceControlUserInstance?.sourceControlServer?.id}" noSelection="['null': '']"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="user"><g:message code="sourceControlUser.user.label" default="User"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: sourceControlUserInstance, field: 'user', 'errors')}">
                        <g:select name="user.id" from="${carm.security.User.list()}" optionKey="id" value="${sourceControlUserInstance?.user?.id}" noSelection="['null': '']"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="applicationRoles"><g:message code="sourceControlUser.applicationRoles.label" default="Application Roles"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: sourceControlUserInstance, field: 'applicationRoles', 'errors')}">
                        <ul>
                            <g:each in="${sourceControlUserInstance?.applicationRoles?}" var="a">
                                <li><g:link controller="applicationRole" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
                            </g:each>
                        </ul>
                        <g:link controller="applicationRole" action="create" params="['sourceControlUser.id': sourceControlUserInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'applicationRole.label', default: 'ApplicationRole')])}</g:link>
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
