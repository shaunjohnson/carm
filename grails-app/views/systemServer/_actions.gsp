<g:if test="${params.action == 'show'}">
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <carm:ifNotInUse domain="${systemServerInstance}">
            <div class="header-action delete-system-server-action">
                <g:link action="delete" id="${systemServerInstance.id}"><g:message
                        code="default.button.delete.label"
                        default="Delete"/></g:link>
            </div>
        </carm:ifNotInUse>

        <div class="header-action edit-system-server-action">
            <g:link action="edit" id="${systemServerInstance.id}"><g:message
                    code="default.button.edit.label"
                    default="Edit"/></g:link>
        </div>
    </sec:ifAllGranted>
</g:if>