<div class="list">
    <table style="width: 100%;">
        <thead>
        <tr>
            <th><g:message code="module.name.label" default="Name"/></th>
            <th><g:message code="module.systemComponents.label" default="System Components"/></th>
            <th><g:message code="module.system.label" default="System"/></th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${applicationReleaseInstance?.moduleReleases?.sort { it.module.name }}" var="moduleReleaseInstance"
            status="i">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
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
                <td>
                    <g:link controller="system" action="show"
                            id="${moduleReleaseInstance.module?.application?.system?.id}">
                        ${moduleReleaseInstance.module?.application?.system?.encodeAsHTML()}
                    </g:link>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>