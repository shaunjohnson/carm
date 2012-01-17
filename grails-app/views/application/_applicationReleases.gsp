<%@ page import="carm.ApplicationRelease" %>

<h2 class="sectionHeader">Releases</h2>

<div class="nav">
    <span class="menuButton">
        <g:link class="create" controller="applicationRelease" action="create"
                params="['application.id': applicationInstance?.id]">Make a New Release</g:link>
    </span>
</div>

<g:set var="applicationReleaseInstanceList" value="${ApplicationRelease.findAllByApplication(applicationInstance).sort{ it.dateCreated }.reverse()}"/>

<g:if test="${applicationReleaseInstanceList?.size()}">
    <table>
        <tbody>
        <g:each in="${applicationReleaseInstanceList}" status="i" var="applicationReleaseInstance">
            <tr>
                <td style="padding-bottom: 1em;">
                    <h4>
                        <g:link controller="applicationRelease" action="show" id="${applicationReleaseInstance.id}">
                            ${applicationReleaseInstance.releaseNumber}
                        </g:link>
                    </h4>
                    <div style="text-align: right;">
                        <g:formatDate type="date" style="short" date="${applicationReleaseInstance.dateCreated}"/>
                    </div>
                </td>
                <td style="padding-bottom: 1em;">
                    ${applicationReleaseInstance.changeLog}
                    <div class="buttons">
                        <span class="button">
                            <g:link controller="applicationDeployment" action="create"
                                    params="['applicationRelease.id': applicationReleaseInstance.id]">Deploy this Release</g:link>
                        </span>
                        <span class="button">
                            <g:link controller="applicationRelease" action="showDeploymentSheet"
                                    id="${applicationReleaseInstance.id}">Deployment Sheet</g:link>
                        </span>
                    </div>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</g:if>
<g:else>
    No releases recorded yet
</g:else>