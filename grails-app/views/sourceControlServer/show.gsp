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
        <table id="sourceControlServerDetails" class="details">
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

            <tr class="prop detailProp">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop detailProp">
                <td valign="top" class="name"><g:message code="sourceControlServer.dateCreated.label" default="Date Created"/></td>
                <td valign="top" class="value"><g:formatDate date="${sourceControlServerInstance?.dateCreated}"/></td>
            </tr>
            <tr class="prop detailProp">
                <td valign="top" class="name"><g:message code="sourceControlServer.lastUpdated.label" default="Last Updated"/></td>
                <td valign="top" class="value"><g:formatDate date="${sourceControlServerInstance?.lastUpdated}"/></td>
            </tr>
            </tbody>
            <sec:ifAllGranted roles="ROLE_ADMIN">
                <tfoot class="detailProp">
                <tr>
                    <td colspan="2">
                        <div class="buttons">
                            <span class="button">
                                <g:link class="edit" action="edit" id="${sourceControlServerInstance?.id}">
                                    <g:message code="default.button.edit.label" default="Edit"/>
                                </g:link>
                            </span>
                            <g:ifNotInUse domain="${sourceControlServerInstance}">
                                <span class="button">
                                    <g:link class="delete" action="delete" id="${sourceControlServerInstance?.id}">
                                        <g:message code="default.button.delete.label" default="Delete"/>
                                    </g:link>
                                </span>
                            </g:ifNotInUse>
                        </div>
                    </td>
                </tr>
                </tfoot>
            </sec:ifAllGranted>
        </table>
        <g:showHideDetails sectionId="sourceControlServerDetails" entityName="$entityName"/>
    </div>


    <h2><g:message code="sourceControlServer.repositories.label" default="Repositories"/></h2>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <div class="nav">
            <span class="menuButton">
                <g:link class="create" controller="sourceControlRepository" action="create" params="['server.id': sourceControlServerInstance?.id]">
                    Add Repository
                </g:link>
            </span>
        </div>
    </sec:ifAllGranted>

    <g:if test="${sourceControlServerInstance.repositories.size()}">
        <ul>
            <g:each in="${sourceControlServerInstance.repositories.sort { it.name }}" var="r">
                <li><g:link controller="sourceControlRepository" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
            </g:each>
        </ul>
    </g:if>


    <h2>Users</h2>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <div class="nav">
            <span class="menuButton">
                <g:link class="create" controller="sourceControlUser" action="create"
                        params="['server.id': sourceControlServerInstance?.id]">Add User</g:link>
            </span>
        </div>
    </sec:ifAllGranted>

    <g:if test="${sourceControlServerInstance?.users?.size()}">
        <ul>
        <g:each in="${sourceControlServerInstance.users.sort { it.name }}" var="user">
            <li><g:link controller="sourceControlUser" action="show" id="${user.id}">${user?.name?.encodeAsHTML()}</g:link></li>
        </g:each>
        </ul>
    </g:if>
    <g:else>
        <p>
            This project does not have any users.
        </p>
    </g:else>
</div>
</body>
</html>
