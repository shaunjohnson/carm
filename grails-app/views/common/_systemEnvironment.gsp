<h4>
    <g:link controller="systemEnvironment" action="show" id="${systemEnvironment.id}">
        ${systemEnvironment.encodeAsHTML()}
    </g:link>
</h4>

<div class="row">
    <div class="offset-half">
        <g:each in="${systemEnvironment.environments}" var="systemDeploymentEnvironment" status="i">
            <g:link controller="systemDeploymentEnvironment" action="show"
                    id="${systemDeploymentEnvironment.id}">${systemDeploymentEnvironment.encodeAsHTML()}</g:link><g:if
                test="${(i + 1) < systemEnvironment.environments.size()}">,</g:if>
        </g:each>
    </div>

    <div class="offset-half">
        <div style="float:left; padding-right: 1em;">
            <g:link controller="systemEnvironment" action="deployments" id="${systemEnvironment.id}">
                <g:message code="deployments.label" default="Deployments"/>
            </g:link>
        </div>

        <div class="clearing"></div>
    </div>
</div>