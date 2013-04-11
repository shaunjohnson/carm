<div id="activityBlock">
    <g:each in="${activityList}" var="activity" status="i">
        <p style="margin-bottom: 1em;">
            <g:link class="activityUsername" controller="user" action="show"
                    params="[username: activity.username]"><carm:formatUser username="${activity.username}"/></g:link>

            <carm:activityMessage activity="${activity}"/>

            <br>

            <carm:formatDateTimePeriod class="muted activityDateOccurred" value="${activity.dateOccurred}"/>
        </p>
    </g:each>
</div>