<g:if test="${params.action == 'show'}">
    <carm:favoriteActions entity="${applicationInstance}"/>

    <carm:watchActions entity="${applicationInstance}"/>

    <sec:ifAllGranted roles="ROLE_ADMIN">
        <div class="header-action move-application-action">
            <g:link action="move" id="${applicationInstance.id}">
                <g:message code="default.button.move.label" args="[entityName]"/>
            </g:link>
        </div>
    </sec:ifAllGranted>

    <carmsec:isProjectOwner project="${applicationInstance.project}">
        <div class="header-action delete-application-action">
            <carm:deleteLink id="${applicationInstance.id}"/>
        </div>

        <div class="header-action edit-application-action">
            <g:link action="edit" id="${applicationInstance.id}">
                <g:message code="default.button.edit.label"/>
            </g:link>
        </div>
    </carmsec:isProjectOwner>
</g:if>