<div class="list">
    <table style="width: 100%;">
        <thead>
        <tr>
            <th><g:message code="applicationReleaseHistory.dateCreated.label" default="Date Created"/></th>
            <th><g:message code="applicationReleaseHistory.username.label" default="Username"/></th>
            <th><g:message code="applicationReleaseHistory.summary.label" default="Summary"/></th>
            <th><g:message code="applicationReleaseHistory.comments.label" default="Comments"/></th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${applicationReleaseInstance?.histories?.sort { it.dateCreated }.reverse()}" var="historyInstance"
            status="i">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td>
                    <g:formatDate date="${historyInstance.dateCreated}"/>
                </td>
                <td>
                    ${historyInstance.username.encodeAsHTML()}
                </td>
                <td>
                    ${historyInstance.summary.encodeAsHTML()}
                </td>
                <td>
                    ${(historyInstance.comments ?: "").decodeHTML()}
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>