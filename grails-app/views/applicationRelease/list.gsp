<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'applicationRelease.label', default: 'Application Release')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${applicationReleaseList}"
                 pageName="${message(code: 'default.list.label', args: [entityName])}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="list">
        <table>
            <thead>
            <tr>
                <th>
                    <g:message code="application.project.label" default="Project"/> -
                    <g:message code="applicationRelease.application.label" default="Application"/>
                </th>
                <g:sortableColumn property="releaseNumber"
                                  title="${message(code: 'applicationRelease.releaseNumber.label', default: 'Release Number')}"/>
                <g:sortableColumn property="releaseState"
                                  title="${message(code: 'applicationRelease.releaseState.label', default: 'Release State')}"/>
                <th>
                    <g:message code="applicationRelease.changeLog.label" default="Change Log"/>
                </th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${applicationReleaseInstanceList}" status="i" var="applicationReleaseInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <td>
                        <g:link controller="applicationRelease" action="show" id="${applicationReleaseInstance.id}">
                            ${applicationReleaseInstance.application.project} -
                            ${fieldValue(bean: applicationReleaseInstance, field: "application")}
                        </g:link>
                    </td>
                    <td>
                        ${fieldValue(bean: applicationReleaseInstance, field: "releaseNumber")}
                    </td>
                    <td>
                        ${fieldValue(bean: applicationReleaseInstance, field: "releaseState")}
                    </td>
                    <td>
                        <div class="expander">
                            ${fieldValue(bean: applicationReleaseInstance, field: "changeLog").decodeHTML()}
                        </div>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${applicationReleaseInstanceTotal}"
                    max="${grailsApplication.config.ui.applicationRelease.listMax}"/>
    </div>
</div>
</body>
</html>
