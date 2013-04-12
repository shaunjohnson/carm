<%@ page import="carm.deployment.ModuleDeploymentState" %>

<carm:formSection legend="${message(code: 'applicationDeploymentDetails.section')}">
    <div class="control-group">
        <carm:label class="control-label" for="applicationRelease.id">
            <g:message code="applicationDeployment.applicationRelease.label" default="Application Release"/>
        </carm:label>
        <div class="controls">
            <g:link controller="applicationRelease" action="show"
                    id="${applicationDeploymentInstance?.applicationRelease?.id}">
                <g:message code="pageHeader.applicationRelease.label"
                           args="[applicationDeploymentInstance?.applicationRelease?.application?.name,
                                   applicationDeploymentInstance?.applicationRelease?.releaseNumber]"/>
            </g:link>
            <g:hiddenField name="applicationRelease.id"
                           value="${applicationDeploymentInstance?.applicationRelease?.id}"/>
        </div>
    </div>

    <div class="control-group ${hasErrors(bean: applicationDeploymentInstance, field: 'deploymentEnvironment', 'error')}">
        <carm:label class="control-label" for="deploymentEnvironment.id" required="true">
            <g:message code="applicationDeployment.deploymentEnvironment.label" default="Environment"/>
        </carm:label>
        <div class="controls">
            <g:select name="deploymentEnvironment.id" class="span4"
                      noSelection="['null': '']" required="required"
                      from="${applicationDeploymentInstance?.applicationRelease?.application?.sysEnvironment?.environments}"
                      optionKey="id" value="${applicationDeploymentInstance?.deploymentEnvironment?.id}"/>
            <carm:alertWarning id="deploymentEnvironmentMessage" display="none"/>
        </div>
    </div>

    <div class="control-group ${hasErrors(bean: applicationDeploymentInstance, field: 'requestedDeploymentDate', 'error')}">
        <carm:label class="control-label" for="requestedDeploymentDate">
            <g:message code="applicationDeployment.requestedDeploymentDate.label"
                       default="Requested Deployment Date"/>
        </carm:label>
        <div class="controls">
            <carm:datePicker name="requestedDeploymentDate" todayLink="true"
                             value="${applicationDeploymentInstance?.requestedDeploymentDate}"/>
        </div>
    </div>
</carm:formSection>

<carm:formSection legend="${message(code: 'deployment.section')}">
    <div class="control-group ${hasErrors(bean: applicationDeploymentInstance, field: 'moduleDeployments', 'error')}">
        <carm:label class="control-label" required="true">
            <g:message code="modules.label" default="Modules"/>
        </carm:label>
        <div class="controls well">
            <g:each var="moduleDeployment"
                    in="${applicationDeploymentInstance.moduleDeployments.sort { it.moduleRelease.module.name }}">
                <carm:isDeployable moduleRelease="${moduleDeployment.moduleRelease}">
                    <carm:label class="checkbox" for="moduleRelease.${moduleDeployment.moduleRelease.id}">
                        <g:if test="${moduleDeployment.deploymentState == ModuleDeploymentState.DEPLOYED}">
                            <g:checkBox name="moduleRelease.${moduleDeployment.moduleRelease.id}" checked="${true}"/>
                        </g:if>
                        <g:else>
                            <g:checkBox name="moduleRelease.${moduleDeployment.moduleRelease.id}"/>
                        </g:else>
                        <g:fieldValue field="name" bean="${moduleDeployment.moduleRelease.module}"/>
                    </carm:label>
                </carm:isDeployable>
                <carm:isNotDeployable moduleRelease="${moduleDeployment.moduleRelease}">
                    <carm:label class="checkbox disabled">
                        <g:checkBox name="moduleRelease.${moduleDeployment.moduleRelease.id}" disabled="true"/>
                        <g:message code="moduleReleaseCannotBeDeployed.message"
                                   args="[moduleDeployment.moduleRelease.module.name]"/>
                    </carm:label>
                </carm:isNotDeployable>
            </g:each>
        </div>
    </div>

    <div class="control-group ${hasErrors(bean: applicationDeploymentInstance, field: 'deploymentInstructions', 'error')}">
        <carm:label class="control-label" for="deploymentInstructions">
            <g:message code="applicationDeployment.deploymentInstructions.label"
                       default="Deployment Instructions"/>
        </carm:label>
        <div class="controls">
            <ckeditor:editor name="deploymentInstructions"
                             height="${grailsApplication.config.ui.richTextEditor.height}"
                             width="${grailsApplication.config.ui.richTextEditor.width}">
                ${applicationDeploymentInstance?.deploymentInstructions}
            </ckeditor:editor>
        </div>
    </div>
</carm:formSection>