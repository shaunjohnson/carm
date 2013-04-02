<g:if test="${myPendingTasks?.size()}">
    <div class="sectionHeader">
        <div class="text">
            <g:message code="myOpenTasks.label" default="My Open Tasks"/>
        </div>

        <div class="section-action">
            <g:link controller="requests" action="list">
                <g:message code="allRequests.label" default="All Requests"/>
            </g:link>
        </div>
    </div>

    <div class="section-content">
        <g:if test="${myPendingTasks?.size()}">
            <g:render template="/common/pendingTasks" model="[pendingTasks: myPendingTasks]"/>
        </g:if>
        <g:else>
            <p>
                <em><g:message code="youDontHaveAnyOpenTasks.message" default="You don't have any open tasks."/></em>
            </p>
        </g:else>
    </div>
</g:if>