<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'home.label', default: 'Home')}"/>
    <title>${entityName}</title>
</head>

<body>
    <!--[if lt IE 7]>
    <div class="alert">
         <strong><g:message code="warning.alert"/></strong>
         <g:message code="outOfDateBrowser.message"/>
    </div>
    <![endif]-->

    <carm:header pageName="${message(code: 'home.label', default: 'Home')}"/>

    <g:render template="/common/messages"/>

    <sec:ifLoggedIn>
        <g:render template="myOpenTasks" model="[myPendingTasks: myPendingTasks]"/>
    </sec:ifLoggedIn>

    <div class="row">
        <div class="span6">
            <sec:ifLoggedIn>
                <g:render template="myProjects" model="[myProjects: myProjects]"/>
            </sec:ifLoggedIn>
            <sec:ifNotLoggedIn>
                <g:render template="login"/>

                <div>&nbsp;</div>

                <g:render template="mostActiveApplications"
                          model="[mostActiveApplications: mostActiveApplications]"/>
            </sec:ifNotLoggedIn>
        </div>

        <div class="span6">
            <sec:ifLoggedIn>
                <g:render template="mySystems" model="[mySystemEnvironments: mySystemEnvironments]"/>
            </sec:ifLoggedIn>
            <sec:ifNotLoggedIn>
                <g:render template="mostActiveSystems"
                          model="[mostActiveSystemEnvironments: mostActiveSystemEnvironments]"/>
            </sec:ifNotLoggedIn>

            <div>&nbsp;</div>

            <div class="sectionHeader">
                <div class="text">
                    <g:message code="activityChart.label" default="Activity Chart"/>
                </div>
            </div>

            <div id="chart_div" style="height: 250px; width: 500px; text-align: center; position:relative">
                <img src="${resource(dir: 'images', file: 'ajax-loader.gif')}"
                     style="position:absolute; top:50%; height: 42px; margin-top:-21px; margin-left: -21px;"
                     alt="${message(code: 'spinner.alt', default: 'Loading...')}"/>
            </div>

            <div>&nbsp;</div>

            <g:render template="/common/activity"
                      model="[activityList: activityList, activityCount: activityCount, listActivityAction: 'listActivity']"/>
        </div>
    </div>

<r:script>
    // Load the Visualization API and the areachart package.
    google.load('visualization', '1', {'packages':['areachart']});

    // Set a callback to run when the API is loaded.
    google.setOnLoadCallback(initialize);

    function initialize() {
        var query = new google.visualization.Query("${createLink(action: 'ajaxGetRecentActivityChartData')}");
        query.send(drawChart);
    }

    // Callback that creates and populates a data table,
    // instantiates the pie chart, passes in the data and
    // draws it.
    function drawChart(response) {
        var data = response.getDataTable();
        var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
        chart.draw(data, {width:500, height:250,
            title:'<g:message code="releaseAndDeploymentActivityByDay.label" default="Releases and Deployments per Day"/>'});
    }
</r:script>

</body>
</html>
