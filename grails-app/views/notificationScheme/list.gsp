<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName"
           value="${message(code: 'notificationScheme.label', default: 'Notification Scheme')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${notificationSchemeInstanceList}"
             pageName="${message(code: 'default.list.label', args: [entityName])}"/>

<g:render template="/common/messages"/>

<g:if test="${notificationSchemeInstanceTotal}">
    <table class="table table-striped">
        <thead>
        <tr>
            <g:sortableColumn property="name"
                              title="${message(code: 'notificationScheme.name.label', default: 'Name')}"/>
            <g:sortableColumn property="description"
                              title="${message(code: 'notificationScheme.description.label', default: 'Description')}"/>
        </tr>
        </thead>
        <tbody>
        <g:each in="${notificationSchemeInstanceList}" status="i" var="notificationSchemeInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td>
                    <g:link action="show" id="${notificationSchemeInstance.id}">
                        ${fieldValue(bean: notificationSchemeInstance, field: "name")}
                    </g:link>
                </td>
                <td>
                    <div class="expander">
                        <carm:plainText value="${notificationSchemeInstance.description}"/>
                    </div>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>

    <carm:paginate total="${notificationSchemeInstanceTotal}"
                   max="${grailsApplication.config.ui.notificationScheme.listMax}"/>
</g:if>
<g:else>
    <p>
        <em><g:message code="carmNoApplicationReleaseTestStates.message"
                       default="There are no application release test states."/></em>
    </p>
</g:else>
</body>
</html>
