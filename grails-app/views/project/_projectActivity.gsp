<%@ page import="org.joda.time.DateTime; org.joda.time.Period; carm.enums.ActivityAction; carm.Application" %>
<h2 class="sectionHeader">
    <g:message code="activity.label" default="Activity"/>
</h2>

<g:if test="${activityList?.size()}">
    <g:each in="${activityList}" var="activity" status="i">
        <p>
            <g:link controller="user" action="show" id="${activity.username}"
                    style="font-weight: bold; padding-right: 1em;">
                ${activity.username}
            </g:link>
            <g:if test="${activity.objectType == Application.class.name && activity.action != ActivityAction.DELETED}">
                <g:link controller="application" action="show" id="${activity.objectId}">
                    <g:message code="activityTrace.${activity.objectType}.${activity.action}"
                               args="[activity.objectName]"/>
                </g:link>
            </g:if>
            <g:else>
                <g:message code="activityTrace.${activity.objectType}.${activity.action}" args="[activity.objectName]"/>
            </g:else>
        </p>

        <p style="color: #bbb; font-size: 0.9em;" title='<joda:format value="${activity.dateOccurred}"/>'>
            <joda:formatPeriod value="${new Period(activity.dateOccurred, new DateTime())}"/> ago
        </p>
    </g:each>
</g:if>