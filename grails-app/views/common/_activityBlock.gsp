<div id="activityBlock">
    <g:each in="${activityList}" var="activity" status="i">
        <p>
            <g:link class="activityUsername" controller="user" action="show" params="[username: activity.username]">
                ${activity.username}
            </g:link>

            <carm:activityMessage activity="${activity}"/>

            <br>

            <carm:formatDateTimePeriod class="activityDateOccurred" value="${activity.dateOccurred}"/>
        </p>
    </g:each>
</div>