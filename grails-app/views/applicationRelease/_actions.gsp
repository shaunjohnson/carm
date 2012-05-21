<g:if test="${params.action == 'show'}">
    <carmsec:isProjectOwner project="${applicationReleaseInstance.application.project}">
        <div class="header-action delete-application-release-action">
            <carm:deleteLink id="${applicationReleaseInstance.id}"/>
        </div>

        <carm:ifNotInUse domain="${applicationReleaseInstance}">
            <div class="header-action edit-application-release-action">
                <g:link action="edit" id="${applicationReleaseInstance.id}"><g:message
                        code="default.button.edit.label"
                        default="Edit"/></g:link>
            </div>
        </carm:ifNotInUse>
    </carmsec:isProjectOwner>
</g:if>