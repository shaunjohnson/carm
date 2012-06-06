<g:if test="${userList.size()}">
    <ul>
        <g:each in="${userList}" var="user">
            <li id="team-leader-user_${user.id}">
                <g:link controller="user" action="show" id="${user.id}">
                    ${user.fullName.encodeAsHTML()}
                </g:link>

                <carmsec:isProjectOwner application="${applicationInstance}">
                    <a href="#" onclick="return removeTeamLeaderUser(${user.id});"
                       title="${message(code: 'removeUser.label')}">
                        <img align="top" src='${fam.icon(name: 'delete')}' alt="Delete"/>
                    </a>
                </carmsec:isProjectOwner>
            </li>
        </g:each>
    </ul>
</g:if>