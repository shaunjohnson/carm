<div class="sectionHeader">
    <div class="text">
        <g:message code="pendingTasks.label" default="Pending Tasks"/>
    </div>
</div>

<h3>
    <g:message code="releases.label" default="Releases"/>
</h3>

<g:if test="${pendingReleases?.size()}">
    <div class="list">
        <table>
            <thead>
            <tr>
                <th>
                    <g:message code="applicationRelease.application.label" default="Application"/>
                </th>
                <th><g:message code="applicationRelease.releaseNumber.label" default="Release Number"/></th>
                <th><g:message code="applicationRelease.releaseState.label" default="Release State"/></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${pendingReleases}" status="i" var="applicationReleaseInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <td>
                        <g:link controller="applicationRelease" action="show" id="${applicationReleaseInstance.id}">
                            ${fieldValue(bean: applicationReleaseInstance, field: "application")}
                        </g:link>
                    </td>
                    <td>${fieldValue(bean: applicationReleaseInstance, field: "releaseNumber")}</td>
                    <td>${fieldValue(bean: applicationReleaseInstance, field: "releaseState")}</td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
</g:if>
<g:else>
    <p class="emphasis">
        <g:message code="projectDoesNotHaveAnyOpenReleases.message"
                   default="This project does not have any open release requests."/>
    </p>
</g:else>


<h3>
    <g:message code="deployments.label" default="Deployments"/>
</h3>

<p class="emphasis">
    <g:message code="projectDoesNotHaveAnyOpenDeployments.message"
               default="This project does not have any open deployment requests."/>
</p>


<h3>
    <g:message code="other.label" default="Other"/>
</h3>

<p class="emphasis">
    <g:message code="projectDoesNotHaveAnyOtherOpenRequests.message"
               default="This project does not have any other open requests."/>
</p>
