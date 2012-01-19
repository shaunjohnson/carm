<%@ page import="carm.SystemEnvironment" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'systemEnvironment.label', default: 'System Environment')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <g:header domain="${systemEnvironmentInstanceList}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="list">
        <table>
            <thead>
            <tr>
                <g:sortableColumn property="name"
                                  title="${message(code: 'systemEnvironment.name.label', default: 'Name')}"/>
                <g:sortableColumn property="description"
                                  title="${message(code: 'systemEnvironment.description.label', default: 'Description')}"/>
                <th><g:message code="systemEnvironment.system.label" default="System"/></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${systemEnvironmentInstanceList}" status="i" var="systemEnvironmentInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <td>
                        <g:link action="show" id="${systemEnvironmentInstance.id}">
                            ${fieldValue(bean: systemEnvironmentInstance, field: "name")}
                        </g:link>
                    </td>
                    <td>
                        ${fieldValue(bean: systemEnvironmentInstance, field: "description")}
                    </td>
                    <td>
                        <g:link controller="system" action="show" id="${systemEnvironmentInstance.system.id}">
                            ${fieldValue(bean: systemEnvironmentInstance, field: "system")}
                        </g:link>
                    </td>
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
