<div class="sectionHeader">
    <div class="text">
        <g:message code="summary.label" default="Summary"/>
    </div>
</div>

<table>
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
            <g:message code="applicationDeployment.deploymentEnvironment.label"
                       default="System Deployment Environment"/>
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
            <g:message code="applicationRelease.changeLog.label"
                       default="Change Log"/>
        </td>
        <td valign="top" class="value">
            ${applicationDeploymentInstance.applicationRelease.changeLog?.decodeHTML()}
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
    </tbody>
</table>