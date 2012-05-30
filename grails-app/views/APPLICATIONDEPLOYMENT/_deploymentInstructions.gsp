<table class="details">
    <tbody>
    <tr>
        <td colspan="2">
            <h3><g:message code="applicationDeploymentInstructions.label"/></h3>
        </td>
    </tr>

    <tr class="prop">
        <td valign="top" class="name">
            ${applicationDeploymentInstance.applicationRelease.application.name.encodeAsHTML()}
        </td>
        <td valign="top" class="value">
            ${applicationDeploymentInstance.deploymentInstructions.decodeHTML()}
        </td>
    </tr>

    <tr>
        <td colspan="2">
            <h3><g:message code="moduleDeploymentInstructions.label"/></h3>
        </td>
    </tr>

    <g:each in="${applicationDeploymentInstance.moduleDeployments.sort { it.moduleRelease.module.name }}" var="moduleDeployment">
        <tr class="prop">
            <td valign="top" class="name">
                ${moduleDeployment.moduleRelease.module.name}
            </td>
            <td valign="top" class="value">
                ${moduleDeployment.deploymentInstructions?.decodeHTML()}
            </td>
        </tr>
    </g:each>

    </tbody>
</table>