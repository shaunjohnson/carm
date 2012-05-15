<div class="sectionHeader">
    <div class="text">
        <g:message code="systemEnvironment.environments.label" default="Environments"/>
    </div>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <div class="header-action new-system-dep-env-action">
            <g:link class="create" controller="systemDeploymentEnvironment" action="create"
                    params="['sysEnvironment.id': systemInstance?.id]">
                <g:message code="addEnvironment.label" default="Add Environment"/>
            </g:link>
        </div>
        <div class="clearing"></div>
    </sec:ifAllGranted>
</div>

<g:if test="${systemInstance.environments.size()}">
    <table class="ol">
        <tbody>
        <g:each in="${systemInstance.environments}" var="environment" status="eindex">
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
                                         params="[systemId: systemInstance.id, index: eindex]"/>
                        </g:if>
                    </sec:ifAllGranted>
                </td>
                <td>
                    <sec:ifAllGranted roles="ROLE_ADMIN">
                        <g:if test="${eindex + 1 < systemInstance.environments.size()}">
                            <carm:moveDown controller="systemEnvironment" action="moveEnvDown"
                                           id="${environment.id}"
                                           params="[systemId: systemInstance.id, index: eindex]"/>
                        </g:if>
                    </sec:ifAllGranted>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</g:if>
<g:else>
    <p class="emphasis">
        <g:message code="systemEnvironmentDoesNotHaveAnyEnvironments.message"
                   default="This environment does not have any deployment environments."/>
    </p>
</g:else>