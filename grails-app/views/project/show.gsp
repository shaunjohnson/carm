<%@ page import="carm.Project; org.springframework.security.acls.domain.BasePermission" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${projectInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="dialog">
        <table id="projectDetails" class="details">
            <tbody>
            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="project.category.label" default="Category"/>
                </td>
                <td valign="top" class="value">
                    <g:link controller="projectCategory" action="show" id="${projectInstance?.category?.id}">
                        ${fieldValue(bean: projectInstance, field: "category")}
                    </g:link>
                </td>
            </tr>
            <tr class="prop">
                <td valign="top" class="name">
                    <g:message code="project.description.label" default="Description"/>
                </td>
                <td valign="top" class="value">
                    <carm:plainText value="${projectInstance?.description}"/>
                </td>
            </tr>

            <tr class="prop detailProp">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="project.dateCreated.label" default="Date Created"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${projectInstance?.dateCreated}"/>
                </td>
            </tr>
            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="project.lastUpdated.label" default="Last Updated"/>
                </td>
                <td valign="top" class="value">
                    <g:formatDate date="${projectInstance?.lastUpdated}"/>
                </td>
            </tr>

            <tr class="prop detailProp">
                <td colspan="2">&nbsp;</td>
            </tr>

            <tr class="prop detailProp">
                <td valign="top" class="name">
                    <g:message code="project.projectOwners.label" default="Project Owners"/>
                </td>
                <td valign="top" class="value">
                    <carm:listUsersWithPermission domainObject="${projectInstance}"
                                                  permission="${BasePermission.ADMINISTRATION}"/>
                </td>
            </tr>
            </tbody>

            <carm:isProjectOwner project="${projectInstance}">
                <tfoot class="detailProp">
                <tr>
                    <td colspan="2">
                        <div class="buttons">
                            <span class="button">
                                <g:link class="edit" action="edit" id="${projectInstance?.id}">
                                    <g:message code="default.button.edit.label" default="Edit"/>
                                </g:link>
                            </span>
                            <span class="button">
                                <g:link class="delete" action="delete" id="${projectInstance?.id}">
                                    <g:message code="default.button.delete.label" default="Delete"/>
                                </g:link>
                            </span>
                        </div>
                    </td>
                </tr>
                </tfoot>
            </carm:isProjectOwner>
        </table>
        <carm:showHideDetails sectionId="projectDetails" entityName="${entityName}"/>
    </div>

    <g:render template="pendingTasks"
              model="[projectInstance: projectInstance, pendingReleases: pendingReleases]"/>

    <table class="twoColumnLayout">
        <tbody>
        <tr>
            <td class="layoutColumnFirst">
                <g:render template="projectApplications" model="['projectInstance': projectInstance]"/>
            </td>
            <td class="layoutColumnLast">
                <g:render template="/common/activity"
                          model="[activityList: activityList, listActivityAction: 'listActivity', domainId: projectInstance.id]"/>
            </td>
        </tr>
        </tbody>
    </table>
</div>

</body>
</html>
