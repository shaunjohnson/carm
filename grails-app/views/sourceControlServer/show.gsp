<%@ page import="carm.SourceControlServer" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'sourceControlServer.label', default: 'SourceControlServer')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>
<body>
<div class="body">
    <g:header domain="${sourceControlServerInstance}" />

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="dialog">
        <table class="details">
            <tbody>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="sourceControlServer.description.label" default="Description"/></td>
                <td valign="top" class="value">${fieldValue(bean: sourceControlServerInstance, field: "description")}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="sourceControlServer.type.label" default="Type"/></td>
                <td valign="top" class="value">${sourceControlServerInstance?.type?.encodeAsHTML()}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="sourceControlServer.url.label" default="Url"/></td>
                <td valign="top" class="value">${fieldValue(bean: sourceControlServerInstance, field: "url")}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="sourceControlServer.dateCreated.label" default="Date Created"/></td>
                <td valign="top" class="value"><g:formatDate date="${sourceControlServerInstance?.dateCreated}"/></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="sourceControlServer.lastUpdated.label" default="Last Updated"/></td>
                <td valign="top" class="value"><g:formatDate date="${sourceControlServerInstance?.lastUpdated}"/></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="sourceControlServer.repositories.label" default="Repositories"/></td>
                <td valign="top" style="text-align: left;" class="value">
                    <ul>
                        <g:each in="${sourceControlServerInstance.repositories.sort { it.name }}" var="r">
                            <li><g:link controller="sourceControlRepository" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
                        </g:each>
                    </ul>
                    <div class="nav">
                        <span class="menuButton"><g:link class="create" controller="sourceControlRepository" action="create" params="['server.id': sourceControlServerInstance?.id]">Add Repository</g:link></span>
                    </div>
                </td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="2">
                    <div class="buttons">
                        <span class="button">
                            <g:link class="edit" action="edit" id="${sourceControlServerInstance?.id}">
                                <g:message code="default.button.edit.label" default="Edit"/>
                            </g:link>
                        </span>
                        <span class="button">
                            <g:link class="delete" action="delete" id="${sourceControlServerInstance?.id}"
                                    onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
                                <g:message code="default.button.delete.label" default="Delete"/>
                            </g:link>
                        </span>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</div>
</body>
</html>
