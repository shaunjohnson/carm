<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'application.label', default: 'Application')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${applicationInstanceList}"
             pageName="${message(code: 'default.list.label', args: [entityName])}"/>

<g:render template="/common/messages"/>

<table class="table table-striped">
    <thead>
    <tr>
        <g:sortableColumn property="name" title="${message(code: 'application.name.label', default: 'Name')}"/>
        <th><g:message code="application.type.label" default="Type"/></th>
        <th><g:message code="application.project.label" default="Project"/></th>
        <th><g:message code="application.sourceControlRepository.label"
                       default="Source Control Repository"/></th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${applicationInstanceList}" status="i" var="applicationInstance">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
            <td>
                <g:link action="show" id="${applicationInstance.id}">
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
            <td>
                <g:if test="${applicationInstance.sourceControlRepository}">
                    <g:link controller="sourceControlRepository" action="show"
                            id="${applicationInstance.sourceControlRepository.id}">
                        ${fieldValue(bean: applicationInstance, field: "sourceControlRepository")}
                    </g:link>
                </g:if>
            </td>
        </tr>
    </g:each>
    </tbody>
</table>

<carm:paginate total="${applicationInstanceTotal}" max="${grailsApplication.config.ui.application.listMax}"/>

</body>
</html>
