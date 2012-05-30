<html>
<head>
    <title>Notification</title>
</head>

<body>

<%
    def applicationAnchor = "<a href='${applicationLink}'>${applicationName}</a>"
    def applicationReleaseAnchor = "<a href='${applicationReleaseLink}'>${applicationReleaseNumber}</a>"
%>

<h2><g:message code="notification.APPLICATION_RELEASE_REJECTED.subject"
               args="[applicationAnchor, applicationReleaseAnchor]"/></h2>

<p>
    <a href="${applicationReleaseLink}">View the release</a>
</p>

<p>
    <strong><g:message code="applicationRelease.changeLog.label"/></strong>
</p>

${applicationReleaseChangeLog}

</body>
</html>