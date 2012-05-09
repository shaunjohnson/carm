<%@ page import="org.springframework.security.acls.domain.BasePermission" %>
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

    <div id="projectTabs" class="tab-container">
        <ul class="tabs">
            <li>
                <a href="#summaryTab">
                    <g:message code="summary.label" default="Summary"/>
                </a>
            </li>
            <li>
                <a href="#detailsTab">
                    <g:message code="details.label" default="Details"/>
                </a>
            </li>
            <li>
                <a href="#permissionsTab">
                    <g:message code="permissions.label" default="Permissions"/>
                </a>
            </li>
        </ul>

        <div class="panel-container">
            <div id="summaryTab">
                <g:render template="pendingTasks" model="[pendingTasks: pendingTasks]"/>

                <table class="twoColumnLayout">
                    <tbody>
                    <tr>
                        <td class="layoutColumnFirst">
                            <g:render template="projectApplications" model="['projectInstance': projectInstance]"/>
                        </td>
                        <td class="layoutColumnLast">
                            <g:render template="/common/activity"
                                      model="[activityList: activityList, activityCount: activityCount, listActivityAction: 'listActivity', domainId: projectInstance.id]"/>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div id="detailsTab">
                <g:render template="projectDetails" model="[projectInstance: projectInstance]"/>
            </div>

            <div id="permissionsTab">
                <g:render template="projectOwners" model="[projectOwners: projectOwners]"/>
            </div>
        </div>
    </div>

    <script type='text/javascript'>
        jQuery(function () {
            jQuery("#projectTabs").easytabs({ animationSpeed:'fast' });
        });
    </script>
</div>

</body>
</html>
