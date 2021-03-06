<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${projectInstanceList}"/>

<g:render template="/common/messages"/>

<g:if test="${projectInstanceTotal}">
    <table class="table table-striped">
        <thead>
        <tr>
            <g:sortableColumn property="name" title="${message(code: 'project.name.label')}"/>
            <g:sortableColumn property="category.name" title="${message(code: 'project.category.label')}"/>
            <g:sortableColumn property="description" title="${message(code: 'project.description.label')}"/>
            <th class="centered"><g:message code="project.applications.label"/></th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${projectInstanceList}" status="i" var="projectInstance">
            <tr>
                <td>
                    <g:link action="show" id="${projectInstance.id}">
                        <g:fieldValue field="name" bean="${projectInstance}"/>
                    </g:link>
                </td>
                <td>
                    <g:link controller="projectCategory" action="show" id="${projectInstance.category.id}">
                        <g:fieldValue field="category" bean="${projectInstance}"/>
                    </g:link>
                </td>
                <td>
                    <div class="expander">
                        <carm:plainText value="${projectInstance.description}"/>
                    </div>
                </td>
                <td class="centered">
                    <g:formatNumber number="${projectInstance.applications.size()}"/>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>

    <carm:paginate total="${projectInstanceTotal}" max="${grailsApplication.config.ui.project.listMax}"/>
</g:if>
<g:else>
    <p>
        <em><g:message code="carmNoProjects.message" default="There are no projects."/></em>
    </p>
</g:else>
</body>
</html>
