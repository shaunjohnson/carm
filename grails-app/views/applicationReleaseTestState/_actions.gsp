<g:if test="${params.action == 'show'}">
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <carm:ifNotInUse domain="${applicationReleaseTestStateInstance}">
            <div class="section-action-icon delete-action">
                <carm:deleteLink id="${applicationReleaseTestStateInstance.id}"/>
            </div>
        </carm:ifNotInUse>

        <div class="section-action-icon edit-action">
            <g:link action="edit" id="${applicationReleaseTestStateInstance.id}"><g:message
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