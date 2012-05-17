<h4>
    <g:link controller="systemEnvironment" action="show" id="${systemEnvironment.id}">
        ${systemEnvironment.encodeAsHTML()}
    </g:link>
</h4>

<div class="row">
    <div class="offset1">
        <g:each in="${systemEnvironment.environments}" var="systemDeploymentEnvironment" status="i">
            <g:link controller="systemDeploymentEnvironment" action="show"
                    id="${systemDeploymentEnvironment.id}">${systemDeploymentEnvironment.encodeAsHTML()}</g:link><g:if
                test="${(i + 1) < systemEnvironment.environments.size()}">,</g:if>
        </g:each>
    </div>

    <div class="offset1">
        <div style="float:left; padding-right: 1em;">
            <g:message code="deployments.label" default="Deployments"/>:
        </div>

        <div style="float:left; padding-right: 1em;">
            <g:link controller="systemEnvironment" action="completedDeployments" id="${systemEnvironment.id}">
                <g:message code="completed.label" default="Completed"/>
            </g:link>
        </div>

        <div style="float:left; padding-right: 1em;">
            <g:link controller="systemEnvironment" action="upcomingDeployments" id="${systemEnvironment.id}">
                <g:message code="upcoming.label" default="Upcoming"/>
            </g:link>
        </div>

        <div class="clearing"></div>
    </div>
</div>