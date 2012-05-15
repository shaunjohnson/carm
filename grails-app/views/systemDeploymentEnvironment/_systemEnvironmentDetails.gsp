<div class="sectionHeader">
    <div class="text">
        <g:message code="details.label" default="Details"/>
    </div>
</div>

<table width="100%">
    <tbody>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="systemDeploymentEnvironment.description.label" default="Description"/>
        </td>
        <td valign="top" class="value">
            <div class="expander">
                <carm:plainText value="${systemDeploymentEnvironmentInstance?.description}"/>
            </div>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="systemDeploymentEnvironment.system.label" default="System"/>
        </td>
        <td valign="top" class="value">
            <g:link controller="systemEnvironment" action="show"
                    id="${systemDeploymentEnvironmentInstance?.sysEnvironment?.id}">
                ${systemDeploymentEnvironmentInstance?.sysEnvironment?.encodeAsHTML()}
            </g:link>
        </td>
    </tr>

    <carm:formDividerRow/>

    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="systemDeploymentEnvironment.dateCreated.label" default="Date Created"/>
        </td>
        <td valign="top" class="value">
            <g:formatDate date="${systemDeploymentEnvironmentInstance?.dateCreated}"/>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="systemDeploymentEnvironment.lastUpdated.label" default="Last Updated"/>
        </td>
        <td valign="top" class="value">
            <g:formatDate date="${systemDeploymentEnvironmentInstance?.lastUpdated}"/>
        </td>
    </tr>
    </tbody>
</table>
