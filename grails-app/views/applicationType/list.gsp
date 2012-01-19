<%@ page import="carm.ApplicationType" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'applicationType.label', default: 'Application Type')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <g:header domain="${applicationTypeInstanceList}"
              pageName="${message(code: 'default.list.label', args: [entityName])}"/>

    <sec:ifAllGranted roles="ROLE_ADMIN">
        <div class="nav">
            <span class="menuButton">
                <g:link class="create" action="create">
                    <g:message code="default.new.label" args="[entityName]"/>
                </g:link>
            </span>
        </div>
    </sec:ifAllGranted>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="list">
        <table>
            <thead>
            <tr>
                <g:sortableColumn property="name"
                                  title="${message(code: 'applicationType.name.label', default: 'Name')}"/>
                <g:sortableColumn property="description"
                                  title="${message(code: 'applicationType.description.label', default: 'Description')}"/>
            </tr>
            </thead>
            <tbody>
            <g:each in="${applicationTypeInstanceList}" status="i" var="applicationTypeInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <td>
                        <g:link action="show" id="${applicationTypeInstance.id}">
                            ${fieldValue(bean: applicationTypeInstance, field: "name")}
                        </g:link>
                    </td>
                    <td>
                        ${fieldValue(bean: applicationTypeInstance, field: "description")}
                    </td>
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
