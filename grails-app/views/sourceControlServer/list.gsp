<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'sourceControlServer.label', default: 'SourceControlServer')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${sourceControlServerInstanceList}"/>

<g:render template="/common/messages"/>

<g:if test="${sourceControlServerInstanceTotal}">
    <table class="table table-striped">
        <thead>
        <tr>
            <g:sortableColumn property="name"
                              title="${message(code: 'sourceControlServer.name.label', default: 'Name')}"/>
            <g:sortableColumn property="description"
                              title="${message(code: 'sourceControlServer.description.label', default: 'Description')}"/>
            <g:sortableColumn property="type"
                              title="${message(code: 'sourceControlServer.type.label', default: 'Type')}"/>
            <g:sortableColumn property="url"
                              title="${message(code: 'sourceControlServer.url.label', default: 'URL')}"/>
        </tr>
        </thead>
        <tbody>
        <g:each in="${sourceControlServerInstanceList}" status="i" var="sourceControlServerInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td>
                    <g:link action="show" id="${sourceControlServerInstance.id}">
                        ${fieldValue(bean: sourceControlServerInstance, field: "name")}
                    </g:link>
                </td>
                <td>
                    <div class="expander">
                        <carm:plainText value="${sourceControlServerInstance.description}"/>
                    </div>
                </td>
                <td>
                    ${fieldValue(bean: sourceControlServerInstance, field: "type")}
                </td>
                <td>
                    ${fieldValue(bean: sourceControlServerInstance, field: "url")}
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>

    <carm:paginate total="${sourceControlServerInstanceTotal}"
                   max="${grailsApplication.config.ui.sourceControlServer.listMax}"/>
</g:if>
<g:else>
    <p>
        <em><g:message code="carmNoSourceControlServers.message" default="There are no source control servers."/></em>
    </p>
</g:else>
</body>
</html>
