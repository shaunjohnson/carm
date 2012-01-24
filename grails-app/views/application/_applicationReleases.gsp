<%@ page import="carm.ApplicationRelease" %>

<h2 class="sectionHeader">
    <g:message code="releases.label" default="Releases"/>
</h2>

<div class="nav">
    <span class="menuButton">
        <g:link class="create" controller="applicationRelease" action="create"
                params="['application.id': applicationInstance?.id]">
            <g:message code="makeNewRelease.label" default="Make a New Release"/>
        </g:link>
    </span>
</div>

<g:set var="applicationReleases"
       value="${applicationInstance.releases.sort{ it.dateCreated }.reverse()}"/>

<g:if test="${applicationReleases.size()}">
    <table>
        <tbody>
        <g:each in="${applicationReleases}" var="applicationRelease">
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
                            <g:link controller="applicationDeployment" action="create"
                                    params="['applicationRelease.id': applicationRelease.id]">
                                <g:message code="deployThisRelease.label" default="Deploy this Release"/>
                            </g:link>
                        </span>
                    </div>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</g:if>
<g:else>
    <p class="emphasis">
        <g:message code="applicationHasNotBeenReleased.message" default="This application has not been released."/>
    </p>
</g:else>