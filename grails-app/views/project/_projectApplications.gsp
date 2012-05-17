<div class="sectionHeader">
    <div class="text">
        <g:message code="applications.label" default="Applications"/>
    </div>
    <carmsec:isProjectOwner project="${projectInstance}">
        <div class="header-action new-application-action">
            <g:link controller="application" action="create"
                    params="['project.id': projectInstance?.id]">
                <g:message code="addApplication.label" default="Add Application"/>
            </g:link>
        </div>

        <div class="clearing"></div>
    </carmsec:isProjectOwner>
</div>

<g:if test="${applicationsGrouped?.size()}">
    <g:each in="${applicationsGrouped}" var="entry">
        <h4>${entry.key}</h4>

        <div class="row">
            <g:each in="${entry.value}" var="application">
                <div class="offset1">
                    <g:link controller="application" action="show" id="${application.id}">
                        ${application?.encodeAsHTML()}
                    </g:link>
                </div>
            </g:each>
        </div>
    </g:each>
</g:if>
<g:else>
    <p class="emphasis">
        <g:message code="projectDoesNotHaveAnyApplications.maessage"
                   default="This project does not have any applications."/>

    </p>
</g:else>