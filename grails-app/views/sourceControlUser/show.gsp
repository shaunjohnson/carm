<%@ page import="carm.SourceControlUser" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'sourceControlUser.label', default: 'SourceControlUser')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></span>
</div>
<div class="body">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="dialog">
        <table class="details">
            <tbody>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="sourceControlUser.name.label" default="Name"/></td>
                <td valign="top" class="value">${fieldValue(bean: sourceControlUserInstance, field: "name")}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="sourceControlUser.description.label" default="Description"/></td>
                <td valign="top" class="value">${fieldValue(bean: sourceControlUserInstance, field: "description")}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="sourceControlUser.sourceControlServer.label" default="Source Control Server"/></td>
                <td valign="top" class="value"><g:link controller="sourceControlServer" action="show" id="${sourceControlUserInstance?.sourceControlServer?.id}">${sourceControlUserInstance?.sourceControlServer?.encodeAsHTML()}</g:link></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="sourceControlUser.user.label" default="User"/></td>
                <td valign="top" class="value"><g:link controller="user" action="show" id="${sourceControlUserInstance?.user?.id}">${sourceControlUserInstance?.user?.encodeAsHTML()}</g:link></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="sourceControlUser.applicationRoles.label" default="Application Roles"/></td>
                <td valign="top" style="text-align: left;" class="value">
                    <ul>
                        <g:each in="${sourceControlUserInstance.applicationRoles}" var="a">
                            <li><g:link controller="applicationRole" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
                        </g:each>
                    </ul>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="sourceControlUser.dateCreated.label" default="Date Created"/></td>
                <td valign="top" class="value"><g:formatDate date="${sourceControlUserInstance?.dateCreated}"/></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="sourceControlUser.lastUpdated.label" default="Last Updated"/></td>
                <td valign="top" class="value"><g:formatDate date="${sourceControlUserInstance?.lastUpdated}"/></td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="2">
                    <div class="buttons">
                        <g:form>
                            <g:hiddenField name="id" value="${sourceControlUserInstance?.id}"/>
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
