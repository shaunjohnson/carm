<%@ page import="net.lmxm.carm.Module" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'module.label', default: 'Module')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></span>
</div>
<div class="body">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${moduleInstance}">
        <div class="errors">
            <g:renderErrors bean="${moduleInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="save">
        <div class="dialog">
            <table>
                <tbody>

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
                        <g:select name="type.id" from="${net.lmxm.carm.ModuleType.list()}" optionKey="id" value="${moduleInstance?.type?.id}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="application"><g:message code="module.application.label" default="Application"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: moduleInstance, field: 'application', 'errors')}">
                        <g:select name="application.id" from="${net.lmxm.carm.Application.list()}" optionKey="id" value="${moduleInstance?.application?.id}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="systemComponent"><g:message code="module.systemComponent.label" default="System Component"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: moduleInstance, field: 'systemComponent', 'errors')}">
                        <g:select name="systemComponent.id" from="${net.lmxm.carm.SystemComponent.list()}" optionKey="id" value="${moduleInstance?.systemComponent?.id}" noSelection="['null': '']"/>
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