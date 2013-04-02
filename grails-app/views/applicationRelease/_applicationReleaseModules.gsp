<div class="sectionHeader">
    <div class="text">
        <g:message code="modules.label" default="Modules"/>
    </div>
</div>

<div class="section-content">
    <table style="width: 100%;">
        <tbody>
        <g:each in="${applicationReleaseInstance?.moduleReleases?.sort { it.module.name }}" var="moduleReleaseInstance"
                status="i">
            <tr>
                <td>
                    <g:link controller="module" action="show" id="${moduleReleaseInstance.module.id}">
                        ${moduleReleaseInstance.module?.encodeAsHTML()}
                    </g:link>
                </td>
                <td>
                    <ul>
                        <g:each in="${moduleReleaseInstance.module.systemServers.sort { it.name }}"
                                var="systemServer">
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
</div>