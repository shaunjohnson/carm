<div class="sectionHeader">
    <div class="text">
        <g:message code="mostActiveProjects.label" default="Most Active Projects"/>
    </div>

    <div class="actions">
        <g:link controller="project" action="list">
            <g:message code="allProjects.label" default="All Projects"/>
        </g:link>
    </div>
</div>

<g:if test="${mostActiveProjects.size()}">
    <p>
        These are the most active projects.
    </p>
    <div style="margin: 0.5em 0;">
        <ul>
            <g:each in="${mostActiveProjects}" var="project">
                <li>
                    <g:link controller="project" action="show" id="${project.id}">
                        ${project.encodeAsHTML()}
                    </g:link>
                </li>
            </g:each>
        </ul>
    </div>
</g:if>
<g:else>
    <p class="emphasis">
        <g:message code="noActiveProjects.message" default="There are no active projects."/>
    </p>
</g:else>