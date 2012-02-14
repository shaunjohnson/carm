<%@ page import="org.joda.time.DateTime; org.joda.time.Period" %>

<g:set var="size" value="${activityList?.size() ?: 0}"/>
<g:set var="maxRecords" value="${Math.min(size, grailsApplication.config.ui.activity.maxRecords)}"/>
<g:set var="activitySubList" value="${activityList.subList(0, maxRecords)}"/>

<h2 class="sectionHeader">
    <g:message code="activity.label" default="Activity"/>

    <g:if test="${size > grailsApplication.config.ui.activity.maxRecords && listActivityAction}">
        <span class="actions">
            <g:link action="${listActivityAction}" id="${domainId}">
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

            <g:render template="/common/activityMessage" model="[activity: activity]"/>

            <br>

            <g:formatDateTimePeriod value="${activity.dateOccurred}"/>
        </p>
    </g:each>
</g:if>
<g:else>
    <p class="emphasis">
        <g:message code="noRecordedActivity.message" default="There is no recorded activity."/>
    </p>
</g:else>