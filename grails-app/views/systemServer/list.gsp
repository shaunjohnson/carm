<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'systemServer.label', default: 'System Server')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${systemServerInstanceList}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="list">
        <table>
            <thead>
            <tr>
                <g:sortableColumn property="name"
                                  title="${message(code: 'systemServer.name.label', default: 'Name')}"/>
                <g:sortableColumn property="description"
                                  title="${message(code: 'systemServer.description.label', default: 'Description')}"/>
                <th><g:message code="systemServer.system.label" default="System"/></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${systemServerInstanceList}" status="i" var="systemServerInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <td>
                        <g:link action="show" id="${systemServerInstance.id}">
                            ${fieldValue(bean: systemServerInstance, field: "name")}
                        </g:link>
                    </td>
                    <td>
                        <div class="expander">
                            <carm:plainText value="${systemServerInstance.description}"/>
                        </div>
                    </td>
                    <td>
                        <g:link controller="system" action="show" id="${systemServerInstance.system.id}">
                            ${fieldValue(bean: systemServerInstance, field: "system")}
                        </g:link>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${systemServerInstanceTotal}"/>
    </div>
</div>
</body>
</html>
