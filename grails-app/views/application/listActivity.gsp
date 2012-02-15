<%@ page import="carm.ApplicationRelease" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'activity.label', default: 'Activity')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${applicationInstance}" pageName="${message(code: 'default.listActivity.label', args: [entityName])}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <g:render template="/common/listActivity" model="[activityList: activityList]"/>

    <div class="paginateButtons">
        <g:paginate total="${activityTotal}" id="${applicationInstance.id}"/>
    </div>
</div>
</body>
</html>
