<%@ page import="carm.ApplicationRelease; carm.ModuleRelease" %>
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
            <td valign="top" class="name"><g:message code="applicationRelease.buildPath.label" default="Build Path" /></td>
            <td valign="top" class="value">
                ${fieldValue(bean: applicationReleaseInstance, field: "buildPath")}
                <br/>
                <g:formatSourceControl application="${applicationReleaseInstance.application}"/>
            </td>
        </tr>
        <tr class="prop">
            <td valign="top" class="name"><g:message code="applicationRelease.changeLog.label" default="Change Log" /></td>
            <td valign="top" class="value">${fieldValue(bean: applicationReleaseInstance, field: "changeLog").decodeHTML()}</td>
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
                        <g:link class="delete" action="delete" id="${applicationReleaseInstance?.id}">
                            <g:message code="default.button.delete.label" default="Delete"/>
                        </g:link>
                    </span>
                    <span class="button">
                        <g:link controller="applicationDeployment" action="create"
                                params="['applicationRelease.id': applicationReleaseInstance.id]">
                            Deploy this Release
                        </g:link>
                    </span>
                </div>
            </td>
        </tr>
        </tfoot>
        </table>
        <g:showHideDetails sectionId="applicationReleaseDetails" entityName="$entityName"/>
    </div>

    <g:if test="${applicationReleaseInstance?.moduleReleases?.size()}">
        <h2>Modules</h2>

        <div class="list">
            <table style="width: 100%;">
                <thead>
                <tr>
                    <th><g:message code="module.name.label" default="Name"/></th>
                    <th><g:message code="module.systemComponents.label" default="System Components"/></th>
                    <th><g:message code="module.system.label" default="System"/></th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${applicationReleaseInstance?.moduleReleases.sort { it.name }}" var="moduleReleaseInstance">
                    <tr>
                        <td><g:link controller="module" action="show" id="${moduleReleaseInstance.module.id}">${moduleReleaseInstance.module?.encodeAsHTML()}</g:link></td>
                        <td>
                            <ul>
                                <g:each in="${moduleReleaseInstance.module.systemComponents.sort { it.name }}" var="s">
                                    <li>
                                        <g:link controller="systemComponent" action="show"
                                                id="${s?.id}">${s?.encodeAsHTML()}</g:link>
                                    </li>
                                </g:each>
                            </ul>
                        </td>
                        <td>
                            <g:link controller="system" action="show" id="${moduleReleaseInstance.module?.application?.system?.id}">
                                ${moduleReleaseInstance.module?.application?.system?.encodeAsHTML()}
                            </g:link>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
    </g:if>
</div>
</body>
</html>
