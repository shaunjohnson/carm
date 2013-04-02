<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'module.label', default: 'Module')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${moduleInstance}"/>

<g:render template="/common/messages"/>

<ul id="moduleTabs" class="nav nav-tabs">
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
        <a href="#activityTab" data-toggle="tab">
            <g:message code="activity.label" default="Activity"/>
        </a>
    </li>
</ul>

<div class="tab-content">
    <div id="summaryTab" class="tab-pane active">
        <g:render template="summary" model="[moduleInstance: moduleInstance]"/>
    </div>

    <div id="detailsTab" class="tab-pane">
        <g:render template="details" model="[moduleInstance: moduleInstance]"/>
    </div>

    <div id="activityTab" class="tab-pane">
        <g:render template="/common/activity"
                  model="[activityList: activityList, activityCount: activityCount, listActivityAction: 'listActivity', domainId: moduleInstance.id]"/>
    </div>
</div>

<r:script>
    jQuery(function () {
        jQuery('#moduleTabs a').click(function (e) {
            e.preventDefault();
            jQuery(this).tab('show');
        });
    });
</r:script>

</body>
</html>