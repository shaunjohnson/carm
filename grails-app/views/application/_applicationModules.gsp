<div class="sectionHeader">
    <div class="text">
        <g:message code="modules.label" default="Modules"/>
    </div>
    <div class="actions">
        <g:link class="create" controller="module" action="create"
                params="['application.id': applicationInstance?.id]">
            <g:message code="addModule.label" default="Add Module"/>
        </g:link>
    </div>
</div>

<g:if test="${applicationInstance?.modules?.size()}">
    <table style="width: 100%;">
        <tbody>
        <g:each in="${applicationInstance.modules.sort { it.name }}" var="moduleInstance" status="i">
            <tr>
                <td>
                    <g:link controller="module" action="show" id="${moduleInstance.id}">
                        ${moduleInstance?.encodeAsHTML()}
                    </g:link>
                </td>
                <td>
                    <ul>
                        <g:each in="${moduleInstance.systemComponents.sort { it.name }}" var="systemComponent">
                            <li>
                                <g:link controller="systemComponent" action="show" id="${systemComponent?.id}">
                                    ${systemComponent?.encodeAsHTML()}
                                </g:link>
                            </li>
                        </g:each>
                    </ul>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</g:if>
<g:else>
    <p class="emphasis">
        <g:message code="applicationDoesNotHaveAnyModules.message"
                   default="This application does not have any modules."/>
    </p>
</g:else>