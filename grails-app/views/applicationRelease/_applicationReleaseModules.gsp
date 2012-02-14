<h2 class="sectionHeader">
    <g:message code="modules.label" default="Modules"/>
</h2>

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
                    <g:each in="${moduleReleaseInstance.module.systemComponents.sort { it.name }}"
                            var="systemComponent">
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