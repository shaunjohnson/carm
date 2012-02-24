<div class="sectionHeader">
    <div class="text">
        <g:message code="system.components.label" default="Components"/>
    </div>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <div class="actions">
            <g:link class="create" controller="systemComponent" action="create"
                    params="['system.id': systemInstance?.id]">
                <g:message code="addComponent.label" default="Add Component"/>
            </g:link>
        </div>
    </sec:ifAllGranted>
</div>

<g:if test="${systemInstance.components.size()}">
    <ul>
        <g:each in="${systemInstance.components.sort { it.name }}" var="component">
            <li>
                <g:link controller="systemComponent" action="show" id="${component.id}">
                    ${component?.encodeAsHTML()}
                </g:link>
            </li>
        </g:each>
    </ul>
</g:if>
<g:else>
    <p class="emphasis">
        <g:message code="systemDoesNotHaveAnyComponents.message"
                   default="This system does not have any components."/>
    </p>
</g:else>