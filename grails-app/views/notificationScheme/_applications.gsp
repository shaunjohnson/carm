<g:if test="${applicationInstanceList.size()}">
    <table class="table table-striped">
        <thead>
        <tr>
            <g:sortableColumn property="name" title="${message(code: 'application.name.label', default: 'Name')}"/>
            <th><g:message code="application.type.label" default="Type"/></th>
            <th><g:message code="application.project.label" default="Project"/></th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${applicationInstanceList}" status="i" var="applicationInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td>
                    <g:link controller="application" action="show" id="${applicationInstance.id}">
                        ${fieldValue(bean: applicationInstance, field: "name")}
                    </g:link>
                </td>
                <td>
                    <g:link controller="applicationType" action="show" id="${applicationInstance.type.id}">
                        ${fieldValue(bean: applicationInstance, field: "type")}
                    </g:link>
                </td>
                <td>
                    <g:link controller="project" action="show" id="${applicationInstance.project.id}">
                        ${fieldValue(bean: applicationInstance, field: "project")}
                    </g:link>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</g:if>
<g:else>
    <p>
        <em><g:message code="notificationSchemeNotUsedByAnyApplication.message"/></em>
    </p>
</g:else>
