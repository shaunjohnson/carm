<ul id='multi-ddm'>
	<li><a href="${createLink(uri: '/')}">Home</a></li>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <li><a href="#">Administration</a>
            <ul>
                <li><a href="#">Projects</a>
                    <ul>
                        <li><g:link controller="project">Projects</g:link></li>
                        <li><g:link controller="application">Applications</g:link></li>
                        <li><g:link controller="module">Modules</g:link></li>
                    </ul>
                </li>
                <li><a href="#">Source Control</a>
                    <ul>
                        <li><g:link controller="sourceControlServer">Servers</g:link></li>
                        <li><g:link controller="sourceControlRepository">Repositories</g:link></li>
                        <li><g:link controller="sourceControlUser">Users</g:link></li>
                        <li><g:link controller="sourceControlRole">Roles</g:link></li>
                    </ul>
                </li>
                <li><a href="#">Systems</a>
                    <ul>
                        <li><g:link controller="system">Systems</g:link></li>
                        <li><g:link controller="systemComponent">Components</g:link></li>
                        <li><g:link controller="systemEnvironment">Environments</g:link></li>
                    </ul>
                </li>
                <li><a href="#">Types</a>
                    <ul>
                        <li><g:link controller="applicationType">Application Types</g:link></li>
                        <li><g:link controller="moduleType">Module Types</g:link></li>
                    </ul>
                </li>
            </ul>
        </li>
    </sec:ifAllGranted>
</ul>