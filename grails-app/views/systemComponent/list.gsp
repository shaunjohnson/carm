<%@ page import="net.lmxm.carm.SystemComponent" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'systemComponent.label', default: 'SystemComponent')}"/>
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
                <g:sortableColumn property="name" title="${message(code: 'systemComponent.name.label', default: 'Name')}"/>
                <g:sortableColumn property="description" title="${message(code: 'systemComponent.description.label', default: 'Description')}"/>
                <th><g:message code="systemComponent.system.label" default="System"/></th>
                <g:sortableColumn property="dateCreated" title="${message(code: 'systemComponent.dateCreated.label', default: 'Date Created')}"/>
                <g:sortableColumn property="lastUpdated" title="${message(code: 'systemComponent.lastUpdated.label', default: 'Last Updated')}"/>
            </tr>
            </thead>
            <tbody>
            <g:each in="${systemComponentInstanceList}" status="i" var="systemComponentInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <td><g:link action="show" id="${systemComponentInstance.id}">${fieldValue(bean: systemComponentInstance, field: "name")}</g:link></td>
                    <td>${fieldValue(bean: systemComponentInstance, field: "description")}</td>
                    <td>${fieldValue(bean: systemComponentInstance, field: "system")}</td>
                    <td><g:formatDate date="${systemComponentInstance.dateCreated}"/></td>
                    <td><g:formatDate date="${systemComponentInstance.lastUpdated}"/></td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
    <div class="paginateButtons">
        <g:paginate total="${systemComponentInstanceTotal}"/>
    </div>
</div>
</body>
</html>
