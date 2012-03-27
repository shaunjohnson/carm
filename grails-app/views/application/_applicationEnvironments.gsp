<div class="sectionHeader">
    <div class="text">
        <g:message code="environments.label" default="Environments"/>
    </div>

    <div class="actions">
        <g:link action="showFullHistory" id="${applicationInstance.id}"
                params="[sort: grailsApplication.config.ui.application.showFullHistorySort,
                        order: grailsApplication.config.ui.application.showFullHistoryOrder]">
            <g:message code="showFullHistory.label" default="Show Full History"/>
        </g:link>
    </div>
</div>

<g:if test="${applicationInstance?.sysEnvironment}">
    <table style="width: 100%;">
        <tbody>
        <g:each in="${applicationInstance.sysEnvironment.environments}" var="deploymentEnvironment">
            <g:set var="deployment" value="${deployments[deploymentEnvironment].lastDeployment}"/>

            <tr>
                <td>
                    <g:link controller="systemDeploymentEnvironment" action="show" id="${deploymentEnvironment.id}">
                        ${deploymentEnvironment.name.encodeAsHTML()}
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
                        <carm:formatDateOnly date="${deployment.completedDeploymentDate}"/>
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
        <g:message code="applicationNotConfiguredForEnvironment.message"
                   default="This application is not configured to work with an environment."/>
    </p>
</g:else>