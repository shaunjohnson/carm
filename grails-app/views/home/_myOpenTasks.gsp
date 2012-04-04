<g:if test="${myPendingTasks?.size()}">
    <div class="sectionHeader">
        <div class="text">
            <g:message code="myOpenTasks.label" default="My Open Tasks"/>
        </div>

        <div class="actions">
            <g:link controller="requests" action="list">
                <g:message code="allRequests.label" default="All Requests"/>
            </g:link>
        </div>
    </div>

    <g:if test="${myPendingTasks?.size()}">
        <g:render template="/common/pendingTasks" model="[pendingTasks: myPendingTasks]"/>
    </g:if>
    <g:else>
        <p class="emphasis">
            <g:message code="youDontHaveAnyOpenTasks.message" default="You don't have any open tasks."/>
        </p>
    </g:else>
</g:if>