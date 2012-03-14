<div class="sectionHeader">
    <div class="text">
        <g:message code="applications.label" default="Applications"/>
    </div>
</div>

<g:if test="${applicationsGrouped?.size()}">
    <g:each in="${applicationsGrouped}" var="entry">
        <div class="list" style="margin: 2em 0;">
            <table style="width: 100%;">
                <thead>
                <tr>
                    <th>
                        <g:link controller="applicationType" action="show" id="${entry.key.id}"
                                title="${message(code: 'showApplicationType.label')}">
                            ${entry.key.encodeAsHTML()}
                        </g:link>
                    </th>
                    <th style="width: 7em;">
                        Deployed
                    </th>
                    <th style="width: 10em;">
                        Deployment Status
                    </th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${entry.value}" var="application" status="i">
                    <g:set var="applicationDeployment" value="${latestDeployments[application]}"/>

                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td>
                            <g:link controller="application" action="show" id="${application.id}"
                                    title="${message(code: 'showApplication.label')}">
                                ${application?.encodeAsHTML()}
                            </g:link>
                            <g:if test="${applicationDeployment?.releaseNumber}">
                                (
                                <g:link controller="applicationRelease" action="show"
                                        id="${applicationDeployment.applicationReleaseId}"
                                        title="${message(code: 'showApplicationRelease.label')}">
                                    ${applicationDeployment.releaseNumber}
                                </g:link>
                                )
                            </g:if>
                        </td>
                        <td>
                            <g:if test="${applicationDeployment?.applicationDeploymentId}">
                                <g:link controller="applicationDeployment" action="show"
                                        id="${applicationDeployment.applicationDeploymentId}"
                                        title="${message(code: 'showApplicationDeployment.label')}">
                                    <carm:formatDateOnly date="${applicationDeployment.completedDeploymentDate}"/>
                                </g:link>
                            </g:if>
                        </td>
                        <td>
                            <g:link controller="applicationDeployment" action="show"
                                    id="${applicationDeployment?.applicationDeploymentId}"
                                    title="${message(code: 'showApplicationDeployment.label')}">
                                ${applicationDeployment?.deploymentState}
                            </g:link>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
    </g:each>
</g:if>
<g:else>
    <p class="emphasis">
        <g:message code="systemDoesNotHaveAnyApplications.message"
                   default="This system does not have any applications."/>
    </p>
</g:else>