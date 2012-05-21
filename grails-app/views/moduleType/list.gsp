<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'moduleType.label', default: 'Module Type')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${moduleTypeInstanceList}"
             pageName="${message(code: 'default.list.label', args: [entityName])}"/>

<g:render template="/common/messages"/>

<g:if test="${moduleTypeInstanceTotal}">
    <table class="table table-striped">
        <thead>
        <tr>
            <g:sortableColumn property="name"
                              title="${message(code: 'moduleType.name.label', default: 'Name')}"/>
            <g:sortableColumn property="description"
                              title="${message(code: 'moduleType.description.label', default: 'Description')}"/>
        </tr>
        </thead>
        <tbody>
        <g:each in="${moduleTypeInstanceList}" status="i" var="moduleTypeInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td>
                    <g:link action="show" id="${moduleTypeInstance.id}">
                        ${fieldValue(bean: moduleTypeInstance, field: "name")}
                    </g:link>
                </td>
                <td>
                    <div class="expander">
                        <carm:plainText value="${moduleTypeInstance.description}"/>
                    </div>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>

    <carm:paginate total="${moduleTypeInstanceTotal}" max="${grailsApplication.config.ui.moduleType.listMax}"/>
</g:if>
<g:else>
    <p>
        <em><g:message code="carmNoModuleTypes.message" default="There are no module types."/></em>
    </p>
</g:else>
</body>
</html>
