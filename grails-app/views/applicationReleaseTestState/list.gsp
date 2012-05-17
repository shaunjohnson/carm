<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName"
           value="${message(code: 'applicationReleaseTestState.label', default: 'Application Release Test State')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${applicationReleaseTestStateInstanceList}"
             pageName="${message(code: 'default.list.label', args: [entityName])}"/>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>

<g:if test="${applicationReleaseTestStateInstanceTotal}">
    <table class="table table-striped">
        <thead>
        <tr>
            <g:sortableColumn property="name"
                              title="${message(code: 'applicationReleaseTestState.name.label', default: 'Name')}"/>
            <g:sortableColumn property="description"
                              title="${message(code: 'applicationReleaseTestState.description.label', default: 'Description')}"/>
        </tr>
        </thead>
        <tbody>
        <g:each in="${applicationReleaseTestStateInstanceList}" status="i"
                var="applicationReleaseTestStateInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td>
                    <g:link action="show" id="${applicationReleaseTestStateInstance.id}">
                        ${fieldValue(bean: applicationReleaseTestStateInstance, field: "name")}
                    </g:link>
                </td>
                <td>
                    <div class="expander">
                        <carm:plainText value="${applicationReleaseTestStateInstance.description}"/>
                    </div>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="paginateButtons">
        <g:paginate total="${applicationReleaseTestStateInstanceTotal}"
                    max="${grailsApplication.config.ui.applicationReleaseTestState.listMax}"/>
    </div>
</g:if>
<g:else>
    <p>
        <em><g:message code="carmNoApplicationReleaseTestStates.message"
                       default="There are no application release test states."/></em>
    </p>
</g:else>
</body>
</html>
