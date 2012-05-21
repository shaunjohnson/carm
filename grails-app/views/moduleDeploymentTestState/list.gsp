<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName"
           value="${message(code: 'moduleDeploymentTestState.label', default: 'Module Deployment Test State')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${moduleDeploymentTestStateInstanceList}"
             pageName="${message(code: 'default.list.label', args: [entityName])}"/>

<g:render template="/common/messages"/>

<g:if test="${moduleDeploymentTestStateInstanceTotal}">
    <table class="table table-striped">
        <thead>
        <tr>
            <g:sortableColumn property="name"
                              title="${message(code: 'moduleDeploymentTestState.name.label', default: 'Name')}"/>
            <g:sortableColumn property="description"
                              title="${message(code: 'moduleDeploymentTestState.description.label', default: 'Description')}"/>
        </tr>
        </thead>
        <tbody>
        <g:each in="${moduleDeploymentTestStateInstanceList}" status="i"
                var="moduleDeploymentTestStateInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td>
                    <g:link action="show" id="${moduleDeploymentTestStateInstance.id}">
                        ${fieldValue(bean: moduleDeploymentTestStateInstance, field: "name")}
                    </g:link>
                </td>
                <td>
                    <div class="expander">
                        <carm:plainText value="${moduleDeploymentTestStateInstance.description}"/>
                    </div>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>

    <carm:paginate total="${moduleDeploymentTestStateInstanceTotal}"
                   max="${grailsApplication.config.ui.moduleDeploymentTestState.listMax}"/>
</g:if>
<g:else>
    <p>
        <em><g:message code="carmNoModuleDeploymentTestStates.message"
                       default="There are no module deployment test states."/></em>
    </p>
</g:else>
</body>
</html>
