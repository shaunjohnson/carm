<%@ page import="carm.Application; carm.system.System" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'system.label', default: 'System')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${systemInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="dialog">
        <table id="systemDetails" class="details">
            <tbody>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="system.description.label" default="Description"/>
                </td>
                <td valign="top" class="value">
                    <div class="expander">
                        <carm:plainText value="${systemInstance?.description}"/>
                    </div>
                </td>
            </tr>

            <tr class="prop detailProp">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="system.dateCreated.label" default="Date Created"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${systemInstance?.dateCreated}"/>
                </td>
            </tr>
            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="system.lastUpdated.label" default="Last Updated"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${systemInstance?.lastUpdated}"/>
                </td>
            </tr>
            </tbody>
        </table>
        <carm:showHideDetails sectionId="systemDetails" entityName="${entityName}">
            <sec:ifAllGranted roles="ROLE_ADMIN">
                <div class="buttons">
                    <span class="button">
                        <g:link class="edit" action="edit" id="${systemInstance?.id}">
                            <g:message code="default.button.edit.label" default="Edit"/>
                        </g:link>
                    </span>
                    <carm:ifNotInUse domain="${systemInstance}">
                        <span class="button">
                            <g:link class="delete" action="delete" id="${systemInstance?.id}">
                                <g:message code="default.button.delete.label" default="Delete"/>
                            </g:link>
                        </span>
                    </carm:ifNotInUse>
                </div>
            </sec:ifAllGranted>
        </carm:showHideDetails>
    </div>

    <table class="twoColumnLayout">
        <tbody>
        <tr>
            <td class="layoutColumnFirst">
                <g:render template="systemComponents" model="['systemInstance': systemInstance]"/>
            </td>
            <td class="layoutColumnLast">
                <g:render template="systemEnvironments" model="['systemInstance': systemInstance]"/>
            </td>
        </tr>
        </tbody>
    </table>

    <g:render template="systemApplications"
              model="['systemInstance': systemInstance, 'applicationsGrouped': applicationsGrouped, 'latestDeployments': latestDeployments]"/>
</div>
</body>
</html>
