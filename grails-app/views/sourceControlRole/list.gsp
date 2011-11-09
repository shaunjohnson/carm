<%@ page import="carm.SourceControlRole" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'sourceControlRole.label', default: 'SourceControlRole')}"/>
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
                <g:sortableColumn property="name" title="${message(code: 'sourceControlRole.name.label', default: 'Name')}"/>
                <g:sortableColumn property="description" title="${message(code: 'sourceControlRole.description.label', default: 'Description')}"/>
            </tr>
            </thead>
            <tbody>
            <g:each in="${sourceControlRoleInstanceList}" status="i" var="sourceControlRoleInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <td><g:link action="show" id="${sourceControlRoleInstance.id}">${fieldValue(bean: sourceControlRoleInstance, field: "name")}</g:link></td>
                    <td>${fieldValue(bean: sourceControlRoleInstance, field: "description")}</td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
    <div class="paginateButtons">
        <g:paginate total="${sourceControlRoleInstanceTotal}"/>
    </div>
</div>
</body>
</html>
