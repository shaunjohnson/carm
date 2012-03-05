<%@ page import="carm.module.ModuleType" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'moduleType.label', default: 'Module Type')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${moduleTypeInstanceList}"
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

    <g:if test="${moduleTypeInstanceTotal}">
        <div class="list">
            <table>
                <thead>
                <tr>
                    <g:sortableColumn property="name"
                                      title="${message(code: 'moduleType.name.label', default: 'Name')}"/>
                    <g:sortableColumn property="description"
                                      title="${message(code: 'moduleType.description.label', default: 'Description')}"/>
                </tr>
                </thead>
                <tbody>
                <g:each in="${moduleTypeInstanceList}" status="i" var="moduleTypeInstance">
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td>
                            <g:link action="show" id="${moduleTypeInstance.id}">
                                ${fieldValue(bean: moduleTypeInstance, field: "name")}
                            </g:link>
                        </td>
                        <td>
                            <div class="expander">
                                ${fieldValue(bean: moduleTypeInstance, field: "description")}
                            </div>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>

        <div class="paginateButtons">
            <g:paginate total="${moduleTypeInstanceTotal}"/>
        </div>
    </g:if>
    <g:else>
        <p class="emphasis">
            <g:message code="carmNoModuleTypes.message" default="There are no module types."/>
        </p>
    </g:else>
</div>
</body>
</html>
