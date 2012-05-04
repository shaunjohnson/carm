<div class="sectionHeader">
    <div class="text">
        <g:message code="mostActiveEnvironments.label" default="Most Active Environments"/>
    </div>

    <div class="actions">
        <g:link controller="systemEnvironment" action="list">
            <g:message code="allSystems.label" default="All Systems"/>
        </g:link>
    </div>
</div>

<g:if test="${mostActiveSystemEnvironments.size()}">
    <g:each in="${mostActiveSystemEnvironments}" var="systemEnvironment" status="systemIndex">
        <div style="margin: 0.5em 0;">
            <carm:formatSystemEnvironment systemEnvironment="${systemEnvironment}"/>
        </div>

        <g:if test="${(systemIndex + 1) < mostActiveSystemEnvironments.size()}">
            <hr class="divider"/>
        </g:if>
    </g:each>
</g:if>
<g:else>
    <p class="emphasis">
        <g:message code="noActiveEnvironments.message"
                   default="There are no active environments."/>
    </p>
</g:else>