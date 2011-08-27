<%@ page import="net.lmxm.carm.SystemEnvironment" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'systemEnvironment.label', default: 'SystemEnvironment')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></span>
</div>
<div class="body">
    <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${systemEnvironmentInstance}">
        <div class="errors">
            <g:renderErrors bean="${systemEnvironmentInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form method="post">
        <g:hiddenField name="id" value="${systemEnvironmentInstance?.id}"/>
        <g:hiddenField name="version" value="${systemEnvironmentInstance?.version}"/>
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="name"><g:message code="systemEnvironment.name.label" default="Name"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: systemEnvironmentInstance, field: 'name', 'errors')}">
                        <g:textField name="name" maxlength="50" value="${systemEnvironmentInstance?.name}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="description"><g:message code="systemEnvironment.description.label" default="Description"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: systemEnvironmentInstance, field: 'description', 'errors')}">
                        <g:textArea name="description" cols="40" rows="5" value="${systemEnvironmentInstance?.description}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="system"><g:message code="systemEnvironment.system.label" default="System"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: systemEnvironmentInstance, field: 'system', 'errors')}">
                        <g:select name="system.id" from="${net.lmxm.carm.System.list()}" optionKey="id" value="${systemEnvironmentInstance?.system?.id}"/>
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
