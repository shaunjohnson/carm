<%@ page import="carm.ApplicationRelease" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'applicationRelease.label', default: 'ApplicationRelease')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<div class="body">
    <g:header domain="${applicationReleaseInstance}" />

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="dialog">
        <table id="applicationReleaseDetails" class="details">
        <tbody>
        <tr class="prop">
            <td valign="top" class="name"><g:message code="applicationRelease.application.label" default="Application"/></td>
            <td valign="top" class="value">
                <g:link controller="application" action="show"
                        id="${applicationReleaseInstance?.application?.id}">${applicationReleaseInstance?.application?.encodeAsHTML()}</g:link>
                [<g:link controller="project" action="show"
                         id="${applicationReleaseInstance?.application?.project?.id}">${applicationReleaseInstance?.application?.project?.encodeAsHTML()}</g:link>]
            </td>
        </tr>
        <tr class="prop">
            <td valign="top" class="name"><g:message code="applicationRelease.changeLog.label" default="Change Log" /></td>
            <td valign="top" class="value">${fieldValue(bean: applicationReleaseInstance, field: "changeLog")}</td>
        </tr>

        <tr class="prop detailProp">
            <td colspan="2">&nbsp;</td>
        </tr>

        <tr class="prop detailProp">
            <td valign="top" class="name"><g:message code="applicationRelease.dateCreated.label" default="Date Created" /></td>
            <td valign="top" class="value"><g:formatDate date="${applicationReleaseInstance?.dateCreated}" /></td>
        </tr>
        <tr class="prop detailProp">
            <td valign="top" class="name"><g:message code="applicationRelease.lastUpdated.label" default="Last Updated" /></td>
            <td valign="top" class="value"><g:formatDate date="${applicationReleaseInstance?.lastUpdated}" /></td>
        </tr>
        </tbody>
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
                        <g:link class="delete" action="delete" id="${applicationReleaseInstance?.id}"
                                onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
                            <g:message code="default.button.delete.label" default="Delete"/>
                        </g:link>
                    </span>
                </div>
            </td>
        </tr>
        </tfoot>
        </table>
        <g:showHideDetails sectionId="applicationReleaseDetails" entityName="$entityName"/>
    </div>
</div>
</body>
</html>
