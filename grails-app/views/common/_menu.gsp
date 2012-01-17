<div id="menuBlock" class="clearning">
    <ul id='multi-ddm'>
        <li><a href="${createLink(uri: '/')}">Home</a></li>
        <li><g:link controller="project">Projects</g:link>
            <ul>
                <li><g:link controller="project">Projects</g:link></li>
                <li><g:link controller="application">Applications</g:link></li>
                <li><g:link controller="module">Modules</g:link></li>
                <li><g:link controller="applicationRelease">App Releases</g:link></li>
            </ul>
        </li>
        <li><g:link controller="system">Systems</g:link>
            <ul>
                <li><g:link controller="system">Systems</g:link></li>
                <li><g:link controller="systemComponent">Components</g:link></li>
                <li><g:link controller="systemEnvironment">Environments</g:link></li>
            </ul>
        </li>
        <li><g:link controller="administration">Administration</g:link>
            <ul>
                <li><g:link controller="sourceControlServer">Source Control</g:link>
                    <ul>
                        <li><g:link controller="sourceControlServer">Servers</g:link></li>
                        <li><g:link controller="sourceControlRepository">Repositories</g:link></li>
                        <li><g:link controller="sourceControlUser">Users</g:link></li>
                        <li><g:link controller="sourceControlRole">Roles</g:link></li>
                    </ul>
                </li>
                <li><a href="#">Types</a>
                    <ul>
                        <li><g:link controller="applicationDeploymentTestState">Application Deployment Test States</g:link></li>
                        <li><g:link controller="applicationReleaseTestState">Application Release Test States</g:link></li>
                        <li><g:link controller="applicationType">Application Types</g:link></li>
                        <li><g:link controller="moduleDeploymentTestState">Module Deployment Test States</g:link></li>
                        <li><g:link controller="moduleType">Module Types</g:link></li>
                        <li><g:link controller="projectCategory">Project Categories</g:link></li>
                    </ul>
                </li>
                <li><g:link controller="user">Security</g:link>
                    <ul>
                        <li><g:link controller="user">Users</g:link></li>
                        <li><g:link controller="role">Roles</g:link></li>
                    </ul>
                </li>
                <li><a href="${createLink(uri: '/index.gsp')}">About</a></li>
            </ul>
        </li>
        <sec:ifLoggedIn>
            <li><g:link controller='logout'>Logout (<sec:loggedInUserInfo field="username"/>)</g:link></li>
        </sec:ifLoggedIn>
        <sec:ifNotLoggedIn>
            <li><g:link controller='login'>Login</g:link></li>
        </sec:ifNotLoggedIn>
    </ul>

    <div id="spinner" class="spinner" style="display: none;">
        <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
    </div>
</div>