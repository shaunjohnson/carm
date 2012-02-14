<%@ page import="carm.Project" %>
<h2 class="sectionHeader">
    <g:message code="myProjects.label" default="My Projects"/>
    <span class="actions">
        <g:link controller="project" action="list">
            <g:message code="allProjects.label" default="All Projects"/>
        </g:link>
    </span>
</h2>

<g:each in="${projectCategoryList}" var="projectCategory" status="projectCategoryIndex">
    <g:set var="projects" value="${Project.findAllByCategory(projectCategory).sort { it.name }}"/>

    <div style="margin: 0.5em 0;">
        <h3>
            <g:link controller="projectCategory" action="show" id="${projectCategory.id}">
                ${projectCategory.encodeAsHTML()}
            </g:link>
        </h3>
        <ul>
            <g:each in="${projects}" var="projectInstance">
                <li>
                    <g:link controller="project" action="show" id="${projectInstance.id}">
                        ${projectInstance.encodeAsHTML()}
                    </g:link>
                </li>
            </g:each>
        </ul>
    </div>

    <g:if test="${(projectCategoryIndex + 1) < projectCategoryList.size()}">
        <hr class="divider"/>
    </g:if>
</g:each>