<%@ page import="carm.System" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'system.label', default: 'System')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></span>
</div>
<div class="body">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
        <table>
            <thead>
            <tr>
                <g:sortableColumn property="name" title="${message(code: 'system.name.label', default: 'Name')}"/>
                <g:sortableColumn property="description" title="${message(code: 'system.description.label', default: 'Description')}"/>
            </tr>
            </thead>
            <tbody>
            <g:each in="${systemInstanceList}" status="i" var="systemInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <td><g:link action="show" id="${systemInstance.id}">${fieldValue(bean: systemInstance, field: "name")}</g:link></td>
                    <td>${fieldValue(bean: systemInstance, field: "description")}</td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
    <div class="paginateButtons">
        <g:paginate total="${systemInstanceTotal}"/>
    </div>
</div>
</body>
</html>
