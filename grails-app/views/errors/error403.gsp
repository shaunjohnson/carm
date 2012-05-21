<html>
<head>
    <title><g:message code="accessDenied.message" default="Access Denied!"/></title>
    <meta name='layout' content='main'/>
    <r:require modules="common"/>
</head>

<body>
<h1><g:message code="accessDenied.message" default="Access Denied!"/></h1>

<g:render template="/common/messages"/>

<p>
    <em><g:message code="accessDeniedExplanation.message"
                   default="We're sorry, but you are not authorized to perform the requested operation."/></em>
</p>
</body>
</html>