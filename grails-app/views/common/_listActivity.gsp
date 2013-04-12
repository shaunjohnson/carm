<carm:header domain="${domainInstance}"
             pageName="${message(code: 'default.listActivity.label', args: [entityName])}"/>

<g:render template="/common/messages"/>

<table class="table table-striped">
    <thead>
    <tr>
        <th><g:message code="activityTrace.username.label" default="Username"/></th>
        <th><g:message code="activityTrace.message.label" default="Message"/></th>
        <th><g:message code="activityTrace.dateOccurred.label" default="Date Occurred"/></th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${activityList}" status="i" var="activity">
        <tr>
            <td>
                <g:link controller="user" action="show" params="[username: activity.username]"><carm:formatUser
                        username="${activity.username}"/></g:link>
            </td>
            <td>
                <carm:activityMessage activity="${activity}"/>
            </td>
            <td>
                <carm:formatDateTimePeriod value="${activity.dateOccurred}"/>
            </td>
        </tr>
    </g:each>
    </tbody>
</table>

<carm:paginate total="${activityTotal}" id="${domainInstance?.id}"
               max="${grailsApplication.config.ui.activityTrace.listMax}"/>