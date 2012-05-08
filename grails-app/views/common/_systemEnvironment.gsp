<h3>
    <g:link controller="systemEnvironment" action="show" id="${systemEnvironment.id}">
        ${systemEnvironment.encodeAsHTML()}
    </g:link>
</h3>

<div style="margin-left: 1.5em;">
    <g:each in="${systemEnvironment.environments}" var="systemDeploymentEnvironment" status="i">
        <g:link controller="systemDeploymentEnvironment" action="show"
                id="${systemDeploymentEnvironment.id}">${systemDeploymentEnvironment.encodeAsHTML()}</g:link><g:if
            test="${(i + 1) < systemEnvironment.environments.size()}">,</g:if>
    </g:each>
</div>

<div style="margin: 0.5em 0 0 1.5em;">
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