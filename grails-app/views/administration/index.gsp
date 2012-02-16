<%@ page import="carm.Project" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'administration.label', default: 'Administration')}"/>
    <title>${entityName}</title>
</head>

<body>
<div class="body">
    <carm:header pageName="${message(code: 'administration.label', default: 'Administration')}"/>

    <table class="twoColumnLayout">
        <tbody>
        <tr>
            <td class="layoutColumnFirst">
                <div class="sectionHeader">
                    <div class="text">
                        <g:message code="sourceControl.label" default="Source Control"/>
                    </div>
                </div>
                <ul>
                    <li>
                        <g:link controller="sourceControlServer">
                            <g:message code="servers.label" default="Servers"/>
                        </g:link>
                    </li>
                    <li>
                        <g:link controller="sourceControlRole">
                            <g:message code="roles.label" default="Roles"/>
                        </g:link>
                    </li>
                </ul>
            </td>
            <td class="layoutColumnLast">
                <div class="sectionHeader">
                    <div class="text">
                        <g:message code="types.label" default="Types"/>
                    </div>
                </div>
                <ul>
                    <li>
                        <g:link controller="applicationDeploymentTestState">
                            <g:message code="applicationDeploymentTestStates.label"
                                       default="Application Deployment Test States"/>
                        </g:link>
                    </li>
                    <li>
                        <g:link controller="applicationReleaseTestState">
                            <g:message code="applicationReleaseTestStates.label"
                                       default="Application Release Test States"/>
                        </g:link>
                    </li>
                    <li>
                        <g:link controller="applicationType">
                            <g:message code="applicationTypes.label" default="Application Types"/>
                        </g:link>
                    </li>
                    <li>
                        <g:link controller="moduleDeploymentTestState">
                            <g:message code="moduleDeploymentTestStates.label" default="Module Deployment Test States"/>
                        </g:link>
                    </li>
                    <li>
                        <g:link controller="moduleType">
                            <g:message code="moduleTypes.label" default="Module Types"/>
                        </g:link>
                    </li>
                    <li>
                        <g:link controller="projectCategory">
                            <g:message code="projectCategories.label" default="Project Categories"/>
                        </g:link>
                    </li>
                </ul>
            </td>
        </tr>
        <tr>
            <td class="layoutColumnFirst">
                <div class="sectionHeader">
                    <div class="text">
                        <g:message code="security.label" default="Security"/>
                    </div>
                </div>
                <ul>
                    <li>
                        <g:link controller="user">
                            <g:message code="users.label" default="Users"/>
                        </g:link>
                    </li>
                    <li>
                        <g:link controller="role">
                            <g:message code="roles.label" default="Roles"/>
                        </g:link>
                    </li>
                </ul>
            </td>
            <td class="layoutColumnLast">
                <div class="sectionHeader">
                    <div class="text">
                        <g:message code="other.label" default="Other"/>
                    </div>
                </div>
                <ul>
                    <li>
                        <g:link uri="/index.gsp">
                            <g:message code="about.label" default="About"/>
                        </g:link>
                    </li>
                </ul>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
