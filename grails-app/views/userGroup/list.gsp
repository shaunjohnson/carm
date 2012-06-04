<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName"
           value="${message(code: 'userGroup.label', default: 'User Group')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${userGroupInstanceList}"
             pageName="${message(code: 'default.list.label', args: [entityName])}"/>

<g:render template="/common/messages"/>

<g:if test="${userGroupInstanceTotal}">
    <table class="table table-striped">
        <thead>
        <tr>
            <g:sortableColumn property="name" title="${message(code: 'userGroup.name.label', default: 'Name')}"/>
            <g:sortableColumn property="description"
                              title="${message(code: 'userGroup.users.size.label', default: 'User Count')}"/>
        </tr>
        </thead>
        <tbody>
        <g:each in="${userGroupInstanceList}" status="i" var="userGroupInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td>
                    <g:link action="show" id="${userGroupInstance.id}">
                        ${fieldValue(bean: userGroupInstance, field: "name")}
                    </g:link>
                </td>
                <td>
                    <div class="expander">
                        ${userGroupInstance.users.size()}
                    </div>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>

    <carm:paginate total="${userGroupInstanceTotal}"
                   max="${grailsApplication.config.ui.userGroup.listMax}"/>
</g:if>
<g:else>
    <p>
        <em><g:message code="carmNoUserGroups.message" default="There are no user groups."/></em>
    </p>
</g:else>
</body>
</html>
