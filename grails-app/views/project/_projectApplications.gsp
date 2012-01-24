<h2 class="sectionHeader">
    <g:message code="applications.label" default="Applications"/>
    <span class="actions">
        <g:link class="create" controller="application" action="create"
                params="['project.id': projectInstance?.id]">
            <g:message code="addApplication.label" default="Add Application"/>
        </g:link>
    </span>
</h2>

<g:if test="${applicationsGrouped?.size()}">
    <g:each in="${applicationsGrouped}" var="entry">
        <h3>${entry.key}</h3>
        <ul>
            <g:each in="${entry.value}" var="application">
                <li>
                    <g:link controller="application" action="show" id="${application.id}">
                        ${application?.encodeAsHTML()}
                    </g:link>
                </li>
            </g:each>
        </ul>
    </g:each>
</g:if>
<g:else>
    <p class="emphasis">
        <g:message code="projectDoesNotHaveAnyApplications.maessage"
                   default="This project does not have any applications."/>

    </p>
</g:else>