<%@ page import="net.lmxm.carm.SystemEnvironment" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'systemEnvironment.label', default: 'SystemEnvironment')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></span>
</div>
<div class="body">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
            <tbody>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="systemEnvironment.id.label" default="Id"/></td>

                <td valign="top" class="value">${fieldValue(bean: systemEnvironmentInstance, field: "id")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="systemEnvironment.name.label" default="Name"/></td>

                <td valign="top" class="value">${fieldValue(bean: systemEnvironmentInstance, field: "name")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="systemEnvironment.description.label" default="Description"/></td>

                <td valign="top" class="value">${fieldValue(bean: systemEnvironmentInstance, field: "description")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="systemEnvironment.system.label" default="System"/></td>

                <td valign="top" class="value"><g:link controller="system" action="show" id="${systemEnvironmentInstance?.system?.id}">${systemEnvironmentInstance?.system?.encodeAsHTML()}</g:link></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="systemEnvironment.dateCreated.label" default="Date Created"/></td>

                <td valign="top" class="value"><g:formatDate date="${systemEnvironmentInstance?.dateCreated}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="systemEnvironment.lastUpdated.label" default="Last Updated"/></td>

                <td valign="top" class="value"><g:formatDate date="${systemEnvironmentInstance?.lastUpdated}"/></td>

            </tr>

            </tbody>
        </table>
    </div>
    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${systemEnvironmentInstance?.id}"/>
            <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}"/></span>
            <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
        </g:form>
    </div>
</div>
</body>
</html>
