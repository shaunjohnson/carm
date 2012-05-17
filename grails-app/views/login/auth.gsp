<html>
<head>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <title><g:message code="springSecurity.login.title"/></title>
</head>

<body>
<carm:header pageName="${message(code: "login.label", default: "Login")}"/>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>

<div class="row">
    <div class="span5">
        <g:render template="/home/login"
                  model="[loginPostUrl: postUrl, rememberMeParameter: rememberMeParameter, hasCookie: hasCookie]"/>
    </div>
</div>

</body>
</html>
