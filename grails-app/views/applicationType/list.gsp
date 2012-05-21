<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'applicationType.label', default: 'Application Type')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${applicationTypeInstanceList}"
             pageName="${message(code: 'default.list.label', args: [entityName])}"/>

<g:render template="/common/messages"/>

<g:if test="${applicationTypeInstanceTotal}">
    <table class="table table-striped">
        <thead>
        <tr>
            <g:sortableColumn property="name"
                              title="${message(code: 'applicationType.name.label', default: 'Name')}"/>
            <g:sortableColumn property="description"
                              title="${message(code: 'applicationType.description.label', default: 'Description')}"/>
        </tr>
        </thead>
        <tbody>
        <g:each in="${applicationTypeInstanceList}" status="i" var="applicationTypeInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td>
                    <g:link action="show" id="${applicationTypeInstance.id}">
                        ${fieldValue(bean: applicationTypeInstance, field: "name")}
                    </g:link>
                </td>
                <td>
                    <div class="expander">
                        <carm:plainText value="${applicationTypeInstance.description}"/>
                    </div>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>

    <carm:paginate total="${applicationTypeInstanceTotal}"
                   max="${grailsApplication.config.ui.applicationType.listMax}"/>
</g:if>
<g:else>
    <p>
        <em><g:message code="carmNoApplicationTypes.message" default="There are no application types."/></em>
    </p>
</g:else>
</body>
</html>
