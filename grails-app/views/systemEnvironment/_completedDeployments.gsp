<g:if test="${applicationDeploymentsGrouped?.size()}">
    <g:render template="deploymentsBlock"
              model="[applicationDeploymentsGrouped: applicationDeploymentsGrouped]"/>
</g:if>
<g:else>
    <div class="sectionHeader">
        <div class="text">
            <g:formatDate date="${date}" type="date" dateStyle="FULL"/>
        </div>
    </div>

    <p class="emphasis">
        <g:message code="noDeploymentsForThisDate.message"
                   default="There are no deployments for this date."/>
    </p>
</g:else>