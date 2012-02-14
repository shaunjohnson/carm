<%@ page import="carm.Project" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'home.label', default: 'Home')}"/>
    <title>${entityName}</title>
</head>

<body>
<div class="body">
    <g:header pageName="${message(code: 'home.label', default: 'Home')}"/>

    <h2 class="sectionHeader">
        <g:message code="myOpenTasks.label" default="My Open Tasks"/>
        <span class="actions">
            <g:link controller="requests" action="list">
                <g:message code="browseAllRequests.label" default="Browse All Requests"/>
            </g:link>
        </span>
    </h2>

    <p class="emphasis">
        <g:message code="youDontHaveAnyOpenTasks.message" default="You don't have any open tasks."/>
    </p>

    <table class="twoColumnLayout">
        <tbody>
        <tr>
            <td class="layoutColumnFirst">
                <g:render template="myProjects" model="[projectCategoryList: projectCategoryList"/>

                <div>&nbsp;</div>

                <g:render template="/common/activity" model="[activityList: activityList]"/>
            </td>
            <td class="layoutColumnLast">
                <g:render template="mySystems" model="[systemInstanceList: systemInstanceList"/>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
