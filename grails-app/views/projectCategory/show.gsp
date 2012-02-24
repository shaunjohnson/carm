<%@ page import="carm.Project; carm.ProjectCategory" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'projectCategory.label', default: 'Project Category')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${projectCategoryInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="dialog">
        <table id="projectCategoryDetails" class="details">
            <tbody>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="projectCategory.description.label" default="Description"/>
                </td>
                <td valign="top" class="value">
                    <carm:plainText value="${projectCategoryInstance?.description}"/>
                </td>
            </tr>

            <tr class="prop detailProp">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="projectCategory.dateCreated.label" default="Date Created"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${projectCategoryInstance?.dateCreated}"/>
                </td>
            </tr>
            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="projectCategory.lastUpdated.label" default="Last Updated"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${projectCategoryInstance?.lastUpdated}"/>
                </td>
            </tr>
            </tbody>
        </table>
        <carm:showHideDetails sectionId="projectCategoryDetails" entityName="${entityName}">
            <sec:ifAllGranted roles="ROLE_ADMIN">
                <div class="buttons">
                    <span class="button">
                        <g:link class="edit" action="edit" id="${projectCategoryInstance?.id}">
                            <g:message code="default.button.edit.label" default="Edit"/>
                        </g:link>
                    </span>
                    <carm:ifNotInUse domain="${projectCategoryInstance}">
                        <span class="button">
                            <g:link class="delete" action="delete" id="${projectCategoryInstance?.id}">
                                <g:message code="default.button.delete.label" default="Delete"/>
                            </g:link>
                        </span>
                    </carm:ifNotInUse>
                </div>
            </sec:ifAllGranted>
        </carm:showHideDetails>
    </div>

    <g:set var="projects" value="${Project.findAllByCategory(projectCategoryInstance).sort { it.name }}"/>

    <h2><g:message code="projects.label" default="Projects"/></h2>

    <div class="dialog">
        <g:if test="${projects.size()}">
            <ul>
                <g:each in="${projects}" var="project">
                    <li>
                        <g:link controller="project" action="show" id="${project.id}">
                            ${project?.encodeAsHTML()}
                        </g:link>
                    </li>
                </g:each>
            </ul>
        </g:if>
        <g:else>
            <p class="emphasis">
                <g:message code="projectCategoryDoesNotHaveAnyProjects.message"
                           default="There are no projects in this category."/>
            </p>
        </g:else>
    </div>
</div>
</body>
</html>
