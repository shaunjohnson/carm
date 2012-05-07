<g:if test="${favorites.size()}">
    <ul>
        <g:each in="${favorites}" var="favorite">
            <li>
                <g:link controller="application" action="show" id="${favorite.application.id}">
                    ${favorite.application.name.encodeAsHTML()}
                </g:link>
            </li>
        </g:each>
    </ul>

    <div>&nbsp;</div>
</g:if>
<g:else>
    <p class="emphasis">
        <g:message code="userDoesNotHaveAnyFavorites.message"
                   default="This user does not have any favorites"/>
    </p>
</g:else>