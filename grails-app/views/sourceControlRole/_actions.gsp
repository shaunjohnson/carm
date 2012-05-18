<g:if test="${params.action == 'show'}">
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <carm:ifNotInUse domain="${sourceControlRoleInstance}">
            <div class="header-action delete-user-role-action">
                <carm:deleteLink id="${sourceControlRoleInstance.id}"/>
            </div>
        </carm:ifNotInUse>

        <div class="header-action edit-user-role-action">
            <g:link action="edit" id="${sourceControlRoleInstance.id}"><g:message
                    code="default.button.edit.label"
                    default="Edit"/></g:link>
        </div>
    </sec:ifAllGranted>
</g:if>
<g:elseif test="${params.action == 'list'}">
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <div class="header-action new-user-role-action">
            <g:link action="create"><g:message code="default.new.label" args="[entityName]"/></g:link>
        </div>
    </sec:ifAllGranted>
</g:elseif>