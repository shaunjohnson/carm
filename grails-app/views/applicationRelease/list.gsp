<%@ page import="net.lmxm.carm.ApplicationRelease" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'applicationRelease.label', default: 'ApplicationRelease')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
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
                            <th><g:message code="applicationRelease.application.label" default="Application" /></th>
                            <g:sortableColumn property="description" title="${message(code: 'applicationRelease.description.label', default: 'Description')}" />
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${applicationReleaseInstanceList}" status="i" var="applicationReleaseInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td><g:link action="show" id="${applicationReleaseInstance.id}">${fieldValue(bean: applicationReleaseInstance, field: "application")}</g:link></td>
                            <td>${fieldValue(bean: applicationReleaseInstance, field: "description")}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${applicationReleaseInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
