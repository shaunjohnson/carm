<div class="sectionHeader">
    <div class="text">
        <g:message code="applications.label" default="Applications"/>
    </div>
    <carmsec:isProjectOwner project="${projectInstance}">
        <div class="section-action-icon new-application-action">
            <g:link controller="application" action="create"
                    params="['project.id': projectInstance?.id]">
                <g:message code="addApplication.label" default="Add Application"/>
            </g:link>
        </div>

        <div class="clearing"></div>
    </carmsec:isProjectOwner>
</div>

<g:if test="${applicationsGrouped?.size()}">
    <g:each in="${applicationsGrouped}" var="entry" status="applicationsGroupedIndex">
        <h3>${entry.key}</h3>

        <div class="row">
            <g:each in="${entry.value}" var="application">
                <div class="offset-half">
                    <g:link controller="application" action="show" id="${application.id}">
                        ${application?.encodeAsHTML()}
                    </g:link>
                </div>
            </g:each>
        </div>

        <g:if test="${(applicationsGroupedIndex + 1) < applicationsGrouped.size()}">
            <hr/>
        </g:if>
    </g:each>
</g:if>
<g:else>
    <carm:alertWarning message="${message(code: "projectDoesNotHaveAnyApplications.message")}"/>
</g:else>