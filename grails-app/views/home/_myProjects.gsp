<div class="sectionHeader">
    <div class="text">
        <g:message code="myProjects.label" default="My Projects"/>
    </div>

    <div class="actions">
        <g:link controller="project" action="list">
            <g:message code="allProjects.label" default="All Projects"/>
        </g:link>
    </div>
</div>

<g:if test="${myProjects.size()}">
    <g:each in="${myProjects}" var="project" status="projectIndex">
        <div style="margin: 0.5em 0;">
            <h3>
                <g:link controller="project" action="show" id="${project.id}">
                    ${project.name.encodeAsHTML()}
                </g:link>
            </h3>
            <ul>
                <g:each in="${project.applications.sort { it.name }}" var="application">
                    <li>
                        <g:link controller="application" action="show" id="${application.id}">
                            ${application.name.encodeAsHTML()}
                        </g:link>
                    </li>
                </g:each>
            </ul>
        </div>

        <g:if test="${(projectIndex + 1) < myProjects.size()}">
            <hr class="divider"/>
        </g:if>
    </g:each>
</g:if>
<g:else>
    <p class="emphasis">
        <g:message code="youDontWorkOnAnyProjects.message" default="You don't work on any projects."/>
    </p>
</g:else>