<g:if test="${params.action == 'show'}">
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <div class="header-action delete-user-action">
            <g:link action="delete" id="${sourceControlUserInstance.id}"><g:message
                    code="default.button.delete.label"
                    default="Delete"/></g:link>
        </div>

        <div class="header-action edit-user-action">
            <g:link action="edit" id="${sourceControlUserInstance.id}"><g:message
                    code="default.button.edit.label"
                    default="Edit"/></g:link>
        </div>
    </sec:ifAllGranted>
</g:if>