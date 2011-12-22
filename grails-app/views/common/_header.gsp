<%@ page import="carm.Application; carm.ApplicationType; carm.Module; carm.ModuleType; carm.Project; carm.ProjectCategory; carm.SystemComponent; carm.SystemEnvironment; carm.SourceControlRepository; carm.SourceControlServer" %>

<g:set var="action" value="${params.action}"/>
<g:set var="controller" value="${params.controller}"/>

<g:if test="${controller == 'application'}">
    <g:set var="entityName" value="${message(code: 'application.label', default: 'Application')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:headerLink uri="/" text="Home" isFirst="true"/>

        <g:if test="${action == 'list'}">
            <g:headerLink controller="application" action="list" title="Show All Applications" text="Applications"/>
        </g:if>
        <g:else>
            <g:headerLink controller="project" action="list" title="Show All Projects" text="Projects"/>
            <g:headerLink controller="project" action="show" title="Show Project" text="${domain.project}" id="${domain.project.id}"/>

            <g:if test="${action == 'show'}">
                <g:headerLink controller="application" action="show" title="Show Application" text="${domain.name}" id="${domain.id}"/>
            </g:if>
            <g:elseif test="${action == 'create'}">
                <g:headerText code="default.create.label" args="[entityName]"/>
            </g:elseif>
            <g:elseif test="${action == 'edit'}">
                <g:headerLink controller="application" action="show" title="Show Application" text="${domain.name}" id="${domain.id}"/>
                <g:headerText code="default.edit.label" args="[entityName]"/>
            </g:elseif>
        </g:else>
    </div>
</g:if>
<g:elseif test="${controller == 'applicationType'}">
    <g:set var="entityName" value="${message(code: 'applicationType.label', default: 'Application Type')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:headerLink uri="/" text="Home" isFirst="true"/>
        <g:headerLink controller="applicationType" action="list" title="Show All Application Types" text="Application Types"/>

        <g:if test="${action == 'show'}">
            <g:headerLink controller="applicationType" action="show" title="Show Application Type"text="${domain.name}" id="${domain.id}"/>
        </g:if>
        <g:elseif test="${action == 'create'}">
            <g:headerText code="default.create.label" args="[entityName]"/>
        </g:elseif>
        <g:elseif test="${action == 'edit'}">
            <g:headerLink controller="applicationType" action="show" title="Show Application Type" text="${domain.name}" id="${domain.id}"/>
            <g:headerText code="default.edit.label" args="[entityName]"/>
        </g:elseif>
    </div>
</g:elseif>
<g:if test="${controller == 'module'}">
    <g:set var="entityName" value="${message(code: 'module.label', default: 'Module')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:headerLink uri="/" text="Home" isFirst="true"/>

        <g:if test="${action == 'list'}">
            <g:headerLink controller="module" action="list" title="Show All Modules" text="Modules"/>
        </g:if>
        <g:else>
            <g:headerLink controller="project" action="list" title="Show All Projects" text="Projects"/>
            <g:headerLink controller="project" action="show" title="Show Project" text="${domain.application.project}" id="${domain.application.project.id}"/>
            <g:headerLink controller="application" action="show" title="Show Application" text="${domain.application.name}" id="${domain.application.id}"/>

            <g:if test="${action == 'show'}">
                <g:headerLink controller="module" action="show" title="Show Module" text="${domain.name}" id="${domain.id}"/>
            </g:if>
            <g:elseif test="${action == 'create'}">
                <g:headerText code="default.create.label" args="[entityName]"/>
            </g:elseif>
            <g:elseif test="${action == 'edit'}">
                <g:headerLink controller="module" action="show" title="Show Module" text="${domain.name}" id="${domain.id}"/>
                <g:headerText code="default.edit.label" args="[entityName]"/>
            </g:elseif>
        </g:else>
    </div>
</g:if>
<g:elseif test="${controller == 'moduleType'}">
    <g:set var="entityName" value="${message(code: 'moduleType.label', default: 'Module Type')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:headerLink uri="/" text="Home" isFirst="true"/>
        <g:headerLink controller="moduleType" action="list" title="Show All Module Types" text="Module Types"/>

        <g:if test="${action == 'show'}">
            <g:headerLink controller="moduleType" action="show" title="Show Module Type" text="${domain.name}" id="${domain.id}"/>
        </g:if>
        <g:elseif test="${action == 'create'}">
            <g:headerText code="default.create.label" args="[entityName]"/>
        </g:elseif>
        <g:elseif test="${action == 'edit'}">
            <g:headerLink controller="moduleType" action="show" title="Show Module Type" text="${domain.name}" id="${domain.id}"/>
            <g:headerText code="default.edit.label" args="[entityName]"/>
        </g:elseif>
    </div>
</g:elseif>
<g:elseif test="${controller == 'project'}">
    <g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:headerLink uri="/" text="Home" isFirst="true"/>
        <g:headerLink controller="project" action="list" title="Show All Projects" text="Projects"/>

        <g:if test="${action == 'show'}">
            <g:headerLink controller="project" action="show" title="Show Project" text="${domain.name}" id="${domain.id}"/>
        </g:if>
        <g:elseif test="${action == 'create'}">
            <g:headerText code="default.create.label" args="[entityName]"/>
        </g:elseif>
        <g:elseif test="${action == 'edit'}">
            <g:headerLink controller="project" action="show" title="Show Project" text="${domain.name}" id="${domain.id}"/>
            <g:headerText code="default.edit.label" args="[entityName]"/>
        </g:elseif>
    </div>
</g:elseif>
<g:elseif test="${controller == 'projectCategory'}">
    <g:set var="entityName" value="${message(code: 'projectCategory.label', default: 'Project Category')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:headerLink uri="/" text="Home" isFirst="true"/>
        <g:headerLink controller="projectCategory" action="list" title="Show All Project Categories" text="Project Categories"/>

        <g:if test="${action == 'show'}">
            <g:headerLink controller="projectCategory" action="show" title="Show Project Category" text="${domain.name}" id="${domain.id}"/>
        </g:if>
        <g:elseif test="${action == 'create'}">
            <g:headerText code="default.create.label" args="[entityName]"/>
        </g:elseif>
        <g:elseif test="${action == 'edit'}">
            <g:headerLink controller="projectCategory" action="show" title="Show Project Category" text="${domain.name}" id="${domain.id}"/>
            <g:headerText code="default.edit.label" args="[entityName]"/>
        </g:elseif>
    </div>
</g:elseif>
<g:elseif test="${controller == 'sourceControlRepository'}">
    <g:set var="entityName" value="${message(code: 'sourceControlRepositorye.label', default: 'Source Control Repository')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:headerLink uri="/" text="Home" isFirst="true"/>

        <g:if test="${action == 'list'}">
            <g:headerLink controller="sourceControlRepository" action="list" title="Show All Source Control Repositories" text="Source Control Repositories"/>
        </g:if>
        <g:else>
            <g:headerLink controller="sourceControlServer" action="list" title="Show All Source Control Servers" text="Source Control Servers"/>
            <g:headerLink controller="sourceControlServer" action="show" title="Show Source Control Server" text="${domain.server.name}" id="${domain.server.id}"/>

            <g:if test="${action == 'show'}">
                <g:headerLink controller="sourceControlRepository" action="show" title="Show Source Control Repository" text="${domain.name}" id="${domain.id}"/>
            </g:if>
            <g:elseif test="${action == 'create'}">
                <g:headerText code="default.create.label" args="[entityName]"/>
            </g:elseif>
            <g:elseif test="${action == 'edit'}">
                <g:headerLink controller="sourceControlRepository" action="show" title="Show Source Control Repository" text="${domain.name}" id="${domain.id}"/>
                <g:headerText code="default.edit.label" args="[entityName]"/>
            </g:elseif>
        </g:else>
    </div>
</g:elseif>
<g:elseif test="${controller == 'sourceControlServer'}">
    <g:set var="entityName" value="${message(code: 'sourceControlServer.label', default: 'Source Control Server')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:headerLink uri="/" text="Home" isFirst="true"/>
        <g:headerLink controller="sourceControlServer" action="list" title="Show All Source Control Servers" text="Source Control Servers"/>

        <g:if test="${action == 'show'}">
            <g:headerLink controller="sourceControlServer" action="show" title="Show Source Control Server" text="${domain.name}" id="${domain.id}"/>
        </g:if>
        <g:elseif test="${action == 'create'}">
            <g:headerText code="default.create.label" args="[entityName]"/>
        </g:elseif>
        <g:elseif test="${action == 'edit'}">
            <g:headerLink controller="sourceControlServer" action="show" title="Show Source Control Server" text="${domain.name}" id="${domain.id}"/>
            <g:headerText code="default.edit.label" args="[entityName]"/>
        </g:elseif>
    </div>
</g:elseif>
<g:elseif test="${controller == 'system'}">
    <g:set var="entityName" value="${message(code: 'system.label', default: 'System')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:headerLink uri="/" text="Home" isFirst="true"/>
        <g:headerLink controller="system" action="list" title="Show All Systems" text="Systems"/>

        <g:if test="${action == 'show'}">
            <g:headerLink controller="system" action="show" title="Show System" text="${domain.name}" id="${domain.id}"/>
        </g:if>
        <g:elseif test="${action == 'create'}">
            <g:headerText code="default.create.label" args="[entityName]"/>
        </g:elseif>
        <g:elseif test="${action == 'edit'}">
            <g:headerLink controller="system" action="show" title="Show System" text="${domain.name}" id="${domain.id}"/>
            <g:headerText code="default.edit.label" args="[entityName]"/>
        </g:elseif>
    </div>
</g:elseif>
<g:elseif test="${controller == 'systemComponent'}">
    <g:set var="entityName" value="${message(code: 'systemComponent.label', default: 'System Component')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:headerLink uri="/" text="Home" isFirst="true"/>

        <g:if test="${action == 'list'}">
            <g:headerLink controller="systemComponent" action="list" title="Show All System Components" text="Systems Components"/>
        </g:if>
        <g:else>
            <g:headerLink controller="system" action="list" title="Show All Systems" text="Systems"/>
            <g:headerLink controller="system" action="show" title="Show System" text="${domain.system.name}" id="${domain.system.id}"/>

            <g:if test="${action == 'show'}">
                <g:headerLink controller="systemComponent" action="show" title="Show System Component" text="${domain.name}" id="${domain.id}"/>
            </g:if>
            <g:elseif test="${action == 'create'}">
                <g:headerText code="default.create.label" args="[entityName]"/>
            </g:elseif>
            <g:elseif test="${action == 'edit'}">
                <g:headerLink controller="systemComponent" action="show" title="Show System Component" text="${domain.name}" id="${domain.id}"/>
                <g:headerText code="default.edit.label" args="[entityName]"/>
            </g:elseif>
        </g:else>
    </div>
</g:elseif>
<g:elseif test="${controller == 'systemEnvironment'}">
    <g:set var="entityName" value="${message(code: 'systemEnvironment.label', default: 'System Environment')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:headerLink uri="/" text="Home" isFirst="true"/>

        <g:if test="${action == 'list'}">
            <g:headerLink controller="systemEnvironment" action="list" title="Show All System Environments" text="Systems Environments"/>
        </g:if>
        <g:else>
            <g:headerLink controller="system" action="list" title="Show All Systems" text="Systems"/>
            <g:headerLink controller="system" action="show" title="Show System" text="${domain.system.name}" id="${domain.system.id}"/>

            <g:if test="${action == 'show'}">
                <g:headerLink controller="systemEnvironment" action="show" title="Show System Environment" text="${domain.name}" id="${domain.id}"/>
            </g:if>
            <g:elseif test="${action == 'create'}">
                <g:headerText code="default.create.label" args="[entityName]"/>
            </g:elseif>
            <g:elseif test="${action == 'edit'}">
                <g:headerLink controller="systemEnvironment" action="show" title="Show System Environment" text="${domain.name}" id="${domain.id}"/>
                <g:headerText code="default.edit.label" args="[entityName]"/>
            </g:elseif>
        </g:else>
    </div>
</g:elseif>