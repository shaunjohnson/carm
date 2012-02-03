<%@ page import="carm.Application; carm.ApplicationType; carm.Module; carm.ModuleType; carm.Project; carm.ProjectCategory; carm.SystemComponent; carm.SystemEnvironment; carm.SourceControlRepository; carm.SourceControlServer" %>

<g:set var="action" value="${params.action}"/>
<g:set var="controller" value="${params.controller}"/>

<g:if test="${controller == 'administration'}">
    <g:pageHeader action="show" beanName="${pageName}"/>
</g:if>
<g:elseif test="${controller == 'application'}">
    <g:set var="entityName" value="${message(code: 'application.label', default: 'Application')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:if test="${action == 'list'}">
            <g:breadcrumbLink controller="application" action="list" title="Show All Applications" text="Applications"
                          isFirst="true"/>
        </g:if>
        <g:else>
            <g:breadcrumbLink controller="project" action="list" title="Show All Projects" text="Projects" isFirst="true"/>

            <g:breadcrumbLink controller="project" action="show" title="Show Project" text="${domain.project}"
                          id="${domain.project.id}"/>

            <g:if test="${action == 'show'}">
                <g:breadcrumbLink controller="application" action="show" title="Show Application" text="${domain.name}"
                              id="${domain.id}"/>
            </g:if>
            <g:elseif test="${action == 'create' || action == 'save'}">
                <g:breadcrumbText code="default.create.label" args="[entityName]"/>
            </g:elseif>
            <g:elseif test="${action == 'edit' || action == 'update'}">
                <g:breadcrumbLink controller="application" action="show" title="Show Application" text="${domain.name}"
                              id="${domain.id}"/>
                <g:breadcrumbText code="default.edit.label" args="[entityName]"/>
            </g:elseif>
        </g:else>
    </div>
</g:elseif>
<g:elseif test="${controller == 'applicationDeployment'}">
    <g:set var="entityName" value="${message(code: 'applicationDeployment.label', default: 'Application Deployment')}"/>

    <g:pageHeader action="${action}"
                  beanName="${domain?.applicationRelease?.application?.name} - Release ${domain?.applicationRelease?.releaseNumber}"
                  entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:breadcrumbLink uri="/" text="Home" isFirst="true"/>

        <g:if test="${action == 'list'}">
            <g:breadcrumbLink controller="applicationDeployment" action="list" title="Show All Application Deployments"
                          text="Application Deployments"/>
        </g:if>
        <g:else>
            <g:breadcrumbLink controller="project" action="show" title="Show Project"
                          text="${domain?.applicationRelease?.application?.project}"
                          id="${domain?.applicationRelease?.application?.project?.id}"/>

            <g:breadcrumbLink controller="application" action="show" title="Show Application"
                          text="${domain?.applicationRelease?.application?.name}"
                          id="${domain?.applicationRelease?.application?.id}"/>

            <g:if test="${action == 'show'}">
                <g:breadcrumbLink controller="applicationDeployment" action="show" title="Show Application Deployment"
                              text="${domain.applicationRelease.releaseNumber}" id="${domain.id}"/>
            </g:if>
            <g:elseif test="${action == 'create' || action == 'save'}">
                <g:breadcrumbText code="default.create.label" args="[entityName]"/>
            </g:elseif>
            <g:elseif test="${action == 'edit' || action == 'update'}">
                <g:breadcrumbLink controller="applicationDeployment" action="show" title="Show Application Deployment"
                              text="${domain.releaseNumber}" id="${domain.id}"/>
                <g:breadcrumbText code="default.edit.label" args="[entityName]"/>
            </g:elseif>
        </g:else>
    </div>
</g:elseif>
<g:elseif test="${controller == 'applicationRelease'}">
    <g:set var="entityName" value="${message(code: 'applicationRelease.label', default: 'Application Release')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.application?.name} - Release ${domain?.releaseNumber}"
                  entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:if test="${action == 'list'}">
            <g:breadcrumbLink controller="applicationRelease" action="list" title="Show All Application Releases"
                          text="Application Releases" isFirst="true"/>
        </g:if>
        <g:else>
            <g:breadcrumbLink controller="project" action="list" title="Show All Projects" text="Projects" isFirst="true"/>

            <g:breadcrumbLink controller="project" action="show" title="Show Project" text="${domain.application.project}"
                          id="${domain.application.project.id}"/>

            <g:breadcrumbLink controller="application" action="show" title="Show Application"
                          text="${domain.application.name}" id="${domain.application.id}"/>

            <g:if test="${action == 'show'}">
                <g:breadcrumbLink controller="applicationRelease" action="show" title="Show Application Release"
                              text="${domain.releaseNumber}" id="${domain.id}"/>
            </g:if>
            <g:elseif test="${action == 'create' || action == 'save'}">
                <g:breadcrumbText code="default.create.label" args="[entityName]"/>
            </g:elseif>
            <g:elseif test="${action == 'edit' || action == 'update'}">
                <g:breadcrumbLink controller="applicationRelease" action="show" title="Show Application Release"
                              text="${domain.releaseNumber}" id="${domain.id}"/>
                <g:breadcrumbText code="default.edit.label" args="[entityName]"/>
            </g:elseif>
        </g:else>
    </div>
</g:elseif>
<g:elseif test="${controller == 'applicationReleaseTestState'}">
    <g:set var="entityName"
           value="${message(code: 'applicationReleaseTestState.label', default: 'Application Release Test State')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:breadcrumbLink uri="/" text="Home" isFirst="true"/>
        <g:breadcrumbLink controller="administration" text="Administration"/>
        <g:breadcrumbLink controller="applicationReleaseTestState" action="list"
                      title="Show All Application Release Test States" text="Application Release Test States"/>

        <g:if test="${action == 'show'}">
            <g:breadcrumbLink controller="applicationReleaseTestState" action="show"
                          title="Show Application Release Test State" text="${domain.name}" id="${domain.id}"/>
        </g:if>
        <g:elseif test="${action == 'create' || action == 'save'}">
            <g:breadcrumbText code="default.create.label" args="[entityName]"/>
        </g:elseif>
        <g:elseif test="${action == 'edit' || action == 'update'}">
            <g:breadcrumbLink controller="applicationReleaseTestState" action="show"
                          title="Show Application Release Test State" text="${domain.name}" id="${domain.id}"/>
            <g:breadcrumbText code="default.edit.label" args="[entityName]"/>
        </g:elseif>
    </div>
</g:elseif>
<g:elseif test="${controller == 'applicationType'}">
    <g:set var="entityName" value="${message(code: 'applicationType.label', default: 'Application Type')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:breadcrumbLink uri="/" text="Home" isFirst="true"/>
        <g:breadcrumbLink controller="administration" text="Administration"/>
        <g:breadcrumbLink controller="applicationType" action="list" title="Show All Application Types"
                      text="Application Types"/>

        <g:if test="${action == 'show'}">
            <g:breadcrumbLink controller="applicationType" action="show" title="Show Application Type" text="${domain.name}"
                          id="${domain.id}"/>
        </g:if>
        <g:elseif test="${action == 'create' || action == 'save'}">
            <g:breadcrumbText code="default.create.label" args="[entityName]"/>
        </g:elseif>
        <g:elseif test="${action == 'edit' || action == 'update'}">
            <g:breadcrumbLink controller="applicationType" action="show" title="Show Application Type" text="${domain.name}"
                          id="${domain.id}"/>
            <g:breadcrumbText code="default.edit.label" args="[entityName]"/>
        </g:elseif>
    </div>
</g:elseif>
<g:elseif test="${controller ==~ /.*home/}">
    <g:pageHeader action="show" beanName="${pageName}"/>
</g:elseif>
<g:elseif test="${controller == 'module'}">
    <g:set var="entityName" value="${message(code: 'module.label', default: 'Module')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:if test="${action == 'list'}">
            <g:breadcrumbLink controller="module" action="list" title="Show All Modules" text="Modules" isFirst="true"/>
        </g:if>
        <g:else>
            <g:breadcrumbLink controller="project" action="show" title="Show Project" text="${domain.application.project}"
                          id="${domain.application.project.id}" isFirst="true"/>
            <g:breadcrumbLink controller="application" action="show" title="Show Application"
                          text="${domain.application.name}" id="${domain.application.id}"/>

            <g:if test="${action == 'show'}">
                <g:breadcrumbLink controller="module" action="show" title="Show Module" text="${domain.name}"
                              id="${domain.id}"/>
            </g:if>
            <g:elseif test="${action == 'create' || action == 'save'}">
                <g:breadcrumbText code="default.create.label" args="[entityName]"/>
            </g:elseif>
            <g:elseif test="${action == 'edit' || action == 'update'}">
                <g:breadcrumbLink controller="module" action="show" title="Show Module" text="${domain.name}"
                              id="${domain.id}"/>
                <g:breadcrumbText code="default.edit.label" args="[entityName]"/>
            </g:elseif>
        </g:else>
    </div>
</g:elseif>
<g:elseif test="${controller == 'moduleType'}">
    <g:set var="entityName" value="${message(code: 'moduleType.label', default: 'Module Type')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:breadcrumbLink uri="/" text="Home" isFirst="true"/>
        <g:breadcrumbLink controller="administration" text="Administration"/>
        <g:breadcrumbLink controller="moduleType" action="list" title="Show All Module Types" text="Module Types"/>

        <g:if test="${action == 'show'}">
            <g:breadcrumbLink controller="moduleType" action="show" title="Show Module Type" text="${domain.name}"
                          id="${domain.id}"/>
        </g:if>
        <g:elseif test="${action == 'create' || action == 'save'}">
            <g:breadcrumbText code="default.create.label" args="[entityName]"/>
        </g:elseif>
        <g:elseif test="${action == 'edit' || action == 'update'}">
            <g:breadcrumbLink controller="moduleType" action="show" title="Show Module Type" text="${domain.name}"
                          id="${domain.id}"/>
            <g:breadcrumbText code="default.edit.label" args="[entityName]"/>
        </g:elseif>
    </div>
</g:elseif>
<g:elseif test="${controller == 'project'}">
    <g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:if test="${action != 'list'}">
            <g:breadcrumbLink controller="project" action="list" title="Show All Projects" text="Projects" isFirst="true"/>

            <g:if test="${action == 'show'}">
                <g:breadcrumbLink controller="project" action="show" title="Show Project" text="${domain.name}"
                              id="${domain.id}"/>
            </g:if>
            <g:elseif test="${action == 'create' || action == 'save'}">
                <g:breadcrumbText code="default.create.label" args="[entityName]"/>
            </g:elseif>
            <g:elseif test="${action == 'edit' || action == 'update'}">
                <g:breadcrumbLink controller="project" action="show" title="Show Project" text="${domain.name}"
                              id="${domain.id}"/>
                <g:breadcrumbText code="default.edit.label" args="[entityName]"/>
            </g:elseif>

        </g:if>
    </div>
</g:elseif>
<g:elseif test="${controller == 'projectCategory'}">
    <g:set var="entityName" value="${message(code: 'projectCategory.label', default: 'Project Category')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:breadcrumbLink uri="/" text="Home" isFirst="true"/>
        <g:breadcrumbLink controller="administration" text="Administration"/>
        <g:breadcrumbLink controller="projectCategory" action="list" title="Show All Project Categories"
                      text="Project Categories"/>

        <g:if test="${action == 'show'}">
            <g:breadcrumbLink controller="projectCategory" action="show" title="Show Project Category" text="${domain.name}"
                          id="${domain.id}"/>
        </g:if>
        <g:elseif test="${action == 'create' || action == 'save'}">
            <g:breadcrumbText code="default.create.label" args="[entityName]"/>
        </g:elseif>
        <g:elseif test="${action == 'edit' || action == 'update'}">
            <g:breadcrumbLink controller="projectCategory" action="show" title="Show Project Category" text="${domain.name}"
                          id="${domain.id}"/>
            <g:breadcrumbText code="default.edit.label" args="[entityName]"/>
        </g:elseif>
    </div>
</g:elseif>
<g:elseif test="${controller == 'sourceControlRepository'}">
    <g:set var="entityName"
           value="${message(code: 'sourceControlRepositorye.label', default: 'Source Control Repository')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:breadcrumbLink uri="/" text="Home" isFirst="true"/>

        <g:if test="${action == 'list'}">
            <g:breadcrumbLink controller="sourceControlRepository" action="list"
                          title="Show All Source Control Repositories" text="Source Control Repositories"/>
        </g:if>
        <g:else>
            <g:breadcrumbLink controller="sourceControlServer" action="show" title="Show Source Control Server"
                          text="${domain.server.name}" id="${domain.server.id}"/>

            <g:if test="${action == 'show'}">
                <g:breadcrumbLink controller="sourceControlRepository" action="show" title="Show Source Control Repository"
                              text="${domain.name}" id="${domain.id}"/>
            </g:if>
            <g:elseif test="${action == 'create' || action == 'save'}">
                <g:breadcrumbText code="default.create.label" args="[entityName]"/>
            </g:elseif>
            <g:elseif test="${action == 'edit' || action == 'update'}">
                <g:breadcrumbLink controller="sourceControlRepository" action="show" title="Show Source Control Repository"
                              text="${domain.name}" id="${domain.id}"/>
                <g:breadcrumbText code="default.edit.label" args="[entityName]"/>
            </g:elseif>
        </g:else>
    </div>
</g:elseif>
<g:elseif test="${controller == 'sourceControlRole'}">
    <g:set var="entityName" value="${message(code: 'sourceControlRole.label', default: 'Source Control Role')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:breadcrumbLink uri="/" text="Home" isFirst="true"/>
        <g:breadcrumbLink controller="sourceControlRole" action="list" title="Show All Source Control Roles"
                      text="Source Control Roles"/>

        <g:if test="${action == 'show'}">
            <g:breadcrumbLink controller="sourceControlRole" action="show" title="Show Source Control Role"
                          text="${domain.name}" id="${domain.id}"/>
        </g:if>
        <g:elseif test="${action == 'create' || action == 'save'}">
            <g:breadcrumbText code="default.create.label" args="[entityName]"/>
        </g:elseif>
        <g:elseif test="${action == 'edit' || action == 'update'}">
            <g:breadcrumbLink controller="sourceControlRole" action="show" title="Show Source Control Role"
                          text="${domain.name}" id="${domain.id}"/>
            <g:breadcrumbText code="default.edit.label" args="[entityName]"/>
        </g:elseif>
    </div>
</g:elseif>
<g:elseif test="${controller == 'sourceControlServer'}">
    <g:set var="entityName" value="${message(code: 'sourceControlServer.label', default: 'Source Control Server')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:breadcrumbLink uri="/" text="Home" isFirst="true"/>
        <g:breadcrumbLink controller="sourceControlServer" action="list" title="Show All Source Control Servers"
                      text="Source Control Servers"/>

        <g:if test="${action == 'show'}">
            <g:breadcrumbLink controller="sourceControlServer" action="show" title="Show Source Control Server"
                          text="${domain.name}" id="${domain.id}"/>
        </g:if>
        <g:elseif test="${action == 'create' || action == 'save'}">
            <g:breadcrumbText code="default.create.label" args="[entityName]"/>
        </g:elseif>
        <g:elseif test="${action == 'edit' || action == 'update'}">
            <g:breadcrumbLink controller="sourceControlServer" action="show" title="Show Source Control Server"
                          text="${domain.name}" id="${domain.id}"/>
            <g:breadcrumbText code="default.edit.label" args="[entityName]"/>
        </g:elseif>
    </div>
</g:elseif>
<g:elseif test="${controller == 'sourceControlUser'}">
    <g:set var="entityName" value="${message(code: 'sourceControlUser.label', default: 'Source Control User')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:breadcrumbLink uri="/" text="Home" isFirst="true"/>

        <g:if test="${action == 'list'}">
            <g:breadcrumbLink controller="sourceControlUser" action="list" title="Show All Source Control Users"
                          text="Source Control Users"/>
        </g:if>
        <g:else>
            <g:breadcrumbLink controller="sourceControlServer" action="show" title="Show Source Control Server"
                          text="${domain.server.name}" id="${domain.server.id}"/>

            <g:if test="${action == 'show'}">
                <g:breadcrumbLink controller="sourceControlUser" action="show" title="Show Source Control User"
                              text="${domain.name}" id="${domain.id}"/>
            </g:if>
            <g:elseif test="${action == 'create' || action == 'save'}">
                <g:breadcrumbText code="default.create.label" args="[entityName]"/>
            </g:elseif>
            <g:elseif test="${action == 'edit' || action == 'update'}">
                <g:breadcrumbLink controller="sourceControlUser" action="show" title="Show Source Control User"
                              text="${domain.name}" id="${domain.id}"/>
                <g:breadcrumbText code="default.edit.label" args="[entityName]"/>
            </g:elseif>
        </g:else>
    </div>
</g:elseif>
<g:elseif test="${controller == 'system'}">
    <g:set var="entityName" value="${message(code: 'system.label', default: 'System')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:if test="${action != 'list'}">
            <g:breadcrumbLink controller="system" action="list" title="Show All Systems" text="Systems" isFirst="true"/>

            <g:if test="${action == 'show'}">
                <g:breadcrumbLink controller="system" action="show" title="Show System" text="${domain.name}"
                              id="${domain.id}"/>
            </g:if>
            <g:elseif test="${action == 'create' || action == 'save'}">
                <g:breadcrumbText code="default.create.label" args="[entityName]"/>
            </g:elseif>
            <g:elseif test="${action == 'edit' || action == 'update'}">
                <g:breadcrumbLink controller="system" action="show" title="Show System" text="${domain.name}"
                              id="${domain.id}"/>
                <g:breadcrumbText code="default.edit.label" args="[entityName]"/>
            </g:elseif>
        </g:if>
    </div>
</g:elseif>
<g:elseif test="${controller == 'systemComponent'}">
    <g:set var="entityName" value="${message(code: 'systemComponent.label', default: 'System Component')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:breadcrumbLink controller="system" action="list" title="Show All Systems" text="Systems" isFirst="true"/>

        <g:if test="${action == 'list'}">
            <g:breadcrumbLink controller="systemComponent" action="list" title="Show All System Components"
                          text="Systems Components"/>
        </g:if>
        <g:else>
            <g:breadcrumbLink controller="system" action="show" title="Show System" text="${domain.system.name}"
                          id="${domain.system.id}"/>

            <g:if test="${action == 'show'}">
                <g:breadcrumbLink controller="systemComponent" action="show" title="Show System Component"
                              text="${domain.name}" id="${domain.id}"/>
            </g:if>
            <g:elseif test="${action == 'create' || action == 'save'}">
                <g:breadcrumbText code="default.create.label" args="[entityName]"/>
            </g:elseif>
            <g:elseif test="${action == 'edit' || action == 'update'}">
                <g:breadcrumbLink controller="systemComponent" action="show" title="Show System Component"
                              text="${domain.name}" id="${domain.id}"/>
                <g:breadcrumbText code="default.edit.label" args="[entityName]"/>
            </g:elseif>
        </g:else>
    </div>
</g:elseif>
<g:elseif test="${controller == 'systemEnvironment'}">
    <g:set var="entityName" value="${message(code: 'systemEnvironment.label', default: 'System Environment')}"/>

    <g:pageHeader action="${action}" beanName="${domain?.name}" entityName="${entityName}"/>

    <div class="breadcrumbs">
        <g:breadcrumbLink controller="system" action="list" title="Show All Systems" text="Systems" isFirst="true"/>

        <g:if test="${action == 'list'}">
            <g:breadcrumbLink controller="systemEnvironment" action="list" title="Show All System Environments"
                          text="Systems Environments"/>
        </g:if>
        <g:else>
            <g:breadcrumbLink controller="system" action="show" title="Show System" text="${domain.system.name}"
                          id="${domain.system.id}"/>

            <g:if test="${action == 'show'}">
                <g:breadcrumbLink controller="systemEnvironment" action="show" title="Show System Environment"
                              text="${domain.name}" id="${domain.id}"/>
            </g:if>
            <g:elseif test="${action == 'create' || action == 'save'}">
                <g:breadcrumbText code="default.create.label" args="[entityName]"/>
            </g:elseif>
            <g:elseif test="${action == 'edit' || action == 'update'}">
                <g:breadcrumbLink controller="systemEnvironment" action="show" title="Show System Environment"
                              text="${domain.name}" id="${domain.id}"/>
                <g:breadcrumbText code="default.edit.label" args="[entityName]"/>
            </g:elseif>
        </g:else>
    </div>
</g:elseif>