<g:set var="action" value="${params.action}"/>
<g:set var="controller" value="${params.controller}"/>

<g:if test="${controller == 'administration'}">
    <carm:pageHeaderLabel action="show" beanName="${pageName}"/>
</g:if>
<g:elseif test="${controller == 'application'}">
    <g:set var="entityName" value="${message(code: 'application.label', default: 'Application')}"/>

    <carm:pageHeaderLabel beanName="${domain?.name}" entityName="${entityName}" entity="${domain}"/>

    <bc:breadcrumbs>
        <g:if test="${action == 'list'}">
            <bc:listApplications isFirst="true"/>
        </g:if>
        <g:else>
            <bc:listProjects isFirst="true"/>
            <bc:showProject project="${domain.project}"/>

            <g:if test="${action == 'show'}">
                <bc:showApplication application="${domain}"/>
            </g:if>
            <g:elseif test="${action == 'create' || action == 'save'}">
                <bc:createLabel entityName="${entityName}"/>
            </g:elseif>
            <g:elseif test="${action == 'edit' || action == 'update'}">
                <bc:showApplication application="${domain}"/>
                <bc:editLabel entityName="${entityName}"/>
            </g:elseif>
            <g:elseif test="${action == 'listActivity'}">
                <bc:showApplication application="${domain}"/>
                <bc:label code="allActivity.label" args="[entityName]"/>
            </g:elseif>
            <g:elseif test="${action == 'listReleases'}">
                <bc:showApplication application="${domain}"/>
                <bc:label code="allReleases.label" args="[entityName]"/>
            </g:elseif>
            <g:elseif test="${action == 'showFullHistory'}">
                <bc:showApplication application="${domain}"/>
                <bc:label code="showFullHistory.label" args="[entityName]"/>
            </g:elseif>
        </g:else>
    </bc:breadcrumbs>
</g:elseif>
<g:elseif test="${controller == 'applicationDeployment'}">
    <g:set var="entityName" value="${message(code: 'applicationDeployment.label', default: 'Application Deployment')}"/>

    <carm:pageHeaderLabel
                          beanName="${message(code: 'pageHeader.applicationDeployment.label',
                                  args: [domain?.applicationRelease?.application?.name, domain?.applicationRelease?.releaseNumber, domain?.deploymentEnvironment])}"
                          entityName="${entityName}"/>

    <bc:breadcrumbs>
        <g:if test="${action != 'list'}">
            <bc:listProjects isFirst="true"/>
            <bc:showProject project="${domain.applicationRelease.application.project}"/>
            <bc:showApplication application="${domain.applicationRelease.application}"/>
            <bc:showApplicationRelease applicationRelease="${domain.applicationRelease}"/>

            <g:if test="${action == 'show'}">
                <bc:showApplicationDeployment applicationDeployment="${domain}"/>
            </g:if>
            <g:elseif test="${action == 'create' || action == 'save' || action == 'redeploy'}">
                <bc:createLabel entityName="${entityName}"/>
            </g:elseif>
            <g:elseif test="${action == 'edit' || action == 'update'}">
                <bc:showApplicationDeployment applicationDeployment="${domain}"/>
                <bc:editLabel entityName="${entityName}"/>
            </g:elseif>
        </g:if>
    </bc:breadcrumbs>
</g:elseif>
<g:elseif test="${controller == 'applicationRelease'}">
    <g:set var="entityName" value="${message(code: 'applicationRelease.label', default: 'Application Release')}"/>

    <carm:pageHeaderLabel
                          beanName="${message(code: 'pageHeader.applicationRelease.label', args: [domain?.application?.name, domain?.releaseNumber])}"
                          entityName="${entityName}"/>

    <bc:breadcrumbs>
        <g:if test="${action != 'list'}">
            <bc:listProjects isFirst="true"/>
            <bc:showProject project="${domain.application.project}"/>
            <bc:showApplication application="${domain.application}"/>

            <g:if test="${action == 'show'}">
                <bc:showApplicationRelease applicationRelease="${domain}"/>
            </g:if>
            <g:elseif test="${action == 'create' || action == 'save'}">
                <bc:createLabel entityName="${entityName}"/>
            </g:elseif>
            <g:elseif test="${action == 'edit' || action == 'update'}">
                <bc:showApplicationRelease applicationRelease="${domain}"/>
                <bc:editLabel entityName="${entityName}"/>
            </g:elseif>
            <g:elseif test="${action == 'listActivity'}">
                <bc:showApplicationRelease applicationRelease="${domain}"/>
                <bc:label code="allActivity.label" args="[entityName]"/>
            </g:elseif>
        </g:if>
    </bc:breadcrumbs>
</g:elseif>
<g:elseif test="${controller == 'applicationReleaseHistory'}">
    <g:set var="entityName"
           value="${message(code: 'applicationReleaseHistory.label', default: 'Application Release History')}"/>

    <g:if test="${action == 'list'}">
        <carm:pageHeaderLabel
                              beanName="${domain}"
                              entityName="${entityName}"/>
    </g:if>
    <g:else>
        <carm:pageHeaderLabel
                              beanName="${formatDate(date: domain.dateCreated)}"
                              entityName="${entityName}"/>
    </g:else>

    <bc:breadcrumbs>
        <g:if test="${action != 'list'}">
            <bc:listProjects isFirst="true"/>
            <bc:showProject project="${domain.applicationRelease.application.project}"/>
            <bc:showApplication application="${domain.applicationRelease.application}"/>
            <bc:showApplicationRelease applicationRelease="${domain.applicationRelease}"/>

            <g:if test="${action == 'show'}">
                <bc:showApplicationReleaseHistory applicationReleaseHistory="${domain}"/>
            </g:if>
        </g:if>
    </bc:breadcrumbs>
</g:elseif>
<g:elseif test="${controller == 'applicationReleaseTestState'}">
    <g:set var="entityName"
           value="${message(code: 'applicationReleaseTestState.label', default: 'Application Release Test State')}"/>

    <carm:pageHeaderLabel beanName="${domain?.name}" entityName="${entityName}"/>

    <bc:breadcrumbs>
        <bc:administration isFirst="true"/>
        <bc:listApplicationReleaseTestStates/>

        <g:if test="${action == 'show'}">
            <bc:link controller="applicationReleaseTestState" action="show"
                     title="Show Application Release Test State" text="${domain.name}" id="${domain.id}"/>
        </g:if>
        <g:elseif test="${action == 'create' || action == 'save'}">
            <bc:createLabel entityName="${entityName}"/>
        </g:elseif>
        <g:elseif test="${action == 'edit' || action == 'update'}">
            <bc:link controller="applicationReleaseTestState" action="show"
                     title="Show Application Release Test State" text="${domain.name}" id="${domain.id}"/>
            <bc:editLabel entityName="${entityName}"/>
        </g:elseif>
    </bc:breadcrumbs>
</g:elseif>
<g:elseif test="${controller == 'applicationType'}">
    <g:set var="entityName" value="${message(code: 'applicationType.label', default: 'Application Type')}"/>

    <carm:pageHeaderLabel beanName="${domain?.name}" entityName="${entityName}"/>

    <bc:breadcrumbs>
        <bc:administration isFirst="true"/>
        <bc:listApplicationTypes/>

        <g:if test="${action == 'show'}">
            <bc:showApplicationType applicationType="${domain}"/>
        </g:if>
        <g:elseif test="${action == 'create' || action == 'save'}">
            <bc:createLabel entityName="${entityName}"/>
        </g:elseif>
        <g:elseif test="${action == 'edit' || action == 'update'}">
            <bc:showApplicationType applicationType="${domain}"/>
            <bc:editLabel entityName="${entityName}"/>
        </g:elseif>
    </bc:breadcrumbs>
</g:elseif>
<g:elseif test="${controller ==~ /.*home/}">
    <carm:pageHeaderLabel action="show" beanName="${pageName}"/>
    <bc:breadcrumbs/>
</g:elseif>
<g:elseif test="${controller == 'login'}">
    <carm:pageHeaderLabel action="show" beanName="${pageName}"/>
    <bc:breadcrumbs/>
</g:elseif>
<g:elseif test="${controller == 'module'}">
    <g:set var="entityName" value="${message(code: 'module.label', default: 'Module')}"/>

    <carm:pageHeaderLabel beanName="${domain?.name}" entityName="${entityName}"/>

    <bc:breadcrumbs>
        <g:if test="${action == 'list'}">
            <bc:listModules isFirst="true"/>
        </g:if>
        <g:else>
            <bc:showProject project="${domain.application.project}" isFirst="true"/>
            <bc:showApplication application="${domain.application}"/>

            <g:if test="${action == 'show'}">
                <bc:showModule module="${domain}"/>
            </g:if>
            <g:elseif test="${action == 'create' || action == 'save'}">
                <bc:createLabel entityName="${entityName}"/>
            </g:elseif>
            <g:elseif test="${action == 'edit' || action == 'update'}">
                <bc:showModule module="${domain}"/>
                <bc:editLabel entityName="${entityName}"/>
            </g:elseif>
            <g:elseif test="${action == 'listActivity'}">
                <bc:showModule module="${domain}"/>
                <bc:label code="allActivity.label" args="[entityName]"/>
            </g:elseif>
        </g:else>
    </bc:breadcrumbs>
</g:elseif>
<g:elseif test="${controller == 'moduleDeployment'}">
    <g:set var="entityName" value="${message(code: 'moduleDeployment.label', default: 'Module Deployment')}"/>
    <g:set var="applicationDeployment" value="${domain?.applicationDeployment}"/>
    <g:set var="applicationRelease" value="${applicationDeployment?.applicationRelease}"/>

    <carm:pageHeaderLabel
                          beanName="${message(code: 'pageHeader.moduleDeployment.label',
                                  args: [applicationRelease?.application?.name, applicationRelease?.releaseNumber, applicationDeployment?.deploymentEnvironment])}"
                          entityName="${entityName}"/>

    <bc:breadcrumbs>
        <g:if test="${action == 'show'}">
            <bc:listProjects isFirst="true"/>
            <bc:showProject project="${applicationRelease.application.project}"/>
            <bc:showApplication application="${applicationRelease.application}"/>
            <bc:showApplicationRelease applicationRelease="${applicationRelease}"/>
            <bc:showApplicationDeployment applicationDeployment="${applicationDeployment}"/>
            <bc:showModuleDeployment moduleDeployment="${domain}"/>
        </g:if>
    </bc:breadcrumbs>
</g:elseif>
<g:elseif test="${controller == 'moduleDeploymentTestState'}">
    <g:set var="entityName"
           value="${message(code: 'moduleDeploymentTestState.label', default: 'Module Deployment Test State')}"/>

    <carm:pageHeaderLabel beanName="${domain?.name}" entityName="${entityName}"/>

    <bc:breadcrumbs>
        <bc:administration isFirst="true"/>
        <bc:listModuleDeploymentTestStates/>

        <g:if test="${action == 'show'}">
            <bc:showModuleDeploymentTestState moduleDeploymentTestState="${domain}"/>
        </g:if>
        <g:elseif test="${action == 'create' || action == 'save'}">
            <bc:createLabel entityName="${entityName}"/>
        </g:elseif>
        <g:elseif test="${action == 'edit' || action == 'update'}">
            <bc:showModuleDeploymentTestState moduleDeploymentTestState="${domain}"/>
            <bc:editLabel entityName="${entityName}"/>
        </g:elseif>
    </bc:breadcrumbs>
</g:elseif>
<g:elseif test="${controller == 'moduleType'}">
    <g:set var="entityName" value="${message(code: 'moduleType.label', default: 'Module Type')}"/>

    <carm:pageHeaderLabel beanName="${domain?.name}" entityName="${entityName}"/>

    <bc:breadcrumbs>
        <bc:administration isFirst="true"/>
        <bc:listModuleTypes/>

        <g:if test="${action == 'show'}">
            <bc:showModuleType moduleType="${domain}"/>
        </g:if>
        <g:elseif test="${action == 'create' || action == 'save'}">
            <bc:createLabel entityName="${entityName}"/>
        </g:elseif>
        <g:elseif test="${action == 'edit' || action == 'update'}">
            <bc:showModuleType moduleType="${domain}"/>
            <bc:editLabel entityName="${entityName}"/>
        </g:elseif>
    </bc:breadcrumbs>
</g:elseif>
<g:elseif test="${controller == 'project'}">
    <g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}"/>

    <carm:pageHeaderLabel beanName="${domain?.name}" entityName="${entityName}"/>

    <bc:breadcrumbs>
        <g:if test="${action != 'list'}">
            <bc:listProjects isFirst="true"/>

            <g:if test="${action == 'show'}">
                <bc:showProject project="${domain}"/>
            </g:if>
            <g:elseif test="${action == 'create' || action == 'save'}">
                <bc:createLabel entityName="${entityName}"/>
            </g:elseif>
            <g:elseif test="${action == 'edit' || action == 'update'}">
                <bc:showProject project="${domain}"/>
                <bc:editLabel entityName="${entityName}"/>
            </g:elseif>
            <g:elseif test="${action == 'listActivity'}">
                <bc:showProject project="${domain}"/>
                <bc:label code="allActivity.label" args="[entityName]"/>
            </g:elseif>
        </g:if>
    </bc:breadcrumbs>
</g:elseif>
<g:elseif test="${controller == 'projectCategory'}">
    <g:set var="entityName" value="${message(code: 'projectCategory.label', default: 'Project Category')}"/>

    <carm:pageHeaderLabel beanName="${domain?.name}" entityName="${entityName}"/>

    <bc:breadcrumbs>
        <bc:administration isFirst="true"/>
        <bc:listProjectCategories/>

        <g:if test="${action == 'show'}">
            <bc:showProjectCategory projectCategory="${domain}"/>
        </g:if>
        <g:elseif test="${action == 'create' || action == 'save'}">
            <bc:createLabel entityName="${entityName}"/>
        </g:elseif>
        <g:elseif test="${action == 'edit' || action == 'update'}">
            <bc:showProjectCategory projectCategory="${domain}"/>
            <bc:editLabel entityName="${entityName}"/>
        </g:elseif>
    </bc:breadcrumbs>
</g:elseif>
<g:elseif test="${controller == 'sourceControlRepository'}">
    <g:set var="entityName"
           value="${message(code: 'sourceControlRepository.label', default: 'Source Control Repository')}"/>

    <carm:pageHeaderLabel beanName="${domain?.name}" entityName="${entityName}"/>

    <bc:breadcrumbs>
        <bc:administration isFirst="true"/>

        <g:if test="${action == 'list'}">
            <bc:listSourceControlRepositories/>
        </g:if>
        <g:else>
            <bc:listSourceControlServers/>
            <bc:showSourceControlServer sourceControlServer="${domain.server}"/>

            <g:if test="${action == 'show'}">
                <bc:showSourceControlRepository sourceControlRepository="${domain}"/>
            </g:if>
            <g:elseif test="${action == 'create' || action == 'save'}">
                <bc:createLabel entityName="${entityName}"/>
            </g:elseif>
            <g:elseif test="${action == 'edit' || action == 'update'}">
                <bc:showSourceControlRepository sourceControlRepository="${domain}"/>
                <bc:editLabel entityName="${entityName}"/>
            </g:elseif>
        </g:else>
    </bc:breadcrumbs>
</g:elseif>
<g:elseif test="${controller == 'sourceControlRole'}">
    <g:set var="entityName" value="${message(code: 'sourceControlRole.label', default: 'Source Control Role')}"/>

    <carm:pageHeaderLabel beanName="${domain?.name}" entityName="${entityName}"/>

    <bc:breadcrumbs>
        <bc:administration isFirst="true"/>
        <bc:listSourceControlRoles/>

        <g:if test="${action == 'show'}">
            <bc:showSourceControlRole sourceControlRole="${domain}"/>
        </g:if>
        <g:elseif test="${action == 'create' || action == 'save'}">
            <bc:createLabel entityName="${entityName}"/>
        </g:elseif>
        <g:elseif test="${action == 'edit' || action == 'update'}">
            <bc:showSourceControlRole sourceControlRole="${domain}"/>
            <bc:editLabel entityName="${entityName}"/>
        </g:elseif>
    </bc:breadcrumbs>
</g:elseif>
<g:elseif test="${controller == 'sourceControlServer'}">
    <g:set var="entityName" value="${message(code: 'sourceControlServer.label', default: 'Source Control Server')}"/>

    <carm:pageHeaderLabel beanName="${domain?.name}" entityName="${entityName}"/>

    <bc:breadcrumbs>
        <bc:administration isFirst="true"/>
        <bc:listSourceControlServers/>

        <g:if test="${action == 'show'}">
            <bc:showSourceControlServer sourceControlServer="${domain}"/>
        </g:if>
        <g:elseif test="${action == 'create' || action == 'save'}">
            <bc:createLabel entityName="${entityName}"/>
        </g:elseif>
        <g:elseif test="${action == 'edit' || action == 'update'}">
            <bc:showSourceControlServer sourceControlServer="${domain}"/>
            <bc:editLabel entityName="${entityName}"/>
        </g:elseif>
    </bc:breadcrumbs>
</g:elseif>
<g:elseif test="${controller == 'sourceControlUser'}">
    <g:set var="entityName" value="${message(code: 'sourceControlUser.label', default: 'Source Control User')}"/>

    <carm:pageHeaderLabel beanName="${domain?.name}" entityName="${entityName}"/>

    <bc:breadcrumbs>
        <bc:administration isFirst="true"/>

        <g:if test="${action == 'list'}">
            <bc:listSourceControlUsers/>
        </g:if>
        <g:else>
            <bc:listSourceControlServers/>
            <bc:showSourceControlServer sourceControlServer="${domain.server}"/>

            <g:if test="${action == 'show'}">
                <bc:showSourceControlUser sourceControlUser="${domain}"/>
            </g:if>
            <g:elseif test="${action == 'create' || action == 'save'}">
                <bc:createLabel entityName="${entityName}"/>
            </g:elseif>
            <g:elseif test="${action == 'edit' || action == 'update'}">
                <bc:showSourceControlUser sourceControlUser="${domain}"/>
                <bc:editLabel entityName="${entityName}"/>
            </g:elseif>
        </g:else>
    </bc:breadcrumbs>
</g:elseif>
<g:elseif test="${controller == 'systemDeploymentEnvironment'}">
    <g:set var="entityName" value="${message(code: 'systemDeploymentEnvironment.label', default: 'SystemEnvironment Deployment Environment')}"/>

    <carm:pageHeaderLabel beanName="${domain?.name}" entityName="${entityName}"/>

    <bc:breadcrumbs>
        <g:if test="${action != 'list'}">
            <bc:listSystemEnvironments isFirst="true"/>
            <bc:showSystemEnvironment systemEnvironment="${domain.sysEnvironment}"/>

            <g:if test="${action == 'show'}">
                <bc:showSystemDeploymentEnvironment systemDeploymentEnvironment="${domain}"/>
            </g:if>
            <g:elseif test="${action == 'create' || action == 'save'}">
                <bc:createLabel entityName="${entityName}"/>
            </g:elseif>
            <g:elseif test="${action == 'edit' || action == 'update'}">
                <bc:showSystemDeploymentEnvironment systemDeploymentEnvironment="${domain}"/>
                <bc:editLabel entityName="${entityName}"/>
            </g:elseif>
            <g:elseif test="${action == 'listActivity'}">
                <bc:showSystemDeploymentEnvironment systemDeploymentEnvironment="${domain}"/>
                <bc:label code="allActivity.label" args="[entityName]"/>
            </g:elseif>
        </g:if>
    </bc:breadcrumbs>
</g:elseif>
<g:elseif test="${controller == 'systemEnvironment'}">
    <g:set var="entityName" value="${message(code: 'systemEnvironment.label', default: 'SystemEnvironment')}"/>

    <carm:pageHeaderLabel beanName="${domain?.name}" entityName="${entityName}"/>

    <bc:breadcrumbs>
        <g:if test="${action != 'list'}">
            <bc:listSystemEnvironments isFirst="true"/>

            <g:if test="${action == 'show'}">
                <bc:showSystemEnvironment systemEnvironment="${domain}"/>
            </g:if>
            <g:elseif test="${action == 'create' || action == 'save'}">
                <bc:createLabel entityName="${entityName}"/>
            </g:elseif>
            <g:elseif test="${action == 'edit' || action == 'update'}">
                <bc:showSystemEnvironment systemEnvironment="${domain}"/>
                <bc:editLabel entityName="${entityName}"/>
            </g:elseif>
            <g:elseif test="${action == 'listActivity'}">
                <bc:showSystemEnvironment systemEnvironment="${domain}"/>
                <bc:label code="allActivity.label" args="[entityName]"/>
            </g:elseif>
            <g:elseif test="${action == 'completedDeployments'}">
                <bc:showSystemEnvironment systemEnvironment="${domain}"/>
                <bc:completedDeploymentsLabel entityName="${entityName}"/>
            </g:elseif>
            <g:elseif test="${action == 'upcomingDeployments'}">
                <bc:showSystemEnvironment systemEnvironment="${domain}"/>
                <bc:upcomingDeploymentsLabel entityName="${entityName}"/>
            </g:elseif>
        </g:if>
    </bc:breadcrumbs>
</g:elseif>
<g:elseif test="${controller == 'systemServer'}">
    <g:set var="entityName" value="${message(code: 'systemServer.label', default: 'SystemEnvironment Server')}"/>

    <carm:pageHeaderLabel beanName="${domain?.name}" entityName="${entityName}"/>

    <bc:breadcrumbs>
        <g:if test="${action != 'list'}">
            <bc:listSystemEnvironments isFirst="true"/>
            <bc:showSystemEnvironment systemEnvironment="${domain.sysEnvironment}"/>

            <g:if test="${action == 'show'}">
                <bc:showSystemServer systemServer="${domain}"/>
            </g:if>
            <g:elseif test="${action == 'create' || action == 'save'}">
                <bc:createLabel entityName="${entityName}"/>
            </g:elseif>
            <g:elseif test="${action == 'edit' || action == 'update'}">
                <bc:showSystemServer systemServer="${domain}"/>
                <bc:editLabel entityName="${entityName}"/>
            </g:elseif>
            <g:elseif test="${action == 'listActivity'}">
                <bc:showSystemServer systemServer="${domain}"/>
                <bc:label code="allActivity.label" args="[entityName]"/>
            </g:elseif>
        </g:if>
    </bc:breadcrumbs>
</g:elseif>
<g:elseif test="${controller == 'user'}">
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>

    <carm:pageHeaderLabel beanName="${domain?.fullName}" entityName="${entityName}"/>

    <bc:breadcrumbs>
        <bc:administration isFirst="true"/>

        <bc:listUsers/>

        <g:if test="${action == 'list'}">
            
        </g:if>
        <g:elseif test="${action == 'listActivity'}">
            <bc:showUser user="${domain}"/>
            <bc:label code="allActivity.label" args="[entityName]"/>
        </g:elseif>
        <g:else>
            <bc:showUser user="${domain}"/>
        </g:else>
    </bc:breadcrumbs>
</g:elseif>