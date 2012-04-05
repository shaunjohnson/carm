<%@ page import="carm.deployment.ApplicationDeploymentState" %>
<div class="sectionHeader">
    <div class="text">
        <g:message code="summary.label" default="Summary"/>
    </div>
</div>

<table>
    <tbody>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="deployment.label" default="Deployment"/>
        </td>
        <td valign="top" class="value">
            <g:link controller="project" action="show"
                    id="${applicationDeploymentInstance.applicationRelease.application.project.id}">
                ${applicationDeploymentInstance.applicationRelease.application.project.name.encodeAsHTML()}
            </g:link>
            -
            <g:link controller="application" action="show"
                    id="${applicationDeploymentInstance.applicationRelease.application.id}">
                ${applicationDeploymentInstance.applicationRelease.application.name.encodeAsHTML()}
            </g:link>
            -
            <g:link controller="applicationRelease" action="show"
                    id="${applicationDeploymentInstance.applicationRelease.id}">
                ${applicationDeploymentInstance.applicationRelease.releaseNumber.encodeAsHTML()}
            </g:link>
            (<g:link controller="systemDeploymentEnvironment" action="show"
                     id="${applicationDeploymentInstance.deploymentEnvironment.id}">${applicationDeploymentInstance.deploymentEnvironment.name.encodeAsHTML()}</g:link>)
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
    
    <g:if test="${applicationDeploymentInstance.deploymentState == ApplicationDeploymentState.COMPLETED}">
        <tr class="prop">
            <td valign="top" class="name">
                <g:message code="applicationDeployment.completedDeploymentDate.label"
                           default="Completed Deployment Date"/>
            </td>
            <td valign="top" class="value">
                <carm:formatDateOnly date="${applicationDeploymentInstance?.completedDeploymentDate}"/>
            </td>
        </tr>
    </g:if>
    <g:else>
        <tr class="prop">
            <td valign="top" class="name">
                <g:message code="applicationDeployment.requestedDeploymentDate.label"
                           default="Requested Deployment Date"/>
            </td>
            <td valign="top" class="value">
                <carm:formatDateOnly date="${applicationDeploymentInstance?.requestedDeploymentDate}"/>
            </td>
        </tr>
    </g:else>

    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="applicationRelease.releaseNumber.label" default="Release Number"/>
        </td>
        <td valign="top" class="value">
            <g:link controller="applicationRelease" action="show"
                    id="${applicationDeploymentInstance.applicationRelease.id}">
                ${applicationDeploymentInstance.applicationRelease.releaseNumber.encodeAsHTML()}
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
    </tbody>
</table>