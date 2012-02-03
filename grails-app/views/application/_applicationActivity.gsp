<%@ page import="carm.Application" %>
<h2 class="sectionHeader">
    <g:message code="activity.label" default="Activity"/>
</h2>

<g:if test="${activityList?.size()}">
    <g:each in="${activityList}" var="activity" status="i">
        <p>
            <g:link controller="user" action="show" id="${activity.username}" style="font-weight: bold; padding-right: 1em;">
                ${activity.username}
            </g:link>
            <g:message code="activityTrace.${Application.class.name}.${activity.action}" args="[activity.objectName]"/>
        </p>

        <p style="color: #bbb; font-size: 0.9em;">
            <g:formatDate date="${activity.dateOccurred}"/>
        </p>
    </g:each>
</g:if>