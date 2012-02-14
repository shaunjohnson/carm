<%@ page import="carm.Module; org.joda.time.DateTime; org.joda.time.Period; carm.enums.ActivityAction; carm.Application" %>

<g:set var="size" value="${activityList?.size() ?: 0}"/>
<g:set var="maxRecords" value="${Math.min(size, grailsApplication.config.ui.activity.maxRecords)}"/>
<g:set var="activitySubList" value="${activityList.subList(0, maxRecords)}"/>

<h2 class="sectionHeader">
    <g:message code="activity.label" default="Activity"/>

    <g:if test="${size > grailsApplication.config.ui.activity.maxRecords && listActivityAction}">
        <span class="actions">
            <g:link action="${listActivityAction}">
                <g:message code="allActivity.label" default="All Activity"/>
            </g:link>
        </span>
    </g:if>
</h2>

<g:if test="${size}">
    <g:each in="${activitySubList}" var="activity" status="i">
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
<g:else>
    <p class="emphasis">
        <g:message code="noRecordedActivity.message" default="There is no recorded activity."/>
    </p>
</g:else>