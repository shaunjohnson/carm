<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'systemDeploymentEnvironment.label', default: 'System Deployment Environment')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${systemDeploymentEnvironmentInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="dialog">
        <table id="systemDeploymentEnvironmentDetails" class="details">
            <tbody>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="systemDeploymentEnvironment.description.label" default="Description"/>
                </td>
                <td valign="top" class="value">
                    <div class="expander">
                        <carm:plainText value="${systemDeploymentEnvironmentInstance?.description}"/>
                    </div>
                </td>
            </tr>
            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="systemDeploymentEnvironment.system.label" default="System"/>
                </td>
                <td valign="top" class="value">
                    <g:link controller="system" action="show" id="${systemDeploymentEnvironmentInstance?.system?.id}">
                        ${systemDeploymentEnvironmentInstance?.system?.encodeAsHTML()}
                    </g:link>
                </td>
            </tr>

            <tr class="prop detailProp">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="systemDeploymentEnvironment.dateCreated.label" default="Date Created"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${systemDeploymentEnvironmentInstance?.dateCreated}"/>
                </td>
            </tr>
            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="systemDeploymentEnvironment.lastUpdated.label" default="Last Updated"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${systemDeploymentEnvironmentInstance?.lastUpdated}"/>
                </td>
            </tr>
            </tbody>
        </table>
        <carm:showHideDetails sectionId="systemDeploymentEnvironmentDetails" entityName="${entityName}">
            <sec:ifAllGranted roles="ROLE_ADMIN">
                <div class="buttons">
                    <span class="button">
                        <g:link class="edit" action="edit" id="${systemDeploymentEnvironmentInstance?.id}">
                            <g:message code="default.button.edit.label" default="Edit"/>
                        </g:link>
                    </span>
                    <carm:ifNotInUse domain="${systemDeploymentEnvironmentInstance}">
                        <span class="button">
                            <g:link class="delete" action="delete" id="${systemDeploymentEnvironmentInstance?.id}">
                                <g:message code="default.button.delete.label" default="Delete"/>
                            </g:link>
                        </span>
                    </carm:ifNotInUse>
                </div>
            </sec:ifAllGranted>
        </carm:showHideDetails>
    </div>

    <g:render template="systemDeploymentEnvironmentApplications"
              model="['systemDeploymentEnvironmentInstance': systemDeploymentEnvironmentInstance, 'applicationsGrouped': applicationsGrouped, 'latestDeployments': latestDeployments]"/>
</div>
</body>
</html>