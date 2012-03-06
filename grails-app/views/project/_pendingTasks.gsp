<div class="sectionHeader">
    <div class="text">
        <g:message code="pendingTasks.label" default="Pending Tasks"/>
    </div>
</div>

<g:if test="${pendingTasks?.size()}">
    <g:render template="/common/pendingTasks" model="[pendingTasks: pendingTasks]"/>
</g:if>
<g:else>
    <p class="emphasis">
        <g:message code="projectDoesNotHaveAnyPendingTasks.message"
                   default="This project does not have any pending tasks."/>
    </p>
</g:else>
