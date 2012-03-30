<g:each in="${applicationReleaseList}" var="applicationReleaseInstance" status="i">
    <table style="width: 100%;">
        <tbody>
        <g:if test="${addLeadingDivider}">
            <tr>
                <td colspan="2"><hr class="divider"/></td>
            </tr>
            <g:set var="addLeadingDivider" value="${false}"/>
        </g:if>
        <tr>
            <td style="padding-bottom: 1em; width: 10%;">
                <h4 class="applicationReleaseNumber">
                    <g:link controller="applicationRelease" action="show" id="${applicationReleaseInstance.id}">
                        ${applicationReleaseInstance.releaseNumber.encodeAsHTML()}
                    </g:link>
                </h4>

                <div style="margin: 0.5em 0;">
                    <carm:formatDateOnly date="${applicationReleaseInstance.dateCreated}"/>
                </div>

                <div>
                    ${applicationReleaseInstance.releaseState.encodeAsHTML()}
                </div>
            </td>
            <td style="padding-bottom: 1em;">
                <div class="expander">
                    ${applicationReleaseInstance.changeLog?.decodeHTML()}
                </div>

                <carmsec:isProjectOwner application="${applicationReleaseInstance.application}">
                    <div class="buttons">
                        <span class="button">
                            <carm:isDeployable applicationRelease="${applicationReleaseInstance}">
                                <carm:button controller="applicationDeployment" action="create"
                                             params="['applicationRelease.id': applicationReleaseInstance.id]">
                                    <g:message code="deployThisRelease.label" default="Deploy this Release"/>
                                </carm:button>
                            </carm:isDeployable>
                        </span>
                    </div>
                </carmsec:isProjectOwner>
            </td>
        </tr>
        <g:if test="${(i + 1) < applicationReleaseList.size()}">
            <tr>
                <td colspan="2"><hr class="divider"/></td>
            </tr>
        </g:if>
        </tbody>
    </table>
</g:each>
<script type="text/javascript">
    jQuery("button.button").button();
</script>