<html>
<head>
    <title>Notification</title>
</head>

<body>

<%
    def applicationAnchor = "<a href='${applicationLink}'>${applicationName}</a>"
    def applicationReleaseAnchor = "<a href='${applicationReleaseLink}'>${applicationReleaseNumber}</a>"
    def systemDeploymentEnvironmentAnchor = "<a href='${systemDeploymentEnvironmentLink}'>${systemDeploymentEnvironmentName}</a>"
%>

<h2><g:message code="notification.APPLICATION_DEPLOYMENT_COMPLETED.subject"
               args="[applicationAnchor, applicationReleaseAnchor, systemDeploymentEnvironmentAnchor]"/></h2>

<p>
    <a href="${applicationDeploymentLink}">View the deployment</a>
</p>

<p>
    <strong><g:message code="applicationRelease.changeLog.label"/></strong>
</p>

${applicationReleaseChangeLog}

</body>
</html>