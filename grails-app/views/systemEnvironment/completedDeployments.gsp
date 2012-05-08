<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'applicationDeployment.label', default: 'Application Deployment')}"/>
    <title><g:message code="default.completedDeployments.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${systemInstance}" pageName="${message(code: 'default.list.label', args: [entityName])}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <div class="buttons">
        <carm:button action="upcomingDeployments" id="${params.id}">
            <g:message code="upcomingDeployments.label" default="Upcoming Deployments"/>
        </carm:button>
    </div>

    <script type="text/javascript">
        function loadDeployments() {
            jQuery("#deploymentsBlock").html("");
            jQuery("#progress").show();

            jQuery.ajax({
                url:'${createLink(action: "ajaxDeploymentsByDay", id: systemInstance.id)}',
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

        jQuery(function () {
            var dates = jQuery("#date").datepicker({
                dateFormat:'<g:message code="jQuery.datepicker.format"/>',
                onSelect:loadDeployments,
                numberOfMonths:3
            });

            jQuery("#date").keydown(function (e) {
                if (e.keyCode == 13) {
                    loadDeployments();
                }
            });
        });
    </script>

    <div style="margin-top: 1em;">
        <div id="date" style="margin-left: auto; margin-right: auto; width: 85%"></div>
    </div>

    <div id="deploymentsBlock">
        <g:render template="completedDeployments"
                  model="[applicationDeploymentsGrouped: applicationDeploymentsGrouped]"/>
    </div>

    <img id="progress" src="${resource(dir: 'images', file: 'ajax-loader.gif')}"
         style="display: none;"
         alt="${message(code: 'spinner.alt', default: 'Loading...')}"/>
</div>
</body>
</html>
