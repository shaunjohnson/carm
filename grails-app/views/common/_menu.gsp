<div id="topMenu" class="clearing">
    <div style="margin: 0 auto; width: 900px;">
        <div>
            <g:link uri="/" style="outline: none; font-size: 1.5em;" class="menuLink">
                <img src="${resource(dir: 'images', file: 'CARM_29x20.gif')}" width="29" height="20" alt="Logo"
                     style="border-style: none"/>
                <g:message code="carm.shortName.label" default="CARM"/>
            </g:link>
        </div>
        <ul class="menuLinkContainer">
            <li>
                <g:link controller="project" class="menuLink">
                    <g:message code="projects.label" default="Projects"/>
                </g:link>
            </li>
            <li>
                <g:link controller="systemEnvironment" class="menuLink">
                    <g:message code="systemEnvironments.label" default="Systems"/>
                </g:link>
            </li>
            <li>
                <g:link controller="administration" class="menuLink">
                    <g:message code="administration.label" default="Administration"/>
                </g:link>
            </li>
        </ul>

        <div style="float: right; margin-left: 5px; margin-top: 5px;">
            <sec:ifLoggedIn>
                <a id="userInfoButton" class="menuLink">
                    <div>
                        <div style="float: left;">
                            <carm:formatCurrentUser/>
                        </div>
                        <div style="float: left;">
                            <span class="ui-icon ui-icon-triangle-1-s"></span>
                        </div>
                        <div class="clearing"></div>
                    </div>
                </a>
            </sec:ifLoggedIn>
            <sec:ifNotLoggedIn>
                <g:link controller="login" class="menuLink">
                    <g:message code="login.label" default="Login"/>
                </g:link>
            </sec:ifNotLoggedIn>
        </div>

        <div style="float: right; margin-right: 1.5em;">
            <input id="quicksearch" size="18" placeholder="Search Applications"/>
        </div>

        <div id="spinner" class="spinner" style="display: none;">
            <img src="${resource(dir: 'images', file: 'spinner.gif')}"
                 alt="${message(code: 'spinner.alt', default: 'Loading...')}"/>
        </div>
    </div>
</div>

<div class="clearing"/>