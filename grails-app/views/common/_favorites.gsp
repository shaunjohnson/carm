<div id="userInfo" class="ui-widget ui-widget-content ui-corner-all" style="display: none;">
    <g:if test="${favorites.size()}">
        <div>
            <ul class="ui-menu ui-widget-content ui-corner-all">
                <g:each in="${favorites}" var="favorite">
                    <li class="ui-menu-item">
                        <g:link controller="application" action="show" id="${favorite.application.id}"
                                class="ui-corner-all" tabindex="-1">
                            ${favorite.application.name.encodeAsHTML()}
                        </g:link>
                    </li>
                </g:each>
            </ul>

            <div class="clearing"></div>
        </div>
    </g:if>

    <div class="ui-widget-header" style="padding: 0.5em;">
        <div style="float: left;">
            <carm:button controller="user" action="show" params="[username: username]" class="menuLink">
                <g:message code="profile.label" default="Profile"/>
            </carm:button>
        </div>

        <div style="float: right;">
            <carm:button controller="logout" class="menuLink">
                <g:message code="logout.label" default="Logout"/>
            </carm:button>
        </div>

        <div class="clearing"></div>
    </div>
</div>