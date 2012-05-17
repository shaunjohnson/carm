<div class="sectionHeader">
    <div class="text">
        <g:message code="applications.label" default="Applications"/>
    </div>

    <div class="actions">
        <g:link controller="systemEnvironment" action="completedDeployments" id="${systemInstance.id}">
            <g:message code="completedDeployments.label" default="Completed Deployments"/>
        </g:link>

        <g:link controller="systemEnvironment" action="upcomingDeployments" id="${systemInstance.id}">
            <g:message code="upcomingDeployments.label" default="Upcoming Deployments"/>
        </g:link>
    </div>
</div>

<g:if test="${applicationsGrouped?.size()}">
    <g:each in="${applicationsGrouped}" var="entry">
        <div style="margin: 2em 0;">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>
                        <g:link controller="applicationType" action="show" id="${entry.key.id}"
                                title="${message(code: 'showApplicationType.label')}">
                            ${entry.key.encodeAsHTML()}
                        </g:link>
                    </th>
                    <g:each var="environment" in="${systemInstance.environments}">
                        <th style="text-align: center; width: 5em;">
                            <g:link controller="systemDeploymentEnvironment" action="show" id="${environment.id}"
                                    title="${message(code: 'showSystemDeploymentEnvironment.label')}">
                                ${environment.name.encodeAsHTML()}
                            </g:link>
                        </th>
                    </g:each>
                </tr>
                </thead>
                <tbody>
                <g:each in="${entry.value}" var="application" status="i">
                    <g:set var="applicationDeployments" value="${latestDeployments[application]}"/>

                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td>
                            <g:link controller="application" action="show" id="${application.id}"
                                    title="${message(code: 'showApplication.label')}">
                                ${application?.encodeAsHTML()}
                            </g:link>
                        </td>
                        <g:each var="environment" in="${systemInstance.environments}">
                            <g:set var="applicationDeployment" value="${applicationDeployments[environment.name]}"/>

                            <td class="centered">
                                <g:if test="${applicationDeployment}">
                                    <g:link controller="applicationDeployment" action="show"
                                            id="${applicationDeployment.applicationDeploymentId}"
                                            title="${message(code: "applicationDeployedOn.message", args: [formatDate(date: applicationDeployment.completedDeploymentDate)])}">
                                        ${applicationDeployment.releaseNumber}
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
    <p>
        <em><g:message code="systemEnvironmentDoesNotHaveAnyApplications.message"
                       default="This environment does not have any applications."/></em>
    </p>
</g:else>