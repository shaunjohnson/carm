<%@ page import="net.lmxm.carm.SystemEnvironment" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'systemEnvironment.label', default: 'SystemEnvironment')}"/>
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
                <g:sortableColumn property="name" title="${message(code: 'systemEnvironment.name.label', default: 'Name')}"/>
                <g:sortableColumn property="description" title="${message(code: 'systemEnvironment.description.label', default: 'Description')}"/>
                <th><g:message code="systemEnvironment.system.label" default="System"/></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${systemEnvironmentInstanceList}" status="i" var="systemEnvironmentInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <td><g:link action="show" id="${systemEnvironmentInstance.id}">${fieldValue(bean: systemEnvironmentInstance, field: "name")}</g:link></td>
                    <td>${fieldValue(bean: systemEnvironmentInstance, field: "description")}</td>
                    <td>${fieldValue(bean: systemEnvironmentInstance, field: "system")}</td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
    <div class="paginateButtons">
        <g:paginate total="${systemEnvironmentInstanceTotal}"/>
    </div>
</div>
</body>
</html>
