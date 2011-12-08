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

    <p></p>

    <sec:ifNotLoggedIn>
        <p>
            You must <g:link controller='login' action='auth'>login</g:link> to use the system.
        </p>
    </sec:ifNotLoggedIn>

    <table style="width: 100%;">
        <tbody>
        <tr>
            <td>
                <h2>My Projects</h2>
                <ul style="margin: 1em 0;">
                    <g:each in="${projectInstanceList}" var="projectInstance">
                        <li><g:link controller="home" action="showProject" id="${projectInstance.id}">${projectInstance.name}</g:link></li>
                    </g:each>
                </ul>
            </td>
            <td>
                <h2>My Environments</h2>
                <g:each in="${systemInstanceList}" var="systemInstance">
                    <div style="margin: 0.5em 0;">
                        <h3><g:link controller="home" action="showSystem" id="${systemInstance.id}">${systemInstance.name}</g:link></h3>
                        <g:each in="${systemInstance.environments}" var="systemEnvironment">
                            <g:link controller="home" action="showSystemEnvironment" id="${systemEnvironment.id}">${systemEnvironment.name}</g:link>,
                        </g:each>
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
