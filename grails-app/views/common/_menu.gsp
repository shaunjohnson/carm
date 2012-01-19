<div id="menuBlock" class="clearning">
    <ul id='multi-ddm'>
        <li>
            <g:link uri="/">
                <g:message code="home.label" default="Home"/>
            </g:link>
        </li>
        <li>
            <g:link controller="project">
                <g:message code="projects.label" default="Projects"/>
            </g:link>
            <ul>
                <li>
                    <g:link controller="project">
                        <g:message code="projects.label" default="Projects"/>
                    </g:link>
                </li>
                <li>
                    <g:link controller="application">
                        <g:message code="applications.label" default="Applications"/>
                    </g:link>
                </li>
                <li>
                    <g:link controller="module">
                        <g:message code="modules.label" default="Modules"/>
                    </g:link>
                </li>
                <li>
                    <g:link controller="applicationRelease">
                        <g:message code="releases.label" default="Releases"/>
                    </g:link>
                </li>
                <li>
                    <g:link controller="applicationDeployment">
                        <g:message code="deployments.label" default="Deployments"/>
                    </g:link>
                </li>
            </ul>
        </li>
        <li>
            <g:link controller="system">
                <g:message code="systems.label" default="Systems"/>
            </g:link>
            <ul>
                <li>
                    <g:link controller="system">
                        <g:message code="systems.label" default="Systems"/>
                    </g:link>
                </li>
                <li>
                    <g:link controller="systemComponent">
                        <g:message code="components.label" default="Components"/>
                    </g:link>
                </li>
                <li>
                    <g:link controller="systemEnvironment">
                        <g:message code="environments.label" default="Environments"/>
                    </g:link>
                </li>
            </ul>
        </li>
        <li>
            <g:link controller="administration">
                <g:message code="administration.label" default="Administration"/>
            </g:link>
            <ul>
                <li>
                    <g:link controller="sourceControlServer">
                        <g:message code="sourceControl.label" default="Source Control"/>
                    </g:link>
                    <ul>
                        <li>
                            <g:link controller="sourceControlServer">
                                <g:message code="servers.label" default="Servers"/>
                            </g:link>
                        </li>
                        <li>
                            <g:link controller="sourceControlRepository">
                                <g:message code="repositories.label" default="Repositories"/>
                            </g:link>
                        </li>
                        <li>
                            <g:link controller="sourceControlUser">
                                <g:message code="users.label" default="Users"/>
                            </g:link>
                        </li>
                        <li>
                            <g:link controller="sourceControlRole">
                                <g:message code="roles.label" default="Roles"/>
                            </g:link>
                        </li>
                    </ul>
                </li>
                <li><a href="#">
                    Types
                </a>
                    <ul>
                        <li>
                            <g:link controller="applicationDeploymentTestState">
                                <g:message code="applicationDeploymentTestStates.label"
                                           default="Application Deployment Test States"/>
                            </g:link>
                        </li>
                        <li>
                            <g:link controller="applicationReleaseTestState">
                                <g:message code="applicationReleaseTestStates.label"
                                           default="Application Release Test States"/>
                            </g:link>
                        </li>
                        <li>
                            <g:link controller="applicationType">
                                <g:message code="applicationTypes.label" default="Application Types"/>
                            </g:link>
                        </li>
                        <li>
                            <g:link controller="moduleDeploymentTestState">
                                <g:message code="moduleDeploymentTestStates.label"
                                           default="Module Deployment Test States"/>
                            </g:link>
                        </li>
                        <li>
                            <g:link controller="moduleType">
                                <g:message code="moduleTypes.label" default="Module Types"/>
                            </g:link>
                        </li>
                        <li>
                            <g:link controller="projectCategory">
                                <g:message code="projectCategories.label" default="Project Categories"/>
                            </g:link>
                        </li>
                    </ul>
                </li>
                <li>
                    <g:link controller="user">
                        <g:message code="security.label" default="Security"/>
                    </g:link>
                    <ul>
                        <li>
                            <g:link controller="user">
                                <g:message code="users.label" default="Users"/>
                            </g:link>
                        </li>
                        <li>
                            <g:link controller="role">
                                <g:message code="roles.label" default="Roles"/>
                            </g:link>
                        </li>
                    </ul>
                </li>
                <li>
                    <g:link uri="/index.gsp">
                        <g:message code="about.label" default="About"/>
                    </g:link>
                </li>
            </ul>
        </li>
        <sec:ifLoggedIn>
            <li>
                <g:link controller='logout'>
                    <g:message code="logout.label" default="Logout"/> (<sec:loggedInUserInfo field="username"/>)
                </g:link>
            </li>
        </sec:ifLoggedIn>
        <sec:ifNotLoggedIn>
            <li>
                <g:link controller='login'>
                    <g:message code="login.label" default="Login"/>
                </g:link>
            </li>
        </sec:ifNotLoggedIn>
    </ul>

    <div id="spinner" class="spinner" style="display: none;">
        <img src="${resource(dir: 'images', file: 'spinner.gif')}"
             alt="${message(code: 'spinner.alt', default: 'Loading...')}"/>
    </div>
</div>