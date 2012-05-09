<div class="sectionHeader">
    <div class="text">
        <g:message code="details.label" default="Details"/>
    </div>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <div class="actions">
            <g:link class="edit" action="edit" id="${systemEnvironmentInstance?.id}">
                <g:message code="default.button.edit.label" default="Edit"/>
            </g:link>

            <carm:ifNotInUse domain="${systemEnvironmentInstance}">
                <g:link class="delete" action="delete" id="${systemEnvironmentInstance?.id}">
                    <g:message code="default.button.delete.label" default="Delete"/>
                </g:link>
            </carm:ifNotInUse>
        </div>
    </sec:ifAllGranted>
</div>

<table width="100%">
    <tbody>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="systemEnvironment.description.label" default="Description"/>
        </td>
        <td valign="top" class="value">
            <div class="expander">
                <carm:plainText value="${systemEnvironmentInstance?.description}"/>
            </div>
        </td>
    </tr>

    <carm:formDividerRow/>

    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="systemEnvironment.dateCreated.label" default="Date Created"/>
        </td>
        <td valign="top" class="value">
            <g:formatDate date="${systemEnvironmentInstance?.dateCreated}"/>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="systemEnvironment.lastUpdated.label" default="Last Updated"/>
        </td>
        <td valign="top" class="value">
            <g:formatDate date="${systemEnvironmentInstance?.lastUpdated}"/>
        </td>
    </tr>
    </tbody>
</table>
