<g:each in="${applicationReleaseList}" var="applicationReleaseInstance" status="i">
    <g:if test="${addLeadingDivider}">
        <hr class="divider"/>
        <g:set var="addLeadingDivider" value="${false}"/>
    </g:if>

    <div class="row">
        <div class="span1">
            <h4 class="applicationReleaseNumber">
                <g:link controller="applicationRelease" action="show" id="${applicationReleaseInstance.id}">
                    ${applicationReleaseInstance.releaseNumber.encodeAsHTML()}
                </g:link>
            </h4>

            <div style="margin: 0.5em 0;">
                <carm:formatDateOnly date="${applicationReleaseInstance.dateCreated}"/>
            </div>

            <div>
                <span class="label label-info">
                    ${applicationReleaseInstance.releaseState.encodeAsHTML()}
                </span>
            </div>
        </div>

        <div class="span5">
            <carmsec:isProjectOwner application="${applicationReleaseInstance.application}">
                <div class="btn-group" style="margin-bottom: 0.5em;">
                    <g:link class="btn btn-mini" controller="applicationRelease" action="show"
                            id="${applicationReleaseInstance.id}">
                        <g:message code="default.button.view.label"/>
                    </g:link>
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
                </div>
            </carmsec:isProjectOwner>

            <div class="expander">
                ${applicationReleaseInstance.changeLog?.decodeHTML()}
            </div>
        </div>
    </div>
    <g:if test="${(i + 1) < applicationReleaseList.size()}">
        <hr class="divider"/>
    </g:if>
</g:each>
<script type="text/javascript">
    jQuery("button.button").button();
</script>