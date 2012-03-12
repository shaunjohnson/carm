<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName"
           value="${message(code: 'applicationReleaseHistory.label', default: 'Application Release History')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${applicationReleaseHistoryInstanceList}"
                 pageName="${message(code: 'default.list.label', args: [entityName])}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="list">
        <table>
            <thead>
            <tr>
                <th>
                    <g:message code="applicationReleaseHistory.summary.label" default="Summary"/>
                </th>
                <th>
                    <g:message code="applicationReleaseHistory.username.label" default="Username"/>
                </th>
                <th>
                    <g:message code="application.name.label" default="Application"/>
                </th>
                <th>
                    <g:message code="applicationRelease.releaseNumber.label" default="Release Number"/>
                </th>
                <th>
                    <g:message code="applicationReleaseHistory.dateCreated.label" default="Date Created"/>
                </th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${applicationReleaseHistoryInstanceList}" var="historyInstance" status="i">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <td>
                        <g:link controller="applicationReleaseHistory" action="show" id="${historyInstance.id}"
                                title="${message(code: 'showApplicationReleaseHistory.label', default: 'Show Application Release History')}">
                            ${historyInstance.summary.encodeAsHTML()}
                        </g:link>
                    </td>
                    <td>
                        ${historyInstance.username.encodeAsHTML()}
                    </td>
                    <td>
                        <g:link controller="application" action="show"
                                id="${historyInstance.applicationRelease.application.id}"
                                title="${message(code: 'showApplication.label', default: 'Show Application')}">
                            ${historyInstance.applicationRelease.application.encodeAsHTML()}
                        </g:link>
                    </td>
                    <td>
                        <g:link controller="applicationRelease" action="show"
                                id="${historyInstance.applicationRelease.id}"
                                title="${message(code: 'showApplicationRelease.label', default: 'Show Application Release')}">
                            ${historyInstance.applicationRelease.releaseNumber.encodeAsHTML()}
                        </g:link>
                    </td>
                    <td>
                        <g:formatDate date="${historyInstance.dateCreated}"/>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${applicationReleaseHistoryInstanceTotal}"/>
    </div>
</div>
</body>
</html>
