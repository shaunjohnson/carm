<g:if test="${params.action == 'show'}">
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <carm:ifNotInUse domain="${notificationSchemeInstance}">
            <div class="section-action-icon delete-action">
                <carm:deleteLink id="${notificationSchemeInstance.id}"/>
            </div>
        </carm:ifNotInUse>

        <div class="section-action-icon copy-action">
            <g:link action="copy" id="${notificationSchemeInstance.id}"><g:message
                    code="default.button.copy.label"
                    default="Copy"/></g:link>
        </div>

        <div class="section-action-icon edit-action">
            <g:link action="edit" id="${notificationSchemeInstance.id}"><g:message
                    code="default.button.edit.label"
                    default="Edit"/></g:link>
        </div>
    </sec:ifAllGranted>
</g:if>
<g:elseif test="${params.action == 'list'}">
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <div class="section-action-icon new-action">
            <g:link action="create"><g:message code="default.new.label" args="[entityName]"/></g:link>
        </div>
    </sec:ifAllGranted>
</g:elseif>