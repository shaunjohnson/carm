<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'applicationDeployment.label', default: 'Application Deployment')}"/>
    <title><g:message code="default.completedDeployments.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${systemInstance}" pageName="${message(code: 'default.list.label', args: [entityName])}"/>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>

<div class="row">
    <div class="well" style="margin-left: auto; margin-right: auto; width: 735px">
        <div class="btn-group" data-toggle="buttons-radio" style="margin-bottom: 2em;">
            <a id="completed-deployments" href="#" class="btn active">
                <g:message code="completedDeployments.label" default="Completed Deployments"/>
            </a>
            <a id="upcoming-deployments" href="#" class="btn">
                <g:message code="upcomingDeployments.label" default="Upcoming Deployments"/>
            </a>
        </div>

        <div id="date"></div>
    </div>
</div>

<div class="row">
    <div id="deploymentsBlock" class="span12">
        <g:render template="completedDeployments"
                  model="[applicationDeploymentsGrouped: applicationDeploymentsGrouped]"/>
    </div>
</div>

<div class="row">
    <div id="progress2" class="span12"
         style="display: none; background-image: url('${resource(dir: 'images', file: 'ajax-loader.gif')}'); background-position-x: center; background-repeat: no-repeat; height: 5em;">
    </div>
</div>

<r:script>
    jQuery(function () {
        var completedDeploymentsUrl = '${createLink(action: "ajaxCompletedDeployments", id: systemInstance.id)}',
            upcomingDeploymentsUrl = '${createLink(action: "ajaxUpcomingDeployments", id: systemInstance.id)}',
            dates = jQuery("#date").datepicker({
                dateFormat:'<g:message code="jQuery.datepicker.format"/>',
                onSelect: function() { loadDeployments(completedDeploymentsUrl); },
                numberOfMonths:3
            });

        dates.keydown(function (e) {
            if (e.keyCode == 13) {
                loadDeployments(completedDeploymentsUrl);
            }
        });

        function loadDeployments(url) {
            jQuery("#deploymentsBlock").html("");
            jQuery("#progress").show();

            jQuery.ajax({
                url: url,
                data:{ date:jQuery("#date").val() },
                dataType:'html',
                success:function (data, textStatus, jqXHR) {
                    jQuery("#progress").hide();
                    jQuery("#deploymentsBlock").html(data);
                },
                error:function (jqXHR, textStatus, errorThrown) {
                    jQuery("#progress").hide();
                }
            });
        }

        jQuery("#completed-deployments").click(function(e) {
            dates.datepicker("enable");
            loadDeployments(completedDeploymentsUrl);
        });

        jQuery("#upcoming-deployments").click(function(e) {
            dates.datepicker("disable");
            loadDeployments(upcomingDeploymentsUrl);
        });

        loadDeployments(completedDeploymentsUrl);
    });
</r:script>

</body>
</html>
