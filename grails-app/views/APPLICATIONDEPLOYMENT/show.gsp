<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'applicationDeployment.label', default: 'Application Deployment')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${applicationDeploymentInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="dialog">
        <table id="applicationDeploymentDetails" class="details">
            <tbody>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="applicationDeployment.applicationRelease.label" default="Application Release"/>
                </td>
                <td valign="top" class="value">
                    <g:link controller="applicationRelease" action="show"
                            id="${applicationDeploymentInstance?.applicationRelease?.id}">
                        ${message(code: 'pageHeader.applicationRelease.label', args: [
                                applicationDeploymentInstance?.applicationRelease?.application?.name,
                                applicationDeploymentInstance?.applicationRelease?.releaseNumber])?.encodeAsHTML()}
                    </g:link>
                </td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="applicationDeployment.deploymentEnvironment.label" default="System Deployment Environment"/>
                </td>
                <td valign="top" class="value">
                    <g:link controller="systemDeploymentEnvironment" action="show"
                            id="${applicationDeploymentInstance?.deploymentEnvironment?.id}">
                        ${applicationDeploymentInstance?.deploymentEnvironment?.encodeAsHTML()}
                    </g:link>
                </td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="applicationDeployment.deploymentInstructions.label"
                               default="Deployment Instructions"/>
                </td>
                <td valign="top" class="value">
                    ${fieldValue(bean: applicationDeploymentInstance, field: "deploymentInstructions").decodeHTML()}
                </td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="applicationDeployment.requestedDeploymentDate.label"
                               default="Requested Deployment Date"/>
                </td>
                <td valign="top" class="value">
                    <carm:formatDateOnly date="${applicationDeploymentInstance?.requestedDeploymentDate}"/>
                </td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="applicationDeployment.completedDeploymentDate.label"
                               default="Completed Deployment Date"/>
                </td>
                <td valign="top" class="value">
                    <carm:formatDateOnly date="${applicationDeploymentInstance?.completedDeploymentDate}"/>
                </td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="applicationDeployment.deploymentState.label" default="Deployment State"/>
                </td>
                <td valign="top" class="value">
                    <carm:formatApplicationDeploymentState
                            deploymentState="${applicationDeploymentInstance.deploymentState}"/>
                </td>
            </tr>

            <tr class="prop detailProp">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="applicationDeployment.dateCreated.label" default="Date Created"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${applicationDeploymentInstance?.dateCreated}"/>
                </td>
            </tr>

            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="applicationDeployment.lastUpdated.label" default="Last Updated"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${applicationDeploymentInstance?.lastUpdated}"/>
                </td>
            </tr>
            </tbody>
        </table>
        <carm:showHideDetails sectionId="applicationDeploymentDetails" entityName="${entityName}">
            <carmsec:isProjectOwner applicationDeployment="${applicationDeploymentInstance}">
                <div class="buttons">
                    <span class="button">
                        <g:link class="edit" action="edit" id="${applicationDeploymentInstance?.id}">
                            <g:message code="default.button.edit.label" default="Edit"/>
                        </g:link>
                    </span>
                    <span class="button">
                        <g:link class="delete" action="delete" id="${applicationDeploymentInstance?.id}">
                            <g:message code="default.button.delete.label" default="Delete"/>
                        </g:link>
                    </span>
                </div>
            </carmsec:isProjectOwner>
        </carm:showHideDetails>
    </div>

    <table class="twoColumnLayout">
        <tbody>
        <tr>
            <td class="layoutColumnFirst">
                <g:render template="applicationDeploymentModules"
                          model="[applicationDeploymentInstance: applicationDeploymentInstance]"/>
            </td>
            <td class="layoutColumnLast">
                <g:render template="applicationReleaseDeployments"
                          model="[applicationDeploymentInstance: applicationDeploymentInstance]"/>

                <div>&nbsp;</div>

                <g:render template="/common/activity"
                          model="[activityList: activityList, listActivityAction: 'listActivity', domainId: applicationDeploymentInstance.id]"/>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
