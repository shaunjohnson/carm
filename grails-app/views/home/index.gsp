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
    <h1>Change And Release Management</h1>

    <sec:ifNotLoggedIn>
        <p>
            You must <g:link controller='login' action='auth'>login</g:link> to use the system.
        </p>
    </sec:ifNotLoggedIn>

    <h2 class="sectionHeader">My Requests</h2>
    <p>
        TODO
    </p>

    <table class="twoColumnLayout">
        <tbody>
        <tr>
            <td class="layoutColumnFirst">
                <h2 class="sectionHeader">My Projects</h2>
                <g:each in="${projectCategoryList}" var="projectCategory">
                    <g:set var="projects" value="${Project.findAllByCategory(projectCategory).sort { it.name } }"/>
                    <g:if test="${projects.size()}">
                        <div style="margin: 0.5em 0;">
                            <h3><g:link controller="projectCategory" action="show" id="${projectCategory.id}">${projectCategory.name}</g:link></h3>
                            <ul>
                                <g:each in="${projects}" var="projectInstance">
                                    <li><g:link controller="project" action="show" id="${projectInstance.id}">${projectInstance.name}</g:link></li>
                                </g:each>
                            </ul>
                        </div>
                    </g:if>
                </g:each>
            </td>
            <td class="layoutColumnLast">
                <h2 class="sectionHeader">My Environments</h2>
                <g:each in="${systemInstanceList}" var="systemInstance">
                    <div style="margin: 0.5em 0;">
                        <h3><g:link controller="system" action="show" id="${systemInstance.id}">${systemInstance.name}</g:link></h3>
                        <ul>
                        <g:each in="${systemInstance.environments}" var="systemEnvironment">
                            <li><g:link controller="systemEnvironment" action="show" id="${systemEnvironment.id}">${systemEnvironment.name}</g:link></li>
                        </g:each>
                        </ul>
                    </div>
                </g:each>
            </td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td>
                <g:link controller="project" action="list">Browse All Projects</g:link>
            </td>
            <td>
                <g:link controller="system" action="list">Browse All Systems</g:link>
            </td>
        </tr>
        </tfoot>
    </table>
</div>
</body>
</html>
