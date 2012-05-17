<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${userInstanceList}" pageName="${message(code: 'default.list.label', args: [entityName])}"/>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>

<table class="table table-striped">
    <thead>
    <tr>
        <g:sortableColumn property="fullName" title="${message(code: 'user.fullName.label', default: 'Full Name')}"/>
        <g:sortableColumn property="username" title="${message(code: 'user.username.label', default: 'Username')}"/>
    </tr>
    </thead>
    <tbody>
    <g:each in="${userInstanceList}" status="i" var="userInstance">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
            <td>
                <g:link action="show" id="${userInstance.id}">
                    ${fieldValue(bean: userInstance, field: "fullName")}
                </g:link>
            </td>
            <td>
                ${fieldValue(bean: userInstance, field: "username")}
            </td>
        </tr>
    </g:each>
    </tbody>
</table>

<div class="paginateButtons">
    <g:paginate total="${userInstanceTotal}" max="${grailsApplication.config.ui.user.listMax}"/>
</div>
</body>
</html>
