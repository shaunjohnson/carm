<%@ page import="carm.Application; carm.ApplicationType; carm.Module; carm.ModuleType; carm.Project; carm.ProjectCategory; carm.SystemComponent; carm.SystemEnvironment; carm.SourceControlRepository; carm.SourceControlServer" %>

<g:set var="action" value="${params.action}"/>
<g:set var="controller" value="${params.controller}"/>

<g:if test="${controller == 'application'}">
    <g:set var="entityName" value="${message(code: 'application.label', default: 'Application')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:link uri="/">Home</g:link>

        <g:if test="${action == 'list'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="application" action="list" title="Show All Applications">
                Applications
            </g:link>
        </g:if>
        <g:elseif test="${action == 'show'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="project" action="list" title="Show All Projects">
                Projects
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="project" action="show" id="${domain.project.id}" title="Show Project">
                ${domain.project.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="application" action="show" id="${domain.id}" title="Show Application">
                ${domain.name.encodeAsHTML()}
            </g:link>
        </g:elseif>
        <g:elseif test="${action == 'create'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="project" action="list" title="Show All Projects">
                Projects
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="project" action="show" id="${domain.project.id}" title="Show Project">
                ${domain.project.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:message code="default.create.label" args="[entityName]"/>
        </g:elseif>
        <g:elseif test="${action == 'edit'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="project" action="list" title="Show All Projects">
                Projects
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="project" action="show" id="${domain.project.id}" title="Show Project">
                ${domain.project.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="application" action="show" id="${domain.id}" title="Show Application">
                ${domain.name.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:message code="default.edit.label" args="[entityName]"/>
        </g:elseif>
    </div>
</g:if>
<g:elseif test="${controller == 'applicationType'}">
    <g:set var="entityName" value="${message(code: 'applicationType.label', default: 'Application Type')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:link uri="/">Home</g:link>

        <span class="spacer"> &gt; </span>
        <g:link controller="applicationType" action="list" title="Show All Application Types">
            Application Types
        </g:link>

        <g:if test="${action == 'show'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="applicationType" action="show" id="${domain.id}" title="Show Application Type">
                ${domain.name.encodeAsHTML()}
            </g:link>
        </g:if>
        <g:elseif test="${action == 'create'}">
            <span class="spacer"> &gt; </span>
            <g:message code="default.create.label" args="[entityName]"/>
        </g:elseif>
        <g:elseif test="${action == 'edit'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="applicationType" action="show" id="${domain.id}" title="Show Application Type">
                ${domain.name.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:message code="default.edit.label" args="[entityName]"/>
        </g:elseif>
    </div>
</g:elseif>
<g:if test="${controller == 'module'}">
    <g:set var="entityName" value="${message(code: 'module.label', default: 'Module')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:link uri="/">Home</g:link>

        <g:if test="${action == 'list'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="module" action="list" title="Show All Modules">
                Modules
            </g:link>
        </g:if>
        <g:elseif test="${action == 'show'}">
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
                ${domain.application.name.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="module" action="show" id="${domain.id}" title="Show Module">
                ${domain.name.encodeAsHTML()}
            </g:link>
        </g:elseif>
        <g:elseif test="${action == 'create'}">
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
                ${domain.application.name.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:message code="default.create.label" args="[entityName]"/>
        </g:elseif>
        <g:elseif test="${action == 'edit'}">
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
                ${domain.application.name.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="module" action="show" id="${domain.id}" title="Show Module">
                ${domain.name.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:message code="default.edit.label" args="[entityName]"/>
        </g:elseif>
    </div>
</g:if>
<g:elseif test="${controller == 'moduleType'}">
    <g:set var="entityName" value="${message(code: 'moduleType.label', default: 'Module Type')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:link uri="/">Home</g:link>

        <span class="spacer"> &gt; </span>
        <g:link controller="moduleType" action="list" title="Show All Module Types">
            Module Types
        </g:link>

        <g:if test="${action == 'show'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="moduleType" action="show" id="${domain.id}" title="Show Module Type">
                ${domain.name.encodeAsHTML()}
            </g:link>
        </g:if>
        <g:elseif test="${action == 'create'}">
            <span class="spacer"> &gt; </span>
            <g:message code="default.create.label" args="[entityName]"/>
        </g:elseif>
        <g:elseif test="${action == 'edit'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="moduleType" action="show" id="${domain.id}" title="Show Module Type">
                ${domain.name.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:message code="default.edit.label" args="[entityName]"/>
        </g:elseif>
    </div>
</g:elseif>
<g:elseif test="${controller == 'project'}">
    <g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:link uri="/">Home</g:link>
        <span class="spacer"> &gt; </span>
        <g:link controller="project" action="list" title="Show All Projects">
            Projects
        </g:link>

        <g:if test="${action == 'show'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="project" action="show" id="${domain.id}" title="Show Project">
                ${domain.name.encodeAsHTML()}
            </g:link>
        </g:if>
        <g:elseif test="${action == 'create'}">
            <span class="spacer"> &gt; </span>
            <g:message code="default.create.label" args="[entityName]"/>
        </g:elseif>
        <g:elseif test="${action == 'edit'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="project" action="show" id="${domain.id}" title="Show Project">
                ${domain.name.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:message code="default.edit.label" args="[entityName]"/>
        </g:elseif>
    </div>
</g:elseif>
<g:elseif test="${controller == 'projectCategory'}">
    <g:set var="entityName" value="${message(code: 'projectCategory.label', default: 'Project Category')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:link uri="/">Home</g:link>

        <span class="spacer"> &gt; </span>
        <g:link controller="projectCategory" action="list" title="Show All Project Categories">
            Project Categories
        </g:link>

        <g:if test="${action == 'show'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="projectCategory" action="show" id="${domain.id}" title="Show Project Category">
                ${domain.name.encodeAsHTML()}
            </g:link>
        </g:if>
        <g:elseif test="${action == 'create'}">
            <span class="spacer"> &gt; </span>
            <g:message code="default.create.label" args="[entityName]"/>
        </g:elseif>
        <g:elseif test="${action == 'edit'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="projectCategory" action="show" id="${domain.id}" title="Show Project Category">
                ${domain.name.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:message code="default.edit.label" args="[entityName]"/>
        </g:elseif>
    </div>
</g:elseif>
<g:elseif test="${controller == 'sourceControlRepository'}">
    <g:set var="entityName" value="${message(code: 'sourceControlRepositorye.label', default: 'Source Control Repository')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:link uri="/">Home</g:link>

        <g:if test="${action == 'list'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="sourceControlRepository" action="list" title="Show All Source Control Repositories">
                Source Control Repositories
            </g:link>
        </g:if>
        <g:elseif test="${action == 'show'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="sourceControlServer" action="list" title="Show All Source Control Servers">
                Source Control Servers
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="sourceControlServer" action="show" id="${domain.server.id}" title="Show Source Control Server">
                ${domain.server.name.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="sourceControlRepository" action="show" id="${domain.id}" title="Show Source Control Repository">
                ${domain.name.encodeAsHTML()}
            </g:link>
        </g:elseif>
        <g:elseif test="${action == 'create'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="sourceControlServer" action="list" title="Show All Source Control Servers">
                Source Control Servers
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="sourceControlServer" action="show" id="${domain.server.id}" title="Show Source Control Server">
                ${domain.server.name.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:message code="default.create.label" args="[entityName]"/>
        </g:elseif>
        <g:elseif test="${action == 'edit'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="sourceControlServer" action="list" title="Show All Source Control Servers">
                Source Control Servers
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="sourceControlServer" action="show" id="${domain.server.id}" title="Show Source Control Server">
                ${domain.server.name.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="sourceControlRepository" action="show" id="${domain.id}" title="Show Source Control Repository">
                ${domain.name.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:message code="default.edit.label" args="[entityName]"/>
        </g:elseif>
    </div>
</g:elseif>
<g:elseif test="${controller == 'sourceControlServer'}">
    <g:set var="entityName" value="${message(code: 'sourceControlServer.label', default: 'Source Control Server')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:link uri="/">Home</g:link>

        <span class="spacer"> &gt; </span>
        <g:link controller="sourceControlServer" action="list" title="Show All Source Control Servers">
            Source Control Servers
        </g:link>

        <g:if test="${action == 'show'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="sourceControlServer" action="show" id="${domain.id}" title="Show Source Control Server">
                ${domain.name.encodeAsHTML()}
            </g:link>
        </g:if>
        <g:elseif test="${action == 'create'}">
            <span class="spacer"> &gt; </span>
            <g:message code="default.create.label" args="[entityName]"/>
        </g:elseif>
        <g:elseif test="${action == 'edit'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="sourceControlServer" action="show" id="${domain.id}" title="Show Source Control Server">
                ${domain.name.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:message code="default.edit.label" args="[entityName]"/>
        </g:elseif>
    </div>
</g:elseif>
<g:elseif test="${controller == 'system'}">
    <g:set var="entityName" value="${message(code: 'system.label', default: 'System')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:link uri="/">Home</g:link>

        <span class="spacer"> &gt; </span>
        <g:link controller="system" action="list" title="Show All Systems">
            Systems
        </g:link>

        <g:if test="${action == 'show'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="system" action="show" id="${domain.id}" title="Show System">
                ${domain.name.encodeAsHTML()}
            </g:link>
        </g:if>
        <g:elseif test="${action == 'create'}">
            <span class="spacer"> &gt; </span>
            <g:message code="default.create.label" args="[entityName]"/>
        </g:elseif>
        <g:elseif test="${action == 'edit'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="system" action="show" id="${domain.id}" title="Show System">
                ${domain.name.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:message code="default.edit.label" args="[entityName]"/>
        </g:elseif>
    </div>
</g:elseif>
<g:elseif test="${controller == 'systemComponent'}">
    <g:set var="entityName" value="${message(code: 'systemComponent.label', default: 'System Component')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:link uri="/">Home</g:link>

        <g:if test="${action == 'list'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="systemComponent" action="list" title="Show All System Components">
                Systems Components
            </g:link>
        </g:if>
        <g:elseif test="${action == 'show'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="system" action="list" title="Show All Systems">
                Systems
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="system" action="show" id="${domain.system.id}" title="Show System">
                ${domain.system.name.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="systemComponent" action="show" id="${domain.id}" title="Show System Component">
                ${domain.name.encodeAsHTML()}
            </g:link>
        </g:elseif>
        <g:elseif test="${action == 'create'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="system" action="list" title="Show All Systems">
                Systems
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="system" action="show" id="${domain.system.id}" title="Show System">
                ${domain.system.name.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:message code="default.create.label" args="[entityName]"/>
        </g:elseif>
        <g:elseif test="${action == 'edit'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="system" action="list" title="Show All Systems">
                Systems
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="system" action="show" id="${domain.system.id}" title="Show System">
                ${domain.system.name.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="systemComponent" action="show" id="${domain.id}" title="Show System Component">
                ${domain.name.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:message code="default.edit.label" args="[entityName]"/>
        </g:elseif>
    </div>
</g:elseif>
<g:elseif test="${controller == 'systemEnvironment'}">
    <g:set var="entityName" value="${message(code: 'systemEnvironment.label', default: 'System Environment')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:link uri="/">Home</g:link>

        <g:if test="${action == 'list'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="systemEnvironment" action="list" title="Show All System Environments">
                Systems Environments
            </g:link>
        </g:if>
        <g:elseif test="${action == 'show'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="system" action="list" title="Show All Systems">
                Systems
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="system" action="show" id="${domain.system.id}" title="Show System">
                ${domain.system.name.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="systemEnvironment" action="show" id="${domain.id}" title="Show System Environment">
                ${domain.name.encodeAsHTML()}
            </g:link>
        </g:elseif>
        <g:elseif test="${action == 'create'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="system" action="list" title="Show All Systems">
                Systems
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="system" action="show" id="${domain.system.id}" title="Show System">
                ${domain.system.name.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:message code="default.create.label" args="[entityName]"/>
        </g:elseif>
        <g:elseif test="${action == 'edit'}">
            <span class="spacer"> &gt; </span>
            <g:link controller="system" action="list" title="Show All Systems">
                Systems
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="system" action="show" id="${domain.system.id}" title="Show System">
                ${domain.system.name.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:link controller="systemEnvironment" action="show" id="${domain.id}" title="Show System Environment">
                ${domain.name.encodeAsHTML()}
            </g:link>
            <span class="spacer"> &gt; </span>
            <g:message code="default.edit.label" args="[entityName]"/>
        </g:elseif>
    </div>
</g:elseif>