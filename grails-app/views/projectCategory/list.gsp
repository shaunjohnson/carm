<%@ page import="carm.ProjectCategory" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'projectCategory.label', default: 'Project Category')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${projectCategoryInstanceList}"
                 pageName="${message(code: 'default.list.label', args: [entityName])}"/>

    <sec:ifAllGranted roles="ROLE_ADMIN">
        <div class="nav">
            <span class="menuButton">
                <g:link class="create" action="create">
                    <g:message code="default.new.label" args="[entityName]"/>
                </g:link>
            </span>
        </div>
    </sec:ifAllGranted>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <g:if test="${projectCategoryInstanceTotal}">
        <div class="list">
            <table>
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
                            ${fieldValue(bean: projectCategoryInstance, field: "description")}
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>

        <div class="paginateButtons">
            <g:paginate total="${projectCategoryInstanceTotal}"/>
        </div>
    </g:if>
    <g:else>
        <p class="emphasis">
            <g:message code="carmNoProjectCategories.message" default="There are no project categories."/>
        </p>
    </g:else>
</div>
</body>
</html>
