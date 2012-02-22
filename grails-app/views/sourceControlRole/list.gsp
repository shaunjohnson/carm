<%@ page import="carm.SourceControlRole" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'sourceControlRole.label', default: 'Source Control Role')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${sourceControlRoleInstanceList}"/>

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

    <g:if test="${sourceControlRoleInstanceTotal}">
        <div class="list">
            <table>
                <thead>
                <tr>
                    <g:sortableColumn property="name"
                                      title="${message(code: 'sourceControlRole.name.label', default: 'Name')}"/>
                    <g:sortableColumn property="description"
                                      title="${message(code: 'sourceControlRole.description.label', default: 'Description')}"/>
                </tr>
                </thead>
                <tbody>
                <g:each in="${sourceControlRoleInstanceList}" status="i" var="sourceControlRoleInstance">
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td>
                            <g:link action="show" id="${sourceControlRoleInstance.id}">
                                ${fieldValue(bean: sourceControlRoleInstance, field: "name")}
                            </g:link>
                        </td>
                        <td>
                            ${fieldValue(bean: sourceControlRoleInstance, field: "description")}
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>

        <div class="paginateButtons">
            <g:paginate total="${sourceControlRoleInstanceTotal}"/>
        </div>
    </g:if>
    <g:else>
        <p class="emphasis">
            <g:message code="carmNoSourceControlRoles.message" default="There are no source control roles."/>
        </p>
    </g:else>
</div>
</body>
</html>
