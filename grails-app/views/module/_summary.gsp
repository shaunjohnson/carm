<div class="sectionHeader">
    <div class="text">
        <g:message code="summary.label" default="Summary"/>
    </div>
</div>

<div class="section-content">
    <table id="moduleSummary" class="details">
        <tbody>
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
        </tbody>
    </table>
</div>