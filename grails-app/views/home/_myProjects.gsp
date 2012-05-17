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
        <h4>
            <g:link controller="project" action="show" id="${project.id}">
                ${project.name.encodeAsHTML()}
            </g:link>
        </h4>

        <div class="row">
            <g:each in="${project.applications.sort { it.name }}" var="application">
                <div class="offset1">
                    <g:link controller="application" action="show" id="${application.id}">
                        ${application.name.encodeAsHTML()}
                    </g:link>
                </div>
            </g:each>
        </div>

        <g:if test="${(projectIndex + 1) < myProjects.size()}">
            <hr class="divider"/>
        </g:if>
    </g:each>
</g:if>
<g:else>
    <p>
        <em><g:message code="youDontWorkOnAnyProjects.message" default="You don't work on any projects."/></em>
    </p>
</g:else>