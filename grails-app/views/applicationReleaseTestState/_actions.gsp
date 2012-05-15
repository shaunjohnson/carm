<g:if test="${params.action == 'show'}">
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <carm:ifNotInUse domain="${applicationReleaseTestStateInstance}">
            <div class="header-action delete-action">
                <g:link action="delete" id="${applicationReleaseTestStateInstance.id}"><g:message
                        code="default.button.delete.label"
                        default="Delete"/></g:link>
            </div>
        </carm:ifNotInUse>

        <div class="header-action edit-action">
            <g:link action="edit" id="${applicationReleaseTestStateInstance.id}"><g:message
                    code="default.button.edit.label"
                    default="Edit"/></g:link>
        </div>
    </sec:ifAllGranted>
</g:if>
<g:elseif test="${params.action == 'list'}">
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <div class="header-action new-action">
            <g:link action="create"><g:message code="default.new.label" args="[entityName]"/></g:link>
        </div>
    </sec:ifAllGranted>
</g:elseif>