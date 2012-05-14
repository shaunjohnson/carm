<div class="sectionHeader">
    <div class="text">
        <g:message code="details.label" default="Details"/>
    </div>
</div>

<table width="100%">
    <tbody>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="application.project.label" default="Project"/></td>
        <td valign="top" class="value">
            <g:link controller="project" action="show" id="${applicationInstance?.project?.id}">
                ${applicationInstance?.project?.encodeAsHTML()}
            </g:link>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="application.type.label" default="Type"/>
        </td>
        <td valign="top" class="value">
            <g:link controller="applicationType" action="show" id="${applicationInstance?.type?.id}">
                ${applicationInstance?.type?.encodeAsHTML()}
            </g:link>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="application.description.label" default="Description"/>
        </td>
        <td valign="top" class="value">
            <div class="expander">
                <div class="expander">
                    <carm:plainText value="${applicationInstance.description}"/>
                </div>
            </div>
        </td>
    </tr>

    <carm:formDividerRow/>

    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="application.sourceControlRepository.label" default="Source Control Repository"/>
        </td>
        <td valign="top" class="value">
            <carm:formatSourceControl repository="${applicationInstance?.sourceControlRepository}"/>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="application.sourceControlPath.label" default="Source Control Path"/>
        </td>
        <td valign="top" class="value">
            <carm:formatSourceControl application="${applicationInstance}"/>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="application.binariesPath.label" default="Binaries Path"/>
        </td>
        <td valign="top" class="value">
            ${applicationInstance?.binariesPath?.encodeAsHTML()}
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="application.buildInstructions.label" default="Build Instructions"/>
        </td>
        <td valign="top" class="value">
            ${fieldValue(bean: applicationInstance, field: "buildInstructions")?.decodeHTML()}
        </td>
    </tr>

    <carm:formDividerRow/>

    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="application.sysEnvironment.label" default="Environment"/>
        </td>
        <td valign="top" class="value">
            <g:link controller="systemEnvironment" action="show"
                    id="${applicationInstance?.sysEnvironment?.id}">
                ${applicationInstance?.sysEnvironment?.encodeAsHTML()}
            </g:link>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="application.deployInstructions.label" default="Deploy Instructions"/>
        </td>
        <td valign="top" class="value">
            ${fieldValue(bean: applicationInstance, field: "deployInstructions")?.decodeHTML()}
        </td>
    </tr>

    <carm:formDividerRow/>

    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="application.dateCreated.label" default="Date Created"/>
        </td>
        <td valign="top" class="value">
            <g:formatDate date="${applicationInstance?.dateCreated}"/>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="application.lastUpdated.label" default="Last Updated"/>
        </td>
        <td valign="top" class="value">
            <g:formatDate date="${applicationInstance?.lastUpdated}"/>
        </td>
    </tr>
    </tbody>
</table>