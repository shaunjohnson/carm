<%@ page import="carm.deployment.ModuleDeploymentState" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'applicationDeployment.label', default: 'Application Deployment')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
    <resource:richTextEditor type="${grailsApplication.config.ui.richTextEditor.type}"/>
</head>

<body>
<div class="body">
    <carm:header domain="${applicationDeploymentInstance}"/>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${applicationDeploymentInstance}">
        <div class="errors">
            <g:renderErrors bean="${applicationDeploymentInstance}" as="list"/>
        </div>
    </g:hasErrors>

    <g:form action="save">
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="applicationRelease.id">
                            <g:message code="applicationDeployment.applicationRelease.label"
                                       default="Application Release"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: applicationDeploymentInstance, field: 'applicationRelease', 'errors')}">
                        <g:link controller="applicationRelease" action="show"
                                id="${applicationDeploymentInstance?.applicationRelease?.id}">
                            ${message(code: 'pageHeader.applicationRelease.label', args: [
                                    applicationDeploymentInstance?.applicationRelease?.application?.name,
                                    applicationDeploymentInstance?.applicationRelease?.releaseNumber])?.encodeAsHTML()}
                        </g:link>
                        <g:hiddenField name="applicationRelease.id"
                                       value="${applicationDeploymentInstance?.applicationRelease?.id}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="deploymentEnvironment.id" required="true">
                            <g:message code="applicationDeployment.deploymentEnvironment.label" default="Environment"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: applicationDeploymentInstance, field: 'deploymentEnvironment', 'errors')}">
                        <g:select name="deploymentEnvironment.id" noSelection="['null': '']" required="required"
                                  from="${applicationDeploymentInstance?.applicationRelease?.application?.sysEnvironment?.environments}"
                                  optionKey="id" value="${applicationDeploymentInstance?.deploymentEnvironment?.id}"/>
                        <carm:highlight id="deploymentEnvironmentMessage" display="none"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="requestedDeploymentDate">
                            <g:message code="applicationDeployment.requestedDeploymentDate.label"
                                       default="Requested Deployment Date"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: applicationDeploymentInstance, field: 'requestedDeploymentDate', 'errors')}">
                        <g:datePicker name="requestedDeploymentDate" precision="day"
                                      value="${applicationDeploymentInstance?.requestedDeploymentDate}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="completedDeploymentDate">
                            <g:message code="applicationDeployment.completedDeploymentDate.label"
                                       default="Completed Deployment Date"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: applicationDeploymentInstance, field: 'completedDeploymentDate', 'errors')}">
                        <g:datePicker name="completedDeploymentDate" precision="day"
                                      value="${applicationDeploymentInstance?.completedDeploymentDate}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label required="true">
                            <g:message code="modules.label" default="Modules"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: applicationDeploymentInstance, field: 'moduleDeployments', 'errors')}">
                        <table class="tight">
                            <g:each var="moduleDeployment" in="${applicationDeploymentInstance.moduleDeployments?.sort{ it.moduleRelease.module.name} }">
                                <g:if test="${moduleReleaseService.isDeployable(moduleDeployment.moduleRelease)}">
                                    <tr>
                                        <td>
                                            <g:if test="${moduleDeployment.deploymentState == ModuleDeploymentState.DEPLOYED}">
                                                <g:checkBox name="moduleRelease.${moduleDeployment.moduleRelease.id}" checked="${true}"/>
                                            </g:if>
                                            <g:else>
                                                <g:checkBox name="moduleRelease.${moduleDeployment.moduleRelease.id}"/>
                                            </g:else>
                                        </td>
                                        <td>
                                            <carm:label for="moduleRelease.${moduleDeployment.moduleRelease.id}">
                                                ${moduleDeployment.moduleRelease.module.name.encodeAsHTML()}
                                            </carm:label>
                                        </td>
                                    </tr>
                                </g:if>
                                <g:else>
                                    <tr>
                                        <td>
                                            <g:checkBox name="moduleRelease.${moduleDeployment.moduleRelease.id}" disabled="true"/>
                                        </td>
                                        <td class="disabled">
                                            <g:message code="moduleReleaseCannotBeDeployed.message"
                                                       args="[moduleDeployment.moduleRelease.module.name]"/>
                                        </td>
                                    </tr>
                                </g:else>
                            </g:each>
                        </table>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <carm:label for="deploymentInstructions">
                            <g:message code="applicationDeployment.deploymentInstructions.label"
                                       default="Deployment Instructions"/>
                        </carm:label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: applicationDeploymentInstance, field: 'deploymentInstructions', 'errors')}">
                        <richui:richTextEditor name="deploymentInstructions"
                                               value="${applicationDeploymentInstance?.deploymentInstructions}"
                                               height="${grailsApplication.config.ui.richTextEditor.height}"
                                               width="${grailsApplication.config.ui.richTextEditor.width}"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button">
                <g:link class="show" controller="application" action="show"
                        id="${applicationDeploymentInstance?.applicationRelease?.application?.id}">
                    <g:message code="default.button.cancel.label" default="Cancel"/>
                </g:link>
            </span>
            <span class="button">
                <g:submitButton name="create" class="save"
                                value="${message(code: 'default.button.create.label', default: 'Create')}"/>
            </span>
        </div>
    </g:form>
</div>

<g:set var="alreadyDeployedTo" value="${existingDeployments*.deploymentEnvironment?.name?.join(", ")}"/>
<g:set var="alreadyDeployedToIds" value="${existingDeployments*.deploymentEnvironment?.id?.join(", ")}"/>

<script type="text/javascript">
    jQuery(function () {
        var alreadyDeployedTo = [${alreadyDeployedToIds}];

        function deploymentEnvironmentChanged() {
            var selectedEnvironmentId = jQuery("#deploymentEnvironment\\.id").val();

            if (selectedEnvironmentId && jQuery.inArray(parseInt(selectedEnvironmentId), alreadyDeployedTo) === -1) {
                jQuery("#deploymentEnvironmentMessage:visible").hide('blind');
                jQuery("#deploymentEnvironmentMessage_message").html("");
            }
            else {
                jQuery("#deploymentEnvironmentMessage_message").html("This release was already deployed to <strong>${alreadyDeployedTo}</strong>");
                jQuery("#deploymentEnvironmentMessage:hidden").show('blind');
            }
        }

        jQuery("#deploymentEnvironment\\.id").change(deploymentEnvironmentChanged);

        deploymentEnvironmentChanged();
    });
</script>

</body>
</html>
