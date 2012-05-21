<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'projectCategory.label', default: 'Project Category')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${projectCategoryInstanceList}"
             pageName="${message(code: 'default.list.label', args: [entityName])}"/>

<g:render template="/common/messages"/>

<g:if test="${projectCategoryInstanceTotal}">
    <table class="table table-striped">
        <thead>
        <tr>
            <g:sortableColumn property="name"
                              title="${message(code: 'projectCategory.name.label', default: 'Name')}"/>
            <g:sortableColumn property="description"
                              title="${message(code: 'projectCategory.description.label', default: 'Description')}"/>
        </tr>
        </thead>
        <tbody>
        <g:each in="${projectCategoryInstanceList}" status="i" var="projectCategoryInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td>
                    <g:link action="show" id="${projectCategoryInstance.id}">
                        ${fieldValue(bean: projectCategoryInstance, field: "name")}
                    </g:link>
                </td>
                <td>
                    <div class="expander">
                        <carm:plainText value="${projectCategoryInstance.description}"/>
                    </div>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>

    <carm:paginate total="${projectCategoryInstanceTotal}"
                   max="${grailsApplication.config.ui.projectCategory.listMax}"/>
</g:if>
<g:else>
    <p>
        <em><g:message code="carmNoProjectCategories.message" default="There are no project categories."/></em>
    </p>
</g:else>
</body>
</html>
