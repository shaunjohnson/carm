<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'application.label', default: 'Home')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
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


</div>
</body>
</html>
