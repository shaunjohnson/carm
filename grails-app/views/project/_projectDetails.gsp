<div class="sectionHeader">
    <div class="text">
        <g:message code="details.label" default="Details"/>
    </div>
</div>

<div class="section-content">
    <table class="details">
        <tbody>
        <tr class="prop">
            <td valign="top" class="name">
                <g:message code="project.category.label" default="Category"/>
            </td>
            <td valign="top" class="value">
                <g:link controller="projectCategory" action="show" id="${projectInstance?.category?.id}">
                    ${fieldValue(bean: projectInstance, field: "category")}
                </g:link>
            </td>
        </tr>
        <tr class="prop">
            <td valign="top" class="name">
                <g:message code="project.description.label" default="Description"/>
            </td>
            <td valign="top" class="value">
                <div class="expander">
                    <carm:plainText value="${projectInstance?.description}"/>
                </div>
            </td>
        </tr>

        <carm:formDividerRow/>

        <tr class="prop">
            <td valign="top" class="name">
                <g:message code="project.dateCreated.label" default="Date Created"/>
            </td>
            <td valign="top" class="value">
                <g:formatDate date="${projectInstance?.dateCreated}"/>
            </td>
        </tr>
        <tr class="prop">
            <td valign="top" class="name">
                <g:message code="project.lastUpdated.label" default="Last Updated"/>
            </td>
            <td valign="top" class="value">
                <g:formatDate date="${projectInstance?.lastUpdated}"/>
            </td>
        </tr>
        </tbody>
    </table>
</div>