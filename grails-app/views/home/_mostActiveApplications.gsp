<div class="sectionHeader">
    <div class="text">
        <g:message code="mostActiveApplications.label" default="Most Active Applications"/>
    </div>

    <div class="section-action">
        <g:link controller="project" action="list">
            <g:message code="allProjects.label" default="All Projects"/>
        </g:link>
    </div>
</div>

<div class="section-content">
    <g:if test="${mostActiveApplications.size()}">
        <g:each in="${mostActiveApplications.entrySet()}" var="projectEntry" status="projectIndex">
            <g:set var="project" value="${projectEntry.key}"/>
            <g:set var="applications" value="${projectEntry.value}"/>

            <h3>
                <g:link controller="project" action="show" id="${project.id}">
                    ${project.encodeAsHTML()}
                </g:link>
            </h3>

            <div class="row">
                <g:each in="${applications}" var="application">
                    <div class="offset-half">
                        <g:link controller="application" action="show" id="${application.id}">
                            ${application.encodeAsHTML()}
                        </g:link>
                    </div>
                </g:each>
            </div>

            <g:if test="${(projectIndex + 1) < mostActiveApplications.size()}">
                <hr/>
            </g:if>
        </g:each>
    </g:if>
    <g:else>
        <p>
            <em><g:message code="noActiveApplications.message" default="There are no active applications."/></em>
        </p>
    </g:else>
</div>