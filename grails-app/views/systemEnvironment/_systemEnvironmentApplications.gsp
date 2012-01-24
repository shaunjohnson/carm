<h2 class="sectionHeader">Applications</h2>

<g:if test="${applicationsGrouped?.size()}">
    <g:each in="${applicationsGrouped}" var="entry">
        <div class="list" style="margin: 2em 0;">
            <table style="width: 100%;">
                <thead>
                <tr>
                    <th>
                        ${entry.key.encodeAsHTML()}
                    </th>
                    <th style="width: 5em;">
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
                            <g:link controller="application" action="show" id="${application.id}">
                                ${application?.encodeAsHTML()}
                            </g:link>
                            <g:if test="${applicationDeployment?.releaseNumber}">
                                (
                                <g:link controller="applicationRelease" action="show"
                                        id="${applicationDeployment.applicationReleaseId}">
                                    ${applicationDeployment.releaseNumber}
                                </g:link>
                                )
                            </g:if>
                        </td>
                        <td>
                            <g:if test="${applicationDeployment?.applicationDeploymentId}">
                                <g:link controller="applicationDeployment" action="show"
                                        id="${applicationDeployment.applicationDeploymentId}">
                                    <g:formatDateOnly date="${applicationDeployment.completedDeploymentDate}"/>
                                </g:link>
                            </g:if>
                        </td>
                        <td>
                            ${applicationDeployment?.deploymentState}
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