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
                <h2 class="sectionHeader">
                    <g:message code="myProjects.label" default="My Projects"/>
                </h2>

                <g:each in="${projectCategoryList}" var="projectCategory">
                    <g:set var="projects" value="${Project.findAllByCategory(projectCategory).sort { it.name }}"/>

                    <g:if test="${projects.size()}">
                        <div style="margin: 0.5em 0;">
                            <h3>
                                <g:link controller="projectCategory" action="show" id="${projectCategory.id}">
                                    ${projectCategory.encodeAsHTML()}
                                </g:link>
                            </h3>
                            <ul>
                                <g:each in="${projects}" var="projectInstance">
                                    <li>
                                        <g:link controller="project" action="show" id="${projectInstance.id}">
                                            ${projectInstance.encodeAsHTML()}
                                        </g:link>
                                    </li>
                                </g:each>
                            </ul>
                        </div>
                    </g:if>
                </g:each>
            </td>
            <td class="layoutColumnLast">
                <h2 class="sectionHeader">
                    <g:message code="myEnvironments.label" default="My Environments"/>
                </h2>
                <g:each in="${systemInstanceList}" var="systemInstance">
                    <div style="margin: 0.5em 0;">
                        <h3>
                            <g:link controller="system" action="show" id="${systemInstance.id}">
                                ${systemInstance.encodeAsHTML()}
                            </g:link>
                        </h3>
                        <g:each in="${systemInstance.environments}" var="systemEnvironment" status="i">
                            <g:link controller="systemEnvironment" action="show" id="${systemEnvironment.id}">
                                ${systemEnvironment.encodeAsHTML()}
                            </g:link><g:if test="${(i + 1) < systemInstance.environments.size()}">,</g:if>
                        </g:each>
                        <div style="margin: 0.5em 0;">
                            <g:link controller="applicationDeployment" action="upcomingDeployments"
                                    params="[systemId: systemInstance.id]">
                                <g:message code="upcomingDeployments.label" default="Upcoming Deployments"/>
                            </g:link>
                        </div>
                    </div>
                </g:each>
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
