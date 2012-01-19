<g:if test="">
    <div class="list">
    <table style="width: 100%;">
        <thead>
        <tr>
            <th><g:message code="applicationDeployment.deploymentState.label" default="Deployment State"/></th>
            <th><g:message code="applicationDeployment.environment.label" default="Environment"/></th>
            <th><g:message code="applicationDeployment.requestedDeploymentDate.label" default="Requested Deployment Date"/></th>
            <th><g:message code="applicationDeployment.completedDeploymentDate.label" default="Completed Deployment Date"/></th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${applicationReleaseInstance?.deployments?.sort { it.completedDeploymentDate }?.reverse()}" var="deploymentInstance">
            <tr>
                <td>
                    <g:link controller="applicationDeployment" action="show" id="${deploymentInstance.id}">
                        ${deploymentInstance.deploymentState?.encodeAsHTML()}
                    </g:link>
                </td>
                <td>
                    <g:link controller="systemEnvironment" action="show"
                            id="${deploymentInstance.environment.id}">${deploymentInstance.environment?.encodeAsHTML()}</g:link>
                </td>
                <td>
                    <g:formatDate type="date" style="short" date="${deploymentInstance.requestedDeploymentDate}"/>
                </td>
                <td>
                    <g:formatDate type="date" style="short" date="${deploymentInstance.completedDeploymentDate}"/>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
    </div>
</g:if>
<g:else>
    <p class="emphasis">This release has no deployments</p>
</g:else>