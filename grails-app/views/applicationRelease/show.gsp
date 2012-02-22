<%@ page import="carm.enums.ApplicationReleaseState; carm.ApplicationRelease; carm.ModuleRelease" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'applicationRelease.label', default: 'Application Release')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${applicationReleaseInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <carm:isProjectOwner applicationRelease="${applicationReleaseInstance}">
        <div class="buttons">
            <g:if test="${applicationReleaseInstance.releaseState == ApplicationReleaseState.COMPLETED}">
                <carm:button controller="applicationDeployment" action="create"
                             params="['applicationRelease.id': applicationReleaseInstance.id]">
                    <g:message code="deployThisRelease.label" default="Deploy this Release"/>
                </carm:button>
            </g:if>
        </div>
    </carm:isProjectOwner>

    <div class="dialog">
        <table id="applicationReleaseDetails" class="details">
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
                    <g:message code="application.system.label" default="System"/>
                </td>
                <td valign="top" class="value">
                    <g:link controller="system" action="show"
                            id="${applicationReleaseInstance?.application?.system?.id}">
                        ${applicationReleaseInstance?.application?.system?.encodeAsHTML()}
                    </g:link>
                </td>
            </tr>
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

            <tr class="prop detailProp">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="applicationRelease.dateCreated.label" default="Date Created"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${applicationReleaseInstance?.dateCreated}"/>
                </td>
            </tr>
            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="applicationRelease.lastUpdated.label" default="Last Updated"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${applicationReleaseInstance?.lastUpdated}"/>
                </td>
            </tr>
            </tbody>

            <carm:isProjectOwner applicationRelease="${applicationReleaseInstance}">
                <tfoot class="detailProp">
                <tr>
                    <td colspan="2">
                        <div class="buttons">
                            <span class="button">
                                <g:link class="edit" action="edit" id="${applicationReleaseInstance?.id}">
                                    <g:message code="default.button.edit.label" default="Edit"/>
                                </g:link>
                            </span>
                            <span class="button">
                                <g:link class="delete" action="delete" id="${applicationReleaseInstance?.id}">
                                    <g:message code="default.button.delete.label" default="Delete"/>
                                </g:link>
                            </span>
                        </div>
                    </td>
                </tr>
                </tfoot>
            </carm:isProjectOwner>
        </table>
        <carm:showHideDetails sectionId="applicationReleaseDetails" entityName="${entityName}"/>
    </div>

    <table class="twoColumnLayout">
        <tbody>
        <tr>
            <td class="layoutColumnFirst">
                <g:render template="applicationReleaseModules"
                          model="['applicationReleaseInstance': applicationReleaseInstance]"/>
            </td>
            <td class="layoutColumnLast">
                <g:render template="applicationReleaseDeployments"
                          model="['applicationReleaseInstance': applicationReleaseInstance]"/>

                <div>&nbsp;</div>

                <g:render template="applicationReleaseHistory"
                          model="['applicationReleaseInstance': applicationReleaseInstance]"/>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
