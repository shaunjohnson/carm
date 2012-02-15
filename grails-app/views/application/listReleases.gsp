<%@ page import="carm.ApplicationRelease" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'applicationRelease.label', default: 'Application Release')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${applicationInstance}" pageName="${message(code: 'default.listReleases.label', args: [entityName])}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="list">
        <table>
            <thead>
            <tr>
                <th><g:message code="applicationRelease.release.label" default="Release"/></th>
                <th><g:message code="applicationRelease.state.label" default="State"/></th>
                <th><g:message code="applicationRelease.dateCreated.label" default="Date Created"/></th>
                <th><g:message code="applicationRelease.changeLog.label" default="Change Log"/></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${applicationReleaseInstanceList}" status="i" var="applicationReleaseInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <td>
                        <g:link controller="applicationRelease" action="show" id="${applicationReleaseInstance.id}">
                            ${fieldValue(bean: applicationReleaseInstance, field: "releaseNumber")}
                        </g:link>
                    </td>
                    <td>
                        ${fieldValue(bean: applicationReleaseInstance, field: "releaseState")}
                    </td>
                    <td>
                        <carm:formatDateOnly date="${applicationReleaseInstance.dateCreated}"/>
                    </td>
                    <td>
                        ${fieldValue(bean: applicationReleaseInstance, field: "changeLog").decodeHTML()}
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${applicationReleaseInstanceTotal}" id="${applicationInstance.id}"/>
    </div>
</div>
</body>
</html>
