<%@ page import="carm.Module; org.joda.time.DateTime; org.joda.time.Period; carm.enums.ActivityAction; carm.Application" %>
<h2 class="sectionHeader">
    <g:message code="activity.label" default="Activity"/>
</h2>

<g:if test="${activityList?.size()}">
    <g:each in="${activityList}" var="activity" status="i">
        <p>
            <g:link class="activityUsername" controller="user" action="show" id="${activity.username}">
                ${activity.username}
            </g:link>

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

            <br>

            <span class="activityDateOccurred" title='<joda:format value="${activity.dateOccurred}"/>'>
                <joda:formatPeriod value="${new Period(activity.dateOccurred, new DateTime())}"/>
                <g:message code="ago.label" default="ago"/>
            </span>
        </p>
    </g:each>
</g:if>