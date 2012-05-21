<g:if test="${applicationDeploymentsGrouped?.size()}">
    <g:render template="deploymentsBlock"
              model="[applicationDeploymentsGrouped: applicationDeploymentsGrouped]"/>
</g:if>
<g:else>
    <div class="sectionHeader">
        <div class="text">
            <g:message code="upcomingDeployments.label"/>
        </div>
    </div>

    <p>
        <em><g:message code="noUpcomingDeploymentsForEnvironment.message"
                       default="There are no upcoming deployments for this environment."/></em>
    </p>
</g:else>