<div class="sectionHeader">
    <div class="text">
        <g:message code="history.label" default="History"/>
    </div>
</div>

<g:if test="${applicationReleaseInstance?.histories?.size()}">
    <div class="list">
        <table style="width: 100%;">
            <thead>
            <tr>
                <th><g:message code="applicationReleaseHistory.summary.label" default="Summary"/></th>
                <th><g:message code="applicationReleaseHistory.username.label" default="Username"/></th>
                <th><g:message code="applicationReleaseHistory.dateCreated.label" default="Date Created"/></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${applicationReleaseInstance?.histories?.sort { it.dateCreated }.reverse()}"
                    var="historyInstance"
                    status="i">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <td>
                        <g:link controller="applicationReleaseHistory" action="show" id="${historyInstance.id}"
                                title="${message(code: 'showApplicationReleaseHistory.label', default: 'Show Application Release History')}">
                            ${historyInstance.summary.encodeAsHTML()}
                        </g:link>
                    </td>
                    <td>
                        ${historyInstance.username.encodeAsHTML()}
                    </td>
                    <td>
                        <g:formatDate date="${historyInstance.dateCreated}"/>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
</g:if>
<g:else>
    <p>
        <em><g:message code="applicationReleaseDoesNotHaveAnyHistory.message"
                       default="This application release does not have any recorded history."/></em>
    </p>
</g:else>