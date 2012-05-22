<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'application.label', default: 'Application')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${applicationInstance}"/>

<g:render template="/common/messages"/>

<ul id="applicationTabs" class="nav nav-tabs">
    <li class="active">
        <a href="#summaryTab" data-toggle="tab">
            <g:message code="summary.label" default="Summary"/>
        </a>
    </li>
    <li>
        <a href="#detailsTab" data-toggle="tab">
            <g:message code="details.label" default="Details"/>
        </a>
    </li>
    <li>
        <a href="#modulesTab" data-toggle="tab">
            <g:message code="modules.label" default="Modules"/>
            <g:if test="${applicationInstance.modules.size()}">
                <span class="badge">${applicationInstance.modules.size()}</span>
            </g:if>
            <g:else>
                <span class="badge badge-warning">!</span>
            </g:else>
        </a>
    </li>
    <li>
        <a href="#permissionsTab" data-toggle="tab">
            <g:message code="permissions.label" default="Permissions"/>
            <g:if test="${applicationInstance.applicationRoles.size() == 0}">
                <span class="badge badge-warning">!</span>
            </g:if>
        </a>
    </li>
    <li>
        <a href="#activityTab" data-toggle="tab">
            <g:message code="activity.label" default="Activity"/>
        </a>
    </li>
</ul>

<div class="tab-content">
    <div id="summaryTab" class="tab-pane active">
        <div class="row">
            <div class="span12">
                <g:render template="pendingTasks" model="[pendingTasks: pendingTasks]"/>
            </div>
        </div>

        <div class="row">
            <div class="span6">
                <g:render template="applicationEnvironments" model="[applicationInstance: applicationInstance]"/>
            </div>

            <div class="span6">
                <g:render template="applicationReleases" model="[applicationInstance: applicationInstance]"/>
            </div>
        </div>
    </div>

    <div id="detailsTab" class="tab-pane">
        <g:render template="applicationDetails" model="[applicationInstance: applicationInstance]"/>
    </div>

    <div id="modulesTab" class="tab-pane">
        <g:render template="applicationModules" model="[applicationInstance: applicationInstance]"/>
    </div>

    <div id="permissionsTab" class="tab-pane">
        <div class="sectionHeader">
            <div class="text">
                <g:message code="application.applicationRoles.label" default="Application Roles"/>
            </div>
            <carmsec:isProjectOwner application="${applicationInstance}">
                <div class="section-action">
                    <g:link class="create" controller="applicationRole" action="create"
                            params="['application.id': applicationInstance?.id]">
                        <g:message code="addApplicationRole.label" default="Add Application Role"/>
                    </g:link>
                </div>
            </carmsec:isProjectOwner>
        </div>

        <g:if test="${applicationInstance.applicationRoles}">
            <ul>
                <g:each in="${applicationInstance.applicationRoles.sort { it.sourceControlUser.name }}"
                        var="applicationRole">
                    <li>
                        <g:link controller="applicationRole" action="show" id="${applicationRole.id}">
                            ${applicationRole?.encodeAsHTML()}
                        </g:link>
                    </li>
                </g:each>
            </ul>
        </g:if>
        <g:else>
            <carm:alertWarning message="${message(code: "applicationDoesNotHaveAnyAssignedUsers.message")}"/>
        </g:else>
    </div>

    <div id="activityTab" class="tab-pane">
        <g:render template="/common/activity"
                  model="[activityList: activityList, activityCount: activityCount, listActivityAction: 'listActivity', domainId: applicationInstance.id]"/>
    </div>
</div>

<r:script>
    jQuery(function () {
        jQuery('#applicationTabs a').click(function (e) {
            e.preventDefault();
            jQuery(this).tab('show');
        });
    });
</r:script>

</body>
</html>
