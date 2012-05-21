<%@ page import="carm.deployment.ModuleDeploymentState" %>
page import="carm.deployment.ModuleDeploymentState" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <r:require modules="common"/>
    <g:set var="entityName" value="${message(code: 'applicationDeployment.label', default: 'Application Deployment')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
    <resource:richTextEditor type="${grailsApplication.config.ui.richTextEditor.type}"/>
</head>

<body>
<carm:header domain="${applicationDeploymentInstance}"/>

<g:render template="/common/messages"/>
<g:hasErrors bean="${applicationDeploymentInstance}">
    <div class="alert alert-error">
        <h4><g:message code="applicationDeployment.error.create"/></h4>
        <g:renderErrors bean="${applicationDeploymentInstance}" as="list"/>
    </div>
</g:hasErrors>

<g:form action="save" class="offset1 span8">
    <carm:formSection legend="${message(code: 'applicationDeploymentDetails.section')}">
        <div class="control-group">
            <carm:label class="control-label" for="applicationRelease.id">
                <g:message code="applicationDeployment.applicationRelease.label" default="Application Release"/>
            </carm:label>
            <div class="controls">
                <g:link controller="applicationRelease" action="show"
                        id="${applicationDeploymentInstance?.applicationRelease?.id}">
                    ${message(code: 'pageHeader.applicationRelease.label', args: [
                            applicationDeploymentInstance?.applicationRelease?.application?.name,
                            applicationDeploymentInstance?.applicationRelease?.releaseNumber])?.encodeAsHTML()}
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
                <g:select name="deploymentEnvironment.id" noSelection="['null': '']" required="required"
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
                <carm:datePicker name="requestedDeploymentDate"
                                 value="${applicationDeploymentInstance?.requestedDeploymentDate}"/>
            </div>
        </div>
    </carm:formSection>

    <carm:formSection legend="${message(code: 'deployment.section')}">
        <div class="control-group ${hasErrors(bean: applicationDeploymentInstance, field: 'moduleDeployments', 'error')}">
            <carm:label class="control-label" required="true">
                <g:message code="modules.label" default="Modules"/>
            </carm:label>
            <div class="controls">
                <g:each var="moduleDeployment"
                        in="${applicationDeploymentInstance.moduleDeployments.sort { it.moduleRelease.module.name}}">
                    <g:if test="${moduleReleaseService.isDeployable(moduleDeployment.moduleRelease)}">
                        <carm:label class="checkbox" for="moduleDeployment.${moduleDeployment.id}">
                            <g:if test="${moduleDeployment.deploymentState == ModuleDeploymentState.DEPLOYED}">
                                <g:checkBox name="moduleDeployment.${moduleDeployment.id}"
                                            checked="${true}"/>
                            </g:if>
                            <g:else>
                                <g:checkBox name="moduleDeployment.${moduleDeployment.id}"/>
                            </g:else>
                            ${moduleDeployment.moduleRelease.module.name.encodeAsHTML()}
                        </carm:label>
                    </g:if>
                    <g:else>
                        <carm:label class="checkbox disabled">
                            <g:checkBox name="moduleDeployment.${moduleDeployment.id}" disabled="true"/>
                            <g:message code="moduleReleaseCannotBeDeployed.message"
                                       args="[moduleDeployment.moduleRelease.module.name]"/>
                        </carm:label>
                    </g:else>
                </g:each>
            </div>
        </div>

        <div class="control-group ${hasErrors(bean: applicationDeploymentInstance, field: 'deploymentInstructions', 'error')}">
            <carm:label class="control-label" for="deploymentInstructions">
                <g:message code="applicationDeployment.deploymentInstructions.label"
                           default="Deployment Instructions"/>
            </carm:label>
            <div class="controls">
                <richui:richTextEditor name="deploymentInstructions"
                                       value="${applicationDeploymentInstance?.deploymentInstructions}"
                                       height="${grailsApplication.config.ui.richTextEditor.height}"
                                       width="${grailsApplication.config.ui.richTextEditor.width}"/>
            </div>
        </div>
    </carm:formSection>

    <carm:formButtons>
        <g:submitButton class="btn btn-primary" name="create"
                        value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        <g:link class="btn" controller="application" action="show"
                id="${applicationDeploymentInstance?.applicationRelease?.application?.id}">
            <g:message code="default.button.cancel.label" default="Cancel"/>
        </g:link>
    </carm:formButtons>
</g:form>

<g:set var="existingEnvironments" value="${existingDeployments*.deploymentEnvironment?.unique()}"/>
<g:set var="alreadyDeployedTo" value="${existingEnvironments.collect { it.name }.join(", ")}"/>
<g:set var="alreadyDeployedToIds" value="${existingEnvironments.collect { it.id }.join(", ")}"/>

<r:script>
    jQuery(function () {
        var alreadyDeployedTo = [${alreadyDeployedToIds}];

        function deploymentEnvironmentChanged() {
            var selectedEnvironmentId = jQuery("#deploymentEnvironment\\.id").val();

            if (selectedEnvironmentId && jQuery.inArray(parseInt(selectedEnvironmentId), alreadyDeployedTo) === -1) {
                jQuery("#deploymentEnvironmentMessage:visible").hide('blind');
                jQuery("#deploymentEnvironmentMessage_message").html("");
            }
            else {
                jQuery("#deploymentEnvironmentMessage_message").html("${message(code: 'applicationReleaseAlreadyDeployedTo.message', args: [alreadyDeployedTo])}");
                jQuery("#deploymentEnvironmentMessage:hidden").show('blind');
            }
        }

        jQuery("#deploymentEnvironment\\.id").change(deploymentEnvironmentChanged);

        deploymentEnvironmentChanged();
    });
</r:script>

</body>
</html>
