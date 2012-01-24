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
    </h2>

    <p class="emphasis">
        <g:message code="youDontHaveAnyOpenTasks.message" default="You don't have any open tasks."/>
    </p>

    <table class="twoColumnLayout">
        <tbody>
        <tr>
            <td class="layoutColumnFirst">
                <g:render template="myProjects" model="[projectCategoryList: projectCategoryList"/>
            </td>
            <td class="layoutColumnLast">
                <g:render template="mySystems" model="[systemInstanceList: systemInstanceList"/>
            </td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td>
                <g:link controller="project" action="list">
                    <g:message code="browseAllProjects.label" default="Browse All Projects"/>
                </g:link>
            </td>
            <td>
                <g:link controller="system" action="list">
                    <g:message code="browseAllSystems.label" default="Browse All Systems"/>
                </g:link>
            </td>
        </tr>
        </tfoot>
    </table>
</div>
</body>
</html>
