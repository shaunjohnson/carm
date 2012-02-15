<%@ page import="carm.SystemEnvironment" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'systemEnvironment.label', default: 'System Environment')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${systemEnvironmentInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="dialog">
        <table id="systemEnvironmentDetails" class="details">
            <tbody>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="systemEnvironment.description.label" default="Description"/>
                </td>
                <td valign="top" class="value">
                    ${fieldValue(bean: systemEnvironmentInstance, field: "description")}
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="systemEnvironment.system.label" default="System"/>
                </td>
                <td valign="top" class="value">
                    <g:link controller="system" action="show" id="${systemEnvironmentInstance?.system?.id}">
                        ${systemEnvironmentInstance?.system?.encodeAsHTML()}
                    </g:link>
                </td>
            </tr>

            <tr class="prop detailProp">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="systemEnvironment.dateCreated.label" default="Date Created"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${systemEnvironmentInstance?.dateCreated}"/>
                </td>
            </tr>
            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="systemEnvironment.lastUpdated.label" default="Last Updated"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${systemEnvironmentInstance?.lastUpdated}"/>
                </td>
            </tr>
            </tbody>
            <sec:ifAllGranted roles="ROLE_ADMIN">
                <tfoot class="detailProp">
                <tr>
                    <td colspan="2">
                        <div class="buttons">
                            <span class="button">
                                <g:link class="edit" action="edit" id="${systemEnvironmentInstance?.id}">
                                    <g:message code="default.button.edit.label" default="Edit"/>
                                </g:link>
                            </span>
                            <span class="button">
                                <g:link class="delete" action="delete" id="${systemEnvironmentInstance?.id}">
                                    <g:message code="default.button.delete.label" default="Delete"/>
                                </g:link>
                            </span>
                        </div>
                    </td>
                </tr>
                </tfoot>
            </sec:ifAllGranted>
        </table>
        <carm:showHideDetails sectionId="systemEnvironmentDetails" entityName="${entityName}"/>
    </div>

    <g:render template="systemEnvironmentApplications"
              model="['systemEnvironmentInstance': systemEnvironmentInstance, 'applicationsGrouped': applicationsGrouped, 'latestDeployments': latestDeployments]"/>
</div>
</body>
</html>
