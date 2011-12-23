<%@ page import="carm.Application" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'application.label', default: 'Application')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>
<body>
<div class="body">
    <g:header domain="${applicationInstanceList}" pageName="${message(code: 'default.list.label', args: [entityName])}" />

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="list">
        <table>
            <thead>
            <tr>
                <g:sortableColumn property="name" title="${message(code: 'application.name.label', default: 'Name')}"/>
                <g:sortableColumn property="description" title="${message(code: 'application.description.label', default: 'Description')}"/>
                <th><g:message code="application.type.label" default="Type"/></th>
                <th><g:message code="application.project.label" default="Project"/></th>
                <th><g:message code="application.sourceControlRepository.label" default="Source Control Repository"/></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${applicationInstanceList}" status="i" var="applicationInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <td><g:link action="show" id="${applicationInstance.id}">${fieldValue(bean: applicationInstance, field: "name")}</g:link></td>
                    <td>${fieldValue(bean: applicationInstance, field: "description")}</td>
                    <td><g:link controller="applicationType" action="show" id="${applicationInstance.type.id}">${fieldValue(bean: applicationInstance, field: "type")}</g:link></td>
                    <td><g:link controller="project" action="show" id="${applicationInstance.project.id}">${fieldValue(bean: applicationInstance, field: "project")}</g:link></td>
                    <td>
                        <g:if test="${applicationInstance.sourceControlRepository}">
                            <g:link controller="sourceControlRepository" action="show" id="${applicationInstance.sourceControlRepository.id}">
                                ${fieldValue(bean: applicationInstance, field: "sourceControlRepository")}
                            </g:link>
                        </g:if>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
    <div class="paginateButtons">
        <g:paginate total="${applicationInstanceTotal}"/>
    </div>
</div>
</body>
</html>
