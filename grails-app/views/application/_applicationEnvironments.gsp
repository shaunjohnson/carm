<div class="sectionHeader">
    <div class="text">
        <g:message code="environments.label" default="Environments"/>
    </div>

    <div class="section-action">
        <g:link action="showFullHistory" id="${applicationInstance.id}"
                params="[sort: grailsApplication.config.ui.application.showFullHistorySort,
                        order: grailsApplication.config.ui.application.showFullHistoryOrder]">
            <g:message code="showFullHistory.label" default="Show Full History"/>
        </g:link>
    </div>
</div>

<g:set var="maxRecordsAllowed" value="${grailsApplication.config.ui.applicationDeployment.maxRecords}"/>

<g:if test="${applicationInstance?.sysEnvironment}">
    <g:set var="applicationDeploymentsTotal" value="100"/>

    <g:each in="${applicationInstance.sysEnvironment.environments}" var="deploymentEnvironment"
            status="environmentIndex">
        <g:set var="deploymentGroup" value="${deployments[deploymentEnvironment]}"/>

        <h4><g:link controller="systemDeploymentEnvironment" action="show" id="${deploymentEnvironment.id}"
                    title="${message(code: "showSystemDeploymentEnvironment.label", default: "Show Deployment Environment")}">
            ${deploymentEnvironment.name.encodeAsHTML()}
        </g:link></h4>

        <div style="position: relative;">
            <g:if test="${maxRecordsAllowed <= deploymentGroup.deploymentListCount}">
                <div style="float:right; position: absolute; bottom: 0; right: 0;">
                    <carm:showMore controller="${params.controller}" action="ajaxShowMoreDeployments"
                                   id="${applicationInstance.id}_${deploymentEnvironment.id}"
                                   appendId="environmentDeploymentsBlock${deploymentEnvironment.id}"
                                   offset="1"
                                   max="${deploymentGroup.deploymentListCount}"
                                   step="${maxRecordsAllowed}"
                                   mini="true"/>
                </div>
            </g:if>

            <div style="float:left;">
                <table id="environmentDeploymentsBlock${deploymentEnvironment.id}" class="offset-half">
                    <g:render template="applicationEnvironmentsBlock"
                              model="[deploymentList: deploymentGroup.deploymentList]"/>
                </table>
            </div>

            <div class="clearing"></div>
        </div>

        <g:if test="${environmentIndex + 1 < applicationInstance.sysEnvironment.environments.size()}">
            <hr/>
        </g:if>
    </g:each>

    <r:script>
        jQuery(function () {
            jQuery('.environment-action').tooltip()
        });
    </r:script>
</g:if>
<g:else>
    <p>
        <em><g:message code="applicationNotConfiguredForEnvironment.message"
                       default="This application is not configured to work with an environment."/></em>
    </p>
</g:else>