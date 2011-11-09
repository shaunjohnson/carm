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

    <h2>Application Releases</h2>
    <g:render template="/applicationRelease/applicationReleaseList"
              model="[applicationReleaseInstanceList: applicationReleaseInstanceList" />
    
    <h2>Projects</h2>
    <g:each in="${projectInstanceList}" var="projectInstance">
        <h3><g:link controller="home" action="showProject" id="${projectInstance.id}">${projectInstance.name}</g:link></h3>

        <ol>
        <g:each in="${projectInstance.applications.sort { it.name }}" var="applicationInstance">
            <li><g:link controller="home" action="showApplication" id="${applicationInstance.id}">${applicationInstance.name}</g:link></li>
        </g:each>
        </ol>
    </g:each>
</div>
</body>
</html>
