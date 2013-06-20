<div class="sectionHeader">
    <div class="text">
        <g:message code="details.label"/>
    </div>
</div>

<div class="section-content">
    <table class="details">
        <tbody>
        <tr class="prop">
            <td class="name">
                <g:message code="application.description.label"/>
            </td>
            <td class="value">
                <div class="expander">
                    <div class="expander">
                        <carm:plainText value="${applicationInstance.description}"/>
                    </div>
                </div>
            </td>
        </tr>

        <carm:formDividerRow/>

        <tr class="prop">
            <td class="name">
                <g:message code="application.sourceControlRepository.label"/>
            </td>
            <td class="value">
                <carm:formatSourceControl repository="${applicationInstance?.sourceControlRepository}"/>
            </td>
        </tr>
        <tr class="prop">
            <td class="name">
                <g:message code="application.sourceControlPath.label"/>
            </td>
            <td class="value">
                <carm:formatSourceControl application="${applicationInstance}"/>
            </td>
        </tr>
        <tr class="prop">
            <td class="name">
                <g:message code="application.binariesPath.label"/>
            </td>
            <td class="value">
                <g:fieldValue field="binariesPath" bean="${applicationInstance}"/>
            </td>
        </tr>
        <tr class="prop">
            <td class="name">
                <g:message code="application.buildInstructions.label"/>
            </td>
            <td class="value">
                ${fieldValue(bean: applicationInstance, field: "buildInstructions")?.decodeHTML()}
            </td>
        </tr>

        <carm:formDividerRow/>

        <tr class="prop">
            <td class="name">
                <g:message code="application.sysEnvironment.label"/>
            </td>
            <td class="value">
                <g:link controller="systemEnvironment" action="show" id="${applicationInstance?.sysEnvironment?.id}">
                    <g:fieldValue field="sysEnvironment" bean="${applicationInstance}"/>
                </g:link>
            </td>
        </tr>
        <tr class="prop">
            <td class="name">
                <g:message code="application.deployInstructions.label"/>
            </td>
            <td class="value">
                ${fieldValue(bean: applicationInstance, field: "deployInstructions")?.decodeHTML()}
            </td>
        </tr>

        <carm:formDividerRow/>
        </tbody>
    </table>

    <div class="row">
        <div class="span3">
            <label class="propName"><g:message code="application.project.label"/></label>
            <g:link controller="project" action="show" id="${applicationInstance?.project?.id}">
                <g:fieldValue field="project" bean="${applicationInstance}"/>
            </g:link>
        </div>

        <div class="span3">
            <label class="propName"><g:message code="application.type.label"/></label>
            <g:link controller="applicationType" action="show" id="${applicationInstance?.type?.id}">
                <g:fieldValue field="type" bean="${applicationInstance}"/>
            </g:link>
        </div>

        <div class="span3">
            <label class="propName"><g:message code="application.notificationScheme.label"/></label>
            <g:link controller="notificationScheme" action="show" id="${applicationInstance.notificationScheme.id}">
                <g:fieldValue field="notificationScheme" bean="${applicationInstance}"/>
            </g:link>
        </div>
    </div>

    <hr/>

    <div class="row">
        <div class="span2">
            <label class="propName"><g:message code="application.dateCreated.label"/></label>
            <g:formatDate date="${applicationInstance?.dateCreated}"/>
        </div>

        <div class="span2">
            <label class="propName"><g:message code="application.lastUpdated.label"/></label>
            <g:formatDate date="${applicationInstance?.lastUpdated}"/>
        </div>
    </div>
</div>