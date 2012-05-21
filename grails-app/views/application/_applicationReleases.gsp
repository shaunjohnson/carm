<g:set var="applicationReleasesTotal" value="${applicationInstance.releases.size()}"/>
<g:set var="maxRecordsAllowed" value="${grailsApplication.config.ui.applicationRelease.maxRecords}"/>
<g:set var="maxRecordsToDisplay"
       value="${Math.min(applicationReleasesTotal, maxRecordsAllowed)}"/>
<g:set var="applicationReleases"
       value="${applicationInstance.releases.sort { it.dateCreated }.reverse().subList(0, maxRecordsToDisplay)}"/>

<div class="sectionHeader">
    <div class="text">
        <g:message code="releases.label" default="Releases"/>
    </div>

    <div class="section-action">
        <g:if test="${applicationReleasesTotal > maxRecordsAllowed}">
            <g:link class="list" controller="application" action="listReleases"
                    params="['id': applicationInstance?.id]">
                <g:message code="allReleases.label" default="All Releases"/>
            </g:link>
        </g:if>
    </div>

    <carmsec:isProjectOwner application="${applicationInstance}">
        <div class="section-action-icon new-application-release-action">
            <g:link class="create" controller="applicationRelease" action="create"
                    params="['application.id': applicationInstance?.id]">
                <g:message code="newRelease.label" default="Make a New Release"/>
            </g:link>
        </div>
    </carmsec:isProjectOwner>
</div>

<g:if test="${applicationReleases.size()}">
    <div id="releaseBlock">
        <g:render template="applicationReleaseBlock" model="[applicationReleaseList: applicationReleases]"/>
    </div>

    <div class="clearing" style="margin: 1em 0;"></div>

    <g:if test="${maxRecordsAllowed <= applicationReleasesTotal}">
        <carm:showMore controller="${params.controller}" action="ajaxShowMoreReleases" id="${applicationInstance.id}"
                       appendId="releaseBlock" max="${applicationReleasesTotal}" step="${maxRecordsAllowed}"/>
    </g:if>
</g:if>
<g:else>
    <p>
        <em><g:message code="applicationHasNotBeenReleased.message"
                       default="This application has not been released."/></em>
    </p>
</g:else>