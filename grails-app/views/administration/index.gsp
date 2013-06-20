<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'administration.label')}"/>
    <title>${entityName}</title>
</head>

<body>
<carm:header pageName="${entityName}"/>

<g:render template="/common/messages"/>

<div class="row">
    <div class="span6">
        <div class="sectionHeader">
            <div class="text">
                <g:message code="sourceControl.label"/>
            </div>
        </div>

        <div class="section-content">
            <ul class="unstyled">
                <li>
                    <g:link controller="sourceControlServer">
                        <g:message code="servers.label"/>
                    </g:link>
                </li>
                <li>
                    <g:link controller="sourceControlRole">
                        <g:message code="roles.label"/>
                    </g:link>
                </li>
            </ul>
        </div>
    </div>

    <div class="span6">
        <div class="sectionHeader">
            <div class="text">
                <g:message code="types.label"/>
            </div>
        </div>

        <div class="section-content">
            <ul class="unstyled">
                <li>
                    <g:link controller="applicationReleaseTestState">
                        <g:message code="applicationReleaseTestStates.label"/>
                    </g:link>
                </li>
                <li>
                    <g:link controller="applicationType">
                        <g:message code="applicationTypes.label"/>
                    </g:link>
                </li>
                <li>
                    <g:link controller="moduleDeploymentTestState">
                        <g:message code="moduleDeploymentTestStates.label"/>
                    </g:link>
                </li>
                <li>
                    <g:link controller="moduleType">
                        <g:message code="moduleTypes.label"/>
                    </g:link>
                </li>
                <li>
                    <g:link controller="projectCategory">
                        <g:message code="projectCategories.label"/>
                    </g:link>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="row">
    <div class="span6">
        <div class="sectionHeader">
            <div class="text">
                <g:message code="security.label"/>
            </div>
        </div>

        <div class="section-content">
            <ul class="unstyled">
                <li>
                    <g:link controller="user">
                        <g:message code="users.label"/>
                    </g:link>
                </li>
                <li>
                    <g:link controller="userGroup">
                        <g:message code="userGroups.label"/>
                    </g:link>
                </li>
            </ul>
        </div>
    </div>

    <div class="span6">
        <div class="sectionHeader">
            <div class="text">
                <g:message code="other.label"/>
            </div>
        </div>

        <div class="section-content">
            <ul class="unstyled">
                <li>
                    <g:link controller="notificationScheme">
                        <g:message code="notificationSchemes.label"/>
                    </g:link>
                </li>
                <li>
                    <g:link uri="/about">
                        <g:message code="about.label"/>
                    </g:link>
                </li>
            </ul>
        </div>
    </div>
</div>

</body>
</html>
