
<%@ page import="net.lmxm.carm.ModuleType" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'moduleType.label', default: 'ModuleType')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'moduleType.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'moduleType.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="description" title="${message(code: 'moduleType.description.label', default: 'Description')}" />
                        
                            <g:sortableColumn property="dateCreated" title="${message(code: 'moduleType.dateCreated.label', default: 'Date Created')}" />
                        
                            <g:sortableColumn property="lastUpdated" title="${message(code: 'moduleType.lastUpdated.label', default: 'Last Updated')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${moduleTypeInstanceList}" status="i" var="moduleTypeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${moduleTypeInstance.id}">${fieldValue(bean: moduleTypeInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: moduleTypeInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: moduleTypeInstance, field: "description")}</td>
                        
                            <td><g:formatDate date="${moduleTypeInstance.dateCreated}" /></td>
                        
                            <td><g:formatDate date="${moduleTypeInstance.lastUpdated}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${moduleTypeInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
