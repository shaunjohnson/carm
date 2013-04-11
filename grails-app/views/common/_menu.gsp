<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <g:link uri="/" class="brand">
            %{--<img src="${resource(dir: 'images', file: 'CARM_29x20.gif')}" width="29" height="20" alt="Logo"--}%
            %{--style="border-style: none"/>--}%
                <g:message code="carm.shortName.label" default="CARM"/>
            </g:link>
            <div class="nav-collapse">
                <ul class="nav">
                    <li>
                        <g:link controller="project">
                            <g:message code="projects.label" default="Projects"/>
                        </g:link>
                    </li>
                    <li>
                        <g:link controller="systemEnvironment">
                            <g:message code="systemEnvironments.label" default="Systems"/>
                        </g:link>
                    </li>
                    <li>
                        <g:link controller="administration">
                            <g:message code="administration.label" default="Administration"/>
                        </g:link>
                    </li>
                </ul>

                <ul class="nav pull-right">
                    <sec:ifLoggedIn>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <carm:formatCurrentUser/> <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <carm:userFavoritesMenu/>
                                <li>
                                    <g:link controller="user" action="show" params="[username: sec.username()]"
                                            class="menuLink">
                                        <i class="icon-user"></i>
                                        <g:message code="profile.label" default="Profile"/>
                                    </g:link>
                                </li>
                                <li>
                                    <g:link controller="logout" class="menuLink">
                                        <i class="icon-off"></i>
                                        <g:message code="logout.label" default="Logout"/>
                                    </g:link>
                                </li>
                            </ul>
                        </li>
                    </sec:ifLoggedIn>
                    <sec:ifNotLoggedIn>
                        <li>
                            <g:link controller="login" class="menuLink">
                                <g:message code="login.label" default="Login"/>
                            </g:link>
                        </li>
                    </sec:ifNotLoggedIn>
                </ul>

                <form class="navbar-search pull-right" action="" onsubmit="return false;">
                    <input id="quicksearch" type="text" class="search-query span2"
                           placeholder="${message(code: 'search.label', default: 'Search')}"/>
                </form>
            </div>
        </div>
    </div>
</div>