<%@ page import="carm.Application; carm.Module; carm.enums.ActivityAction" %>
<g:if test="${activity.objectType == Application.class.name && activity.action != ActivityAction.DELETED}">
    <g:link controller="application" action="show" id="${activity.objectId}">
        <g:message code="activityTrace.${activity.objectType}.${activity.action}"
                   args="[activity.objectName]"/>
    </g:link>
</g:if>
<g:elseif test="${activity.objectType == Module.class.name && activity.action != ActivityAction.DELETED}">
    <g:link controller="module" action="show" id="${activity.objectId}">
        <g:message code="activityTrace.${activity.objectType}.${activity.action}"
                   args="[activity.objectName]"/>
    </g:link>
</g:elseif>
<g:else>
    <g:message code="activityTrace.${activity.objectType}.${activity.action}" args="[activity.objectName]"/>
</g:else>