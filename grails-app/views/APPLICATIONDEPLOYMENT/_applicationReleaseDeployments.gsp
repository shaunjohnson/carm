<div class="sectionHeader">
    <div class="text">
        <g:message code="allDeployments.label" default="All Deployments"/>
    </div>
</div>

<g:set var="applicationReleaseDeployments" value="${applicationDeploymentInstance.applicationRelease.deployments}"/>

<g:if test="${applicationReleaseDeployments?.size()}">
    <div class="list">
        <table style="width: 100%;">
            <thead>
            <tr>
                <th>
                    <g:message code="applicationDeployment.deploymentState.label" default="Deployment State"/>
                </th>
                <th>
                    <g:message code="applicationDeployment.sysEnvironment.label" default="Environment"/>
                </th>
                <th>
                    <g:message code="applicationDeployment.requestedDeploymentDate.label"
                               default="Requested Deployment Date"/>
                </th>
                <th>
                    <g:message code="applicationDeployment.completedDeploymentDate.label"
                               default="Completed Deployment Date"/>
                </th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${applicationReleaseDeployments?.sort { it.completedDeploymentDate }?.reverse()}"
                    var="deploymentInstance" status="i">
                <g:if test="${applicationDeploymentInstance == deploymentInstance}">
                    <tr class="highlight ${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td>
                            <carm:formatApplicationDeploymentState
                                    deploymentState="${deploymentInstance.deploymentState}"/>
                        </td>
                        <td>
                            ${deploymentInstance.sysEnvironment?.encodeAsHTML()}
                        </td>
                        <td>
                            <carm:formatDateOnly date="${deploymentInstance.requestedDeploymentDate}"/>
                        </td>
                        <td>
                            <carm:formatDateOnly date="${deploymentInstance.completedDeploymentDate}"/>
                        </td>
                    </tr>
                </g:if>
                <g:else>
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td>
                            <g:link controller="applicationDeployment" action="show" id="${deploymentInstance.id}">
                                <carm:formatApplicationDeploymentState
                                        deploymentState="${deploymentInstance.deploymentState}"/>
                            </g:link>
                        </td>
                        <td>
                            <g:link controller="systemEnvironment" action="show"
                                    id="${deploymentInstance.sysEnvironment.id}">
                                ${deploymentInstance.sysEnvironment?.encodeAsHTML()}
                            </g:link>
                        </td>
                        <td>
                            <carm:formatDateOnly date="${deploymentInstance.requestedDeploymentDate}"/>
                        </td>
                        <td>
                            <carm:formatDateOnly date="${deploymentInstance.completedDeploymentDate}"/>
                        </td>
                    </tr>
                </g:else>
            </g:each>
            </tbody>
        </table>
    </div>
</g:if>
<g:else>
    <p class="emphasis">
        <g:message code="applicationReleaseDoesNotHaveAnyOtherDeployments.message"
                   default="This application release does not have any other deployments."/>
    </p>
</g:else>