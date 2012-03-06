<div class="sectionHeader">
    <div class="text">
        <g:message code="myEnvironments.label" default="My Environments"/>
    </div>
    <div class="actions">
        <g:link controller="system" action="list">
            <g:message code="allSystems.label" default="All Systems"/>
        </g:link>
    </div>
</div>

<g:if test="${systemInstanceList?.size()}">
    <g:each in="${systemInstanceList}" var="systemInstance" status="systemIndex">
        <div style="margin: 0.5em 0;">
            <h3>
                <g:link controller="system" action="show" id="${systemInstance.id}">
                    ${systemInstance.encodeAsHTML()}
                </g:link>
            </h3>
            <g:each in="${systemInstance.environments}" var="systemEnvironment" status="i">
                <g:link controller="systemEnvironment" action="show"
                        id="${systemEnvironment.id}">${systemEnvironment.encodeAsHTML()}</g:link><g:if
                    test="${(i + 1) < systemInstance.environments.size()}">,</g:if>
            </g:each>
            <div style="margin: 0.5em 0;">
                <g:link controller="system" action="upcomingDeployments" id="${systemInstance.id}">
                    <g:message code="upcomingDeployments.label" default="Upcoming Deployments"/>
                </g:link>
            </div>
        </div>

        <g:if test="${(systemIndex + 1) < systemInstanceList.size()}">
            <hr class="divider"/>
        </g:if>
    </g:each>
</g:if>
<g:else>
    <p class="emphasis">
        <g:message code="youDontWorkOnAnyProjectsAssociatedWithASystem.message"
                   default="You don't work on any projects associated with a system."/>
    </p>
</g:else>