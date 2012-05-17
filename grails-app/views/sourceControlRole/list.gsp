<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'sourceControlRole.label', default: 'Source Control Role')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${sourceControlRoleInstanceList}"/>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>

<g:if test="${sourceControlRoleInstanceTotal}">
    <table class="table table-striped">
        <thead>
        <tr>
            <g:sortableColumn property="name"
                              title="${message(code: 'sourceControlRole.name.label', default: 'Name')}"/>
            <g:sortableColumn property="description"
                              title="${message(code: 'sourceControlRole.description.label', default: 'Description')}"/>
        </tr>
        </thead>
        <tbody>
        <g:each in="${sourceControlRoleInstanceList}" status="i" var="sourceControlRoleInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td>
                    <g:link action="show" id="${sourceControlRoleInstance.id}">
                        ${fieldValue(bean: sourceControlRoleInstance, field: "name")}
                    </g:link>
                </td>
                <td>
                    <div class="expander">
                        <carm:plainText value="${sourceControlRoleInstance.description}"/>
                    </div>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="paginateButtons">
        <g:paginate total="${sourceControlRoleInstanceTotal}"
                    max="${grailsApplication.config.ui.sourceControlRole.listMax}"/>
    </div>
</g:if>
<g:else>
    <p>
        <em><g:message code="carmNoSourceControlRoles.message" default="There are no source control roles."/></em>
    </p>
</g:else>
</body>
</html>
