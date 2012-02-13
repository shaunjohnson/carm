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
    <g:header domain="${sourceControlServerInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="dialog">
        <table id="sourceControlServerDetails" class="details">
            <tbody>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="sourceControlServer.description.label" default="Description"/>
                </td>
                <td valign="top" class="value">
                    ${fieldValue(bean: sourceControlServerInstance, field: "description")}
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="sourceControlServer.type.label" default="Type"/>
                </td>
                <td valign="top" class="value">
                    ${sourceControlServerInstance?.type?.encodeAsHTML()}
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="sourceControlServer.url.label" default="URL"/>
                </td>
                <td valign="top" class="value">
                    ${fieldValue(bean: sourceControlServerInstance, field: "url")}
                </td>
            </tr>

            <tr class="prop detailProp">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="sourceControlServer.dateCreated.label" default="Date Created"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${sourceControlServerInstance?.dateCreated}"/>
                </td>
            </tr>
            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="sourceControlServer.lastUpdated.label" default="Last Updated"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${sourceControlServerInstance?.lastUpdated}"/>
                </td>
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
        <g:showHideDetails sectionId="sourceControlServerDetails" entityName="${entityName}"/>
    </div>

    <table class="twoColumnLayout">
        <tbody>
        <tr>
            <td class="layoutColumnFirst">
                <g:render template="sourceControlRepositories" model="[sourceControlServerInstance: sourceControlServerInstance]"/>
            </td>
            <td class="layoutColumnLast">
                <g:render template="sourceControlUsers" model="[sourceControlServerInstance: sourceControlServerInstance]"/>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
