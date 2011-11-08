<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'application.label', default: 'Home')}"/>
    <title>${entityName}</title>
</head>
<body>
<div class="body">
    <h1>${entityName}</h1>

    <p>Welcome to the Change And Request Management system known as CARM.</p>

    <sec:ifNotLoggedIn>
        <p>
        You must <g:link controller='login' action='auth'>login</g:link> to use the system.
        </p>
    </sec:ifNotLoggedIn>

    <sec:ifLoggedIn>
        <h2>Application Releases</h2>
        <div class="list">
            <table>
                <thead>
                <tr>
                    <th>
                        <g:message code="application.project.label" default="Project"/> -
                        <g:message code="applicationRelease.application.label" default="Application"/>
                    </th>
                    <th><g:message code="applicationRelease.description.label" default="Description"/></th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${applicationReleaseInstanceList}" status="i" var="applicationReleaseInstance">
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td>
                            <g:link controller="applicationRelease" action="show" id="${applicationReleaseInstance.id}">${applicationReleaseInstance.application.project} -
                                ${fieldValue(bean: applicationReleaseInstance, field: "application")}</g:link>
                        </td>
                        <td>${fieldValue(bean: applicationReleaseInstance, field: "description")}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>

        <h2>Projects</h2>
        <g:each in="${projectInstanceList}" var="projectInstance">
            <h3>${projectInstance.name}</h3>

            <ol>
            <g:each in="${projectInstance.applications.sort { it.name }}" var="applicationInstance">
                <li>${applicationInstance.name}</li>
            </g:each>
            </ol>
        </g:each>
    </sec:ifLoggedIn>
</div>
</body>
</html>
