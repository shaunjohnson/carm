<%@ page import="carm.Application" %>
<%@ page import="carm.ApplicationRelease" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'application.label', default: 'Application')}"/>
    <g:set var="entityReleaseName" value="${message(code: 'applicationRelease.label', default: 'Application Release')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>
<body>
<sec:ifLoggedIn>
    <div class="nav">
        <span class="menuButton">
            <g:link class="create" controller="applicationRelease" action="create" params="['application.id': applicationInstance?.id]">
                <g:message code="default.new.label" args="[entityReleaseName]"/>
            </g:link>
        </span>
    </div>
</sec:ifLoggedIn>
<div class="body">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
            <tbody>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.name.label" default="Name"/></td>
                <td valign="top" class="value">${fieldValue(bean: applicationInstance, field: "name")}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.description.label" default="Description"/></td>
                <td valign="top" class="value">${fieldValue(bean: applicationInstance, field: "description")}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.type.label" default="Type"/></td>
                <td valign="top" class="value">${applicationInstance?.type?.encodeAsHTML()}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.project.label" default="Project"/></td>
                <td valign="top" class="value"><g:link controller="home" action="showProject" id="${applicationInstance?.project?.id}">${applicationInstance?.project?.encodeAsHTML()}</g:link></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.sourceControlRepository.label" default="Source Control Repository"/></td>
                <td valign="top" class="value">
                    <g:formatSourceControlRepository sourceControlRepository="${applicationInstance?.sourceControlRepository}"/>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.system.label" default="System"/></td>
                <td valign="top" class="value"><g:link controller="home" action="showSystem" id="${applicationInstance?.system?.id}">${applicationInstance?.system?.encodeAsHTML()}</g:link></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.applicationRoles.label" default="Application Roles"/></td>
                <td valign="top" style="text-align: left;" class="value">
                    <g:if test="${applicationInstance.applicationRoles}">
                        <ul>
                            <g:each in="${applicationInstance.applicationRoles}" var="a">
                                <li>${a?.encodeAsHTML()}</li>
                            </g:each>
                        </ul>
                    </g:if>
                    <g:else>
                        None
                    </g:else>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="application.modules.label" default="Modules"/></td>
                <td valign="top" style="text-align: left;" class="value">
                    <g:if test="${applicationInstance.modules}">
                        <ul>
                            <g:each in="${applicationInstance.modules.sort { it.name }}" var="m">
                                <li><g:link controller="home" action="showModule" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
                            </g:each>
                        </ul>
                    </g:if>
                    <g:else>
                        None
                    </g:else>
                </td>
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
        </table>
    </div>

    <h2>Environments</h2>
    <div class="dialog">
        <table>
            <tbody>
            <g:each in="${applicationInstance.system.environments}" var="environment">
                <tr>
                    <td>${environment}</td>
                    <td><a href="#">Latest release</a></td>
                    <td>on ??/??/????</td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <h2><g:message code="default.list.label" args="[entityReleaseName]"/></h2>
    <g:set var="applicationReleaseInstanceList" value="${ApplicationRelease.findAllByApplication(applicationInstance).sort{ it.releaseNumber }.reverse()}"/>

    <g:if test="${applicationReleaseInstanceList?.size()}">
        <g:render template="/applicationRelease/applicationReleaseList" model="[applicationReleaseInstanceList: applicationReleaseInstanceList]"/>
    </g:if>
</div>
</body>
</html>
