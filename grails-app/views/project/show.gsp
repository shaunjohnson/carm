<%@ page import="carm.Project; org.springframework.security.acls.domain.BasePermission" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></span>
</div>
<div class="body">
    <h1>${fieldValue(bean: projectInstance, field: "name")}</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table class="details">
            <tbody>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="project.description.label" default="Description"/></td>
                <td valign="top" class="value">${fieldValue(bean: projectInstance, field: "description")}</td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="project.category.label" default="Category"/></td>
                <td valign="top" class="value"><g:link controller="projectCategory" action="show" id="${projectInstance?.category?.id}">${fieldValue(bean: projectInstance, field: "category")}</g:link></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="project.dateCreated.label" default="Date Created"/></td>
                <td valign="top" class="value"><g:formatDate date="${projectInstance?.dateCreated}"/></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="project.lastUpdated.label" default="Last Updated"/></td>
                <td valign="top" class="value"><g:formatDate date="${projectInstance?.lastUpdated}"/></td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name"><g:message code="project.projectManagers.label" default="Project Managers"/></td>
                <td valign="top" class="value">
                    <g:listUsersWithPermission domainObject="${projectInstance}" permission="${BasePermission.ADMINISTRATION}"/>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <h2>Applications</h2>
    <g:if test="${applicationsGrouped?.size()}">
        <g:each in="${applicationsGrouped}" var="entry">
            <h3>${entry.key}</h3>
            <ul>
                <g:each in="${entry.value}" var="application">
                    <li><g:link controller="application" action="show" id="${application.id}">${application?.encodeAsHTML()}</g:link></li>
                </g:each>
            </ul>
        </g:each>
    </g:if>
    <g:else>
        <p>
            This project does not have any applications.
        </p>
    </g:else>
    <div style="margin: 1em 0;">
        <g:link controller="application" action="create" params="['project.id': projectInstance?.id]">Add Application</g:link>
    </div>


    <sec:permitted className='carm.Project' id='${projectInstance?.id}' permission='${BasePermission.ADMINISTRATION}'>
        <div class="buttons">
            <g:form>
                <g:hiddenField name="id" value="${projectInstance?.id}"/>
                <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}"/></span>
                <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
            </g:form>
        </div>
    </sec:permitted>
</div>
</body>
</html>
