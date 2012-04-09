<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'home.label', default: 'Home')}"/>
    <title>${entityName}</title>
</head>

<body>
<div class="body">
    <carm:header pageName="${message(code: 'home.label', default: 'Home')}"/>

    <sec:ifLoggedIn>
        <g:render template="myOpenTasks" model="[myPendingTasks: myPendingTasks]"/>
    </sec:ifLoggedIn>

    <table class="twoColumnLayout">
        <tbody>
        <tr>
            <td class="layoutColumnFirst">
                <sec:ifLoggedIn>
                    <g:render template="myFavorites" model="[myFavorites: myFavorites]"/>

                    <g:render template="myProjects" model="[myProjects: myProjects]"/>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                    <g:render template="mostActiveApplications"
                              model="[mostActiveApplications: mostActiveApplications]"/>
                </sec:ifNotLoggedIn>
            </td>
            <td class="layoutColumnLast">
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

                <div id="chart_div" style="height: 240px; width: 450px; text-align: center; position:relative">
                    <img src="${resource(dir: 'images', file: 'ajax-loader.gif')}"
                         style="position:absolute; top:50%; height: 42px; margin-top:-21px; margin-left: -21px;"
                         alt="${message(code: 'spinner.alt', default: 'Loading...')}"/>
                </div>

                <div>&nbsp;</div>

                <g:render template="/common/activity"
                          model="[activityList: activityList, listActivityAction: 'listActivity']"/>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<script type="text/javascript">
    // Load the Visualization API and the areachart package.
    google.load('visualization', '1', {'packages':['areachart']});

    // Set a callback to run when the API is loaded.
    google.setOnLoadCallback(initialize);

    function initialize() {
        var query = new google.visualization.Query("${createLink(action: 'ajaxActivityData')}");
        query.send(drawChart);
    }

    // Callback that creates and populates a data table,
    // instantiates the pie chart, passes in the data and
    // draws it.
    function drawChart(response) {
        var data = response.getDataTable();
        var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
        chart.draw(data, {width:450, height:240,
            title:'<g:message code="releaseAndDeploymentActivityByDay.label" default="Releases and Deployments per Day"/>'});
    }
</script>

</body>
</html>
