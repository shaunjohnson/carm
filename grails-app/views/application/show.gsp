<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'application.label', default: 'Application')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${applicationInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div id="applicationTabs" class="tab-container">
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
            <li>
                <a href="#activityTab">
                    <g:message code="activity.label" default="Activity"/>
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
                            <g:render template="applicationEnvironments"
                                      model="[applicationInstance: applicationInstance]"/>
                        </td>
                        <td class="layoutColumnLast">
                            <g:render template="applicationReleases"
                                      model="[applicationInstance: applicationInstance]"/>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div id="detailsTab">
                <g:render template="details" model="[applicationInstance: applicationInstance]"/>

                <div>&nbsp;</div>

                <g:render template="applicationModules" model="[applicationInstance: applicationInstance]"/>
            </div>

            <div id="permissionsTab">
                <div class="sectionHeader">
                    <div class="text">
                        <g:message code="application.applicationRoles.label" default="Application Roles"/>
                    </div>
                    <carmsec:isProjectOwner application="${applicationInstance}">
                        <div class="actions">
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
                    <p class="emphasis">
                        <g:message code="applicationDoesNotHaveAnyAssignedUsers.message"
                                   default="This application does not have any assigned users."/>
                    </p>
                </g:else>
            </div>

            <div id="activityTab">
                <g:render template="/common/activity"
                          model="[activityList: activityList, activityCount: activityCount, listActivityAction: 'listActivity', domainId: applicationInstance.id]"/>
            </div>
        </div>
    </div>

    <script type='text/javascript'>
        jQuery(function () {
            jQuery("#applicationTabs").easytabs({ animationSpeed:'fast' });
        });
    </script>
</div>
</body>
</html>
