<g:if test="${pendingTasks?.size()}">
    <div class="sectionHeader">
        <div class="text">
            <g:message code="pendingTasks.label" default="Pending Tasks"/>
        </div>
    </div>

    <div class="section-content">
        <g:if test="${pendingTasks?.size()}">
            <g:render template="/common/pendingTasks" model="[pendingTasks: pendingTasks]"/>
        </g:if>
        <g:else>
            <p>
                <em><g:message code="applicationDoesNotHaveAnyPendingTasks.message"
                               default="This application does not have any pending tasks."/></em>
            </p>
        </g:else>
    </div>
</g:if>