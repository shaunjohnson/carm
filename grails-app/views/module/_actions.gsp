<g:if test="${params.action == 'show'}">
    <carmsec:isProjectOwner project="${moduleInstance.application.project}">
        <div class="header-action delete-module-action">
            <carm:deleteLink id="${moduleInstance.id}"/>
        </div>

        <div class="header-action edit-module-action">
            <g:link action="edit" id="${moduleInstance.id}"><g:message
                    code="default.button.edit.label"
                    default="Edit"/></g:link>
        </div>
    </carmsec:isProjectOwner>
</g:if>