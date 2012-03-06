<%@ page import="carm.release.ApplicationReleaseState; carm.deployment.ApplicationDeploymentState; carm.release.ApplicationRelease; carm.deployment.ApplicationDeployment" %>
<ul>
    <g:each in="${pendingTasks}" status="i" var="task">
        <li>
            <g:if test="${task instanceof ApplicationDeployment}">
                <g:link controller="applicationDeployment" action="show" id="${task.id}"
                        title="${message(code: 'showApplicationDeployment.label')}">Deployment</g:link>
                of
                <g:link controller="application" action="show" id="${task.applicationRelease.application.id}"
                        title="${message(code: 'showApplication.label')}">${task.applicationRelease.application.name}</g:link>
                release
                <g:link controller="applicationRelease" action="show" id="${task.applicationRelease.id}"
                        title="${message(code: 'showApplicationRelease.label')}">${task.applicationRelease.releaseNumber}</g:link>
                to
                <g:link controller="systemEnvironment" action="show" id="${task.sysEnvironment.id}"
                        title="${message(code: 'showSystemEnvironment.label')}">${task.sysEnvironment.name}</g:link>

                <g:if test="${task.deploymentState == ApplicationDeploymentState.DRAFT}">
                    was created, but has not been submitted.
                </g:if>
                <g:elseif test="${task.deploymentState == ApplicationDeploymentState.SUBMITTED}">
                    was submitted, but not yet deloyed.
                </g:elseif>
            </g:if>
            <g:elseif test="${task instanceof ApplicationRelease}">
                <g:link controller="applicationRelease" action="show" id="${task.id}"
                        title="${message(code: 'showApplicationRelease.label')}">Release ${task.releaseNumber}</g:link>
                of
                <g:link controller="application" action="show" id="${task.application.id}"
                        title="${message(code: 'showApplication.label')}">${task.application.name}</g:link>

                <g:if test="${task.releaseState == ApplicationReleaseState.DRAFT}">
                    was created, but has not been submitted.
                </g:if>
                <g:elseif test="${task.releaseState == ApplicationReleaseState.SUBMITTED}">
                    was submitted, but not yet built.
                </g:elseif>
            </g:elseif>
            <g:else>
                ${task}
            </g:else>
        </li>
    </g:each>
</ul>