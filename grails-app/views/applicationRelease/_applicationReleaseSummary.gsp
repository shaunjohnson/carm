<div class="sectionHeader">
    <div class="text">
        <g:message code="summary.label" default="Summary"/>
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
            <g:message code="applicationRelease.releaseState.label" default="Release State"/>
        </td>
        <td valign="top" class="value">
            <carm:formatApplicationReleaseState releaseState="${applicationReleaseInstance.releaseState}"/>
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

    <g:if test="${applicationReleaseInstance.application.binariesPath}">
        <tr class="prop">
            <td valign="top" class="name">
                <g:message code="application.binariesPath.label" default="Binaries Path"/>
            </td>
            <td valign="top" class="value">
                <carm:formatBinariesPath applicationRelease="${applicationReleaseInstance}"/>
            </td>
        </tr>
    </g:if>
    </tbody>
</table>