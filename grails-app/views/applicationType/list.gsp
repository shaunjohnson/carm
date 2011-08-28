<%@ page import="net.lmxm.carm.ApplicationType" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'applicationType.label', default: 'ApplicationType')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
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

                <g:sortableColumn property="id" title="${message(code: 'applicationType.id.label', default: 'Id')}"/>

                <g:sortableColumn property="name" title="${message(code: 'applicationType.name.label', default: 'Name')}"/>

                <g:sortableColumn property="description" title="${message(code: 'applicationType.description.label', default: 'Description')}"/>

                <g:sortableColumn property="dateCreated" title="${message(code: 'applicationType.dateCreated.label', default: 'Date Created')}"/>

                <g:sortableColumn property="lastUpdated" title="${message(code: 'applicationType.lastUpdated.label', default: 'Last Updated')}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${applicationTypeInstanceList}" status="i" var="applicationTypeInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show" id="${applicationTypeInstance.id}">${fieldValue(bean: applicationTypeInstance, field: "id")}</g:link></td>

                    <td>${fieldValue(bean: applicationTypeInstance, field: "name")}</td>

                    <td>${fieldValue(bean: applicationTypeInstance, field: "description")}</td>

                    <td><g:formatDate date="${applicationTypeInstance.dateCreated}"/></td>

                    <td><g:formatDate date="${applicationTypeInstance.lastUpdated}"/></td>

                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
    <div class="paginateButtons">
        <g:paginate total="${applicationTypeInstanceTotal}"/>
    </div>
</div>
</body>
</html>