<div class="sectionHeader">
    <div class="text">
        <g:message code="project.projectOwners.label" default="Project Owners"/>
    </div>
</div>

<g:if test="${projectOwners?.size()}">
    <ul>
        <g:each in="${projectOwners}" var="projectOwner">
            <li>
                <g:link controller="user" action="show" params="[username: projectOwner]">
                    <carm:formatUser username="${projectOwner}"/>
                </g:link>
            </li>
        </g:each>
    </ul>
</g:if>
<g:else>
    <g:message code="projectDoesNotHaveAnyOwners.message" default="This project does not have any owners."/>
</g:else>
