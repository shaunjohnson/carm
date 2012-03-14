<div class="sectionHeader">
    <div class="text">
        <g:message code="system.environments.label" default="Environments"/>
    </div>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <div class="actions">
            <g:link class="create" controller="systemDeplymentEnvironment" action="create"
                    params="['system.id': systemInstance?.id]">
                <g:message code="addEnvironment.label" default="Add Environment"/>
            </g:link>
        </div>
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
                    <g:link controller="systemDeplymentEnvironment" action="show" id="${environment?.id}">
                        ${environment?.encodeAsHTML()}
                    </g:link>
                </td>
                <td>
                    <sec:ifAllGranted roles="ROLE_ADMIN">
                        <g:if test="${eindex > 0}">
                            <carm:moveUp controller="system" action="moveEnvUp"
                                         id="${environment.id}"
                                         params="[systemId: systemInstance.id, index: eindex]"/>
                        </g:if>
                    </sec:ifAllGranted>
                </td>
                <td>
                    <sec:ifAllGranted roles="ROLE_ADMIN">
                        <g:if test="${eindex + 1 < systemInstance.environments.size()}">
                            <carm:moveDown controller="system" action="moveEnvDown"
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
        <g:message code="systemDoesNotHaveAnyEnvironments.message"
                   default="This system does not have any environments."/>
    </p>
</g:else>