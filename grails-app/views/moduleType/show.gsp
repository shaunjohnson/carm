<%@ page import="carm.ModuleType" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'moduleType.label', default: 'ModuleType')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<div class="body">
    <g:header domain="${moduleTypeInstance}"/>
    
    <div class="nav">
        <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
    </div>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="dialog">
        <table class="details">
            <tbody>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="moduleType.description.label" default="Description" /></td>
                <td valign="top" class="value">${fieldValue(bean: moduleTypeInstance, field: "description")}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="moduleType.dateCreated.label" default="Date Created" /></td>
                <td valign="top" class="value"><g:formatDate date="${moduleTypeInstance?.dateCreated}" /></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="moduleType.lastUpdated.label" default="Last Updated" /></td>
                <td valign="top" class="value"><g:formatDate date="${moduleTypeInstance?.lastUpdated}" /></td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
            <td colspan="2">
                <div class="buttons">
                    <g:form>
                        <g:hiddenField name="id" value="${moduleTypeInstance?.id}" />
                        <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                        <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                    </g:form>
                </div>
            </td>
            </tr>
            </tfoot>
        </table>
    </div>
</div>
</body>
</html>
