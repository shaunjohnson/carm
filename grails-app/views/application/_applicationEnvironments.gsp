<%@ page import="carm.ApplicationDeployment" %>
<h2 class="sectionHeader">
    <g:message code="environments.label" default="Environments"/>
    <span class="actions">
        <g:link action="showFullHistory">
            <g:message code="showFullHistory.label" default="Show Full History"/>
        </g:link>
    </span>
</h2>

<g:if test="${applicationInstance?.system?.environments?.size()}">
<table style="width: 100%;">
    <tbody>
    <g:each in="${applicationInstance.system.environments}" var="environment">
        <g:set var="deployment" value="${deployments[environment].lastDeployment}"/>

        <tr>
            <td>
                <g:link controller="systemEnvironment" action="show" id="${environment.id}">
                    ${environment.name.encodeAsHTML()}
                </g:link>
            </td>
            <td>
                <g:if test="${deployment}">
                    <g:link controller="applicationRelease" action="show" id="${deployment.applicationRelease.id}">
                        ${deployment.applicationRelease.releaseNumber.encodeAsHTML()}
                    </g:link>
                </g:if>
            </td>
            <td>
                <g:if test="${deployment}">
                    <g:formatDateOnly date="${deployment.completedDeploymentDate}"/>
                </g:if>
            </td>
            <td>
                <g:if test="${deployment}">
                    <g:link controller="applicationDeployment" action="show" id="${deployment.id}">
                        <g:message code="viewDeployment.label" default="View Deployment"/>
                    </g:link>
                </g:if>
            </td>
        </tr>
    </g:each>
    </tbody>
</table>
</g:if>
<g:else>
    <p class="emphasis">
        <g:message code="applicationNotConfiguredForSystem.message"
                   default="This application is not configured to work with a system."/>
    </p>
</g:else>