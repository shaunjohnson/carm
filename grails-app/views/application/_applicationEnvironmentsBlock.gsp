<g:each in="${deploymentList}" var="deployment">
    <tr>
        <td class="span1" style="text-align: left;">
            <g:if test="${deployment.requestedDeploymentDate > new java.util.Date()}">
                <span class="label label-important">Upcoming</span>
            </g:if>
        </td>
        <td class="span1" style="text-align: center;">
            <h5>
                <g:link controller="applicationRelease" action="show"
                        id="${deployment.applicationRelease.id}"
                        title="${message(code: "showApplicationRelease.label", default: "Show Application Release")}">
                    ${deployment.applicationRelease.releaseNumber.encodeAsHTML()}
                </g:link>
            </h5>
        </td>
        <td class="span2" style="text-align: center;">
            <carm:formatDateOnly date="${deployment.completedDeploymentDate}"/>
        </td>
        <td class="span2" style="text-align: center; white-space: nowrap;">
            <g:link class="environment-action" controller="applicationDeployment" action="show"
                    id="${deployment.id}"
                    title="${message(code: "showApplicationDeployment.label", default: "Show Application Deployment")}"><img
                    src="${fam.icon(name: 'page_white_text')}" alt="Deployment"/></g:link>
            <g:link class="environment-action" controller="applicationDeployment" action="show"
                    id="${deployment.id}"
                    title="${message(code: "promote.label", default: "Promote")}"><img
                    src="${fam.icon(name: 'page_white_get')}" alt="Promote"/>
            </g:link>
        </td>
    </tr>
</g:each>