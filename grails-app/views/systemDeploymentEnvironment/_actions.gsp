<g:if test="${params.action == 'show'}">
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <carm:ifNotInUse domain="${systemDeploymentEnvironmentInstance}">
            <div class="header-action delete-system-dep-env-action">
                <g:link action="delete" id="${systemDeploymentEnvironmentInstance.id}"><g:message
                        code="default.button.delete.label"
                        default="Delete"/></g:link>
            </div>
        </carm:ifNotInUse>

        <div class="header-action edit-system-dep-env-action">
            <g:link action="edit" id="${systemDeploymentEnvironmentInstance.id}"><g:message
                    code="default.button.edit.label"
                    default="Edit"/></g:link>
        </div>
    </sec:ifAllGranted>
</g:if>