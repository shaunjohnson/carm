<g:if test="${params.action == 'show'}">
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <carm:ifNotInUse domain="${systemEnvironmentInstance}">
            <div class="header-action delete-system-env-action">
                <g:link action="delete" id="${systemEnvironmentInstance.id}"><g:message
                        code="default.button.delete.label"
                        default="Delete"/></g:link>
            </div>
        </carm:ifNotInUse>

        <div class="header-action edit-system-env-action">
            <g:link action="edit" id="${systemEnvironmentInstance.id}"><g:message
                    code="default.button.edit.label"
                    default="Edit"/></g:link>
        </div>
    </sec:ifAllGranted>
</g:if>
<g:elseif test="${params.action == 'list'}">
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <div class="header-action new-system-env-action">
            <g:link action="create"><g:message code="default.new.label" args="[entityName]"/></g:link>
        </div>
    </sec:ifAllGranted>
</g:elseif>