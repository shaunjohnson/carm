<%@ page import="carm.project.Project" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${projectInstanceList}"/>

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

    <g:if test="${projectInstanceTotal}">
        <div class="list">
            <table>
                <thead>
                <tr>
                    <g:sortableColumn property="name" title="${message(code: 'project.name.label', default: 'Name')}"/>
                    <th><g:message code="project.category.label" default="Category"/></th>
                    <g:sortableColumn property="description"
                                      title="${message(code: 'project.description.label', default: 'Description')}"/>
                </tr>
                </thead>
                <tbody>
                <g:each in="${projectInstanceList}" status="i" var="projectInstance">
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td><g:link action="show" id="${projectInstance.id}">
                            ${fieldValue(bean: projectInstance, field: "name")}
                        </g:link>
                        </td>
                        <td>
                            <g:link controller="projectCategory" action="show" id="${projectInstance.category.id}">
                                ${fieldValue(bean: projectInstance, field: "category")}
                            </g:link>
                        </td>
                        <td>
                            ${fieldValue(bean: projectInstance, field: "description")}
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>

        <div class="paginateButtons">
            <g:paginate total="${projectInstanceTotal}"/>
        </div>
    </g:if>
    <g:else>
        <p class="emphasis">
            <g:message code="carmNoProjects.message" default="There are no projects."/>
        </p>
    </g:else>
</div>
</body>
</html>
