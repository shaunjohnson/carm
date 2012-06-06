<g:if test="${userGroupList.size()}">
    <ul>
        <g:each in="${userGroupList}" var="userGroup">
            <li id="app-developer-group_${userGroup.id}">
                <g:link controller="userGroup" action="show" id="${userGroup.id}">
                    ${userGroup.name.encodeAsHTML()}
                </g:link>

                <carmsec:isProjectOwner application="${applicationInstance}">
                    <a href="#" onclick="return removeApplicationDeveloperGroup(${userGroup.id});"
                       title="${message(code: 'removeGroup.label')}">
                        <img align="top" src='${fam.icon(name: 'delete')}' alt="Delete"/>
                    </a>
                </carmsec:isProjectOwner>
            </li>
        </g:each>
    </ul>
</g:if>