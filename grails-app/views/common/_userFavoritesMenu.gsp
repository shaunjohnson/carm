<g:each in="${favorites}" var="favorite">
    <li>
        <g:if test="${favorite.application}">
            <g:link controller="application" action="show" id="${favorite.application.id}">
                <span class="application-item-icon"></span>
                ${favorite.application.name.encodeAsHTML()}
            </g:link>
        </g:if>
        <g:elseif test="${favorite.project}">
            <g:link controller="project" action="show" id="${favorite.project.id}">
                <span class="project-item-icon"></span>
                ${favorite.project.name.encodeAsHTML()}
            </g:link>
        </g:elseif>
    </li>
</g:each>
<li class="divider"></li>