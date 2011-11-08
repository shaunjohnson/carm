<%@ page import="net.lmxm.carm.SourceControlServer" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'sourceControlServer.label', default: 'SourceControlServer')}"/>
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
                <g:sortableColumn property="name" title="${message(code: 'sourceControlServer.name.label', default: 'Name')}"/>
                <g:sortableColumn property="description" title="${message(code: 'sourceControlServer.description.label', default: 'Description')}"/>
                <g:sortableColumn property="type" title="${message(code: 'sourceControlServer.type.label', default: 'Type')}"/>
                <g:sortableColumn property="url" title="${message(code: 'sourceControlServer.url.label', default: 'Url')}"/>
                <g:sortableColumn property="dateCreated" title="${message(code: 'sourceControlServer.dateCreated.label', default: 'Date Created')}"/>
            </tr>
            </thead>
            <tbody>
            <g:each in="${sourceControlServerInstanceList}" status="i" var="sourceControlServerInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <td><g:link action="show" id="${sourceControlServerInstance.id}">${fieldValue(bean: sourceControlServerInstance, field: "name")}</g:link></td>
                    <td>${fieldValue(bean: sourceControlServerInstance, field: "description")}</td>
                    <td>${fieldValue(bean: sourceControlServerInstance, field: "type")}</td>
                    <td>${fieldValue(bean: sourceControlServerInstance, field: "url")}</td>
                    <td><g:formatDate date="${sourceControlServerInstance.dateCreated}"/></td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
    <div class="paginateButtons">
        <g:paginate total="${sourceControlServerInstanceTotal}"/>
    </div>
</div>
</body>
</html>
