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
                    <g:each var="environment" in="${systemInstance.environments}">
                        <th>${environment.name.encodeAsHTML()}</th>
                    </g:each>
                </tr>
                </thead>
                <tbody>
                <g:each in="${entry.value}" var="application" status="i">
                    <g:set var="applicationDeployments" value="${latestDeployments[application]}"/>
                    
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td>
                            <g:link controller="application" action="show" id="${application.id}">
                                ${application?.encodeAsHTML()}
                            </g:link>
                        </td>
                        <g:each var="environment" in="${systemInstance.environments}">
                            <g:set var="deployment" value="${applicationDeployments[environment.name]}"/>

                            <td>
                                <g:if test="${deployment}">
                                    <g:link controller="applicationDeployment" action="show" id="${deployment.applicationDeploymentId}">
                                        ${deployment.releaseNumber}
                                    </g:link>
                                </g:if>
                            </td>
                        </g:each>
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