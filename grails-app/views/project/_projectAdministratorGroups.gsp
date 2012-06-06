<g:if test="${userGroupList.size()}">
    <ul>
        <g:each in="${userGroupList}" var="userGroup">
            <li id="group_${userGroup.id}">
                <g:link controller="userGroup" action="show" id="${userGroup.id}">
                    ${userGroup.name.encodeAsHTML()}
                </g:link>

                <sec:ifAllGranted roles="ROLE_ADMIN">
                    <a href="#" onclick="return removeGroup(${userGroup.id});"
                       title="${message(code: 'removeGroup.label')}">
                        <img align="top" src='${fam.icon(name: 'delete')}' alt="Delete"/>
                    </a>
                </sec:ifAllGranted>
            </li>
        </g:each>
    </ul>
</g:if>