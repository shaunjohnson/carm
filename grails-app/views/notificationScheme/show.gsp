<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'notificationScheme.label', default: 'Notification Scheme')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${notificationSchemeInstance}"/>

<g:render template="/common/messages"/>

<ul id="applicationTabs" class="nav nav-tabs">
    <li class="active">
        <a href="#summaryTab" data-toggle="tab">
            <g:message code="summary.label" default="Summary"/>
        </a>
    </li>
    <li>
        <a href="#applicationsTab" data-toggle="tab">
            <g:message code="applications.label" default="Applications"/>
            <span class="badge">${applicationInstanceList.size()}</span>
        </a>
    </li>
</ul>

<div class="tab-content">
    <div id="summaryTab" class="tab-pane active">
        <g:render template="summary" model="[notificationSchemeInstance: notificationSchemeInstance]"/>
    </div>

    <div id="applicationsTab" class="tab-pane">
        <g:render template="applications" model="[applicationInstanceList: applicationInstanceList]"/>
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
