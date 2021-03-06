<div class="sectionHeader">
    <div class="text">
        <g:message code="allDeployments.label" default="All Deployments"/>
    </div>
</div>

<div class="section-content">
    <g:if test="${applicationDeploymentList.size()}">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>
                    <g:message code="applicationDeployment.deploymentState.label" default="Deployment State"/>
                </th>
                <th>
                    <g:message code="applicationDeployment.deploymentEnvironment.label" default="Environment"/>
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
            <g:each in="${applicationDeploymentList}"
                    var="deploymentInstance" status="i">
                <g:if test="${applicationDeploymentInstance.id == deploymentInstance.id}">
                    <tr class="highlight ${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td class="highlight">
                            <carm:formatApplicationDeploymentState
                                    deploymentState="${deploymentInstance.deploymentState}"/>
                        </td>
                        <td class="highlight">
                            ${deploymentInstance.deploymentEnvironment?.encodeAsHTML()}
                        </td>
                        <td class="highlight">
                            <carm:formatDateOnly date="${deploymentInstance.requestedDeploymentDate}"/>
                        </td>
                        <td class="highlight">
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
                            <g:link controller="systemDeploymentEnvironment" action="show"
                                    id="${deploymentInstance.deploymentEnvironment.id}">
                                ${deploymentInstance.deploymentEnvironment?.encodeAsHTML()}
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
    </g:if>
    <g:else>
        <p>
            <em><g:message code="applicationReleaseDoesNotHaveAnyOtherDeployments.message"
                           default="This application release does not have any other deployments."/></em>
        </p>
    </g:else>
</div>