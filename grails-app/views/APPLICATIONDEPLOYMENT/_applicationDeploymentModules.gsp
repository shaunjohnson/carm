<div class="sectionHeader">
    <div class="text">
        <g:message code="modules.label" default="Modules"/>
    </div>
</div>

<g:if test="${applicationDeploymentInstance?.moduleDeployments?.size()}">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>
                <g:message code="module.name.label" default="Name"/>
            </th>
            <th>
                <g:message code="moduleDeployment.deploymentState.label" default="Deployment State"/>
            </th>
            <th>
                <g:message code="moduleDeployment.testState.label" default="Test State"/>
            </th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${applicationDeploymentInstance.moduleDeployments.sort { it.moduleRelease.module }}"
                var="moduleDeploymentInstance" status="i">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td>
                    <g:link controller="moduleDeployment" action="show" id="${moduleDeploymentInstance.id}">
                        ${moduleDeploymentInstance.moduleRelease.module.encodeAsHTML()}
                    </g:link>
                </td>
                <td>
                    <carm:formatModuleDeploymentState
                            deploymentState="${moduleDeploymentInstance.deploymentState}"/>
                </td>
                <td>
                    ${fieldValue(bean: moduleDeploymentInstance, field: "testState")}
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</g:if>