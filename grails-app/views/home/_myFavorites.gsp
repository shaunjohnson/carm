<g:if test="${myFavorites.size()}">
    <div class="sectionHeader">
        <div class="text">
            <g:message code="myFavorites.label" default="My Favorites"/>
        </div>
    </div>

    <ul>
        <g:each in="${myFavorites}" var="favorite">
            <li>
                <g:link controller="application" action="show" id="${favorite.application.id}">
                    ${favorite.application.name.encodeAsHTML()}
                </g:link>
            </li>
        </g:each>
    </ul>

    <div>&nbsp;</div>
</g:if>