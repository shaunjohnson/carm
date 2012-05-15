<g:if test="${params.action == 'show'}">
    <carm:favoriteActions entity="${projectInstance}"/>

    <carm:watchActions entity="${projectInstance}"/>

    <carmsec:isProjectOwner project="${projectInstance}">
        <div class="header-action delete-project-action">
            <g:link class="delete" action="delete" id="${projectInstance.id}"><g:message
                    code="default.button.delete.label"
                    default="Delete"/></g:link>
        </div>

        <div class="header-action edit-project-action">
            <g:link action="edit" id="${projectInstance.id}"><g:message code="default.button.edit.label"
                                                                        default="Edit"/></g:link>
        </div>
    </carmsec:isProjectOwner>
</g:if>
<g:elseif test="${params.action == 'list'}">
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <div class="header-action new-project-action">
            <g:link action="create"><g:message code="default.new.label" args="[entityName]"/></g:link>
        </div>
    </sec:ifAllGranted>
</g:elseif>