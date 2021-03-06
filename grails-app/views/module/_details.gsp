<div class="sectionHeader">
    <div class="text">
        <g:message code="details.label" default="Details"/>
    </div>
</div>

<div class="section-content">
    <table id="moduleDetails" class="details">
        <tbody>
        <tr class="prop">
            <td valign="top" class="name">
                <g:message code="module.application.label" default="Application"/>
            </td>
            <td valign="top" class="value">
                <g:link controller="application" action="show" id="${moduleInstance?.application?.id}">
                    ${moduleInstance?.application?.encodeAsHTML()}
                </g:link>
            </td>
        </tr>
        <tr class="prop">
            <td valign="top" class="name">
                <g:message code="module.type.label" default="Type"/>
            </td>
            <td valign="top" class="value">
                <g:link controller="moduleType" action="show" id="${moduleInstance?.type?.id}">
                    ${fieldValue(bean: moduleInstance, field: "type")}
                </g:link>
            </td>
        </tr>
        <tr class="prop">
            <td valign="top" class="name">
                <g:message code="module.description.label" default="Description"/>
            </td>
            <td valign="top" class="value">
                <div class="expander">
                    <carm:plainText value="${moduleInstance?.description}"/>
                </div>
            </td>
        </tr>

        <carm:formDividerRow/>

        <tr class="prop">
            <td valign="top" class="name">
                <g:message code="module.systemServers.label" default="System Servers"/>
            </td>
            <td valign="top" style="text-align: left;" class="value">
                <ul>
                    <g:each in="${moduleInstance.systemServers.sort { it.name }}" var="systemServer">
                        <li>
                            <g:link controller="systemServer" action="show" id="${systemServer?.id}">
                                ${systemServer?.encodeAsHTML()}
                            </g:link>
                        </li>
                    </g:each>
                </ul>
            </td>
        </tr>
        <tr class="prop">
            <td valign="top" class="name">
                <g:message code="module.deployInstructions.label" default="Deploy Instructions"/>
            </td>
            <td valign="top" class="value">
                ${fieldValue(bean: moduleInstance, field: "deployInstructions").decodeHTML()}
            </td>
        </tr>

        <carm:formDividerRow/>

        <tr class="prop">
            <td valign="top" class="name">
                <g:message code="module.dateCreated.label" default="Date Created"/>
            </td>
            <td valign="top" class="value">
                <g:formatDate date="${moduleInstance?.dateCreated}"/>
            </td>
        </tr>
        <tr class="prop">
            <td valign="top" class="name">
                <g:message code="module.lastUpdated.label" default="Last Updated"/>
            </td>
            <td valign="top" class="value">
                <g:formatDate date="${moduleInstance?.lastUpdated}"/>
            </td>
        </tr>
        </tbody>
    </table>
</div>