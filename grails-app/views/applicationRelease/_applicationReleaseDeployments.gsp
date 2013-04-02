<div class="sectionHeader">
    <div class="text">
        <g:message code="deployments.label" default="Deployments"/>
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
            <g:each in="${applicationDeploymentList}" var="deploymentInstance" status="i">
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
            </g:each>
            </tbody>
        </table>
    </g:if>
    <g:else>
        <p>
            <em><g:message code="applicationReleaseDoesNotHaveAnyDeployments.message"
                           default="This application release does not have any deployments."/></em>
        </p>
    </g:else>
</div>