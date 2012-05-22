<div class="sectionHeader">
    <div class="text">
        <g:message code="systemEnvironment.environments.label" default="Environments"/>
    </div>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <div class="section-action-icon  new-system-dep-env-action">
            <g:link class="create" controller="systemDeploymentEnvironment" action="create"
                    params="['sysEnvironment.id': systemEnvironmentInstance?.id]">
                <g:message code="addEnvironment.label" default="Add Environment"/>
            </g:link>
        </div>

        <div class="clearing"></div>
    </sec:ifAllGranted>
</div>

<g:if test="${systemEnvironmentInstance.environments.size()}">
    <div>
        <table class="ol">
            <tbody>
            <g:each in="${systemEnvironmentInstance.environments}" var="environment" status="eindex">
                <tr>
                    <td class="olIndex">
                        ${eindex + 1}.
                    </td>
                    <td class="olContent">
                        <g:link controller="systemDeploymentEnvironment" action="show" id="${environment?.id}">
                            ${environment?.encodeAsHTML()}
                        </g:link>
                    </td>
                    <td>
                        <sec:ifAllGranted roles="ROLE_ADMIN">
                            <g:if test="${eindex > 0}">
                                <carm:moveUp controller="systemEnvironment" action="moveEnvUp"
                                             id="${environment.id}"
                                             params="[systemId: systemEnvironmentInstance.id, index: eindex]"/>
                            </g:if>
                        </sec:ifAllGranted>
                    </td>
                    <td>
                        <sec:ifAllGranted roles="ROLE_ADMIN">
                            <g:if test="${eindex + 1 < systemEnvironmentInstance.environments.size()}">
                                <carm:moveDown controller="systemEnvironment" action="moveEnvDown"
                                               id="${environment.id}"
                                               params="[systemId: systemEnvironmentInstance.id, index: eindex]"/>
                            </g:if>
                        </sec:ifAllGranted>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
</g:if>
<g:else>
    <carm:alertWarning message="${message(code: "systemEnvironmentDoesNotHaveAnyEnvironments.message")}"/>
</g:else>