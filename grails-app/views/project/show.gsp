<%@ page import="org.springframework.security.acls.domain.BasePermission" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${projectInstance}"/>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>

<ul id="projectTabs" class="nav nav-tabs">
    <li class="active">
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

<div class="tab-content">
    <div id="summaryTab" class="tab-pane active">
        <div>
            <g:render template="pendingTasks" model="[pendingTasks: pendingTasks]"/>
        </div>

        <div class="row">
            <div class="span6">
                <g:render template="projectApplications" model="['projectInstance': projectInstance]"/>
            </div>

            <div class="span6">
                <g:render template="/common/activity"
                          model="[activityList: activityList, activityCount: activityCount, listActivityAction: 'listActivity', domainId: projectInstance.id]"/>
            </div>
        </div>
    </div>

    <div id="detailsTab" class="tab-pane">
        <g:render template="projectDetails" model="[projectInstance: projectInstance]"/>
    </div>

    <div id="permissionsTab" class="tab-pane">
        <g:render template="projectOwners" model="[projectOwners: projectOwners]"/>
    </div>
</div>

<r:script>
    jQuery(function () {
        jQuery('#projectTabs a').click(function (e) {
            e.preventDefault();
            jQuery(this).tab('show');
        });
    });
</r:script>

</body>
</html>
