<%@ page import="carm.release.ApplicationReleaseTestState" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName"
           value="${message(code: 'applicationReleaseTestState.label', default: 'Application Release Test State')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${applicationReleaseTestStateInstanceList}"
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

    <g:if test="${applicationReleaseTestStateInstanceTotal}">
        <div class="list">
            <table>
                <thead>
                <tr>
                    <g:sortableColumn property="name"
                                      title="${message(code: 'applicationReleaseTestState.name.label', default: 'Name')}"/>
                    <g:sortableColumn property="description"
                                      title="${message(code: 'applicationReleaseTestState.description.label', default: 'Description')}"/>
                </tr>
                </thead>
                <tbody>
                <g:each in="${applicationReleaseTestStateInstanceList}" status="i"
                        var="applicationReleaseTestStateInstance">
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td>
                            <g:link action="show" id="${applicationReleaseTestStateInstance.id}">
                                ${fieldValue(bean: applicationReleaseTestStateInstance, field: "name")}
                            </g:link>
                        </td>
                        <td>
                            ${fieldValue(bean: applicationReleaseTestStateInstance, field: "description")}
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>

        <div class="paginateButtons">
            <g:paginate total="${applicationReleaseTestStateInstanceTotal}"/>
        </div>
    </g:if>
    <g:else>
        <p class="emphasis">
            <g:message code="carmNoApplicationReleaseTestStates.message"
                       default="There are no application release test states."/>
        </p>
    </g:else>
</div>
</body>
</html>
