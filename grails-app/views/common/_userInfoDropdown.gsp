<div id="userInfo" class="ui-widget ui-widget-content ui-corner-all" style="display: none;">
    <g:if test="${favorites.size()}">
        <div>
            <ul class="ui-menu ui-widget-content ui-corner-all">
                <g:each in="${favorites}" var="favorite">
                    <li class="ui-menu-item ui-menu-item-with-icon">
                        <g:if test="${favorite.application}">
                            <g:link controller="application" action="show" id="${favorite.application.id}"
                                    class="ui-corner-all" tabindex="-1">
                                <span class="application-item-icon"></span>
                                ${favorite.application.name.encodeAsHTML()}
                            </g:link>
                        </g:if>
                        <g:elseif test="${favorite.project}">
                            <g:link controller="project" action="show" id="${favorite.project.id}"
                                    class="ui-corner-all" tabindex="-1">
                                <span class="project-item-icon"></span>
                                ${favorite.project.name.encodeAsHTML()}
                            </g:link>
                        </g:elseif>
                    </li>
                </g:each>
            </ul>

            <div class="clearing"></div>
        </div>
    </g:if>

    <div class="ui-widget-header" style="padding: 0 0.5em;">
        <div style="float: left;">
            <g:link controller="user" action="show" params="[username: username]" class="menuLink">
                <g:message code="profile.label" default="Profile"/>
            </g:link>
        </div>

        <div style="float: right;">
            <g:link controller="logout" class="menuLink">
                <g:message code="logout.label" default="Logout"/>
            </g:link>
        </div>

        <div class="clearing"></div>
    </div>
</div>