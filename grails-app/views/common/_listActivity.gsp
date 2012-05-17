<div class="body">
    <carm:header domain="${domainInstance}"
                 pageName="${message(code: 'default.listActivity.label', args: [entityName])}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

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
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td>
                    <g:link controller="user" action="show" id="${activity.username}"><carm:formatUser
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

    <div class="paginateButtons">
        <g:paginate total="${activityTotal}" id="${domainInstance?.id}"
                    max="${grailsApplication.config.ui.activityTrace.listMax}"/>
    </div>
</div>