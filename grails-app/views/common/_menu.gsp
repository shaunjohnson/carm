<ul id='multi-ddm'>
	<li><a href="${createLink(uri: '/')}">Home</a></li>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <li><a href="#">Administration</a>
            <ul>
                <li><g:link controller="application">Applications</g:link></li>
                <li><g:link controller="applicationType">Application Types</g:link></li>
                <li><g:link controller="module">Modules</g:link></li>
                <li><g:link controller="moduleType">Module Types</g:link></li>
                <li><g:link controller="project">Projects</g:link></li>
                <li><g:link controller="sourceControlRepository">Source Control Repositories</g:link></li>
                <li><g:link controller="sourceControlRole">Source Control Roles</g:link></li>
                <li><g:link controller="sourceControlServer">Source Control Servers</g:link></li>
                <li><g:link controller="sourceControlUser">Source Control Users</g:link></li>
                <li><g:link controller="system">Systems</g:link></li>
                <li><g:link controller="systemComponent">System Components</g:link></li>
                <li><g:link controller="systemEnvironemnt">System Environemnts</g:link></li>
            </ul>
        </li>
    </sec:ifAllGranted>
</ul>