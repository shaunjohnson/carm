<g:each in="${deploymentList}" var="deployment">
    <tr>
        <td style="width: 30px;">
            <carm:upcomingIndicator applicationDeployment="${deployment}"/>
        </td>
        <td class="span1">
            <h4 style="text-align: center;">
                ${deployment.applicationRelease.releaseNumber.encodeAsHTML()}
            </h4>
        </td>
        <td class="span2" style="text-align: center;">
            <carm:formatDateOnly date="${deployment.completedDeploymentDate}"/>
        </td>
        <td class="span3" style="text-align: center; white-space: nowrap;">
            <div class="btn-group" style="margin-bottom: 0.5em;">
                <g:link class="btn btn-mini" controller="applicationDeployment" action="show" id="${deployment.id}"
                        title="${message(code: "showApplicationDeployment.label", default: "Show Application Deployment")}">
                    <g:message code="default.button.view.label"/>
                </g:link>
                <g:link class="btn btn-mini" controller="applicationDeployment" action="edit" id="${deployment.id}"
                        title="${message(code: "showApplicationDeployment.label", default: "Show Application Deployment")}">
                    <g:message code="default.button.edit.label"/>
                </g:link>
                <g:link class="btn btn-mini" controller="applicationDeployment" action="show" id="${deployment.id}"
                        title="${message(code: "promote.label", default: "Promote")}">
                    <g:message code="promote.label" default="Promote"/>
                </g:link>
            </div>
        </td>
    </tr>
</g:each>