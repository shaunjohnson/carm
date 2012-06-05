<div class="sectionHeader">
    <div class="text">
        <g:message code="groups.label" default="Groups"/>
    </div>
</div>

<g:if test="${groups.size()}">
    <ul>
        <g:each in="${groups}" var="group">
            <li>
                <g:link controller="userGroup" action="show" id="${group.id}">
                    ${group.name.encodeAsHTML()}
                </g:link>
            </li>
        </g:each>
    </ul>
</g:if>
<g:else>
    <p>
        <em><g:message code="userDoesNotHaveAnygroups.message" default="This user does not have any groups"/></em>
    </p>
</g:else>