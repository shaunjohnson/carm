<g:set var="applicationReleasesTotal" value="${applicationInstance.releases.size()}"/>
<g:set var="maxRecordsAllowed" value="${grailsApplication.config.ui.application.maxRecords}"/>
<g:set var="maxRecordsToDisplay"
       value="${Math.min(applicationReleasesTotal, maxRecordsAllowed)}"/>
<g:set var="applicationReleases"
       value="${applicationInstance.releases.sort { it.dateCreated }.reverse().subList(0, maxRecordsToDisplay)}"/>

<div class="sectionHeader">
    <div class="text">
        <g:message code="releases.label" default="Releases"/>
    </div>

    <div class="actions">
        <carmsec:isProjectOwner application="${applicationInstance}">
            <g:link class="create" controller="applicationRelease" action="create"
                    params="['application.id': applicationInstance?.id]">
                <g:message code="newRelease.label" default="Make a New Release"/>
            </g:link>
        </carmsec:isProjectOwner>

        <g:if test="${applicationReleasesTotal > maxRecordsAllowed}">
            <g:link class="list" controller="application" action="listReleases"
                    params="['id': applicationInstance?.id]">
                <g:message code="allReleases.label" default="All Releases"/>
            </g:link>
        </g:if>
    </div>
</div>

<g:if test="${applicationReleases.size()}">
    <table style="width: 100%;">
        <tbody>
        <g:each in="${applicationReleases}" var="applicationReleaseInstance" status="i">
            <tr>
                <td style="padding-bottom: 1em;">
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

                    <carmsec:isProjectOwner application="${applicationInstance}">
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
            <g:if test="${(i + 1) < applicationReleases.size()}">
                <tr>
                    <td colspan="2"><hr class="divider"/></td>
                </tr>
            </g:if>
        </g:each>
        </tbody>
    </table>
</g:if>
<g:else>
    <p class="emphasis">
        <g:message code="applicationHasNotBeenReleased.message" default="This application has not been released."/>
    </p>
</g:else>