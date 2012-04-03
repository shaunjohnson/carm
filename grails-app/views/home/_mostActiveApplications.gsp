<div class="sectionHeader">
    <div class="text">
        <g:message code="mostActiveApplications.label" default="Most Active Applications"/>
    </div>

    <div class="actions">
        <g:link controller="project" action="list">
            <g:message code="allProjects.label" default="All Projects"/>
        </g:link>
    </div>
</div>

<g:if test="${mostActiveApplications.size()}">
    <g:each in="${mostActiveApplications.entrySet()}" var="projectEntry" status="projectIndex">
        <g:set var="project" value="${projectEntry.key}"/>
        <g:set var="applications" value="${projectEntry.value}"/>

        <div style="margin: 0.5em 0;">
            <h3>
                <g:link controller="project" action="show" id="${project.id}">
                    ${project.encodeAsHTML()}
                </g:link>
            </h3>
            <ul>
                <g:each in="${applications}" var="application">
                    <li>
                        <g:link controller="application" action="show" id="${application.id}">
                            ${application.encodeAsHTML()}
                        </g:link>
                    </li>
                </g:each>
            </ul>
        </div>

        <g:if test="${(projectIndex + 1) < mostActiveApplications.size()}">
            <hr class="divider"/>
        </g:if>
    </g:each>
</g:if>
<g:else>
    <p class="emphasis">
        <g:message code="noActiveApplications.message" default="There are no active applications."/>
    </p>
</g:else>