<%@ page import="carm.ProjectCategory" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'projectCategory.label', default: 'ProjectCategory')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>
<body>
<div class="body">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>

    <div class="nav">
        <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></span>
    </div>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="list">
        <table>
            <thead>
            <tr>
                <g:sortableColumn property="name" title="${message(code: 'projectCategory.name.label', default: 'Name')}"/>
                <g:sortableColumn property="description" title="${message(code: 'projectCategory.description.label', default: 'Description')}"/>
            </tr>
            </thead>
            <tbody>
            <g:each in="${projectCategoryInstanceList}" status="i" var="projectCategoryInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <td><g:link action="show" id="${projectCategoryInstance.id}">${fieldValue(bean: projectCategoryInstance, field: "name")}</g:link></td>
                    <td>${fieldValue(bean: projectCategoryInstance, field: "description")}</td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
    <div class="paginateButtons">
        <g:paginate total="${projectCategoryInstanceTotal}"/>
    </div>
</div>
</body>
</html>
