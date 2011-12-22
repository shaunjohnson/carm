<%@ page import="carm.Application; carm.ApplicationType; carm.Module; carm.ModuleType; carm.Project; carm.ProjectCategory; carm.SystemComponent; carm.SystemEnvironment; carm.SourceControlRepository; carm.SourceControlServer" %>

<g:if test="${pageName}">
    <h1>${pageName}</h1>
</g:if>
<g:elseif test="${domain}">
    <h1>${domain.name}</h1>
</g:elseif>
<g:else>
    <h1>!!! Unknown !!!</h1>
</g:else>

<div class="breadcrumbs">
    <g:link uri="/">Home</g:link>

    <g:if test="${domain instanceof List}">
        <g:set var="domainBean" value="${domain[0]}"/>

        <g:if test="${domainBean instanceof Project}">
            <span class="spacer"> &gt; </span>
            <g:link controller="project" action="list" title="Show All Projects">
                Projects
            </g:link>
        </g:if>
        <g:elseif test="${domainBean instanceof Application}">
            <span class="spacer"> &gt; </span>
            <g:link controller="application" action="list" title="Show All Applications">
                Applications
            </g:link>
        </g:elseif>
        <g:elseif test="${domainBean instanceof ApplicationType}">
            <span class="spacer"> &gt; </span>
            <g:link controller="applicationType" action="list" title="Show All Application Types">
                Application Types
            </g:link>
        </g:elseif>
        <g:elseif test="${domainBean instanceof Module}">
            <span class="spacer"> &gt; </span>
            <g:link controller="module" action="list" title="Show All Modules">
                Modules
            </g:link>
        </g:elseif>
        <g:elseif test="${domainBean instanceof ModuleType}">
            <span class="spacer"> &gt; </span>
            <g:link controller="moduleType" action="list" title="Show All Module Types">
                Module Types
            </g:link>
        </g:elseif>
        <g:elseif test="${domainBean instanceof ProjectCategory}">
            <span class="spacer"> &gt; </span>
            <g:link controller="projectCategory" action="list" title="Show All Project Categories">
                Project Categories
            </g:link>
        </g:elseif>
    </g:if>
    <g:else>
        <g:if test="${domain instanceof Application}">
            <span class="spacer"> &gt; </span>
            <g:link controller="project" action="list" title="Show All Projects">
                Projects
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="project" action="show" id="${domain.project.id}" title="Show Project">
                ${domain.project.encodeAsHTML()}
            </g:link>

            <g:if test="${domain.name}">
                <span class="spacer"> &gt; </span>
                <g:link controller="application" action="show" id="${domain.id}" title="Show Application">
                    ${domain.name.encodeAsHTML()}
                </g:link>
            </g:if>
        </g:if>
        <g:elseif test="${domain instanceof ApplicationType}">
            <span class="spacer"> &gt; </span>
            <g:link controller="applicationType" action="list" title="Show All Application Types">
                Application Types
            </g:link>

            <g:if test="${domain.name}">
                <span class="spacer"> &gt; </span>
                <g:link controller="applicationType" action="show" id="${domain.id}" title="Show Application Type">
                    ${domain.name.encodeAsHTML()}
                </g:link>
            </g:if>
        </g:elseif>
        <g:elseif test="${domain instanceof Project}">
            <span class="spacer"> &gt; </span>
            <g:link controller="project" action="list" title="Show All Projects">
                Projects
            </g:link>

            <g:if test="${domain.name}">
                <span class="spacer"> &gt; </span>
                <g:link controller="project" action="show" id="${domain.id}" title="Show Project">
                    ${domain.name.encodeAsHTML()}
                </g:link>
            </g:if>
        </g:elseif>
        <g:elseif test="${domain instanceof Module}">
            <span class="spacer"> &gt; </span>
            <g:link controller="project" action="list" title="Show All Projects">
                Projects
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="project" action="show" id="${domain.application.project.id}" title="Show Project">
                ${domain.application.project.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="application" action="show" id="${domain.application.id}" title="Show Application">
                ${domain.application.encodeAsHTML()}
            </g:link>

            <g:if test="${domain.name}">
                <span class="spacer"> &gt; </span>
                <g:link controller="module" action="show" id="${domain.id}" title="Show Module">
                    ${domain.name.encodeAsHTML()}
                </g:link>
            </g:if>
        </g:elseif>
        <g:elseif test="${domain instanceof ModuleType}">
            <span class="spacer"> &gt; </span>
            <g:link controller="moduleType" action="list" title="Show All Module Types">
                Module Types
            </g:link>

            <g:if test="${domain.name}">
                <span class="spacer"> &gt; </span>
                <g:link controller="moduleType" action="show" id="${domain.id}" title="Show Module Type">
                    ${domain.name.encodeAsHTML()}
                </g:link>
            </g:if>
        </g:elseif>
        <g:elseif test="${domain instanceof SystemComponent}">
            <span class="spacer"> &gt; </span>
            <g:link controller="system" action="list" title="Show All Systems">
                Systems
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="system" action="show" id="${domain.system.id}" title="Show System">
                ${domain.system.encodeAsHTML()}
            </g:link>

            <g:if test="${domain.name}">
                <span class="spacer"> &gt; </span>
                <g:link controller="systemComponent" action="show" id="${domain.id}" title="Show System Component">
                    ${domain.name.encodeAsHTML()}
                </g:link>
            </g:if>
        </g:elseif>
        <g:elseif test="${domain instanceof SystemComponent || domain instanceof SystemEnvironment}">
            <span class="spacer"> &gt; </span>
            <g:link controller="system" action="list" title="Show All Systems">
                Systems
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="system" action="show" id="${domain.system.id}" title="Show System">
                ${domain.system.encodeAsHTML()}
            </g:link>

            <g:if test="${domain.name}">
                <span class="spacer"> &gt; </span>
                <g:link controller="systemEnvironment" action="show" id="${domain.id}" title="Show System Environment">
                    ${domain.name.encodeAsHTML()}
                </g:link>
            </g:if>
        </g:elseif>
        <g:elseif test="${domain instanceof SourceControlServer}"><span class="spacer"> &gt; </span>
            <g:link controller="sourceControlServer" action="list" title="Show All Source Control Servers">
                Servers
            </g:link>

            <g:if test="${domain.name}">
                <span class="spacer"> &gt; </span>
                <g:link controller="sourceControlServer" action="show" id="${domain.id}" title="Show Source Control Server">
                    ${domain.name.encodeAsHTML()}
                </g:link>
            </g:if>
        </g:elseif>
        <g:elseif test="${domain instanceof SourceControlRepository}"><span class="spacer"> &gt; </span>
            <g:link controller="sourceControlServer" action="list" title="Show All Source Control Servers">
                Servers
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="sourceControlServer" action="show" id="${domain.server.id}" title="Show Source Control Server">
                ${domain.server.encodeAsHTML()}
            </g:link>

            <g:if test="${domain.name}">
                <span class="spacer"> &gt; </span>
                <g:link controller="sourceControlRepository" action="show" id="${domain.id}" title="Show Source Control Repository">
                    ${domain.name.encodeAsHTML()}
                </g:link>
            </g:if>
        </g:elseif>

        <g:if test="${pageName}">
            <span class="spacer"> &gt; </span>
            ${pageName}
        </g:if>
    </g:else>
</div>