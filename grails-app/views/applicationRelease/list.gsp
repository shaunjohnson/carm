<%@ page import="carm.ApplicationRelease" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'applicationRelease.label', default: 'ApplicationRelease')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<div class="body">
    <g:header domain="${applicationReleaseList}" pageName="${message(code: 'default.list.label', args: [entityName])}" />

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <g:render template="applicationReleaseList" model="[applicationReleaseInstanceList: applicationReleaseInstanceList" />

    <div class="paginateButtons">
        <g:paginate total="${applicationReleaseInstanceTotal}" />
    </div>
</div>
</body>
</html>
