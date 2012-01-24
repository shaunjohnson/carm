<h2 class="sectionHeader">
    <g:message code="myEnvironments.label" default="My Environments"/>
    <span class="actions">
        <g:link controller="system" action="list">
            <g:message code="browseAllSystems.label" default="Browse All Systems"/>
        </g:link>
    </span>
</h2>
<g:each in="${systemInstanceList}" var="systemInstance" status="systemIndex">
    <div style="margin: 0.5em 0;">
        <h3>
            <g:link controller="system" action="show" id="${systemInstance.id}">
                ${systemInstance.encodeAsHTML()}
            </g:link>
        </h3>
        <g:each in="${systemInstance.environments}" var="systemEnvironment" status="i">
            <g:link controller="systemEnvironment" action="show" id="${systemEnvironment.id}">
                ${systemEnvironment.encodeAsHTML()}
            </g:link><g:if test="${(i + 1) < systemInstance.environments.size()}">,</g:if>
        </g:each>
        <div style="margin: 0.5em 0;">
            <g:link controller="applicationDeployment" action="upcomingDeployments"
                    params="[systemId: systemInstance.id]">
                <g:message code="upcomingDeployments.label" default="Upcoming Deployments"/>
            </g:link>
        </div>
    </div>

    <g:if test="${(systemIndex + 1) < systemInstanceList.size()}">
        <hr class="divider"/>
    </g:if>
</g:each>