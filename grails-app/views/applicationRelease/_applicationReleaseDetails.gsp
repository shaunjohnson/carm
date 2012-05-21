<div class="sectionHeader">
    <div class="text">
        <g:message code="details.label" default="Details"/>
    </div>
</div>

<table class="details">
    <tbody>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="applicationRelease.application.label" default="Application"/>
        </td>
        <td valign="top" class="value">
            <g:link controller="application" action="show" id="${applicationReleaseInstance?.application?.id}">
                ${applicationReleaseInstance?.application?.encodeAsHTML()}
            </g:link>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="application.sysEnvironment.label" default="System"/>
        </td>
        <td valign="top" class="value">
            <g:link controller="systemEnvironment" action="show"
                    id="${applicationReleaseInstance?.application?.sysEnvironment?.id}">
                ${applicationReleaseInstance?.application?.sysEnvironment?.encodeAsHTML()}
            </g:link>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="applicationRelease.releaseState.label" default="Release State"/>
        </td>
        <td valign="top" class="value">
            ${fieldValue(bean: applicationReleaseInstance, field: "releaseState")}
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="applicationRelease.changeLog.label" default="Change Log"/>
        </td>
        <td valign="top" class="value">
            ${fieldValue(bean: applicationReleaseInstance, field: "changeLog").decodeHTML()}
        </td>
    </tr>

    <carm:formDividerRow/>

    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="applicationRelease.buildPath.label" default="Build Path"/>
        </td>
        <td valign="top" class="value">
            ${fieldValue(bean: applicationReleaseInstance, field: "buildPath")}
            <br/>
            <carm:formatSourceControl application="${applicationReleaseInstance.application}"/>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="applicationRelease.buildInstructions.label" default="Build Instructions"/>
        </td>
        <td valign="top" class="value">
            ${fieldValue(bean: applicationReleaseInstance, field: "buildInstructions").decodeHTML()}
        </td>
    </tr>

    <carm:formDividerRow/>

    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="applicationRelease.dateCreated.label" default="Date Created"/>
        </td>
        <td valign="top" class="value">
            <g:formatDate date="${applicationReleaseInstance?.dateCreated}"/>
        </td>
    </tr>
    <tr class="prop">
        <td valign="top" class="name">
            <g:message code="applicationRelease.lastUpdated.label" default="Last Updated"/>
        </td>
        <td valign="top" class="value">
            <g:formatDate date="${applicationReleaseInstance?.lastUpdated}"/>
        </td>
    </tr>
    </tbody>
</table>