<%@ page import="carm.enums.ApplicationReleaseState; carm.ApplicationRelease" %>

<h2 class="sectionHeader">
    <g:message code="releases.label" default="Releases"/>
    <span class="actions">
        <g:link class="create" controller="applicationRelease" action="create"
                params="['application.id': applicationInstance?.id]">
            <g:message code="makeNewRelease.label" default="Make a New Release"/>
        </g:link>
        <g:link class="list" controller="application" action="listReleases"
                params="['id': applicationInstance?.id]">
            <g:message code="showAllReleases.label" default="Show All Releases"/>
        </g:link>
    </span>
</h2>

<g:set var="maxRecords" value="${Math.min(applicationInstance.releases.size(), grailsApplication.config.ui.application.maxRecords)}"/>
<g:set var="applicationReleases"
       value="${applicationInstance.releases.sort { it.dateCreated }.reverse().subList(0, maxRecords)}"/>

<g:if test="${applicationReleases.size()}">
    <table>
        <tbody>
        <g:each in="${applicationReleases}" var="applicationRelease" status="i">
            <tr>
                <td style="padding-bottom: 1em;">
                    <h4 class="applicationReleaseNumber">
                        <g:link controller="applicationRelease" action="show" id="${applicationRelease.id}">
                            ${applicationRelease.releaseNumber.encodeAsHTML()}
                        </g:link>
                    </h4>

                    <div style="margin: 0.5em 0;">
                        <g:formatDateOnly date="${applicationRelease.dateCreated}"/>
                    </div>

                    <div>
                        ${applicationRelease.releaseState.encodeAsHTML()}
                    </div>
                </td>
                <td style="padding-bottom: 1em;">
                    ${applicationRelease.changeLog?.decodeHTML()}
                    <div class="buttons">
                        <span class="button">
                            <g:if test="${applicationRelease.releaseState == ApplicationReleaseState.COMPLETED}">
                                <g:button controller="applicationDeployment" action="create"
                                          params="['applicationRelease.id': applicationRelease.id]">
                                    <g:message code="deployThisRelease.label" default="Deploy this Release"/>
                                </g:button>
                            </g:if>
                        </span>
                    </div>
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