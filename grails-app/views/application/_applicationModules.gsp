<h2 class="sectionHeader">Modules</h2>

<div class="nav">
    <span class="menuButton">
        <g:link class="create" controller="module" action="create"
                params="['application.id': applicationInstance?.id]">Add Module</g:link>
    </span>
</div>

<g:if test="${applicationInstance?.modules?.size()}">
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
        <g:each in="${applicationInstance.modules.sort { it.name }}" var="moduleInstance">
            <tr>
                <td><g:link controller="module" action="show" id="${moduleInstance.id}">${moduleInstance?.encodeAsHTML()}</g:link></td>
                <td>
                    <ul>
                        <g:each in="${moduleInstance.systemComponents.sort { it.name }}" var="s">
                            <li>
                                <g:link controller="systemComponent" action="show"
                                        id="${s?.id}">${s?.encodeAsHTML()}</g:link>
                            </li>
                        </g:each>
                    </ul>
                </td>
                <td>
                    <g:link controller="system" action="show" id="${moduleInstance?.application?.system?.id}">
                        ${moduleInstance?.application?.system?.encodeAsHTML()}
                    </g:link>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
</g:if>