<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'systemServer.label', default: 'SystemEnvironment Server')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${systemServerInstanceList}"/>

<g:render template="/common/messages"/>

<table class="table table-striped">
    <thead>
    <tr>
        <g:sortableColumn property="name"
                          title="${message(code: 'systemServer.name.label', default: 'Name')}"/>
        <g:sortableColumn property="description"
                          title="${message(code: 'systemServer.description.label', default: 'Description')}"/>
        <th><g:message code="systemServer.sysEnvironment.label" default="System"/></th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${systemServerInstanceList}" status="i" var="systemServerInstance">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
            <td>
                <g:link action="show" id="${systemServerInstance.id}">
                    ${fieldValue(bean: systemServerInstance, field: "name")}
                </g:link>
            </td>
            <td>
                <div class="expander">
                    <carm:plainText value="${systemServerInstance.description}"/>
                </div>
            </td>
            <td>
                <g:link controller="systemEnvironment" action="show"
                        id="${systemServerInstance.sysEnvironment.id}">
                    ${fieldValue(bean: systemServerInstance, field: "sysEnvironment")}
                </g:link>
            </td>
        </tr>
    </g:each>
    </tbody>
</table>

<carm:paginate total="${systemServerInstanceTotal}" max="${grailsApplication.config.ui.systemServer.listMax}"/>

</html>
