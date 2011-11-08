<%@ page import="net.lmxm.carm.SourceControlUser" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'sourceControlUser.label', default: 'SourceControlUser')}"/>
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
                <g:sortableColumn property="name" title="${message(code: 'sourceControlUser.name.label', default: 'Name')}"/>
                <g:sortableColumn property="description" title="${message(code: 'sourceControlUser.description.label', default: 'Description')}"/>
                <th><g:message code="sourceControlUser.sourceControlServer.label" default="Source Control Server"/></th>
                <th><g:message code="sourceControlUser.user.label" default="User"/></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${sourceControlUserInstanceList}" status="i" var="sourceControlUserInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <td><g:link action="show" id="${sourceControlUserInstance.id}">${fieldValue(bean: sourceControlUserInstance, field: "name")}</g:link></td>
                    <td>${fieldValue(bean: sourceControlUserInstance, field: "description")}</td>
                    <td>${fieldValue(bean: sourceControlUserInstance, field: "sourceControlServer")}</td>
                    <td>${fieldValue(bean: sourceControlUserInstance, field: "user")}</td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
    <div class="paginateButtons">
        <g:paginate total="${sourceControlUserInstanceTotal}"/>
    </div>
</div>
</body>
</html>
