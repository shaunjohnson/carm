<g:each in="${applicationReleaseList}" var="applicationReleaseInstance" status="i">
    <g:if test="${addLeadingDivider}">
        <hr/>
        <g:set var="addLeadingDivider" value="${false}"/>
    </g:if>

    <div class="row">
        <div class="span1">
            <h5>
                <g:link controller="applicationRelease" action="show" id="${applicationReleaseInstance.id}">
                    ${applicationReleaseInstance.releaseNumber.encodeAsHTML()}
                </g:link>
            </h5>

            <div>
                <carm:formatDateOnly date="${applicationReleaseInstance.dateCreated}"/>
            </div>

            <div>
                <span class="label">
                    <carm:formatApplicationReleaseState releaseState="${applicationReleaseInstance.releaseState}"/>
                </span>
            </div>
        </div>

        <div class="span4">
            <div class="btn-group" style="margin-bottom: 0.5em;">
                <g:link class="btn btn-mini" controller="applicationRelease" action="show"
                        id="${applicationReleaseInstance.id}">
                    <g:message code="default.button.view.label"/>
                </g:link>
                <carmsec:isProjectOwner application="${applicationReleaseInstance.application}">
                    <g:link class="btn btn-mini" controller="applicationRelease" action="edit"
                            id="${applicationReleaseInstance.id}">
                        <g:message code="default.button.edit.label"/>
                    </g:link>
                    <carm:isDeployable applicationRelease="${applicationReleaseInstance}">
                        <g:link class="btn btn-mini" controller="applicationDeployment" action="create"
                                params="['applicationRelease.id': applicationReleaseInstance.id]">
                            <g:message code="deploy.label" default="Deploy"/>
                        </g:link>
                    </carm:isDeployable>
                </carmsec:isProjectOwner>
            </div>

            <div class="expander">
                ${applicationReleaseInstance.changeLog?.decodeHTML()}
            </div>
        </div>
    </div>
    <g:if test="${(i + 1) < applicationReleaseList.size()}">
        <hr/>
    </g:if>
</g:each>