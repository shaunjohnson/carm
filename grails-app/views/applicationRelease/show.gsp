<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'applicationRelease.label', default: 'Application Release')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="body">
    <carm:header domain="${applicationReleaseInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <carmsec:isProjectOwner applicationRelease="${applicationReleaseInstance}">
        <div class="buttons">
            <g:if test="${nextEnvironment}">
                <carm:button controller="applicationDeployment" action="create"
                             id="${lastApplicationDeploymentInstance?.id}"
                             params="['deploymentEnvironment.id': nextEnvironment?.id, 'applicationRelease.id': applicationReleaseInstance?.id]">
                    <g:message code="default.button.promote.label" default="Promote to {0}"
                               args="[nextEnvironment?.name]"/>
                </carm:button>
            </g:if>
            <g:else>
                <carm:isDeployable applicationRelease="${applicationReleaseInstance}">
                    <carm:button controller="applicationDeployment" action="create"
                                 params="['applicationRelease.id': applicationReleaseInstance.id]">
                        <g:message code="deployThisRelease.label" default="Deploy this Release"/>
                    </carm:button>
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

    <div id="applicationReleaseTabs" class="tab-container">
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
                <a href="#deploymentsTab">
                    <g:message code="deployments.label" default="Deployments"/>
                </a>
            </li>
            <li>
                <a href="#historyTab">
                    <g:message code="history.label" default="History"/>
                </a>
            </li>
        </ul>

        <div class="panel-container">
            <div id="summaryTab">
                <g:render template="applicationReleaseSummary"
                          model="[applicationReleaseInstance: applicationReleaseInstance]"/>
            </div>

            <div id="detailsTab">
                <g:render template="applicationReleaseDetails"
                          model="[applicationReleaseInstance: applicationReleaseInstance]"/>

                <div>&nbsp;</div>

                <g:render template="applicationReleaseModules"
                          model="['applicationReleaseInstance': applicationReleaseInstance]"/>
            </div>

            <div id="deploymentsTab">
                <g:render template="applicationReleaseDeployments"
                          model="['applicationDeploymentList': applicationDeploymentList]"/>
            </div>

            <div id="historyTab">
                <g:render template="applicationReleaseHistory"
                          model="['applicationReleaseInstance': applicationReleaseInstance]"/>
            </div>
        </div>
    </div>

    <script type='text/javascript'>
        jQuery(function () {
            jQuery("#applicationReleaseTabs").easytabs({ animationSpeed:'fast' });
        });
    </script>
</div>
</body>
</html>
