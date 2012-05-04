<div class="sectionHeader">
    <div class="text">
        <g:message code="myEnvironments.label" default="My Environments"/>
    </div>

    <div class="actions">
        <g:link controller="systemEnvironment" action="list">
            <g:message code="allSystems.label" default="All Systems"/>
        </g:link>
    </div>
</div>

<g:if test="${mySystemEnvironments.size()}">
    <g:each in="${mySystemEnvironments}" var="systemEnvironment" status="systemIndex">
        <div style="margin: 0.5em 0;">
            <carm:formatSystemEnvironment systemEnvironment="${systemEnvironment}"/>
        </div>

        <g:if test="${(systemIndex + 1) < mySystemEnvironments.size()}">
            <hr class="divider"/>
        </g:if>
    </g:each>
</g:if>
<g:else>
    <p class="emphasis">
        <g:message code="youDontWorkOnAnyProjectsAssociatedWithAnEnvironment.message"
                   default="You don't work on any projects associated with an environment."/>
    </p>
</g:else>