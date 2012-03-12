<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'sourceControlUser.label', default: 'Source Control User')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${sourceControlUserInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="dialog">
        <table id="sourceControlUserDetails" class="details">
            <tbody>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="sourceControlUser.name.label" default="Name"/>
                </td>
                <td valign="top" class="value">
                    ${fieldValue(bean: sourceControlUserInstance, field: "name")}
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="sourceControlUser.description.label" default="Description"/>
                </td>
                <td valign="top" class="value">
                    <div class="expander">
                        <carm:plainText value="${sourceControlUserInstance?.description}"/>
                    </div>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="sourceControlUser.server.label" default="Source Control Server"/>
                </td>
                <td valign="top" class="value">
                    <g:link controller="sourceControlServer" action="show"
                            id="${sourceControlUserInstance?.server?.id}">
                        ${sourceControlUserInstance?.server?.encodeAsHTML()}
                    </g:link>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="sourceControlUser.user.label" default="User"/>
                </td>
                <td valign="top" class="value">
                    <g:link controller="user" action="show" id="${sourceControlUserInstance?.user?.id}">
                        ${sourceControlUserInstance?.user?.encodeAsHTML()}
                    </g:link>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="sourceControlUser.applicationRoles.label" default="Application Roles"/>
                </td>
                <td valign="top" style="text-align: left;" class="value">
                    <g:if test="${sourceControlUserInstance.applicationRoles.size()}">
                        <ul>
                            <g:each in="${sourceControlUserInstance.applicationRoles.sort { it.application.name }}"
                                    var="applicationRole">
                                <li>
                                    <g:link controller="applicationRole" action="show" id="${applicationRole.id}">
                                        ${applicationRole?.role?.encodeAsHTML()}
                                    </g:link>
                                    <g:link controller="application" action="show"
                                            id="${applicationRole?.application?.id}">
                                        (${applicationRole?.application?.encodeAsHTML()})
                                    </g:link>
                                </li>
                            </g:each>
                        </ul>
                    </g:if>
                    <g:else>
                        <p class="emphasis">
                            <g:message code="userDoesNotHaveAnyRoles.message"
                                       default="This user does not have any roles."/>
                        </p>
                    </g:else>
                </td>
            </tr>

            <tr class="prop detailProp">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="sourceControlUser.dateCreated.label" default="Date Created"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${sourceControlUserInstance?.dateCreated}"/>
                </td>
            </tr>
            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="sourceControlUser.lastUpdated.label" default="Last Updated"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${sourceControlUserInstance?.lastUpdated}"/>
                </td>
            </tr>
            </tbody>
        </table>
        <carm:showHideDetails sectionId="sourceControlUserDetails" entityName="${entityName}">
            <sec:ifAllGranted roles="ROLE_ADMIN">
                <div class="buttons">
                    <span class="button">
                        <g:link class="edit" action="edit" id="${sourceControlUserInstance?.id}">
                            <g:message code="default.button.edit.label" default="Edit"/>
                        </g:link>
                    </span>
                    <span class="button">
                        <g:link class="delete" action="delete" id="${sourceControlUserInstance?.id}">
                            <g:message code="default.button.delete.label" default="Delete"/>
                        </g:link>
                    </span>
                </div>
            </sec:ifAllGranted>
        </carm:showHideDetails>
    </div>
</div>
</body>
</html>
