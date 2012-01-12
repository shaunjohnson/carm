<h2>Environments</h2>

<g:if test="${applicationInstance?.system?.environments?.size()}">
<table style="width: 100%;">
    <tbody>
    <g:each in="${applicationInstance.system.environments}" var="environment">
        <tr>
            <td>
                <g:link controller="systemEnvironment" action="show" id="${environment.id}">
                    ${environment.name.encodeAsHTML()}
                </g:link>
            </td>
            <td>
                <g:link controller="applicationRelease" action="show">
                    1.2.3
                </g:link>
            </td>
            <td>
                <g:formatDate type="date" style="short" date="${new Date()}"/>
            </td>
            <td>
                <g:link controller="applicationRelease" action="showDeploymentSheet">Deployment Sheet</g:link>
            </td>
        </tr>
    </g:each>
    </tbody>
    <tfoot>
    <tr>
        <td colspan="4" style="text-align: right;">
            <g:link action="showFullHistory">(Show Full History)</g:link>
        </td>
    </tr>
    </tfoot>
</table>
</g:if>
<g:else>
Application not configured for a system
</g:else>