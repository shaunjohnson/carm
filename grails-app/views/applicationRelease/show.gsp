<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'applicationRelease.label', default: 'Application Release')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<carm:header domain="${applicationReleaseInstance}"/>

<g:render template="/common/messages"/>

<carmsec:isProjectOwner applicationRelease="${applicationReleaseInstance}">
    <div style="margin-bottom: 1em;">
        <g:if test="${nextEnvironment}">
            <g:link class="btn" controller="applicationDeployment" action="create"
                    id="${lastApplicationDeploymentInstance?.id}"
                    params="['deploymentEnvironment.id': nextEnvironment?.id, 'applicationRelease.id': applicationReleaseInstance?.id]">
                <g:message code="default.button.promote.label" default="Promote to {0}"
                           args="[nextEnvironment?.name]"/>
            </g:link>
        </g:if>
        <g:else>
            <carm:isDeployable applicationRelease="${applicationReleaseInstance}">
                <g:link class="btn" controller="applicationDeployment" action="create"
                        params="['applicationRelease.id': applicationReleaseInstance.id]">
                    <g:message code="deployThisRelease.label" default="Deploy this Release"/>
                </g:link>
            </carm:isDeployable>
        </g:else>

        <%--
        <carm:isSubmittable applicationRelease="${applicationReleaseInstance}">
            <g:form action="submit">
                <g:hiddenField name="id" value="${applicationReleaseInstance?.id}"/>
                <g:submitButton name="submit" class="submit"
                                value="${message(code: 'default.button.submit.label', default: 'Submit')}"/>
            </g:form>
        </carm:isSubmittable>
        --%>
    </div>
</carmsec:isProjectOwner>

<ul id="applicationReleaseTabs" class="nav nav-tabs">
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
        <a href="#deploymentsTab">
            <g:message code="deployments.label" default="Deployments"/>
            <span class="badge">${applicationDeploymentList.size()}</span>
        </a>
    </li>
    <li>
        <a href="#historyTab">
            <g:message code="history.label" default="History"/>
        </a>
    </li>
</ul>

<div class="tab-content">
    <div id="summaryTab" class="tab-pane active">
        <g:render template="applicationReleaseSummary"
                  model="[applicationReleaseInstance: applicationReleaseInstance]"/>
    </div>

    <div id="detailsTab" class="tab-pane">
        <g:render template="applicationReleaseDetails"
                  model="[applicationReleaseInstance: applicationReleaseInstance]"/>

        <div>&nbsp;</div>

        <g:render template="applicationReleaseModules"
                  model="['applicationReleaseInstance': applicationReleaseInstance]"/>
    </div>

    <div id="deploymentsTab" class="tab-pane">
        <g:render template="applicationReleaseDeployments"
                  model="['applicationDeploymentList': applicationDeploymentList]"/>
    </div>

    <div id="historyTab" class="tab-pane">
        <g:render template="applicationReleaseHistory"
                  model="['applicationReleaseInstance': applicationReleaseInstance]"/>
    </div>
</div>

<r:script>
    jQuery(function () {
        jQuery('#applicationReleaseTabs a').click(function (e) {
            e.preventDefault();
            jQuery(this).tab('show');
        });
    });
</r:script>
</body>
</html>
