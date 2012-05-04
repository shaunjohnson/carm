<sec:ifLoggedIn>
    <g:if test="${favorites.size()}">
        <div style="float: right; margin-left: 5px; margin-top: 3px;">
            <div class="ui-state-default ui-corner-all">
                <span id="favoritesButton" class="ui-icon ui-icon-triangle-1-s"></span>
            </div>

            <ul id="favorites" style="display: none;"
                class="ui-autocomplete ui-menu ui-widget ui-widget-content ui-corner-all">
                <g:each in="${favorites}" var="favorite">
                    <li class="ui-menu-item">
                        <g:link controller="application" action="show" id="${favorite.application.id}"
                                class="ui-corner-all" tabindex="-1">
                            ${favorite.application.name.encodeAsHTML()}
                        </g:link>
                    </li>
                </g:each>
            </ul>
        </div>
    </g:if>
</sec:ifLoggedIn>