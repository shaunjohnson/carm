<%@ page import="carm.release.ApplicationRelease; carm.project.Project; carm.application.Application; carm.module.Module; carm.activity.ActivityAction" %>

<g:set var="activityMessage" value="${message(code: "activityTrace.${activity.objectType}.${activity.action}", args: [activity.objectName])}"/>

<g:if test="${activity.objectType == Application.class.name && activity.action != ActivityAction.DELETED && Application.exists(activity.objectId)}">
    <g:link controller="application" action="show" id="${activity.objectId}">
        ${activityMessage.encodeAsHTML()}
    </g:link>
</g:if>
<g:elseif test="${activity.objectType == ApplicationRelease.class.name && activity.action != ActivityAction.DELETED && ApplicationRelease.exists(activity.objectId)}">
    <g:link controller="applicationRelease" action="show" id="${activity.objectId}">
        ${activityMessage.encodeAsHTML()}
    </g:link>
</g:elseif>
<g:elseif test="${activity.objectType == Module.class.name && activity.action != ActivityAction.DELETED && Module.exists(activity.objectId)}">
    <g:link controller="module" action="show" id="${activity.objectId}">
        ${activityMessage.encodeAsHTML()}
    </g:link>
</g:elseif>
<g:elseif test="${activity.objectType == Project.class.name && activity.action != ActivityAction.DELETED && Project.exists(activity.objectId)}">
    <g:link controller="project" action="show" id="${activity.objectId}">
        ${activityMessage.encodeAsHTML()}
    </g:link>
</g:elseif>
<g:else>
    ${activityMessage.encodeAsHTML()}
</g:else>