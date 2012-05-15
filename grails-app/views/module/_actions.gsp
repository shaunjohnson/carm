<g:if test="${params.action == 'show'}">
    <carmsec:isProjectOwner project="${moduleInstance.application.project}">
        <div class="header-action delete-module-action">
            <g:link action="delete" id="${moduleInstance.id}"><g:message
                    code="default.button.delete.label"
                    default="Delete"/></g:link>
        </div>

        <div class="header-action edit-module-action">
            <g:link action="edit" id="${moduleInstance.id}"><g:message
                    code="default.button.edit.label"
                    default="Edit"/></g:link>
        </div>
    </carmsec:isProjectOwner>
</g:if>