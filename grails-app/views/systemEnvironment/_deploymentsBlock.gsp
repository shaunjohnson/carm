<g:each in="${applicationDeploymentsGrouped.entrySet()}" var="requestedDeploymentDateEntry">
    <div class="sectionHeader">
        <div class="text">
            <g:formatDate date="${requestedDeploymentDateEntry.key}" type="date" dateStyle="FULL"/>
        </div>
    </div>

    <div class="section-content">
        <div style="margin-bottom: 2em;">
            <g:if test="${requestedDeploymentDateEntry.value.size()}">
                <g:each in="${requestedDeploymentDateEntry.value.entrySet()}" var="deploymentEnvironmentEntry">
                    <h4>${deploymentEnvironmentEntry.key}</h4>

                    <g:each in="${deploymentEnvironmentEntry.value.entrySet()}" var="applicationTypeEntry">
                        <div class="offset-half">
                            <h5>${applicationTypeEntry.key}</h5>

                            <ul class="offset-half">
                                <g:each in="${applicationTypeEntry.value}" var="applicationDeployment">
                                    <li>
                                        <g:link controller="application" action="show"
                                                id="${applicationDeployment.applicationRelease.application.id}"
                                                title="${message(code: 'showApplication.label')}">${applicationDeployment.applicationRelease.application.name}</g:link>

                                        (<g:link controller="applicationRelease" action="show"
                                                 id="${applicationDeployment.applicationRelease.id}"
                                                 title="${message(code: 'showApplicationRelease.label')}">${applicationDeployment.applicationRelease.releaseNumber}</g:link>) -

                                        <g:link controller="applicationDeployment" action="show"
                                                id="${applicationDeployment.id}"
                                                title="${message(code: 'showApplicationDeployment.label')}">
                                            <g:message code="viewDeployment.label" default="View Deployment"/>
                                        </g:link>
                                    </li>
                                </g:each>
                            </ul>
                        </div>
                    </g:each>
                </g:each>
            </g:if>
            <g:else>
                <p>
                    <em><g:message code="noDeploymentsForThisDate.message"
                                   default="There are no deployments for this date."/></em>
                </p>
            </g:else>
        </div>
    </div>
</g:each>