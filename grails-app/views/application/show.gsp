<%@ page import="carm.ApplicationRelease; carm.Application" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'application.label', default: 'Application')}"/>
    <g:set var="entityReleaseName" value="${message(code: 'applicationRelease.label', default: 'Application Release')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>
<body>
<div class="body">
    <g:header domain="${applicationInstance}" />

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="dialog">
        <table class="details">
            <tbody>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.project.label" default="Project"/></td>
                <td valign="top" class="value">
                    <g:link controller="project" action="show" id="${applicationInstance?.project?.id}">${applicationInstance?.project?.encodeAsHTML()}</g:link>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.type.label" default="Type"/></td>
                <td valign="top" class="value">
                    <g:link controller="applicationType" action="show" id="${applicationInstance?.type?.id}">${applicationInstance?.type?.encodeAsHTML()}</g:link>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.description.label" default="Description"/></td>
                <td valign="top" class="value">${fieldValue(bean: applicationInstance, field: "description")}</td>
            </tr>

            <tr class="prop">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.sourceControlRepository.label" default="Source Control Repository"/></td>
                <td valign="top" class="value">
                    <g:link controller="sourceControlRepository" action="show" id="${applicationInstance?.sourceControlRepository?.id}">${applicationInstance?.sourceControlRepository?.encodeAsHTML()}</g:link>
                    <g:formatSourceControlRepository sourceControlRepository="${applicationInstance?.sourceControlRepository}"/>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.sourceControlPath.label" default="Source Control Path"/></td>
                <td valign="top" class="value">${fieldValue(bean: applicationInstance, field: "sourceControlPath")}</td>
            </tr>

            <tr class="prop">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.system.label" default="System"/></td>
                <td valign="top" class="value"><g:link controller="system" action="show" id="${applicationInstance?.system?.id}">${applicationInstance?.system?.encodeAsHTML()}</g:link></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.deployInstructions.label" default="Deploy Instructions"/></td>
                <td valign="top" class="value">${fieldValue(bean: applicationInstance, field: "deployInstructions")}</td>
            </tr>

            <tr class="prop">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.applicationRoles.label" default="Application Roles"/></td>
                <td valign="top" style="text-align: left;" class="value">
                    <g:if test="${applicationInstance.applicationRoles}">
                        <ul>
                            <g:each in="${applicationInstance.applicationRoles.sort{ it.sourceControlUser.name }}" var="a">
                                <li><g:link controller="applicationRole" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
                            </g:each>
                        </ul>
                    </g:if>
                    <g:else>
                        <div>None</div>
                    </g:else>
                    <div class="nav">
                        <span class="menuButton"><g:link class="create" controller="applicationRole" action="create" params="['application.id': applicationInstance?.id]">Add Application Role</g:link></span>
                    </div>
                </td>
            </tr>

            <tr class="prop">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.dateCreated.label" default="Date Created"/></td>
                <td valign="top" class="value"><g:formatDate date="${applicationInstance?.dateCreated}"/></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.lastUpdated.label" default="Last Updated"/></td>
                <td valign="top" class="value"><g:formatDate date="${applicationInstance?.lastUpdated}"/></td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="2">
                    <div class="buttons">
                        <span class="button">
                            <g:link class="edit" action="edit" id="${applicationInstance?.id}">
                                <g:message code="default.button.edit.label" default="Edit"/>
                            </g:link>
                        </span>
                        <span class="button">
                            <g:link class="delete" action="delete" id="${applicationInstance?.id}"
                                    onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
                                <g:message code="default.button.delete.label" default="Delete"/>
                            </g:link>
                        </span>
                    </div>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>

    <h2>Modules</h2>

    <div class="nav">
        <span class="menuButton"><g:link class="create" controller="module" action="create" params="['application.id': applicationInstance?.id]">Add Module</g:link></span>
    </div>

    <div class="list">
        <table>
            <thead>
            <tr>
                <th><g:message code="module.name.label" default="Name"/></th>
                <th><g:message code="module.systemComponents.label" default="System Components"/></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${applicationInstance.modules.sort { it.name }}" var="moduleInstance">
                <tr>
                    <td><g:link controller="module" action="show" id="${moduleInstance.id}">${moduleInstance?.encodeAsHTML()}</g:link></td>
                    <td>
                        <ul>
                            <g:each in="${moduleInstance.systemComponents.sort { it.name }}" var="s">
                                <li>
                                    <g:link controller="systemComponent" action="show"
                                            id="${s?.id}">${s?.encodeAsHTML()}</g:link>
                                    [<g:link controller="system" action="show"
                                             id="${s?.system?.id}">${s?.system?.encodeAsHTML()}</g:link>]
                                </li>
                            </g:each>
                        </ul>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <g:if test="${applicationInstance?.system?.environments?.size()}">
        <h2>Environments</h2>
        <div class="dialog">
            <table>
                <tbody>
                <g:each in="${applicationInstance.system.environments}" var="environment">
                    <tr>
                        <td>
                            <g:link controller="systemEnvironment" action="show" id="${environment.id}">
                                ${environment.name.encodeAsHTML()}
                            </g:link>
                        </td>
                        <td><a href="#">Latest release</a></td>
                        <td>on ??/??/????</td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
    </g:if>

    <g:set var="applicationReleaseInstanceList" value="${ApplicationRelease.findAllByApplication(applicationInstance).sort{ it.releaseNumber }.reverse()}"/>

    <g:if test="${applicationReleaseInstanceList?.size()}">
        <h2><g:message code="default.list.label" args="[entityReleaseName]"/></h2>
        <g:render template="/applicationRelease/applicationReleaseList" model="[applicationReleaseInstanceList: applicationReleaseInstanceList]"/>
    </g:if>
</div>
</body>
</html>
