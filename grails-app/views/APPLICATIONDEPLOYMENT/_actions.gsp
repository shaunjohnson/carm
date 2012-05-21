<g:if test="${params.action == 'show'}">
    <carmsec:isProjectOwner project="${applicationDeploymentInstance.applicationRelease.application.project}">
        <div class="header-action delete-application-release-action">
            <carm:deleteLink id="${applicationDeploymentInstance.id}"/>
        </div>

        <div class="header-action edit-application-release-action">
            <g:link action="edit" id="${applicationDeploymentInstance.id}"><g:message
                    code="default.button.edit.label"
                    default="Edit"/></g:link>
        </div>
    </carmsec:isProjectOwner>
</g:if>