<g:if test="${userList.size()}">
    <ul>
        <g:each in="${userList}" var="user">
            <li id="user_${user.id}">
                <g:link controller="user" action="show" id="${user.id}">
                    ${user.fullName.encodeAsHTML()}
                </g:link>

                <sec:ifAllGranted roles="ROLE_ADMIN">
                    <a href="#" onclick="return removeUser(${user.id});"
                       title="${message(code: 'removeUser.label')}">
                        <img align="top" src='${fam.icon(name: 'delete')}' alt="Delete"/>
                    </a>
                </sec:ifAllGranted>
            </li>
        </g:each>
    </ul>
</g:if>