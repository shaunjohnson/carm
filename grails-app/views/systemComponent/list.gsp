<%@ page import="carm.SystemComponent" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'systemComponent.label', default: 'System Component')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <g:header domain="${systemComponentInstanceList}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="list">
        <table>
            <thead>
            <tr>
                <g:sortableColumn property="name"
                                  title="${message(code: 'systemComponent.name.label', default: 'Name')}"/>
                <g:sortableColumn property="description"
                                  title="${message(code: 'systemComponent.description.label', default: 'Description')}"/>
                <th><g:message code="systemComponent.system.label" default="System"/></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${systemComponentInstanceList}" status="i" var="systemComponentInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <td>
                        <g:link action="show" id="${systemComponentInstance.id}">
                            ${fieldValue(bean: systemComponentInstance, field: "name")}
                        </g:link>
                    </td>
                    <td>
                        ${fieldValue(bean: systemComponentInstance, field: "description")}
                    </td>
                    <td>
                        <g:link controller="system" action="show" id="${systemComponentInstance.system.id}">
                            ${fieldValue(bean: systemComponentInstance, field: "system")}
                        </g:link>
                    </td>
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
