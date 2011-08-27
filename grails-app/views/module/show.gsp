<%@ page import="net.lmxm.carm.Module" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'module.label', default: 'Module')}"/>
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
                <td valign="top" class="name"><g:message code="module.id.label" default="Id"/></td>

                <td valign="top" class="value">${fieldValue(bean: moduleInstance, field: "id")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="module.name.label" default="Name"/></td>

                <td valign="top" class="value">${fieldValue(bean: moduleInstance, field: "name")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="module.description.label" default="Description"/></td>

                <td valign="top" class="value">${fieldValue(bean: moduleInstance, field: "description")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="module.type.label" default="Type"/></td>

                <td valign="top" class="value"><g:link controller="moduleType" action="show" id="${moduleInstance?.type?.id}">${moduleInstance?.type?.encodeAsHTML()}</g:link></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="module.application.label" default="Application"/></td>

                <td valign="top" class="value"><g:link controller="application" action="show" id="${moduleInstance?.application?.id}">${moduleInstance?.application?.encodeAsHTML()}</g:link></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="module.systemComponent.label" default="System Component"/></td>

                <td valign="top" class="value"><g:link controller="systemComponent" action="show" id="${moduleInstance?.systemComponent?.id}">${moduleInstance?.systemComponent?.encodeAsHTML()}</g:link></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="module.dateCreated.label" default="Date Created"/></td>

                <td valign="top" class="value"><g:formatDate date="${moduleInstance?.dateCreated}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="module.lastUpdated.label" default="Last Updated"/></td>

                <td valign="top" class="value"><g:formatDate date="${moduleInstance?.lastUpdated}"/></td>

            </tr>

            </tbody>
        </table>
    </div>
    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${moduleInstance?.id}"/>
            <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}"/></span>
            <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
        </g:form>
    </div>
</div>
</body>
</html>
