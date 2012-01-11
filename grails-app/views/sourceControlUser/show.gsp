<%@ page import="carm.SourceControlUser" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'sourceControlUser.label', default: 'SourceControlUser')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>
<body>
<div class="body">
    <g:header domain="${sourceControlUserInstance}" />

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="dialog">
        <table id="sourceControlUserDetails" class="details">
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
                <td valign="top" class="name"><g:message code="sourceControlUser.server.label" default="Source Control Server"/></td>
                <td valign="top" class="value">
                    <g:link controller="sourceControlServer" action="show" id="${sourceControlUserInstance?.server?.id}">
                        ${sourceControlUserInstance?.server?.encodeAsHTML()}
                    </g:link>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="sourceControlUser.user.label" default="User"/></td>
                <td valign="top" class="value">
                    <g:link controller="user" action="show" id="${sourceControlUserInstance?.user?.id}">
                        ${sourceControlUserInstance?.user?.encodeAsHTML()}
                    </g:link>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="sourceControlUser.applicationRoles.label" default="Application Roles"/></td>
                <td valign="top" style="text-align: left;" class="value">
                    <ul>
                        <g:each in="${sourceControlUserInstance.applicationRoles.sort { it.application.name }}" var="a">
                            <li>
                                <g:link controller="applicationRole" action="show" id="${a.id}">${a?.role?.encodeAsHTML()}</g:link>

                                <g:link controller="application" action="show" id="${a?.application?.id}">
                                    (${a?.application?.name?.encodeAsHTML()})
                                </g:link>
                            </li>
                        </g:each>
                    </ul>
                </td>
            </tr>

            <tr class="prop detailProp">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop detailProp">
                <td valign="top" class="name"><g:message code="sourceControlUser.dateCreated.label" default="Date Created"/></td>
                <td valign="top" class="value"><g:formatDate date="${sourceControlUserInstance?.dateCreated}"/></td>
            </tr>
            <tr class="prop detailProp">
                <td valign="top" class="name"><g:message code="sourceControlUser.lastUpdated.label" default="Last Updated"/></td>
                <td valign="top" class="value"><g:formatDate date="${sourceControlUserInstance?.lastUpdated}"/></td>
            </tr>
            </tbody>
            <sec:ifAllGranted roles="ROLE_ADMIN">
                <tfoot>
                <tr>
                    <td colspan="2">
                        <div class="buttons">
                            <span class="button">
                                <g:link class="edit" action="edit" id="${sourceControlUserInstance?.id}">
                                    <g:message code="default.button.edit.label" default="Edit"/>
                                </g:link>
                            </span>
                            <span class="button">
                                <g:link class="delete" action="delete" id="${sourceControlUserInstance?.id}"
                                        onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
                                    <g:message code="default.button.delete.label" default="Delete"/>
                                </g:link>
                            </span>
                        </div>
                    </td>
                </tr>
                </tfoot>
            </sec:ifAllGranted>
        </table>
        <g:showHideDetails sectionId="sourceControlUserDetails" entityName="$entityName"/>
    </div>
</div>
</body>
</html>
