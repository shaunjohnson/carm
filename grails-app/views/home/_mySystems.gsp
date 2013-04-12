<div class="sectionHeader">
    <div class="text">
        <g:message code="myEnvironments.label" default="My Environments"/>
    </div>

    <sec:ifAllGranted roles="ROLE_ADMIN">
        <div class="section-action-icon new-system-env-action">
            <g:link controller="systemEnvironment" action="create">
                <g:message code="default.new.label" args="[message(code: 'systemEnvironment.label')]"/>
            </g:link>
        </div>
    </sec:ifAllGranted>

    <div class="section-action">
        <g:link controller="systemEnvironment" action="list">
            <g:message code="allSystems.label" default="All Systems"/>
        </g:link>
    </div>
</div>

<div class="section-content">
    <g:if test="${mySystemEnvironments.size()}">
        <g:each in="${mySystemEnvironments}" var="systemEnvironment" status="systemIndex">
            <carm:formatSystemEnvironment systemEnvironment="${systemEnvironment}"/>

            <g:if test="${(systemIndex + 1) < mySystemEnvironments.size()}">
                <hr/>
            </g:if>
        </g:each>
    </g:if>
    <g:else>
        <p>
            <em><g:message code="youDontWorkOnAnyProjectsAssociatedWithAnEnvironment.message"
                           default="You don't work on any projects associated with an environment."/></em>
        </p>
    </g:else>
</div>