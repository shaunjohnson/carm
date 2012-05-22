<div class="sectionHeader">
    <div class="text">
        <g:message code="modules.label" default="Modules"/>
    </div>
    <carmsec:isProjectOwner application="${applicationInstance}">
        <div class="section-action-icon new-module-action">
            <g:link controller="module" action="create"
                    params="['application.id': applicationInstance?.id]">
                <g:message code="addModule.label" default="Add Module"/>
            </g:link>
        </div>

        <div class="clearing"></div>
    </carmsec:isProjectOwner>
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
                        <g:each in="${moduleInstance.systemServers.sort { it.name }}" var="systemServer">
                            <li>
                                <g:link controller="systemServer" action="show" id="${systemServer?.id}">
                                    ${systemServer?.encodeAsHTML()}
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
    <carm:alertWarning message="${message(code: "applicationDoesNotHaveAnyModules.message")}"/>
</g:else>