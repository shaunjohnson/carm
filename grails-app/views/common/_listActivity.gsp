<%@ page import="org.joda.time.Period; org.joda.time.DateTime" %>
<div class="list">
    <table>
        <thead>
        <tr>
            <th><g:message code="activityTrace.username.label" default="Username"/></th>
            <th><g:message code="activityTrace.message.label" default="Message"/></th>
            <th><g:message code="activityTrace.dateOccurred.label" default="Date Occurred"/></th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${activityList}" status="i" var="activity">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td>
                    <g:link class="activityUsername" controller="user" action="show" id="${activity.username}">
                        ${activity.username}
                    </g:link>
                </td>
                <td>
                    <g:render template="/common/activityMessage" model="[activity: activity]"/>
                </td>
                <td>
                    <carm:formatDateTimePeriod value="${activity.dateOccurred}"/>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
