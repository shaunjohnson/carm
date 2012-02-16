<div id="topMenu" class="clearing">
    <div style="margin: 0 auto; width: 900px;">
        <div>
            <g:link uri="/" style="outline: none; font-size: 1.5em;">
                <img src="${resource(dir: 'images', file: 'CARM_29x20.gif')}" width="29" height="20" alt="Logo"
                     style="border-style: none"/>
                <g:message code="carm.shortName.label" default="CARM"/>
            </g:link>
        </div>
        <ul style="margin-top: 3px">
            <li>
                <g:link controller="project">
                    <g:message code="projects.label" default="Projects"/>
                </g:link>
            </li>
            <li>
                <g:link controller="system">
                    <g:message code="systems.label" default="Systems"/>
                </g:link>
            </li>
            <li>
                <g:link controller="administration">
                    <g:message code="administration.label" default="Administration"/>
                </g:link>
            </li>
        </ul>

        <div style="float: right; margin-top: 3px;">
            <sec:ifLoggedIn>
                <g:link controller='logout'>
                    <g:message code="logout.label" default="Logout"/> (<sec:loggedInUserInfo field="username"/>)
                </g:link>
            </sec:ifLoggedIn>
            <sec:ifNotLoggedIn>
                <g:link controller='login'>
                    <g:message code="login.label" default="Login"/>
                </g:link>
            </sec:ifNotLoggedIn>
        </div>

        <div id="spinner" class="spinner" style="display: none;">
            <img src="${resource(dir: 'images', file: 'spinner.gif')}"
                 alt="${message(code: 'spinner.alt', default: 'Loading...')}"/>
        </div>
    </div>
</div>

<div class="clearing"/>