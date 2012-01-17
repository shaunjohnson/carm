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
    <g:header pageName="Administration" />

    <table class="twoColumnLayout">
        <tbody>
        <tr>
            <td class="layoutColumnFirst">
                <h2 class="sectionHeader">Source Control</h2>
                <ul>
                    <li><g:link controller="sourceControlServer">Servers</g:link></li>
                    <li><g:link controller="sourceControlRepository">Repositories</g:link></li>
                    <li><g:link controller="sourceControlUser">Users</g:link></li>
                    <li><g:link controller="sourceControlRole">Roles</g:link></li>
                </ul>
            </td>
            <td class="layoutColumnLast">
                <h2 class="sectionHeader">Types</h2>
                <ul>
                    <li><g:link controller="applicationDeploymentTestState">Application Deployment Test States</g:link></li>
                    <li><g:link controller="applicationReleaseTestState">Application Release Test States</g:link></li>
                    <li><g:link controller="applicationType">Application Types</g:link></li>
                    <li><g:link controller="moduleDeploymentTestState">Module Deployment Test States</g:link></li>
                    <li><g:link controller="moduleType">Module Types</g:link></li>
                    <li><g:link controller="projectCategory">Project Categories</g:link></li>
                </ul>
            </td>
        </tr>
        <tr>
            <td class="layoutColumnFirst">
                <h2 class="sectionHeader">Security</h2>
                <ul>
                    <li><g:link controller="user">Users</g:link></li>
                    <li><g:link controller="role">Roles</g:link></li>
                </ul>
            </td>
            <td class="layoutColumnLast">
                <h2 class="sectionHeader">Other</h2>
                <ul>
                    <li><a href="${createLink(uri: '/index.gsp')}">About</a></li>
                </ul>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
