<g:set var="size" value="${activityList?.size() ?: 0}"/>
<g:set var="maxRecords" value="${Math.min(size, grailsApplication.config.ui.activity.maxRecords)}"/>
<g:set var="activitySubList" value="${activityList.subList(0, maxRecords)}"/>

<div class="sectionHeader">
    <div class="text">
        <g:message code="activity.label" default="Activity"/>
    </div>
    <g:if test="${size > grailsApplication.config.ui.activity.maxRecords && listActivityAction}">
        <g:set var="controller" value="${params.controller ==~ /.*home/ ? 'home' : params.controller}"/>

        <div class="actions">
            <g:link controller="${controller}" action="${listActivityAction}" id="${domainId}">
                <g:message code="allActivity.label" default="All Activity"/>
            </g:link>
        </div>
    </g:if>
</div>

<g:if test="${size}">
    <g:each in="${activitySubList}" var="activity" status="i">
        <p>
            <g:link class="activityUsername" controller="user" action="show" params="[username: activity.username]">
                ${activity.username}
            </g:link>

            <g:render template="/common/activityMessage" model="[activity: activity]"/>

            <br>

            <carm:formatDateTimePeriod class="activityDateOccurred" value="${activity.dateOccurred}"/>
        </p>
    </g:each>
</g:if>
<g:else>
    <p class="emphasis">
        <g:message code="noRecordedActivity.message" default="There is no recorded activity."/>
    </p>
</g:else>