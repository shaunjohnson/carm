<%@ page import="carm.SourceControlUser" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'sourceControlUser.label', default: 'Source Control User')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${sourceControlUserInstanceList}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="list">
        <table>
            <thead>
            <tr>
                <g:sortableColumn property="name"
                                  title="${message(code: 'sourceControlUser.name.label', default: 'Name')}"/>
                <g:sortableColumn property="description"
                                  title="${message(code: 'sourceControlUser.description.label', default: 'Description')}"/>
                <th><g:message code="sourceControlUser.server.label" default="Source Control Server"/></th>
                <th><g:message code="sourceControlUser.user.label" default="User"/></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${sourceControlUserInstanceList}" status="i" var="sourceControlUserInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <td>
                        <g:link action="show" id="${sourceControlUserInstance.id}">
                            ${fieldValue(bean: sourceControlUserInstance, field: "name")}
                        </g:link>
                    </td>
                    <td>${fieldValue(bean: sourceControlUserInstance, field: "description")}</td>
                    <td>
                        <g:link controller="sourceControlServer" action="show"
                                id="${sourceControlUserInstance.server.id}">
                            ${fieldValue(bean: sourceControlUserInstance, field: "server")}
                        </g:link>
                    </td>
                    <td>
                        ${fieldValue(bean: sourceControlUserInstance, field: "user")}
                    </td>
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
