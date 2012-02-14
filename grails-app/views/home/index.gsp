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

    <g:render template="myOpenTasks" model="[]"/>

    <table class="twoColumnLayout">
        <tbody>
        <tr>
            <td class="layoutColumnFirst">
                <g:render template="myProjects" model="[projectCategoryList: projectCategoryList]"/>

                <div>&nbsp;</div>

                <g:render template="/common/activity" model="[activityList: activityList, listActivityAction: 'listActivity']"/>
            </td>
            <td class="layoutColumnLast">
                <g:render template="mySystems" model="[systemInstanceList: systemInstanceList]"/>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
