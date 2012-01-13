<h2 class="sectionHeader">Applications</h2>

<div class="nav">
    <span class="menuButton">
        <g:link class="create" controller="application" action="create"
                params="['project.id': projectInstance?.id]">Add Application</g:link>
    </span>
</div>

<g:if test="${applicationsGrouped?.size()}">
    <g:each in="${applicationsGrouped}" var="entry">
        <h3>${entry.key}</h3>
        <ul>
            <g:each in="${entry.value}" var="application">
                <li><g:link controller="application" action="show" id="${application.id}">${application?.encodeAsHTML()}</g:link></li>
            </g:each>
        </ul>
    </g:each>
    </g:if>
<g:else>
    <p>
        This project does not have any applications.
    </p>
</g:else>