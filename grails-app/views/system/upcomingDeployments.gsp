<%@ page import="carm.deployment.ApplicationDeployment" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'applicationDeployment.label', default: 'Application Deployment')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${systemInstance}" pageName="${message(code: 'default.list.label', args: [entityName])}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <g:if test="${applicationDeploymentsGrouped?.size()}">
        <g:each in="${applicationDeploymentsGrouped.entrySet()}" var="requestedDeploymentDateEntry">
            <div class="sectionHeader">
                <div class="text">
                    <g:formatDate date="${requestedDeploymentDateEntry.key}" type="date" dateStyle="FULL"/>
                </div>
            </div>

            <div style="margin-bottom: 2em;">
                <g:each in="${requestedDeploymentDateEntry.value.entrySet()}" var="sysEnvironmentEntry">
                    <h3>${sysEnvironmentEntry.key}</h3>

                    <g:each in="${sysEnvironmentEntry.value.entrySet()}" var="applicationTypeEntry">
                        <div style="margin-left: 2em;">
                            <h4>${applicationTypeEntry.key}</h4>

                            <ul style="margin-left: 1em;">
                                <g:each in="${applicationTypeEntry.value}" var="applicationDeployment">
                                    <li>
                                        <g:link controller="application" action="show"
                                                id="${applicationDeployment.applicationRelease.application.id}"
                                                title="${message(code: 'showApplication.label')}">${applicationDeployment.applicationRelease.application.name}</g:link>

                                        (<g:link controller="applicationRelease" action="show"
                                                 id="${applicationDeployment.applicationRelease.id}"
                                                 title="${message(code: 'showApplicationRelease.label')}">${applicationDeployment.applicationRelease.releaseNumber}</g:link>) -

                                        <g:link controller="applicationDeployment" action="show"
                                                id="${applicationDeployment.id}"
                                                title="${message(code: 'showApplicationDeployment.label')}">
                                            <g:message code="viewDeployment.label" default="View Deployment"/>
                                        </g:link>
                                    </li>
                                </g:each>
                            </ul>
                        </div>
                    </g:each>
                </g:each>
            </div>
        </g:each>
    </g:if>
    <g:else>
        <p class="emphasis">
            <g:message code="noUpcomingDeploymentsForSystem.message"
                       default="There are no upcoming deployments for this system."/>
        </p>
    </g:else>
</div>
</body>
</html>
