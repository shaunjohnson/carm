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
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>

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
                    <td>${fieldValue(bean: applicationInstance, field: "type")}</td>
                    <td>${fieldValue(bean: applicationInstance, field: "project")}</td>
                    <td>${fieldValue(bean: applicationInstance, field: "sourceControlRepository")}</td>
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
