<%@ page import="carm.ApplicationType" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'applicationType.label', default: 'ApplicationType')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>
<body>
<div class="body">
    <g:header domain="${applicationTypeInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="dialog">
        <table class="details">
            <tbody>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="applicationType.description.label" default="Description"/></td>
                <td valign="top" class="value">${fieldValue(bean: applicationTypeInstance, field: "description")}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="applicationType.dateCreated.label" default="Date Created"/></td>
                <td valign="top" class="value"><g:formatDate date="${applicationTypeInstance?.dateCreated}"/></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="applicationType.lastUpdated.label" default="Last Updated"/></td>
                <td valign="top" class="value"><g:formatDate date="${applicationTypeInstance?.lastUpdated}"/></td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="2">
                    <div class="buttons">
                        <g:form>
                            <g:hiddenField name="id" value="${applicationTypeInstance?.id}"/>
                            <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}"/></span>
                            <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
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
