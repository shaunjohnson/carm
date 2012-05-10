<div class="sectionHeader">
    <div class="text">
        <g:message code="favorites.label" default="Favorites"/>
    </div>
</div>

<g:if test="${favorites.size()}">
    <div id="favorites-block">
        <g:if test="${isCurrentUser}">
            <div class="buttons" style="margin-bottom: 1em;">
                <button class="button" onclick="return deleteAllFavorites();">
                    <g:message code="deleteAllFavorites.label" default="Delete All Favorites"/>
                </button>
            </div>
        </g:if>

        <ul>
            <g:each in="${favorites}" var="favorite">
                <g:set var="className" value="${favorite.application ? 'application-favorite' : 'project-favorite'}"/>

                <li id="favorite_${favorite.id}" class="${className}">
                    <g:if test="${favorite.application}">
                        <g:link controller="application" action="show" id="${favorite.application.id}">
                            ${favorite.application.name.encodeAsHTML()}
                        </g:link>
                    </g:if>
                    <g:elseif test="${favorite.project}">
                        <g:link controller="project" action="show" id="${favorite.project.id}">
                            ${favorite.project.name.encodeAsHTML()}
                        </g:link>
                    </g:elseif>

                    <g:if test="${isCurrentUser}">
                        <a href="#" onclick="return deleteFavorite(${favorite.id});"
                           title="${message(code: 'deleteFavorite.label')}">
                            <img align="top" src='${fam.icon(name: 'delete')}' alt="Delete"/>
                        </a>
                    </g:if>
                </li>
            </g:each>
        </ul>
    </div>

    <g:if test="${isCurrentUser}">
        <script type="text/javascript">
            function deleteAllFavorites() {
                jQuery.ajax({ cache: false, url: '${createLink(controller: "user", action: "ajaxDeleteAllFavorites")}' });
                jQuery("#favorites-block").remove();
                jQuery("#no-favorites-message").show();

                return false;
            }

            function deleteFavorite(id) {
                jQuery.ajax({ cache: false, url: '${createLink(controller: "user", action: "ajaxRemoveFromFavorites")}/' + id });
                jQuery("#favorite_" + id).remove();

                if (!jQuery("#favorites-block li").length) {
                    jQuery("#favorites-block").remove();
                    jQuery("#no-favorites-message").show();
                }

                return false;
            }
        </script>
    </g:if>
</g:if>

<p id="no-favorites-message" class="emphasis" style="display: ${favorites.size() ? 'none' : 'block'};">
    <g:message code="userDoesNotHaveAnyFavorites.message"
               default="This user does not have any favorites"/>
</p>