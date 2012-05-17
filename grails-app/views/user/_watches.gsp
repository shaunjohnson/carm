<div class="sectionHeader">
    <div class="text">
        <g:message code="watches.label" default="Watches"/>
    </div>
</div>

<g:if test="${watches.size()}">
    <div id="watches-block">
        <g:if test="${isCurrentUser}">
            <div style="margin-bottom: 1em;">
                <button class="btn" onclick="return deleteAllWatches();">
                    <g:message code="deleteAllWatches.label" default="Delete All Watches"/>
                </button>
            </div>
        </g:if>

        <ul>
            <g:each in="${watches}" var="watch">
                <g:set var="className" value="${watch.application ? 'application-watch' : 'project-watch'}"/>

                <li id="watch_${watch.id}" class="${className}">
                    <g:if test="${watch.application}">
                        <g:link controller="application" action="show" id="${watch.application.id}">
                            ${watch.application.name.encodeAsHTML()}
                        </g:link>
                    </g:if>
                    <g:elseif test="${watch.project}">
                        <g:link controller="project" action="show" id="${watch.project.id}">
                            ${watch.project.name.encodeAsHTML()}
                        </g:link>
                    </g:elseif>

                    <g:if test="${isCurrentUser}">
                        <a href="#" onclick="return deleteWatch(${watch.id});"
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
            function deleteAllWatches() {
                jQuery.ajax({ cache:false, url:'${createLink(controller: "user", action: "ajaxDeleteAllWatches")}' });
                jQuery("#watches-block").remove();
                jQuery("#no-watches-message").show();

                return false;
            }

            function deleteWatch(id) {
                jQuery.ajax({ cache:false, url:'${createLink(controller: "user", action: "ajaxRemoveFromWatches")}/' + id });
                jQuery("#watch_" + id).remove();

                if (!jQuery("#watches-block li").length) {
                    jQuery("#watches-block").remove();
                    jQuery("#no-watches-message").show();
                }

                return false;
            }
        </script>
    </g:if>
</g:if>

<p id="no-watches-message" style="display: ${watches.size() ? 'none' : 'block'};">
    <em><g:message code="userDoesNotHaveAnyWatches.message"
                   default="This user does not have any watches"/></em>
</p>