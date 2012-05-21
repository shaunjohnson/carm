<div class="sectionHeader">
    <div class="text">
        <g:message code="mostActiveEnvironments.label" default="Most Active Environments"/>
    </div>

    <div class="section-action">
        <g:link controller="systemEnvironment" action="list">
            <g:message code="allSystems.label" default="All Systems"/>
        </g:link>
    </div>
</div>

<g:if test="${mostActiveSystemEnvironments.size()}">
    <g:each in="${mostActiveSystemEnvironments}" var="systemEnvironment" status="systemIndex">
        <carm:formatSystemEnvironment systemEnvironment="${systemEnvironment}"/>

        <g:if test="${(systemIndex + 1) < mostActiveSystemEnvironments.size()}">
            <hr/>
        </g:if>
    </g:each>
</g:if>
<g:else>
    <p>
        <em><g:message code="noActiveEnvironments.message"
                       default="There are no active environments."/></em>
    </p>
</g:else>