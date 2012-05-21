<div class="sectionHeader">
    <div class="text">
        <g:message code="environments.label" default="Environments"/>
    </div>

    <div class="section-action">
        <g:link action="showFullHistory" id="${applicationInstance.id}"
                params="[sort: grailsApplication.config.ui.application.showFullHistorySort,
                        order: grailsApplication.config.ui.application.showFullHistoryOrder]">
            <g:message code="showFullHistory.label" default="Show Full History"/>
        </g:link>
    </div>
</div>

<g:if test="${applicationInstance?.sysEnvironment}">
    <table class="table">
        <thead>
            <tr>
                <th><g:message code="environment.label"/></th>
                <th><g:message code="lastRelease.label"/></th>
                <th><g:message code="releasedOn.label"/></th>
                <th><g:message code="actions.label"/></th>
            </tr>
        </thead>
        <tbody>
        <g:each in="${applicationInstance.sysEnvironment.environments}" var="deploymentEnvironment">
            <g:set var="deployment" value="${deployments[deploymentEnvironment].lastDeployment}"/>

            <tr>
                <td>
                    <g:link controller="systemDeploymentEnvironment" action="show" id="${deploymentEnvironment.id}"
                            title="${message(code: "showSystemDeploymentEnvironment.label", default: "Show Deployment Environment")}">
                        ${deploymentEnvironment.name.encodeAsHTML()}
                    </g:link>
                </td>
                <g:if test="${deployment}">
                    <td>
                        <g:link controller="applicationRelease" action="show" id="${deployment.applicationRelease.id}"
                                title="${message(code: "showApplicationRelease.label", default: "Show Application Release")}">
                            ${deployment.applicationRelease.releaseNumber.encodeAsHTML()}
                        </g:link>
                    </td>
                    <td>
                        <carm:formatDateOnly date="${deployment.completedDeploymentDate}"/>
                    </td>
                    <td style="white-space: nowrap;">
                        <g:link controller="applicationDeployment" action="show" id="${deployment.id}"
                                title="${message(code: "showApplicationDeployment.label", default: "Show Application Deployment")}">
                            <img src="${fam.icon(name: 'page_white_text')}" alt="Deployment"/>
                        </g:link>
                        <g:link controller="applicationDeployment" action="show" id="${deployment.id}"
                                title="${message(code: "promote.label", default: "Promote")}">
                            <img src="${fam.icon(name: 'page_white_get')}" alt="Promote"/>
                        </g:link>
                    </td>
                </g:if>
                <g:else>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </g:else>
            </tr>
        </g:each>
        </tbody>
    </table>
</g:if>
<g:else>
    <p>
        <em><g:message code="applicationNotConfiguredForEnvironment.message"
                       default="This application is not configured to work with an environment."/></em>
    </p>
</g:else>