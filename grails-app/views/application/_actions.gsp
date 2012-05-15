<g:if test="${params.action == 'show'}">
    <carm:favoriteActions entity="${applicationInstance}"/>

    <carm:watchActions entity="${applicationInstance}"/>

    <carmsec:isProjectOwner project="${applicationInstance.project}">
        <div class="header-action delete-application-action">
            <g:link action="delete" id="${applicationInstance.id}"><g:message
                    code="default.button.delete.label"
                    default="Delete"/></g:link>
        </div>

        <div class="header-action edit-application-action">
            <g:link action="edit" id="${applicationInstance.id}"><g:message
                    code="default.button.edit.label"
                    default="Edit"/></g:link>
        </div>
    </carmsec:isProjectOwner>
</g:if>