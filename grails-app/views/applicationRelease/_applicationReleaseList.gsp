<div class="list">
    <table>
        <thead>
        <tr>
            <th>
                <g:message code="application.project.label" default="Project"/> -
                <g:message code="applicationRelease.application.label" default="Application"/>
            </th>
            <th><g:message code="applicationRelease.releaseNumber.label" default="Release Number"/></th>
            <th><g:message code="applicationRelease.changeLog.label" default="Change Log"/></th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${applicationReleaseInstanceList}" status="i" var="applicationReleaseInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td>
                    <g:link controller="applicationRelease" action="show" id="${applicationReleaseInstance.id}">${applicationReleaseInstance.application.project} -
                        ${fieldValue(bean: applicationReleaseInstance, field: "application")}</g:link>
                </td>
                <td>${fieldValue(bean: applicationReleaseInstance, field: "releaseNumber")}</td>
                <td>${fieldValue(bean: applicationReleaseInstance, field: "changeLog")}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>